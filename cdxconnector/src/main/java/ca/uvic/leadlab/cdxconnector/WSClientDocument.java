package ca.uvic.leadlab.cdxconnector;

import ca.interiorhealth.BizTalkServiceInstance;
import ca.uvic.leadlab.cdxconnector.messages.*;
import org.hl7.v3.*;

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

            WSUtil.logObject(LOGGER, "\nSubmit Document Request:\n", request);

            BizTalkServiceInstance documentService = createCDASubmitService();
            MCCIIN000002UV01 response = documentService.getCustomBindingITwoWayAsync().submitCDA(request);

            WSUtil.logObject(LOGGER, "\nSubmit Document Response:\n", response);

            return WSUtil.parseObject(response, false);
        } catch (MessageBuilderException e) {
            LOGGER.log(Level.SEVERE, "Error submitting document", e);
            throw new ConnectorException("Error submitting document", e);
        }
    }

    private BizTalkServiceInstance createCDASubmitService() throws ConnectorException {
        try {
            BizTalkServiceInstance submitService = new BizTalkServiceInstance(new URL(baseUrl + "/CDASubmitService/CDASubmit.svc?WSDL"));
            submitService.setHandlerResolver(handlerResolver(submitService.getServiceName()));
            return submitService;
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

            WSUtil.logObject(LOGGER, "\nList New Documents Request:\n", request);

            RCMRAR000003UV01_Service documentService = createCDARequestService();
            RCMRIN000030UV01 response = documentService.getCDARequestEndpoint().mcciIN100001UV01(request);

            WSUtil.logObject(LOGGER, "\nList New Documents Response:\n", response);

            return WSUtil.parseObject(response, false);
        } catch (MessageBuilderException e) {
            LOGGER.log(Level.SEVERE, "Error listing new documents", e);
            throw new ConnectorException("Error listing new documents", e);
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

            WSUtil.logObject(LOGGER, "\nSearch Document Request:\n", request);

            RCMRAR000003UV01_Service documentService = createCDARequestService();
            RCMRIN000030UV01 response = documentService.getCDARequestEndpoint().rcmrIN000029UV01(request);

            WSUtil.logObject(LOGGER, "\nSearch Document Response:\n", response);

            return WSUtil.parseObject(response, false);
        } catch (MessageBuilderException e) {
            LOGGER.log(Level.SEVERE, "Error searching documents", e);
            throw new ConnectorException("Error searching documents", e);
        }
    }

    public String getDocument(String locationId, String documentId) throws ConnectorException {
        try {
            RCMRIN000031UV01 request = new GetDocumentBuilder(UUID.randomUUID().toString())// Unique Message ID (GUID)
                    .receiver("CDX") // ID Of receiver
                    .sender(locationId) // ID Of requestor
                    .documentQuery(new DocumentQueryParameterBuilder()
                            .clinicId(locationId)
                            .documentId(documentId)) // query parameters
                    .build();

            WSUtil.logObject(LOGGER, "\nGet Document Request:\n", request);

            RCMRAR000003UV01_Service documentService = createCDARequestService();
            RCMRIN000032UV01 response = documentService.getCDARequestEndpoint().rcmrIN000031UV01(request);

            WSUtil.logObject(LOGGER, "\nGet Document Response:\n", response);

            return WSUtil.parseObject(response, false);
        } catch (MessageBuilderException e) {
            LOGGER.log(Level.SEVERE, "Error getting documents", e);
            throw new ConnectorException("Error getting documents", e);
        }
    }

    private RCMRAR000003UV01_Service createCDARequestService() throws ConnectorException {
        try {
            RCMRAR000003UV01_Service requestService = new RCMRAR000003UV01_Service(new URL(baseUrl + "/CDArequestService/CDARequest.svc?WSDL"));
            requestService.setHandlerResolver(handlerResolver(requestService.getServiceName()));
            return requestService;
        } catch (MalformedURLException e) {
            throw new ConnectorException("Error creating CDARequestService", e);
        }
    }
}
