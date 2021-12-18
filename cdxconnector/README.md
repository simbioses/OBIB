# CDX Connector (Java Lib)

Library responsible for connecting to CDX Web Services.

### Maven execution details:

* Importing the CDX certificates into a PFX keystore.

  By default, the certificates are not imported. If necessary, use the ```<skip.generate.certificate>``` property 
  to configure this execution on the phase ```generate-sources``` for example.
     
* Downloading the WSDL and XSD into resources folder.

  The WSDLs are already downloaded, then they are not downloaded by default. If necessary, use the 
  ```<skip.wsdl.download>``` property to configure this execution on the phase ```generate-sources``` for example.
  
  Do download the WSDLs the certificate chain is necessary. To set the correct certificate path and password 
  configuring the properties ```<certificate.path>``` and ```<certificate.pass>```. 

* Generating Java classes from CDX WSDLs and XSDs.
  
  It is executed by default at the ```generate-sources``` phase. The default WSDL location is configured by the 
  ```<server.url>``` property. It is not necessary change this setup, since the *CDXConnector* utilizes a specific 
  property for that.

* Copying the dependency jars to /target/dependencies/ folder.

  It is executed by default at the ```deploy``` phase.
  
* Executing the JUnit Tests.

  The automated execution of the tests is skipped by default. To execute them change the ```<skip.tests>``` 
  property to ```false```.
  
  To execute the tests necessary resources, such as certificates and CDA Test Messages are copied by the 
  execution 'build-test-resources'.

* Building the **jar-test** file. 

  A jar-test is generated at the ```deploy``` phase. This jar contains a java client for executing the CDX operations.

### CDX Test Client:

The CDX Test Client is a cdxconnector client that permit executing the cdxconnector operations directly from command line.

To execute the CDX Test Client the following files are necessary: 
1. cdxconnector-\<version>-test.jar
2. cdxconnector-\<version>.jar
3. folder /cdxconnector/target/dependencies (it is generated in maven deploy phase)
4. cdx_client.sh
5. CDXTestClient.properties
6. clinic keystore file (e.g.: OSCAR_test_clinic.pfx)

#### Registering a clinic

To register a clinic to submit documents using this client it is necessary configure the following data 
in the **CDXTestClient.properties** inside the *cdxconnector-\<version>-test.jar*:
```
obib.clinic.<location_id>.username = <cdx clinic username>
obib.clinic.<location_id>.password = <cdx clinic password>
obib.clinic.<location_id>.certificate = <relative path for the clinic PFX certificate file>
obib.clinic.<location_id>.certpassword = <clinic certificate password>
``` 

where a clinic is identify by the portion of the keys: **<location_id>** 

#### Executing via shell script

Copy the required jar files, shell script, properties file, and clinic keystore files.

Configure the cdxconnector version (if needed) in **cdx_client.sh** file.

```
# Set the cdxconnector version
CDX_VERSION="1.1.2"
```

To execute the CDX Test Client call the shell script **cdx_client.sh** as following:

```
$ ./cdx_client.sh location_id operation <operation args>
```

where:
- *location_id*: is the 'location id' configured in the CDXTestClient.properties file.
- *operation*: is the CDX method that will be called (see ca.uvic.leadlab.cdxconnector.CDXTestClient).
- *operation args*: are the method's arguments;
