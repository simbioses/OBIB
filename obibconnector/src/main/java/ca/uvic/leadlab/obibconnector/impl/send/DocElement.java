package ca.uvic.leadlab.obibconnector.impl.send;

import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;
import ca.uvic.leadlab.obibconnector.facades.send.IAnd;
import ca.uvic.leadlab.obibconnector.facades.send.ISubmitDoc;

public class DocElement implements IAnd {

    ISubmitDoc submitDoc;

    @Override
    public ISubmitDoc and() {
        return submitDoc;
    }

    @Override
    public String submit() throws OBIBException {
        return submitDoc.submit();
    }
}
