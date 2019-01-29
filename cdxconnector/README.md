#### CDXConnector Java App

Connects to CDX Webservices.

###### Maven executions:

* Utilizes keytool-maven-plugin to:
    * Import CDX certificate chain into a jks keystore
    * Import client certificate into a jks keystore
     
* Utilizes jaxws-maven-plugin to:
    * Generate Java classes from CDX WSDLs
