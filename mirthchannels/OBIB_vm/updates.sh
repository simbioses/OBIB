#!/bin/sh

## Configurable variables
DB_USERNAME='mirth'
DB_PASSWORD='Mirth!123'

## Config and Mirth Connect root paths
CONF_ROOT='/vagrant/configs'
MIRTH_ROOT='/opt/MirthConnect'

## Update the OBIB_DB database - May 6, 2019
#mysql --user=$DB_USERNAME --password=$DB_PASSWORD < $CONF_ROOT/dbscripts/OBIB_DB_update.sql
#mysql --user=$DB_USERNAME --password=$DB_PASSWORD < $CONF_ROOT/dbscripts/OBIB_DB_insert_ids.sql
#mysql --user=$DB_USERNAME --password=$DB_PASSWORD < $CONF_ROOT/dbscripts/OBIB_DB_insert_loinc.sql

## Update the custom-lib files - May 8, 2019
sudo cp -R $CONF_ROOT/custom-lib/ $MIRTH_ROOT/

