#!/bin/bash

## Load the settings
. ./mirth_connect.sh

DATABASE='OBIB_DB'
MIRTH_CERTS_PATH=$MIRTH_ROOT'/certs'

OBIB_CA_CERT_PATH=$CERTS_PATH/$OBIB_CA_CERT
OBIB_CA_KEY_PATH=$KEYS_PATH/$OBIB_CA_KEY

OSCAR_LOCATION_ID='2.16.840.1.113883.3.277.126.4'

# check if a clinic is registered - params <clinic_id>
checkClinic() {
    echo ''
    echo 'Verifying clinic registration...'
    
    # check if clinic is into database
    cert=$(mysql --user="$DB_USERNAME" --password="$DB_PASSWORD" --database=$DATABASE -se "SELECT certificate_file \
        FROM clinic_credential WHERE clinic_id = '$1';")
    
    if [[ -z $cert ]]; then
        echo 'Clinic not registered.'
        return 1
    else
        echo 'Clinic registered.'
        
        # check if certificate exists
        cert_path=$MIRTH_CERTS_PATH/$cert
        sudo [ -f "$cert_path" ] && echo 'Certificate file found.'; return 0 || echo 'Certificate file not found.'; return 1
    fi
}

# save a clinic - params <clinic_id> <clinic_name> <username> <password> <key_path> <key_pass> <location_id>
saveClinic() {
    echo ''
    echo 'Saving clinic information...'

    # check if clinic already exist
    clinic=$(mysql --user="$DB_USERNAME" --password="$DB_PASSWORD" --database=$DATABASE -se "SELECT clinic_name \
        FROM clinic_credential WHERE clinic_id = '$1';")

    if [[ -n $clinic ]]; then
        echo 'Error: Clinic already exists!'
        exit 1
    fi

    # copy the clinic certificate
    sudo cp "$5" "$MIRTH_CERTS_PATH"/

    # get only the certificate file name
    cert=$(basename -- "$5")

    # concatenates the oscar location id with the new value
    loc_id="$OSCAR_LOCATION_ID.$7"
    
    # insert the clinic info into database
    mysql --user="$DB_USERNAME" --password="$DB_PASSWORD" --database=$DATABASE -se "INSERT INTO clinic_credential \
        (clinic_id, clinic_name, clinic_username, clinic_password, certificate_file, certificate_password, location_id) \
        VALUES ('$1', '$2', '$3', '$4', '$cert', '$6', '$loc_id');"

    # validate
    checkClinic "$1" "$MIRTH_CERTS_PATH/$cert"
    return $?
}

# remove a clinic - params <clinic_id> <password>
removeClinic() {
    echo ''
    echo "Removing clinic information..."

    # get clinic certificate
    cert=$(mysql --user="$DB_USERNAME" --password="$DB_PASSWORD" --database=$DATABASE -se "SELECT certificate_file \
        FROM clinic_credential WHERE clinic_id = '$1' AND clinic_password = '$2';")
    
    if [[ -z $cert ]]; then
        echo "Error: Clinic not found!"
        exit 1
    fi
    
    # delete clinic certificate
    sudo rm "$MIRTH_CERTS_PATH/$cert" 2> /dev/null
    
    # delete clinic register in DB
    mysql --user="$DB_USERNAME" --password="$DB_PASSWORD" --database=$DATABASE -se "DELETE FROM clinic_credential \
        WHERE clinic_id = '$1' AND clinic_password = '$2';"

    # validate
    checkClinic "$1" "$MIRTH_CERTS_PATH/$cert"
    return $?
}

# generate a clinic certificate - params <clinic_id> <clinic_name> <cert_pass>
generateCertificate() {
    echo ''
    echo 'Generating clinic cetificate for OBIB connector...'

    obib_keystore="./$1/$OBIB_KEYSTORE_FILE"
    cli_key="./$1/$1.key"
    cli_csr="./$1/$1.csr"
    cli_crt="./$1/$1.crt"

    # create a directory for the certificates
    mkdir "$1"

    # generate the client certificate and key
    openssl req -new -newkey rsa:2048 -keyout "$cli_key" -out "$cli_csr" -passout pass:"$3" -nodes -subj "/C=CA/O=OSP/OU=OBIB/CN=$1"
    sudo openssl x509 -req -days 730 -in "$cli_csr" -CA "$OBIB_CA_CERT_PATH" -CAkey "$OBIB_CA_KEY_PATH" \
      -passin pass:"$OBIB_CA_PASS" -set_serial 01 -out "$cli_crt"

    # generate the obib keystore
    sudo openssl pkcs12 -export -in "$cli_crt" -inkey "$cli_key" -certfile "$OBIB_CA_CERT_PATH" -passin pass:"$3" \
      -out "$obib_keystore" -passout pass:"$OBIB_KEYSTORE_PASS" -name "$2"

    # import the OBIB CA certificate into the keystore to validate the server certificate
    sudo keytool -importcert -alias "OBIB CA" -file "$OBIB_CA_CERT_PATH" -noprompt -trustcacerts \
      -keystore "$obib_keystore" -storepass "$OBIB_KEYSTORE_PASS"

    echo ''
    echo 'The clinic certificate was created and imported into the OBIB keystore.'
    echo -e "\e[1;92mPlease, move the generated directory '$1' to a secure place.\e[0m"
}

