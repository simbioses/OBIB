package ca.uvic.leadlab.obibconnector.impl.receive.mock;

import ca.uvic.leadlab.obibconnector.facades.receive.IDocument;
import ca.uvic.leadlab.obibconnector.facades.receive.IReceiveDoc;

public class ReceiveDocMock implements IReceiveDoc {

    @Override
    public String[] pollNewDocIDs() {
        String[] result = {"11f5f3e4-aeae-4507-8ceb-ede4afb21234"};
        return result;
    }

    @Override
    public IDocument retrieveDocument(String id) throws Exception {

        if (id.equals("11f5f3e4-aeae-4507-8ceb-ede4afb21234")) {

            IDocument doc = new DocumentMock1();

        } else {
            throw new Exception("no document for given id ");
        }
        return null;
    }
}
