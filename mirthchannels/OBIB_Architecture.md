# OBIB Architecture Overview

OBIB is a bi-directional interoperability system for transmitting CDA Documents through the CDX System.
OBIB is formed by a set of messaging channels within Mirth Connect, Java libraries for message transmission and
a few other pieces of software. The following Figure illustrates who the various components interact.

![obib architecture](OBIB_architecture.png "OBIB Architecture")

* OBIB Connector (JAR File) - OBIB REST client. 
* nginx - HTTPS reverse proxy to secure the connections between clients and OBIB.
* OBIB_DB - Database used by OBIB channels to store clinics' info, documents and error messages.
* mirthdb - Database used by Mirth Connect.
* SMTP Server - Used by OBIB's "/NotifyError" endpoint to send error notifications via email.
* CDX Connector (JAR File) - CDX WS client.

## OBIB channels

OBIB consists of 5 Mirth Connect channels, which are described below. Each channel has one _Source Connector_ and 
multiple _Destination Connectors_, and each connector can have multiple filters and transformers.

![obib channels](OBIB_channels.png "OBIB Channels")

### OBIB Services channel

**OBIB Services channel** is the main channel of OBIB. This channel acts as a REST server API and translates the 
messages from the OBIB JSON format to the CDX XML format.

![obib services channel](OBIB_Services_channel.png "OBIB Services channel")

#### Source Connector

The Source Connector is an _HTTP Listener_ that performs the following tasks:
- listen to HTTP connections on a port in local interface (e.g. 127.0.0.1:8081);
- perform HTTP authentication (_Javascript_ that verifies the clinicId and password sent in the connection header);
- route messages to the appropriate Destination Connector;

The message routing is performed by Source Transformers which are elements of the Source Connector. The Source Transformers
use the URL context path to route the message to a Destination Connector. For example, a connection with URL
http://127.0.0.1:8081/SubmitCDA is routed to the Destination Connector **Service Submit Document**.

In addition, the Source Connector has two extra Source Transformers that are responsible for retrieving the clinic credentials
from the OBIB_DB database and verify the OBIB Connector version.

#### Destination Connectors

The Destination Connectors are _Javascript Writers_ responsible for the bi-directional transformation and submission of
the messages. They receive messages in the OBIB format (JSON) and submit messages in the CDX format (XML). Then transform
the CDX response from XML to JSON, and return it to the client.

Moreover, the Destination Connectors uses the **CDX Connector** to submit the messages, and each Destination Connector
is responsible for one CDX WS method. For example, **Service Submit Document** submits CDA documents, **Service List New
documents** lists new CDA documents, and so on. The only exception is the **Service OSP Support**, which redirects error
messages sent by the OBIB clients to the **OSP Support** channel.

##### Service Submit Documents

The **Service Submit Documents** connector is responsible for the CDA document submission. It uses a list of transformers
to convert the messages from the JSON to the CDA XML document. Each transformer is implemented to work with a specific
part of the document. For example, **Header Attributes** deals with the CDA headers (loinc_code, template_id, document_id,
document_version, and effective_time). Moreover, the last transformer in the list, **Document Validation**, checks the
document against the XML Schema to ensure the XML is correct.

With the CDA document ready and valid, **Service Submit Documents** stores the document in the database using the
**Document Storage** channel and submits the document using the **CDX Connector**. Then, the CDX response is stored
in the database, and finally, it is processed by the **Submit Document Response** Transformer. This Response Transformer
converts the CDX acknowledgement XML message to an OBIB JSON message. 

##### Service List New Documents

The **Service List New Documents** connector calls the CDX method to list new documents for the current clinic. It has 
only one response transformer that translates the CDX response into JSON.

##### Service Search Document

The **Service Search Document** connector calls the CDX method to search for documents. It can filter documents by 
clinic id, document id, effective time or event time. These search criteria are retrieved from the requests by 
transformers of type _Mapper_. Moreover, this connector has only one response transformer that translates the 
CDX response into JSON.

##### Service Get Document

The **Service Get Document** connector calls the CDX method to get a document by its id. This connector has only one 
transformer to get the document id from the request, and a few response transformers to convert the CDA document (XML)
into JSON, retrieve attachments, and get response data. The document conversion is made by the **CDA Document Parser** 
channel.

##### Service List Clinics

The **Service List Clinics** connector calls the CDX method to list clinics. The clinics can be filtered by id, name, 
or address. These list criteria are retrieved from the requests by transformers of type _Mapper_. Moreover, this 
connector has only one response transformer that calls the **CDA Registry Parser** channel to translate the 
CDX response into JSON.

##### Service List Providers

The **Service List Providers** connector calls the CDX method to list providers. The providers can be filtered by id, 
name, or clinic id. These list criteria are retrieved from the requests by transformers of type _Mapper_. Moreover, this
connector has only one response transformer that calls the **CDA Registry Parser** channel to translate the
CDX response into JSON.

##### Service Distribution Status

The **Service Distribution Status** connector calls the CDX method to get the distribution status of a sent document.
It can filter documents by clinic id, document id, effective time or event time. These search criteria are retrieved 
from the requests by transformers of type _Mapper_. Moreover, this connector has only one response transformer that 
translates the CDX response into JSON.

##### Service OSP Support

The **Service OSP Support** connector stores and notifies by email error reports sent by the OBIB clients. The 
notifications are processed by the **OSP Support** channel. Moreover, this connector has one transformer to get 
some request metadata, and a response transformer to return a response status for the caller. 

### CDA Document Parser channel

The **CDA Document Parser**'s Source Connector is a default _Channel Reader_ with no filter or transformer.

**CDA Document Parser** has one Destination Connector that is responsible for parsing CDA XML documents into JSON. 
This connector has a list of transformers to perform the transformation.

### CDA Registry Parser channel

The **CDA Registry Parser**'s Source Connector is a _Channel Reader_ that route the messages to the Destination Channels
according to the type of the messages' assigned entity code, namely Clinic or Provider.

**CDA Registry Parser** has two Destination Connectors responsible for parsing CDA XML messages into JSON. A connector
to parse Clinic Registries and a connector tor parse Provider Registry. Each connector has a list of transformers to
perform the message transformation.

### Document Storage channel

The **Document Storage**'s Source Connector is a _Channel Reader_ that routes the messages to the Destination Channels 
according to the message type.

**Document Storage** has three Destination Connectors of the type _Database Writer_. Each of them store a type of message, 
namely Document, Response and Attachment.

### OSP Support channel

The **OSP Support**'s Source Connector is a default _Channel Reader_ with no filter or transformer.

**OSP Support** has two Destination Connectors. A _Database Writer_ for store the error messages in the database, and 
a _SMTP Sender_ for sending notifications via email.
