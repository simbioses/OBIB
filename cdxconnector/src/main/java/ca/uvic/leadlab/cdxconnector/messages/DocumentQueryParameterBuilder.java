package ca.uvic.leadlab.cdxconnector.messages;

import org.hl7.v3.*;

import java.time.ZonedDateTime;

public class DocumentQueryParameterBuilder extends MessageBuilder {

    private DocumentType documentType;
    private RCMRMT000003UV01ClinicalDocumentId clinicalDocumentId;
    private RCMRMT000003UV01RepresentedOrganizationId representedOrganizationId;
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
        representedOrganizationId = new RCMRMT000003UV01RepresentedOrganizationId();
        representedOrganizationId.setValue(factory.createII("2.16.840.1.113883.3.277.100.2", clinicId));
        return this;
    }

    public DocumentQueryParameterBuilder documentId(String documentId) {
        clinicalDocumentId = new RCMRMT000003UV01ClinicalDocumentId();
        clinicalDocumentId.setValue(factory.createII("2.16.840.1.113883.3.277.3.4.1", documentId));
        return this;
    }

    public DocumentQueryParameterBuilder documentEffectiveTime(ZonedDateTime lowDate, boolean lowDateInclusive,
                                                               ZonedDateTime highDate, boolean highDateInclusive) {
        clinicalDocumentEffectiveTime = new RCMRMT000003UV01ClinicalDocumentEffectiveTime();
        clinicalDocumentEffectiveTime.setValue(factory.createIVLTS(
                factory.createIVXBTS(lowDateInclusive, lowDate),
                factory.createIVXBTS(highDateInclusive, highDate)));
        return this;
    }

    public DocumentQueryParameterBuilder eventEffectiveTime(ZonedDateTime lowDate, boolean lowDateInclusive,
                                                            ZonedDateTime highDate, boolean highDateInclusive) {
        serviceEventEffectiveTime = new RCMRMT000003UV01ServiceEventEffectiveTime();
        serviceEventEffectiveTime.setValue(factory.createIVLTS(
                factory.createIVXBTS(lowDateInclusive, lowDate),
                factory.createIVXBTS(highDateInclusive, highDate)));
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
        if (documentType != null) {
            parameter.getTemplateId().add(factory.createII("2.16.840.1.113883.3.277.100.20", documentType.name()));
        }
        if (clinicalDocumentEffectiveTime != null) {
            parameter.setClinicalDocumentEffectiveTime(clinicalDocumentEffectiveTime);
        }
        if (clinicalDocumentId != null) {
            parameter.setClinicalDocumentId(clinicalDocumentId);
        }
        parameter.getRepresentedOrganizationId().add(representedOrganizationId);
        if (serviceEventEffectiveTime != null) {
            parameter.getServiceEventEffectiveTime().add(serviceEventEffectiveTime);
        }
        return parameter;
    }
}
