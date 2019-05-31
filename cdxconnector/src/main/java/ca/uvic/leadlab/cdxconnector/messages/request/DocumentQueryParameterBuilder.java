package ca.uvic.leadlab.cdxconnector.messages.request;

import ca.uvic.leadlab.cdxconnector.DateRange;
import cdasubmitrequest.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class DocumentQueryParameterBuilder {

    private RequestMessageObjectFactory factory = new RequestMessageObjectFactory();

    private DocumentType documentType;
    private RCMRMT000003UV01RepresentedOrganizationId representedOrganizationId;
    private RCMRMT000003UV01ClinicalDocumentId clinicalDocumentId;
    private RCMRMT000003UV01ClinicalDocumentEffectiveTime clinicalDocumentEffectiveTime;
    private RCMRMT000003UV01ServiceEventEffectiveTime serviceEventEffectiveTime;

    public enum DocumentType {
        CDA, HL7v2
    }

    public DocumentQueryParameterBuilder documentType(DocumentType documentType) {
        this.documentType = documentType;
        return this;
    }

    public DocumentQueryParameterBuilder clinicId(String clinicId) {
        if (StringUtils.isNotBlank(clinicId)) {
            representedOrganizationId = new RCMRMT000003UV01RepresentedOrganizationId(); // CONF-CDXMQ070
            representedOrganizationId.setValue(factory.createII("2.16.840.1.113883.3.277.100.2", clinicId)); // CONF-CDXMQ071
        }
        return this;
    }

    public DocumentQueryParameterBuilder documentId(String documentId) {
        if (StringUtils.isNotBlank(documentId)) {
            clinicalDocumentId = new RCMRMT000003UV01ClinicalDocumentId(); // CONF-CDXMQ068
            clinicalDocumentId.setValue(factory.createII("2.16.840.1.113883.3.277.100.3", documentId)); // CONF-CDXMQ069
        }
        return this;
    }

    public DocumentQueryParameterBuilder messageId(String messageId) {
        if (StringUtils.isNotBlank(messageId)) {
            clinicalDocumentId = new RCMRMT000003UV01ClinicalDocumentId(); // CONF-CDXMQ068
            clinicalDocumentId.setValue(factory.createII("2.16.840.1.113883.3.277.100.1", messageId)); // CONF-CDXMQ069
        }
        return this;
    }

    public DocumentQueryParameterBuilder documentEffectiveTime(DateRange effectiveTime) {
        if (effectiveTime != null) {
            documentEffectiveTime(effectiveTime.getLowDate(), effectiveTime.isLowDateInclusive(),
                    effectiveTime.getHighDate(), effectiveTime.isHighDateInclusive());
        }
        return this;
    }

    public DocumentQueryParameterBuilder documentEffectiveTime(Date lowDate, boolean lowDateInclusive,
                                                               Date highDate, boolean highDateInclusive) {
        clinicalDocumentEffectiveTime = new RCMRMT000003UV01ClinicalDocumentEffectiveTime(); // CONF-CDXMQ065
        clinicalDocumentEffectiveTime.setValue(factory.createIVLTS(
                factory.createIVXBTS(lowDateInclusive, lowDate), // CONF-CDXMQ066
                factory.createIVXBTS(highDateInclusive, highDate))); // CONF-CDXMQ067
        return this;
    }

    public DocumentQueryParameterBuilder eventEffectiveTime(DateRange eventTime) {
        if (eventTime != null) {
            eventEffectiveTime(eventTime.getLowDate(), eventTime.isLowDateInclusive(),
                    eventTime.getHighDate(), eventTime.isHighDateInclusive());
        }
        return this;
    }

    public DocumentQueryParameterBuilder eventEffectiveTime(Date lowDate, boolean lowDateInclusive,
                                                            Date highDate, boolean highDateInclusive) {
        serviceEventEffectiveTime = new RCMRMT000003UV01ServiceEventEffectiveTime(); // CONF-CDXMQ072
        serviceEventEffectiveTime.setValue(factory.createIVLTS(
                factory.createIVXBTS(lowDateInclusive, lowDate), // CONF-CDXMQ073
                factory.createIVXBTS(highDateInclusive, highDate))); // CONF-CDXMQ074
        return this;
    }

    public RCMRMT000003UV01QueryByParameter buildGet() {
        RCMRMT000003UV01QueryByParameter parameter = new RCMRMT000003UV01QueryByParameter();
        if (clinicalDocumentId != null) {
            parameter.setClinicalDocumentId(clinicalDocumentId);
        }
        parameter.getRepresentedOrganizationId().add(representedOrganizationId);
        return parameter;
    }

    public RCMRMT000003UV01QueryByParameter buildSearch() {
        RCMRMT000003UV01QueryByParameter parameter = new RCMRMT000003UV01QueryByParameter();
        if (documentType != null) { // CONF-CDXMQ064
            parameter.getTemplateId().add(factory.createII("2.16.840.1.113883.3.277.100.20", documentType.name()));
        }
        if (representedOrganizationId != null) { // clinicId
            parameter.getRepresentedOrganizationId().add(representedOrganizationId);
        }
        if (clinicalDocumentId != null) {   // documentId
            parameter.setClinicalDocumentId(clinicalDocumentId);
        }
        if (clinicalDocumentEffectiveTime != null) { // documentEffectiveTime
            parameter.setClinicalDocumentEffectiveTime(clinicalDocumentEffectiveTime);
        }
        if (serviceEventEffectiveTime != null) { // eventEffectiveTime
            parameter.getServiceEventEffectiveTime().add(serviceEventEffectiveTime);
        }
        return parameter;
    }
}
