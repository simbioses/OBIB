#!/bin/bash

## Server IP Address (change as needed)
SERVER_IP='192.168.100.101'

## Server timezone (change as needed)
TIMEZONE='Canada/Pacific'

## Mirth Connect Database credentials (change passwords for production)
DB_ROOT_PASS='_DBrP445!'
DB_USERNAME='mirth'
DB_PASSWORD='Mirth!123'

## Mirth Connect and nginx paths
MIRTH_ROOT='/opt/MirthConnect'
NGINX_ROOT='/etc/nginx'

## Default SSL paths
CERTS_PATH='/etc/ssl/certs'
KEYS_PATH='/etc/ssl/private'

## Vagrant shared config folder
CONF_ROOT='/vagrant/configs'

## Mirth Connect Administrator credentials (change password for production)
ADMIN_USERNAME='admin'
ADMIN_PASSWORD='admin'

## OBIB SSL CA certificate (regenerate or replace for production)
OBIB_CA_CERT='obib_ca.crt'
OBIB_CA_KEY='obib_ca.key'
OBIB_CA_PASS='OBIB!123'

## OBIB SSL Server certificate
OBIB_SERVER_CERT='obib.crt'
OBIB_SERVER_KEY='obib.key'

## OBIB Connector SSL KeyStore (change password for production)
OBIB_KEYSTORE_FILE='obibconnector.keystore'
OBIB_KEYSTORE_PASS='obibconnector'

## EMR secure directory (change as needed)
EMR_SECURE_DIRECTORY='$CATALINA_HOME'
