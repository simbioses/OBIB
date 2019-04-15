package ca.uvic.leadlab.obibconnector.impl.send;

import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;
import ca.uvic.leadlab.obibconnector.facades.receive.IDocument;
import ca.uvic.leadlab.obibconnector.facades.send.IAnd;
import ca.uvic.leadlab.obibconnector.facades.send.ISubmitDoc;

public abstract class DocElement implements IAnd {

    private final ISubmitDoc submitDoc;

    DocElement(ISubmitDoc submitDoc) {
        this.submitDoc = submitDoc;
    }

    @Override
    public ISubmitDoc and() {
        return submitDoc;
    }

    @Override
    public IDocument submit() throws OBIBException {
        return submitDoc.submit();
    }
}
