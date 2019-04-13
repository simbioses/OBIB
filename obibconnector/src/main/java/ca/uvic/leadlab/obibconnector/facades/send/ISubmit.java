package ca.uvic.leadlab.obibconnector.facades.send;

import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;
import ca.uvic.leadlab.obibconnector.facades.receive.IDocument;

public interface ISubmit {

    IDocument submit() throws OBIBException;
}
