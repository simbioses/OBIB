package ca.uvic.leadlab.obibconnector.facades.send;

import ca.uvic.leadlab.obibconnector.impl.send.SubmitDoc;
import ca.uvic.leadlab.obibconnector.facades.datatypes.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class SubmitDocTest {

    @Test
    public void testSubmitDoc() {
        String response = new SubmitDoc("11111")
                    .patient()
                        .id("2222")
                        .name(NameType.LEGAL, "Joe", "Wine")
                        .address(AddressType.HOME, "111 Main St", "Victoria", "BC", "V8V Z9Z", "CA")
                        .phone(PhoneType.HOME, "250-111-1234")
                    .and().author()
                        .id("3333")
                        .participantTime(new Date())
                        .name(NameType.LEGAL, "Joseph", "Cloud")
                        .address(AddressType.HOME, "111 Main St", "Victoria", "BC", "V8V Z9Z", "CA")
                        .phone(PhoneType.HOME, "250-111-1234")
                    .and().recipient()
                        .id("4444")
                        .name(NameType.LEGAL, "Joseph", "Cloud")
                        .address(AddressType.HOME, "111 Main St", "Victoria", "BC", "V8V Z9Z", "CA")
                        .phone(PhoneType.HOME, "250-111-1234")
                    .and().participant()
                        .id("555")
                        .name(NameType.LEGAL, "Joseph", "Cloud")
                        .address(AddressType.HOME, "111 Main St", "Victoria", "BC", "V8V Z9Z", "CA")
                        .phone(PhoneType.HOME, "250-111-1234")
                .submit();

        System.out.println(response);

        Assert.assertNotNull(response);
    }

}
