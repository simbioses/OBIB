#!/bin/sh

## Configurable variables
DB_USERNAME='mirth'
DB_PASSWORD='Mirth!123'

## Config and Mirth Connect root paths
CONF_ROOT='/vagrant/configs'
MIRTH_ROOT='/opt/MirthConnect'

## Update May 17, 2019: update db
mysql --user=root --password=$DB_ROOT_PASS < $CONF_ROOT/dbscripts/OBIB_DB_update.sql

