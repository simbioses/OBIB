# OBIB Connector (Java Lib)

Library responsible for connecting to OBIB

## OBIB Test Client

The OBIB Test Client implements the obibconnector methods, allowing the execution of OBIB operations from the command line.

To execute the OBIB Test Client the following files are necessary:
1. obibconnector-\<version>-test.jar
2. obibconnector-\<version>.jar
3. folder /obibconnector/target/dependencies (it is generated in maven deploy phase)
4. obib_client.sh
5. obibconnector.properties
6. obibconnector.keystore

### Configuring obibconnector

Configure the clinic information and keystore data in **obibconnector.keystore**.

```
# Path of the OBIB Keystore, used for mutual SSL authentication on OBIB
obib.keystore.path=./obibconnector.keystore
obib.keystore.pass=keystore_password

# CDX clinic credentials, used to log in to OBIB
cdx.clinic.id=CLINIC_ID
cdx.clinic.password=CLINIC_PASS
```

### Executing via shell script

Copy the required jar files, shell script, and obibconnector properties and keystore files (if needed).

Configure the OBIB server URL and obibconnector version (if needed) in **obib_client.sh** file.

```
# Set the OBIB server url
OBIB_URL="https://192.168.100.101"

# Set the obibconnector version
OBIBCONNECTOR_VERSION=1.0-65
```

To execute the OBIB Test Client call the shell script **obib_client.sh** as following:

```
$ ./obib_client.sh operation <operation args>
```

where:
- *operation*: is the OBIB method that will be called (see ca.uvic.leadlab.obibconnector.OBIBTestClient).
- *operation args*: are the method's arguments;
