package ca.uvic.leadlab.cdxconnector;

import ca.uvic.leadlab.cdxconnector.messages.distribution.DistributionStatusBuilder;
import ca.uvic.leadlab.cdxconnector.messages.distribution.DistributionStatusQueryParameterBuilder;
import ca.uvic.leadlab.cdxconnector.messages.request.DocumentQueryParameterBuilder;
import ca.uvic.leadlab.cdxconnector.messages.request.GetDocumentBuilder;
import ca.uvic.leadlab.cdxconnector.messages.request.ListNewDocumentsBuilder;
import ca.uvic.leadlab.cdxconnector.messages.request.SearchDocumentBuilder;
import ca.uvic.leadlab.cdxconnector.messages.submit.DocumentAttachment;
import ca.uvic.leadlab.cdxconnector.messages.submit.SubmitDocumentBuilder;
import cdasubmit.*;
import cdarequest.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

public class WSClientDocument extends WSClient {

    public WSClientDocument(String baseUrl, String username, String password,
                            String certPath, String certPass) throws ConnectorException {
        super(baseUrl, username, password, certPath, certPass);
    }

    public String submitDocument(String locationId, String document, List<DocumentAttachment> attachments,
                                 String... receiversIds) throws ConnectorException {
        try {
            SubmitDocumentBuilder documentBuilder = new SubmitDocumentBuilder(UUID.randomUUID().toString()) // Unique Message ID (GUID)
                    .sender(locationId) // ID Of requestor
                    .document(UUID.randomUUID().toString(), document);
            if (attachments != null) { // Attachments
                for (DocumentAttachment attachment : attachments) {
                    documentBuilder.attachment(attachment);
                }
            }
            for (String receiverId : receiversIds) { // Ids of receivers
                documentBuilder.receiver(receiverId);
            }
            RCMRIN000002UV01 request = documentBuilder.build();

            WSUtil.validateObjectSize(request);

            WSUtil.logObject(LOGGER, "Submit Document Request:", request);

            CDASubmit documentService = createCDASubmitService();
            MCCIIN000002UV01 response = documentService.submitCDA(request);

            WSUtil.logObject(LOGGER, "Submit Document Response:", response);

            return WSUtil.parseObject(response, false);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error submitting document.", e);
            throw new ConnectorException("Error submitting document.", e);
        }
    }

    private CDASubmit createCDASubmitService() throws ConnectorException {
        try {
            BizTalkServiceInstance submitService = new BizTalkServiceInstance(new URL(baseUrl + "/CDASubmitService/CDASubmit.svc?WSDL"));
            submitService.setHandlerResolver(handlerResolver(submitService.getServiceName()));

            CDASubmit service = submitService.getCustomBindingITwoWayAsync();
            setupTimeout(service);

            return service;
        } catch (MalformedURLException e) {
            throw new ConnectorException("Error creating CDASubmitService", e);
        }
    }

    public String listNewDocuments(String locationId) throws ConnectorException {
        try {
            MCCIIN100001UV01 request = new ListNewDocumentsBuilder(UUID.randomUUID().toString())// Unique Message ID (GUID)
                    .receiver("CDX") // ID Of receiver
                    .sender(locationId) // ID Of requestor
                    .build();

            WSUtil.logObject(LOGGER, "List New Documents Request:", request);

            RCMRAR000003UV01 documentService = createCDARequestService();
            RCMRIN000030UV01 response = documentService.mcciIN100001UV01(request);

            WSUtil.logObject(LOGGER, "List New Documents Response:", response);

            return WSUtil.parseObject(response, false);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error listing new documents.", e);
            throw new ConnectorException("Error listing new documents.", e);
        }
    }

