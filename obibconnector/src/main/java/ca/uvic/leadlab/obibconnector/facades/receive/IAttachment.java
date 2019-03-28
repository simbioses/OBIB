package ca.uvic.leadlab.obibconnector.facades.receive;

import ca.uvic.leadlab.obibconnector.facades.datatypes.AttachmentType;

public interface IAttachment {
     AttachmentType getType();
     String getReference();
     byte[] getContent();
}
