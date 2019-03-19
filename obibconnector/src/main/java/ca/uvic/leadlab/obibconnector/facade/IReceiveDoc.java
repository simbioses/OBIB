package ca.uvic.leadlab.obibconnector.facade;

public interface IReceiveDoc {
    String[] pollNewDocIDs();

    IDocument retrieveDocument(String id);
}
