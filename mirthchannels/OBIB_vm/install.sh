#!/bin/bash

## Load the settings
. /vagrant/./mirth_connect.sh

# CA Certificate
CA_KEY_PATH=$KEYS_PATH/$OBIB_CA_KEY
CA_CERT_PATH=$CERTS_PATH/$OBIB_CA_CERT

# Server Certificate
OBIB_KEY_PATH=$KEYS_PATH/$OBIB_SERVER_KEY
OBIB_CERT_PATH=$CERTS_PATH/$OBIB_SERVER_CERT

## Set the correct timezone
sudo timedatectl set-timezone "$TIMEZONE"

## Update the OS
sudo apt-get update
#sudo apt-get -y upgrade

## Install Java 8
sudo apt-get -y install openjdk-8-jdk

## Install nginx
sudo apt-get -y install nginx

## Copy CA certificate and key
sudo cp "$CONF_ROOT/ssl/$OBIB_CA_KEY" "$CA_KEY_PATH"
sudo cp "$CONF_ROOT/ssl/$OBIB_CA_CERT" "$CA_CERT_PATH"

sudo sed -E "s/^(IP\.1.*=)(.*)$/\1 $SERVER_IP/" -i "$CONF_ROOT/nginx/openssl.cnf"

## Generate nginx certificate and key from the CA certificate
sudo openssl req -new -newkey rsa:2048 -keyout "$OBIB_KEY_PATH" -out obib.csr -nodes \
  -subj "/C=CA/O=OSP/OU=OBIB/CN=OBIB Server" -extensions v3_req -config "$CONF_ROOT/nginx/openssl.cnf"
sudo chmod 400 "$OBIB_KEY_PATH"

sudo openssl x509 -req -sha256 -days 730 -in obib.csr -CA "$CA_CERT_PATH" -CAkey "$CA_KEY_PATH" -passin pass:"$OBIB_CA_PASS" \
  -set_serial 1 -out "$OBIB_CERT_PATH" -extensions v3_req -extfile "$CONF_ROOT/nginx/openssl.cnf"
#sudo cat "$OBIB_CERT_PATH" "$CA_CERT_PATH" > "$OBIB_CERT_PATH"
sudo chmod 444 "$OBIB_CERT_PATH"

sudo openssl verify -CAfile "$CA_CERT_PATH" "$OBIB_CERT_PATH"

#sudo openssl dhparam -out $NGINX_ROOT/dhparam.pem 4096

## Copy nginx configuration files
sudo cp "$CONF_ROOT/nginx/obib.conf" "$NGINX_ROOT/snippets/"
sudo cp "$CONF_ROOT/nginx/obib" "$NGINX_ROOT/sites-available/"
sudo rm "$NGINX_ROOT"/sites-enabled/*
sudo ln -s "$NGINX_ROOT/sites-available/obib" "$NGINX_ROOT/sites-enabled/"
sudo nginx -t
#sudo systemctl restart nginx

## Install xmllint and xmlstarlet (used by the deploy.sh script)
sudo apt-get -y install libxml2-utils xmlstarlet

## Install MariaDB
echo 'mysql-server mysql-server/root_password password' "$DB_ROOT_PASS" | sudo debconf-set-selections
echo 'mysql-server mysql-server/root_password_again password' "$DB_ROOT_PASS" | sudo debconf-set-selections
sudo apt-get -y install mariadb-server
mysql --user=root --password="$DB_ROOT_PASS" -e \
 "GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost' IDENTIFIED BY '$DB_ROOT_PASS' WITH GRANT OPTION; FLUSH PRIVILEGES;"

## Execute database creation script as 'root'
mysql --user=root --password="$DB_ROOT_PASS" < "$CONF_ROOT/dbscripts/mirth_create.sql"
mysql --user=root --password="$DB_ROOT_PASS" < "$CONF_ROOT/dbscripts/OBIB_DB_create.sql"

## Create database user
mysql --user=root --password="$DB_ROOT_PASS" -e "CREATE USER '$DB_USERNAME'@'%' identified by '$DB_PASSWORD';"
mysql --user=root --password="$DB_ROOT_PASS" -e "GRANT ALL ON mirthdb.* to '$DB_USERNAME'@'%' identified by '$DB_PASSWORD';"
mysql --user=root --password="$DB_ROOT_PASS" -e "GRANT ALL ON OBIB_DB.* to '$DB_USERNAME'@'%' identified by '$DB_PASSWORD';"

## Enable mariadb remote access
sudo sed 's/^bind-address/#bind-address/g' -i /etc/mysql/mariadb.conf.d/50-server.cnf

## Increase mysql innodb log size
sudo sed -e '/# InnoDB/a innodb_log_file_size=512M' -i /etc/mysql/mariadb.conf.d/50-server.cnf

## Download Mirth Connect
wget -q http://downloads.mirthcorp.com/connect/3.8.0.b2464/mirthconnect-3.8.0.b2464-unix.tar.gz

## Extract Mirth Connect
sudo tar -xzf mirthconnect-3.8.0.b2464-unix.tar.gz
sudo mv 'Mirth Connect' "$MIRTH_ROOT"

## Copy Mirth Connect's configuration files
sudo cp -R "$CONF_ROOT/appdata" "$MIRTH_ROOT/"
sudo cp -R "$CONF_ROOT/conf" "$MIRTH_ROOT/"
sudo cp "$CONF_ROOT/mirth.service" /etc/systemd/system/
sudo cp "$CONF_ROOT/mariadb-java-client-2.4.2.jar" "$MIRTH_ROOT/server-lib/database/"

## Setup Mirth Connect's configuration files
sudo sed -e 's,${MIRTH_ROOT},'"$MIRTH_ROOT"',g' -i "$MIRTH_ROOT/appdata/configuration.properties"
sudo sed -e 's,${SERVER_IP},'"$SERVER_IP"',g' -e 's,${TIMEZONE},'"$TIMEZONE"',g' \
 -e 's,${DB_USERNAME},'"$DB_USERNAME"',g' -e 's,${DB_PASSWORD},'"$DB_PASSWORD"',g' -i "$MIRTH_ROOT/conf/mirth.properties"
sudo sed -e 's,${ADMIN_USERNAME},'"$ADMIN_USERNAME"',g' -e 's,${ADMIN_PASSWORD},'"$ADMIN_PASSWORD"',g' \
 -i "$MIRTH_ROOT/conf/mirth-cli-config.properties"
sudo sed -e 's,${MIRTH_ROOT},'"$MIRTH_ROOT"',g' -i /etc/systemd/system/mirth.service

## Create a folder to store the clinic certificates
sudo mkdir "$MIRTH_ROOT/certs/"
sudo chmod 700 "$MIRTH_ROOT/certs/"

## Configure Mirth Connect to start on boot
sudo chmod +x /etc/systemd/system/mirth.service
sudo systemctl enable mirth

## Clean temporary files
#sudo rm obib.csr
#sudo rm mirthconnect-3.8.0.b2464-unix.tar.gz

## End
echo -e "\e[1;92mSetup completed, please restart the VM - e.g.: vagrant reload\e[0m"
