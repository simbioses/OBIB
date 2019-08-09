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

OBIB_CA_KEY_PATH=$SECURE_PATH/$OBIB_CA_KEY
OBIB_CA_CERT_PATH=$SECURE_PATH/$OBIB_CA_CERT

## Generate SSL CA certificate and private key if needed
if [[ ! -f $OBIB_CA_KEY_PATH && ! -f $OBIB_CA_CERT_PATH ]]; then
  echo "Creating CA certificate and private key..."
  openssl req -x509 -sha256 -newkey rsa:4096 -days 1825 -subj "/C=CA/O=OSP/OU=OBIB/CN=OBIB CA" \
    -keyout "$OBIB_CA_KEY_PATH" -out "$OBIB_CA_CERT_PATH" -passout pass:"$OBIB_CA_PASS"
  chmod 400 "$OBIB_CA_KEY_PATH"
  chmod 444 "$OBIB_CA_CERT_PATH"
fi

## Generate a new KeyStore with the CA cetificate
if [[ -f $OBIB_KEYSTORE_PATH ]]; then
  echo "KeyStore '" "$OBIB_KEYSTORE_PATH" \
    "' already exist. Set another path on mirth_connect.sh or move the current file to create a new KeyStore."
else
  echo "Creating keystore..."
  openssl pkcs12 -export -in "$OBIB_CA_CERT_PATH" -passin pass:password -out "$OBIB_KEYSTORE_PATH" \
    -passout pass:"$OBIB_KEYSTORE_PASS" -name "OBIB Certs"
fi