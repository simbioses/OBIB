package ca.uvic.leadlab.obibconnector.impl.receive;

import ca.uvic.leadlab.obibconnector.facades.datatypes.AttachmentType;
import ca.uvic.leadlab.obibconnector.facades.receive.IAttachment;

public class Attachment implements IAttachment {

    private AttachmentType type;
    private String reference;
    private byte[] content;

    Attachment(String mediaType, String reference, byte[] content) {
        this.type = AttachmentType.fromMediaType(mediaType);
        this.reference = reference;
        this.content = content;
    }

    @Override
    public AttachmentType getType() {
        return type;
    }

    @Override
    public String getReference() {
        return reference;
    }

    @Override
    public byte[] getContent() {
        return content;
    }
}
