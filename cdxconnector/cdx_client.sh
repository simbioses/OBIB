#!/bin/bash

# Checking Java
if type -p java; then
    JAVA=java
elif [[ -n "$JAVA_HOME" ]] && [[ -x "$JAVA_HOME/bin/java" ]];  then
    JAVA="$JAVA_HOME/bin/java"
else
    echo "Error: Java not found"
    exit 1
fi
echo $("$JAVA" -version 2>&1 | awk -F '"' '/version/ {print}')

# Checking script parameters; see ca.uvic.leadlab.cdxconnector.CDXTestClient
if [[ "$#" -lt 2 ]]; then
    echo 'Usage: $ ./cdx_client.sh <location_id> <operation> <operation args>'
    exit 1
fi

# Set the cdxconnector version
CDX_VERSION="1.1.2"

# Adjust classpath according the environment
CLASS_PATH="cdxconnector-$CDX_VERSION-tests.jar:cdxconnector-$CDX_VERSION.jar:dependencies/*:."

# Optional remote debug configuration
#DEBUG=-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005

# Other optional JVM and debug parameters
#OPTIONS=-Djavax.net.debug=ssl:handshake:verbose

# Calling the java client CDXTestClient
echo "Calling CDXTestClient with: $@"
${JAVA} ${DEBUG} ${OPTIONS} -cp ${CLASS_PATH} ca.uvic.leadlab.cdxconnector.CDXTestClient "$@"
