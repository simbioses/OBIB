#!/bin/sh

## Configurable variables
DB_USERNAME='mirth'
DB_PASSWORD='Mirth!123'

## Config and Mirth Connect root paths
CONF_ROOT='/vagrant/configs'

## Update the OBIB_DB database
mysql --user=$DB_USERNAME --password=$DB_PASSWORD < $CONF_ROOT/dbscripts/OBIB_DB_update.sql
mysql --user=$DB_USERNAME --password=$DB_PASSWORD < $CONF_ROOT/dbscripts/OBIB_DB_insert_ids.sql
mysql --user=$DB_USERNAME --password=$DB_PASSWORD < $CONF_ROOT/dbscripts/OBIB_DB_insert_loinc.sql

