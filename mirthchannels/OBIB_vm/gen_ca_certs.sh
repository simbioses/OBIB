#!/bin/bash

## Load the settings
. ./mirth_connect.sh

#if [[ $(/usr/bin/id -u) -ne 0 ]]; then
#    echo "Please, run this script as root."
#    exit
#fi

# Create a local secure storage
SECURE_PATH=./configs/ssl
if [[ ! -d $SECURE_PATH ]]; then
  mkdir $SECURE_PATH
  chmod 700 $SECURE_PATH
fi

## Generate SSL CA certificate and private key if needed
if [[ ! -f $SECURE_PATH/$OBIB_CA_KEY && ! -f $SECURE_PATH/$OBIB_CA_CERT ]]; then
  openssl req -x509 -sha256 -newkey rsa:4096 -days 1825 -subj "/C=CA/O=OSP/OU=OBIB/CN=OBIB CA" \
    -keyout $SECURE_PATH/$OBIB_CA_KEY -out $SECURE_PATH/$OBIB_CA_CERT -passout pass:$OBIB_CA_PASS
  chmod 400 $SECURE_PATH/$OBIB_CA_KEY
  chmod 444 $SECURE_PATH/$OBIB_CA_CERT
fi
