package ca.uvic.leadlab.obibconnector.facades.send;

import ca.uvic.leadlab.obibconnector.facades.FacadesBaseTest;
import ca.uvic.leadlab.obibconnector.impl.send.SubmitDoc;
import ca.uvic.leadlab.obibconnector.facades.datatypes.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class SubmitDocTest extends FacadesBaseTest {

    //@Test
    public void testSubmitDoc() throws Exception {
        ISubmitDoc submitDoc = new SubmitDoc(config);

        String response = submitDoc.newDoc()
                        .patient()
                            .id("2222")
                            .name(NameType.LEGAL, "Joe", "Wine")
                            .address(AddressType.HOME, "111 Main St", "Victoria", "BC", "V8V Z9Z", "CA")
                            .phone(TelcoType.HOME, "250-111-1234")
                        .and().author()
                            .id("3333")
                            .time(new Date())
                            .name(NameType.LEGAL, "Joseph", "Cloud")
                            .address(AddressType.HOME, "111 Main St", "Victoria", "BC", "V8V Z9Z", "CA")
                            .phone(TelcoType.HOME, "250-111-1234")
                        .and().recipient()
                            .id("4444")
                            .name(NameType.LEGAL, "Joseph", "Cloud")
                            .address(AddressType.HOME, "111 Main St", "Victoria", "BC", "V8V Z9Z", "CA")
                            .phone(TelcoType.HOME, "250-111-1234")
                        .and().participant()
                            .functionCode("PCP")
                            .id("555")
                            .name(NameType.LEGAL, "Joseph", "Cloud")
                            .address(AddressType.HOME, "111 Main St", "Victoria", "BC", "V8V Z9Z", "CA")
                            .phone(TelcoType.HOME, "250-111-1234")
                    .and().content("Document plain text")
                    .submit();

        Assert.assertNotNull(response);
    }


}
