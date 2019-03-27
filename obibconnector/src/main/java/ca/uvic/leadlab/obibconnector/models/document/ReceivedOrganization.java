package ca.uvic.leadlab.obibconnector.models.document;

import ca.uvic.leadlab.obibconnector.models.common.Id;

public class ReceivedOrganization {

    private Id id;
    private String name;

    public ReceivedOrganization() {
    }

    public ReceivedOrganization(String id, String name) {
        this.id = new Id(id, null);
        this.name = name;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
