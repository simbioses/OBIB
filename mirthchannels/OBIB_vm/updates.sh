#!/bin/sh

## Configurable variables
DB_USERNAME='mirth'
DB_PASSWORD='Mirth!123'

## Config and Mirth Connect root paths
CONF_ROOT='/vagrant/configs'
MIRTH_ROOT='/opt/MirthConnect'

## Update May 9, 2019: cdxconnector jar 
sudo cp -R $CONF_ROOT/custom-lib/cdxconnector/cdxconnector-0.0.1-SNAPSHOT.jar $MIRTH_ROOT/custom-lib/cdxconnector/

