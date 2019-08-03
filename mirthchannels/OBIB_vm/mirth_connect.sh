#!/bin/bash

## Server Settings
SERVER_IP='192.168.100.101'

## Database credentials
DB_ROOT_PASS='_DBrP445!'
DB_USERNAME='mirth'
DB_PASSWORD='Mirth!123'

## Mirth Connect and nginx paths
MIRTH_ROOT='/opt/MirthConnect'
NGINX_ROOT='/etc/nginx'

## Vagrant shared config folder
CONF_ROOT='/vagrant/configs'

## Config timezone
TIMEZONE='Canada/Pacific'

## Mirth Connect Administrator credentials
ADMIN_USERNAME='admin'
ADMIN_PASSWORD='admin'

## SSL paths
CERTS_PATH='/etc/ssl/certs'
KEYS_PATH='/etc/ssl/private'

## OBIB SSL CA credentials
OBIB_CA_CERT='obib_ca.crt'
OBIB_CA_KEY='obib_ca.key'
OBIB_CA_PASS='OBIB!123'