package ca.uvic.leadlab.obibconnector.facades.receive;

public interface IReceiveDoc {
    String[] pollNewDocIDs();

    IDocument retrieveDocument(String id) throws Exception;
}