# customize the obibconnector.properties for a clinic - params <clinic_id> <password> <location_id>
customizeProperties() {
    echo ''
    echo 'Customizing obibconnector.properties file...'

    properties_path="./$1/obibconnector.properties"

    # copy the properties file
    cp "$CONF_ROOT/obibconnector.properties" "$properties_path"

    # configure the properties
    sudo sed -e 's,${KEYSTORE_PATH},'"$EMR_SECURE_DIRECTORY/$OBIB_KEYSTORE_FILE"',g' -i "$properties_path"
    sudo sed -e 's,${KEYSTORE_PASS},'"$OBIB_KEYSTORE_PASS"',g' -i "$properties_path"
    sudo sed -e 's,${CLINIC_ID},'"$1"',g' -i "$properties_path"
    sudo sed -e 's,${CLINIC_PASS},'"$2"',g' -i "$properties_path"
    sudo sed -e 's,${CLINIC_LOCATION_ID},'"$OSCAR_LOCATION_ID.$3"',g' -i "$properties_path"

    echo -e "\e[1;92mPlease, copy '$OBIB_KEYSTORE_FILE' and 'obibconnector.properties' to the EMR secure directory '$EMR_SECURE_DIRECTORY'.\e[0m"
}

# delete a clinic certificate - params <certificate_alias>
#deleteCertificate() {
#    #  delete clinic certificate from keystore
#    keytool -delete -alias $1 -keystore $OBIB_KEYSTORE_PATH -storepass $OBIB_KEYSTORE_PASS
#
#    echo 'The clinic certificate was removed from the OBIB keystore.'
#}

# interactive script to register a clinic
registerClinic() {
    echo 'Please, enter the clinic information.'
    echo ' notes:'
    echo '  - (*) refer to information provided or generated by CDX.'
    echo '  - (+) refer to information related to the exported CDX Vendor Certificate.'
    echo '  - (!) refer to new information required to generate the OBIB Connector Certificate.'
    echo '  - (i) inform the last number of the Oscar Location ID for the new clinic.'

    while true; do
        read -rp 'Clinic ID (*): ' clinic_id
        read -rp 'Clinic Name (*): ' clinic_name
        read -rp 'Clinic Username (*): ' username
        read -rp 'Clinic Password (*): ' password
        read -rp 'CDX Certificate Path (+): ' key_path
        read -rp 'CDX Certificate Password (+): ' key_pass
        read -rp 'New OBIB Certificate Password (!): ' cert_pass
        read -rp 'New Clinic Location ID (i): ' location_id

        while true; do
            read -rp 'Please, confirm the clinic information. "c" = confirm, "r" = redo, "q" = quit: ' opts
            case $opts in
                [Cc]* ) if saveClinic "$clinic_id" "$clinic_name" "$username" "$password" "$key_path" "$key_pass" "$location_id"; then
                            generateCertificate "$clinic_id" "$clinic_name" "$cert_pass"
                            customizeProperties "$clinic_id" "$password" "$location_id"
                        fi
                        break 2;;
                [Rr]* ) break;;
                [Qq]* ) echo 'bye.'; exit;;
                * )     echo 'Please answer "c", "r" or "q": ';;
            esac
        done
    done
}

# interactive script to unregister a clinic
unregisterClinic() {
    echo 'Please, enter the clinic information.'

    read -rp 'Clinic ID: ' id
    read -rp 'Clinic Password: ' pass

    while true; do
        read -rp 'Are you sure you want to unregister this clinic? "y" or "n": ' yn
        case $yn in
            [Yy]* ) removeClinic "$id" "$pass"; break;;
            [Nn]* ) echo "bye."; exit;;
            * )     echo 'Please answer "y" or "n": ';;
        esac
    done
}

# display usage instructions
usage() {
    echo "Usage: ./register.sh OPTION [VALUE]"
    echo "Options:"
    echo " -r | --register   : register a new clinic"
    echo " -u | --unregister : unregister an existent clinic"
    echo " -c | --check      : check if a clinic is registered."
    echo "                     requires the clinic id as [VALUE]"
    echo " -h | --help       : this help information"
}

# main
if [[ $# -lt 1 ]]; then
    usage
    exit 1
fi

case $1 in
    -r | --register )   registerClinic;;
    -u | --unregister ) unregisterClinic;;
    -c | --check )      checkClinic "$2";;
    -h | --help )       usage;;
    * )                 usage;;
esac