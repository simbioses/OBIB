#!/bin/sh

## Configurable variables
SERVER_IP='192.168.100.101'

DB_ROOT_PASS='_DBrP445!'

DB_USERNAME='mirth'
DB_PASSWORD='Mirth!123'

## Config and Mirth Connect root paths
CONF_ROOT='/home/vagrant/configs'
MIRTH_ROOT='/opt/MirthConnect'

## Set the correct timezone
sudo cp /usr/share/zoneinfo/Canada/Pacific /etc/localtime

## Update the OS
sudo apt-get update
#sudo apt-get upgrade

## Install Java 8 and MariaDB
sudo apt-get -y install openjdk-8-jdk

echo 'mysql-server mysql-server/root_password password' $DB_ROOT_PASS | sudo debconf-set-selections
echo 'mysql-server mysql-server/root_password_again password' $DB_ROOT_PASS | sudo debconf-set-selections
sudo apt-get -y install mariadb-server
mysql --user=root --password=$DB_ROOT_PASS -e "GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost' IDENTIFIED BY '$DB_ROOT_PASS' WITH GRANT OPTION; FLUSH PRIVILEGES;"

## Execute database creation script as 'root'
mysql --user=root --password=$DB_ROOT_PASS < $CONF_ROOT/dbscripts/mirth_create.sql
mysql --user=root --password=$DB_ROOT_PASS < $CONF_ROOT/dbscripts/OBIB_DB_create.sql

## Create database user
mysql --user=root --password=$DB_ROOT_PASS -e "CREATE USER '$DB_USERNAME'@'%' identified by '$DB_PASSWORD';"
mysql --user=root --password=$DB_ROOT_PASS -e "GRANT ALL ON mirthdb.* to '$DB_USERNAME'@'%' identified by '$DB_PASSWORD';"
mysql --user=root --password=$DB_ROOT_PASS -e "GRANT ALL ON OBIB_DB.* to '$DB_USERNAME'@'%' identified by '$DB_PASSWORD';"

## Enable mariadb remote access
sudo sed 's/^bind-address/#bind-address/g' -i /etc/mysql/mariadb.conf.d/50-server.cnf

## Execute database insertion script as 'user'
mysql --user=$DB_USERNAME --password=$DB_PASSWORD < $CONF_ROOT/dbscripts/OBIB_DB_insert.sql

## Download Mirth Connect
wget -q http://downloads.mirthcorp.com/connect/3.7.1.b243/mirthconnect-3.7.1.b243-unix.tar.gz

## Extract Mirth Connect
sudo tar -xzf mirthconnect-3.7.1.b243-unix.tar.gz
sudo mv 'Mirth Connect' $MIRTH_ROOT

## Copy and setup the Mirth Connect configuration files
sudo cp -R $CONF_ROOT/appdata/ $MIRTH_ROOT/
sudo cp -R $CONF_ROOT/certs/ $MIRTH_ROOT/
sudo cp $CONF_ROOT/conf/mirth.properties $MIRTH_ROOT/conf/mirth.properties
sudo cp -R $CONF_ROOT/custom-lib/ $MIRTH_ROOT/
sudo cp $CONF_ROOT/mirth.service /etc/systemd/system/mirth.service

sudo sed -e 's,${MIRTH_ROOT},'"$MIRTH_ROOT"',g' -i $MIRTH_ROOT/appdata/configuration.properties
sudo sed -e 's,${SERVER_IP},'"$SERVER_IP"',g' -e 's,${DB_USERNAME},'"$DB_USERNAME"',g' -e 's,${DB_PASSWORD},'"$DB_PASSWORD"',g' -i $MIRTH_ROOT/conf/mirth.properties
sudo sed -e 's,${MIRTH_ROOT},'"$MIRTH_ROOT"',g' -e 's,${DB_USERNAME},'"$DB_USERNAME"',g' -e 's,${DB_PASSWORD},'"$DB_PASSWORD"',g' -i $MIRTH_ROOT/custom-lib/CDA.properties
sudo sed -e 's,${MIRTH_ROOT},'"$MIRTH_ROOT"',g' -i /etc/systemd/system/mirth.service

## Configure Mirth Connect to start on boot
sudo chmod +x /etc/systemd/system/mirth.service
sudo systemctl enable mirth

## Clean temporary files
sudo rm mirthconnect-3.7.1.b243-unix.tar.gz
sudo rm -rf $CONF_ROOT

## End
echo -e "\e[1;92mSetup completed, please restart the VM - e.g.: vagrant reload\e[0m"