#!/bin/bash

# Check Java
if type -p java; then
    JAVA=java
elif [[ -n "$JAVA_HOME" ]] && [[ -x "$JAVA_HOME/bin/java" ]];  then
    JAVA="$JAVA_HOME/bin/java"
else
    echo "Error: Java not found"
    exit 1
fi
echo $("$JAVA" -version 2>&1 | awk -F '"' '/version/ {print}')

# Check parameters
if [[ "$#" -ne 3 ]]; then
    echo 'Usage: $ ./submit_documents.sh <location_id> <receivers_id> <cda_path>.'
    exit 1
fi

# clinics ids
LOCATION_ID=$1
RECEIVERS_ID=$2

# path of the cda file
CDA_PATH=$3

# java program settings
CLASS_PATH='cdxconnector-0.0.1-SNAPSHOT-tests.jar:.:*'
#DEBUG=-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005

# Call the java client
# parameters: 1 = location_id, 2 = receivers_id, 3 = cda_file_path
call_submit_document() {
    echo "Executing submit document for CDA:" $3
    ${JAVA} ${DEBUG} -cp ${CLASS_PATH} ca.uvic.leadlab.cdxconnector.SubmitDocumentClient $1 $2 "$3"
}

if [[ -d ${CDA_PATH} ]]; then
    for file in ${CDA_PATH}/*; do
        call_submit_document ${LOCATION_ID} ${RECEIVERS_ID} "${file}"
    done
elif [[ -f ${CDA_PATH} ]]; then
    call_submit_document ${LOCATION_ID} ${RECEIVERS_ID} "${CDA_PATH}"
else
    echo "ERROR: CDA path '${CDA_PATH}' is not valid"
    exit 1
fi
