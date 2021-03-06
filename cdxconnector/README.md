# CDX Connector (Java Lib)

Library responsible for connecting to CDX Web Services.

### Maven execution details:

* Importing the CDX certificates into a PFX keystore.

  By default the certificates are not imported. If necessary, use the ```<skip.generate.certificate>``` property 
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

  A jar-test is generate at the ```deploy``` phase. This jar contains a java client for submitting documents.

### Submit Document Client:

The Submit Document Client is a cdxconnector client that permit submit CDX documents directly from command line
using CDA XML files.

To execute the Submit Document Client the following files are necessary: 
1. cdxconnector-\<version>.jar
2. cdxconnector-\<version>-test.jar
3. commons-codec-1.11.jar
4. commons-lang3-3.8.1.jar
5. submit_documents.sh \[optional]
6. \<clinic-certificate>.pfx
7. \<CDA.XML> or \<directory containing CDA xmls>

#### Registering a clinic

To register a clinic to submit documents using this client it is necessary configure the following data 
in the **SubmitDocumentClient.properties** inside the *cdxconnector-\<version>-test.jar*:
```
obib.clinic.<location_id>.username = <cdx clinic username>
obib.clinic.<location_id>.password = <cdx clinic password>
obib.clinic.<location_id>.certificate = <relative path for the clinic PFX certificate file>
obib.clinic.<location_id>.certpassword = <clinic certificate password>
``` 

where a clinic is identify by the portion of the keys: **<location_id>** 

#### Executing the java client

To execute the Submit Document Client call the **jar-test** file as following:

```
$ java -cp .:* -jar cdxconnector-0.0.1-SNAPSHOT-tests.jar ca.uvic.leadlab.cdxconnector.SubmitDocumentClient location_id receivers_ids cda_file_path [attachment_files_paths]
```

where:
- *location_id*: is the clinic 'location id' registered in the **SubmitDocumentClient.properties** file;
- *receivers_ids*: are the receiver clinics 'location id' separated by comma ',';
- *cda_file_path*: is the relative path for the cda file to be submitted;
- *attachment_files_paths*: (optional) are the document's attachment file separated by comma ','.

#### Alternative: Executing via shell script

To execute the Submit Document Client call the shell script **submit_documents.sh** as following:

```
$ ./submit_documents.sh location_id receivers_id cda_path
```

where:
- *location_id*: is the clinic 'location id' registered in the **SubmitDocumentClient.properties** file;
- *receivers_ids*: are the receiver clinics 'location id' separated by comma ',';
- *cda_path*: is the path of a CDA XML file or a directory that contains more than one CDA XML.
In this case all files inside the directory will be submitted.

*Notes:*
1. If the CDA files are not located relative to the current directory, it is necessary adjust the classpath in the script.
2. By default the classpath is configured to the current project structure.