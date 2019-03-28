package ca.uvic.leadlab.obibconnector.facades.receive;

import java.util.List;

public interface IReceiveDoc {
    List<String> pollNewDocIDs();

    IDocument retrieveDocument(String id) throws Exception;
}
