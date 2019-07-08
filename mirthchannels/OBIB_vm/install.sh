#!/bin/bash

## Load the settings
. /vagrant/./mirth_connect.sh

## Set the correct timezone
sudo timedatectl set-timezone $TIMEZONE

## Update the OS
sudo apt update
#sudo apt -y upgrade

## Install Java 8
sudo apt -y install openjdk-8-jdk

## Install MariaDB
echo 'mysql-server mysql-server/root_password password' $DB_ROOT_PASS | sudo debconf-set-selections
echo 'mysql-server mysql-server/root_password_again password' $DB_ROOT_PASS | sudo debconf-set-selections
sudo apt -y install mariadb-server
mysql --user=root --password=$DB_ROOT_PASS -e "GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost' IDENTIFIED BY '$DB_ROOT_PASS' WITH GRANT OPTION; FLUSH PRIVILEGES;"

## Install nginx
sudo apt -y install nginx

## Install xmllint (used by the update.sh script)
sudo apt -y install libxml2-utils

## Execute database creation script as 'root'
mysql --user=root --password=$DB_ROOT_PASS < $CONF_ROOT/dbscripts/mirth_create.sql
mysql --user=root --password=$DB_ROOT_PASS < $CONF_ROOT/dbscripts/OBIB_DB_create.sql

## Create database user
mysql --user=root --password=$DB_ROOT_PASS -e "CREATE USER '$DB_USERNAME'@'%' identified by '$DB_PASSWORD';"
mysql --user=root --password=$DB_ROOT_PASS -e "GRANT ALL ON mirthdb.* to '$DB_USERNAME'@'%' identified by '$DB_PASSWORD';"
mysql --user=root --password=$DB_ROOT_PASS -e "GRANT ALL ON OBIB_DB.* to '$DB_USERNAME'@'%' identified by '$DB_PASSWORD';"

## Enable mariadb remote access
sudo sed 's/^bind-address/#bind-address/g' -i /etc/mysql/mariadb.conf.d/50-server.cnf

## Execute database insertion scripts as 'user'
mysql --user=$DB_USERNAME --password=$DB_PASSWORD < $CONF_ROOT/dbscripts/OBIB_DB_insert_ids.sql
mysql --user=$DB_USERNAME --password=$DB_PASSWORD < $CONF_ROOT/dbscripts/OBIB_DB_insert_loinc.sql
mysql --user=$DB_USERNAME --password=$DB_PASSWORD < $CONF_ROOT/dbscripts/OBIB_DB_insert_clinic_credential.sql

## Download Mirth Connect
wget -q http://downloads.mirthcorp.com/connect/3.7.1.b243/mirthconnect-3.7.1.b243-unix.tar.gz

## Extract Mirth Connect
sudo tar -xzf mirthconnect-3.7.1.b243-unix.tar.gz
sudo mv 'Mirth Connect' $MIRTH_ROOT

## Copy the Mirth Connect configuration files
sudo cp -R $CONF_ROOT/appdata/ $MIRTH_ROOT/
sudo cp -R $CONF_ROOT/certs/ $MIRTH_ROOT/
sudo cp $CONF_ROOT/conf/mirth.properties $MIRTH_ROOT/conf/mirth.properties
sudo cp -R $CONF_ROOT/custom-lib/ $MIRTH_ROOT/
sudo cp $CONF_ROOT/mirth.service /etc/systemd/system/mirth.service

## Setup the configurations files
sudo sed -e 's,${MIRTH_ROOT},'"$MIRTH_ROOT"',g' -i $MIRTH_ROOT/appdata/configuration.properties
sudo sed -e 's,${SERVER_IP},'"$SERVER_IP"',g' -e 's,${DB_USERNAME},'"$DB_USERNAME"',g' -e 's,${DB_PASSWORD},'"$DB_PASSWORD"',g' -i $MIRTH_ROOT/conf/mirth.properties
sudo sed -e 's,${MIRTH_ROOT},'"$MIRTH_ROOT"',g' -e 's,${DB_USERNAME},'"$DB_USERNAME"',g' -e 's,${DB_PASSWORD},'"$DB_PASSWORD"',g' -i $MIRTH_ROOT/custom-lib/CDA.properties
sudo sed -e 's,${MIRTH_ROOT},'"$MIRTH_ROOT"',g' -i /etc/systemd/system/mirth.service

## Configure Mirth Connect to start on boot
sudo chmod +x /etc/systemd/system/mirth.service
sudo systemctl enable mirth

## Secure certs folder
sudo chmod 700 /opt/MirthConnect/certs/

## Clean temporary files
#sudo rm mirthconnect-3.7.1.b243-unix.tar.gz

## End
echo -e "\e[1;92mSetup completed, please restart the VM - e.g.: vagrant reload\e[0m"
