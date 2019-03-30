package ca.uvic.leadlab.obibconnector.impl.receive;

import ca.uvic.leadlab.obibconnector.facades.datatypes.AttachmentType;
import ca.uvic.leadlab.obibconnector.facades.receive.IAttachment;

public class Attachment implements IAttachment {

    @Override
    public AttachmentType getType() {
        return null;
    }

    @Override
    public String getReference() {
        return null;
    }

    @Override
    public byte[] getContent() {
        return new byte[0];
    }
}
