package ca.uvic.leadlab.obibconnector.impl.receive.mock;

import ca.uvic.leadlab.obibconnector.facades.datatypes.AttachmentType;
import ca.uvic.leadlab.obibconnector.facades.receive.IAttachment;
import ca.uvic.leadlab.obibconnector.facades.receive.IDocument;
import ca.uvic.leadlab.obibconnector.facades.receive.IPerson;

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
    public String getLoingCodeDisplayName() {
        return "History & Physical Note";
    }

    @Override
    public String getTitle() {
        return "History & Physical Note";
    }

    @Override
    public IPerson getPatient() {
        return new PersonJens();
    }

    @Override
    public IPerson getAuthor() {
        return new PersonMorgan();
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
    public String getCustodian() {
        return "Interior Health Authority";
    }

    @Override
    public IPerson getPrimaryRecipient() {
        return new ProviderAdeshina();
    }

    @Override
    public List<IPerson> getSecondaryRecipients() {
        return new ArrayList<IPerson>();
    }

    @Override
    public IPerson getOrderingProvider() {
        return new ProviderRaymond();
    }

    @Override
    public IPerson getFamilyProvider() {
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
    public IPerson getProcedurePerformer() {
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
    public List<IPerson> getParticipatingProviders() {
        List<IPerson> result = new ArrayList<>();
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
