package ca.uvic.leadlab.cdxconnector.messages;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class MessageObjectFactoryTest {

    @Test
    public void testDateFormatter() {
        String date = MessageObjectFactory.DATE_TIME_FORMAT.format(new Date());

        Assert.assertNotNull(date);
        System.out.println(date);
    }
}
