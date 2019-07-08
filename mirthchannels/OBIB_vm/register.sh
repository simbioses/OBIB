#!/bin/bash

## Load the settings
. ./mirth_connect.sh

DATABASE='OBIB_DB'
CERTS_PATH=$MIRTH_ROOT'/certs'

# check if clinic is registered - params <id> <cert_path>
checkClinic() {
    echo "Verifying clinic registration..."
    
    # check if clinic is into database
    clinic=$(mysql --user=$DB_USERNAME --password=$DB_PASSWORD --database=$DATABASE -se "SELECT clinic_name \
        FROM clinic_credential WHERE clinic_id = $1;")
    if [[ -z $clinic ]]; then
        echo "Clinic '$clinic' found!"
    else
        echo "Clinic not found!"
    fi

    # check if certificate exists
    if [[ -f $2 ]]; then
        echo "Certificate file not found!"
    else
        echo "Certificate file found!"
    fi
}

# save a clinic - params <id> <name> <username> <password> <cert_path> <cert_pass>
saveClinic() {
    # check if clinic already exist
    clinic=$(mysql --user=$DB_USERNAME --password=$DB_PASSWORD --database=$DATABASE -se "SELECT clinic_name \
        FROM clinic_credential WHERE clinic_id = $1;")

    if [[ ! -z $clinic ]]; then
        echo "Clinic already exists!"
        exit 1
    fi

    # copy the clinic certificate
    sudo cp $5 $CERTS_PATH/

    # get only the certificate file name
    cert=$(basename -- "$5")
    
    # insert the clinic info into database
    mysql --user=$DB_USERNAME --password=$DB_PASSWORD --database=$DATABASE -se "INSERT INTO \
        clinic_credential (clinic_id, clinic_name, clinic_username, clinic_password, certificate_file, certificate_password) \
        VALUES ($1, $2, $3, $4, $cert, $6);"

    # validate
    checkClinic $1 "$CERTS_PATH/$cert"
}

# remove a clinic - params <id> <password>
removeClinic() {
    # get clinic certificate
    cert=$(mysql --user=$DB_USERNAME --password=$DB_PASSWORD --database=$DATABASE -se "SELECT certificate_file \
        FROM clinic_credential WHERE clinic_id = $1 AND clinic_password = $2;")
    
    if [[ -z $cert ]]; then
        echo "Clinic not found!"
        exit 1
    fi
    
    # delete clinic certificate
    sudo rm "$CERTS_PATH/$cert" 2> /dev/null
    
    # delete clinic register in DB
    mysql --user=$DB_USERNAME --password=$DB_PASSWORD --database=$DATABASE -se "DELETE FROM certificate_file \
        WHERE clinic_id = $1 AND clinic_password = $2;"

    # validate
    checkClinic $1 "$CERTS_PATH/$cert"
}

# interative script to register a clinic
registerClinic() {
    echo 'Please, enter the clinic information.'

    while true; do
        read -rp 'Clinic ID: ' id
        read -rp 'Clinic Name: ' name
        read -rp 'Clinic Username: ' username
        read -rp 'Clinic Password: ' password
        read -rp 'Clinic Certificate Path: ' cert_path
        read -rp 'Clinic Certificate Password: ' cert_pass

        while true; do
            read -p 'Please, confirm the clinic information. "c" = confirm, "r" = redo, "q" = quit: ' opts
            case $opts in
                [Cc]* ) saveClinic $id $name $username $password $cert_path $cert_pass; break 2;;
                [Rr]* ) break;;
                [Qq]* ) echo "bye."; exit;;
                * )     echo 'Please answer "c", "r" or "q": ';;
            esac
        done
    done
}

# interative script to unregister a clinic
unregisterClinic() {
    echo 'Please, enter the clinic information.'

    read -rp 'Clinic ID: ' id
    read -rp 'Clinic Password: ' pass

    while true; do
        read -p 'Are you sure you want to unregister this clinic? "y" or "n": ' yn
        case $yn in
            [Yy]* ) removeClinic $id $pass; break;;
            [Nn]* ) echo "bye."; exit;;
            * ) echo 'Please answer "y" or "n": ';;
        esac
    done
}

# main
usage() {
    echo "Usage: ./register.sh OPTION"
    echo "Options:"
    echo " -r | --register   : register a new clinic"
    echo " -u | --unregister : unregister an existent clinic"
    echo " -h | --help       : this help information"
}

if [[ $# -lt 1 ]]; then
    usage
    exit 1
fi

case $1 in
    -r | --register )   registerClinic;;
    -u | --unregister ) unregisterClinic;;
    -h | --help )       usage;;
    * )                 usage;;
esac