package ca.uvic.leadlab.obibconnector.rest;

import ca.uvic.leadlab.obibconnector.models.CDXReturnEntities.*;

/*
Processing the returned messages
 */
public class ProcessReturnMessages {

    public CDResponses getReturnedMessage(Object returnMessages)
    {
        CDResponses cdResponses = new CDResponses();

        //cdResponses.setAckDetail(returnMessages);


        return cdResponses;
    }
}
