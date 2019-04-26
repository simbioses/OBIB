# CDXConnector Java App

Library responsible for connects to the CDX Web Services.

#### Maven execution details:

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

#### Submit Document Java Client:

To execute the Submit Document Client call the **jar-test** file as following:

```
$ java -cp .:* -jar cdxconnector-0.0.1-SNAPSHOT-tests.jar ca.uvic.leadlab.cdxconnector.SubmitDocumentClient location_id receivers_ids cda_file_path [attachment_files_paths]
```

where:
- *location_id*: is the clinic 'location id' registered in the **SubmitDocumentClient.properties** file
- *receivers_ids*: are the receiver clinics 'location id' separated by comma ','
- *cda_file_path*: is the relative path for the cda file to be submitted
- *attachment_files_paths*: (optional) are the document's attachment file separated by comma ','

To register a clinic to submit documents using this client it is necessary configure the following information 
in the **SubmitDocumentClient.properties** file:
```
obib.clinic.location_id.username = <cdx clinic username>
obib.clinic.location_id.password = <cdx clinic password>
obib.clinic.location_id.certificate = <relative path for the clinic PFX certificate file>
obib.clinic.location_id.certpassword = <clinic certificate password>
```