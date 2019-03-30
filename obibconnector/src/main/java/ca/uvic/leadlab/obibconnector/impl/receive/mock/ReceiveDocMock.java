package ca.uvic.leadlab.obibconnector.impl.receive.mock;

import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;
import ca.uvic.leadlab.obibconnector.facades.receive.IDocument;
import ca.uvic.leadlab.obibconnector.facades.receive.IReceiveDoc;

import java.util.ArrayList;
import java.util.List;

public class ReceiveDocMock implements IReceiveDoc {

    @Override
    public List<String> pollNewDocIDs() throws OBIBException {
        List<String> result = new ArrayList<>();
        result.add("11f5f3e4-aeae-4507-8ceb-ede4afb21234");
        return result;
    }

    @Override
    public IDocument retrieveDocument(String id) throws OBIBException {
        IDocument doc;

        if (id.equals("11f5f3e4-aeae-4507-8ceb-ede4afb21234")) {

            doc = new DocumentMock1();

        } else {
            throw new OBIBException("no document for given id ");
        }
        return doc;
    }
}
