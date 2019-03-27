package ca.uvic.leadlab.obibconnector.models.document;

import ca.uvic.leadlab.obibconnector.models.common.Id;

import java.util.List;

public class Custodian {

    private List<Id> id;
    private String name;

    public List<Id> getId() {
        return id;
    }

    public void setId(List<Id> id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
