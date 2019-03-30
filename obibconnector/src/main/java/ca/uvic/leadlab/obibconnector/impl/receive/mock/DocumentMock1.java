package ca.uvic.leadlab.obibconnector.impl.receive.mock;

import ca.uvic.leadlab.obibconnector.facades.receive.IAttachment;
import ca.uvic.leadlab.obibconnector.facades.receive.IDocument;
import ca.uvic.leadlab.obibconnector.facades.receive.IPatient;
import ca.uvic.leadlab.obibconnector.facades.registry.IProvider;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DocumentMock1 implements IDocument {

    @Override
    public String getTemplateID() {
        return "2.16.840.1.113883.10.20.7";
    }

    @Override
    public String getTemplateName() {
        return "Operative Note";
    }

    @Override
    public String getDocumentID() {
        return "11f5f3e4-aeae-4507-8ceb-ede4afb21234";
    }

    @Override
    public String getLoincCode() {
        return "34117-2";
    }

    @Override
    public String getLoincCodeDisplayName() {
        return "History & Physical Note";
    }

    @Override
    public String getTitle() {
        return "History & Physical Note";
    }

    @Override
    public IPatient getPatient() {
        return new PatientJens();
    }

    @Override
    public IProvider getAuthor() {
        return new ProviderRaymond();
    }

    @Override
    public Date getAuthoringTime() {
        return new Date(300);
    }

    @Override
    public String getAuthorDevice() {
        return "Meditech Software Device";
    }

    @Override
    public Date getAuthorDeviceTime() {
        return new Date(5000);
    }

    @Override
    public String getCustodianName() {
        return "Interior Health Authority";
    }

    @Override
    public IProvider getPrimaryRecipient() {
        return new ProviderAdeshina();
    }

    @Override
    public List<IProvider> getSecondaryRecipients() {
        return new ArrayList<>();
    }

    @Override
    public IProvider getOrderingProvider() {
        return new ProviderRaymond();
    }

    @Override
    public IProvider getFamilyProvider() {
        return null;
    }

    @Override
    public String getOrderID() {
        return "123456789";
    }

    @Override
    public String getStatusCode() {
        return "signed";
    }

    @Override
    public Date getObservationDate() {
        SimpleDateFormat sdf = new SimpleDateFormat ( "dd-mm-yyyy" ) ;

        Date result = null;

        try {
            sdf.parse("01-01-2019");
        } catch (Exception e) {
            // should never happen
        }

        return result;
    }

    @Override
    public String getProcedureName() {
        return "Elbow LT";
    }

    @Override
    public IProvider getProcedurePerformer() {
        return new ProviderRaymond();
    }

    @Override
    public String getParentDocumentID() {
        return "ITS123456789";
    }

    @Override
    public String getPatientEncounterID() {
        return "KA0108436/17";
    }

    @Override
    public Date getAdmissionDate() {
        SimpleDateFormat sdf = new SimpleDateFormat ( "dd-mm-yyyy" ) ;

        Date result = null;

        try {
            sdf.parse("02-02-2019");
        } catch (Exception e) {
            // should never happen
        }

        return result;
    }

    @Override
    public Date getDischargeDate() {
        SimpleDateFormat sdf = new SimpleDateFormat ( "dd-mm-yyyy" ) ;

        Date result = null;

        try {
            sdf.parse("03-03-2019");
        } catch (Exception e) {
            // should never happen
        }

        return result;
    }

    @Override
    public String getDischargeDisposition() {
        return "Discharge to home";
    }

    @Override
    public List<IProvider> getParticipatingProviders() {
        List<IProvider> result = new ArrayList<>();
        result.add(new ProviderRaymond());
        result.add(new ProviderAdeshina());
        return result;
    }

    @Override
    public String getContents() {
        return "this is the contents";
    }

    @Override
    public List<IAttachment> getAttachments() {
        return new ArrayList<IAttachment>();
    }


}
