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

# Checking script parameters; see ca.uvic.leadlab.obibconnector.OBIBTestClient
if [[ "$#" -lt 1 ]]; then
    echo 'Usage: $ ./obib_client.sh <operation> [operation args]'
    exit 1
fi

# Set the OBIB server url
OBIB_URL="https://192.168.100.101"

# Set the obibconnector version
OBIBCONNECTOR_VERSION=1.0-65

# Adjust classpath according the environment
CLASS_PATH="obibconnector-$OBIBCONNECTOR_VERSION-tests.jar:obibconnector-$OBIBCONNECTOR_VERSION.jar:dependencies/*:."

# Optional remote debug configuration
#DEBUG=-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005

# Overriding CATALINA_HOME in this section, so obibconnector get the local properties and keystore
export CATALINA_HOME="."

# Calling OBIBTestClient
echo "Calling OBIBTestClient with arguments: $OBIB_URL " "$@"
${JAVA} $DEBUG -cp $CLASS_PATH ca.uvic.leadlab.obibconnector.OBIBTestClient $OBIB_URL "$@"