    public String searchDocuments(String locationId, String clinicId, String documentId,
                                  DateRange effectiveTime, DateRange eventTime) throws ConnectorException {
        try {
            RCMRIN000029UV01 request = new SearchDocumentBuilder(UUID.randomUUID().toString())// Unique Message ID (GUID)
                    .receiver("CDX") // ID Of receiver
                    .sender(locationId) // ID Of requestor
                    .documentQuery(new DocumentQueryParameterBuilder()
                            .clinicId(clinicId)
                            .documentId(documentId)
                            .documentEffectiveTime(effectiveTime)
                            .eventEffectiveTime(eventTime)) // query parameters
                    .build();

            WSUtil.logObject(LOGGER, "Search Document Request:", request);

            RCMRAR000003UV01 documentService = createCDARequestService();
            RCMRIN000030UV01 response = documentService.rcmrIN000029UV01(request);

            WSUtil.logObject(LOGGER, "Search Document Response:", response);

            return WSUtil.parseObject(response, false);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error searching documents", e);
            throw new ConnectorException("Error searching documents.", e);
        }
    }

    public String getDocument(String locationId, String messageId) throws ConnectorException {
        try {
            RCMRIN000031UV01 request = new GetDocumentBuilder(UUID.randomUUID().toString())// Unique Message ID (GUID)
                    .receiver("CDX") // ID Of receiver
                    .sender(locationId) // ID Of requestor
                    .documentQuery(new DocumentQueryParameterBuilder()
                            .clinicId(locationId)
                            .messageId(messageId)) // query parameters
                    .build();

            WSUtil.logObject(LOGGER, "Get Document Request:", request);

            RCMRAR000003UV01 documentService = createCDARequestService();
            RCMRIN000032UV01 response = documentService.rcmrIN000031UV01(request);

            WSUtil.logObject(LOGGER, "Get Document Response:", response);

            return WSUtil.parseObject(response, false);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error getting documents", e);
            throw new ConnectorException("Error getting documents.", e);
        }
    }

    private RCMRAR000003UV01 createCDARequestService() throws ConnectorException {
        try {
            RCMRAR000003UV01_Service requestService = new RCMRAR000003UV01_Service(new URL(baseUrl
                    + "/CDArequestService/CDARequest.svc?WSDL"));
            requestService.setHandlerResolver(handlerResolver(requestService.getServiceName()));

            RCMRAR000003UV01 service = requestService.getCDARequestEndpoint();
            setupTimeout(service);

            return service;
        } catch (MalformedURLException e) {
            throw new ConnectorException("Error creating CDARequestService", e);
        }
    }

    public String getDistributionStatus(String locationId, String clinicId, String documentId,
                                        DateRange effectiveTime, DateRange eventTime) throws ConnectorException {
        try {
            distributionstatus.RCMRIN000029UV01 request = new DistributionStatusBuilder(UUID.randomUUID().toString())// Unique Message ID (GUID)
                    .receiver("CDX") // ID Of receiver
                    .sender(locationId) // ID Of requestor
                    .documentQuery(new DistributionStatusQueryParameterBuilder()
                            .clinicId(clinicId)
                            .documentId(documentId)
                            .documentEffectiveTime(effectiveTime)
                            .eventEffectiveTime(eventTime)) // query parameters
                    .build();

            WSUtil.logObject(LOGGER, "Search Document Request:", request);

            distributionstatus.RCMRAR000003UV01 documentService = createDistributionStatusService();
            distributionstatus.RCMRIN000030UV01 response = documentService.getStatus(request);

            WSUtil.logObject(LOGGER, "Search Document Response:", response);

            return WSUtil.parseObject(response, false);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error searching documents", e);
            throw new ConnectorException("Error searching documents.", e);
        }
    }

    private distributionstatus.RCMRAR000003UV01 createDistributionStatusService() throws ConnectorException {
        try {
            distributionstatus.RCMRAR000003UV01_Service requestService = new distributionstatus
                    .RCMRAR000003UV01_Service(new URL(baseUrl + "/DistributionStatusService/DistributionStatus.svc?WSDL"));
            requestService.setHandlerResolver(handlerResolver(requestService.getServiceName()));

            distributionstatus.RCMRAR000003UV01 service = requestService.getDistributionStatusEndpoint();
            setupTimeout(service);

            return service;
        } catch (MalformedURLException e) {
            throw new ConnectorException("Error creating CDARequestService", e);
        }
    }
}
