package ca.uvic.leadlab.obibconnector.models.document;

import ca.uvic.leadlab.obibconnector.models.common.Id;

import java.util.ArrayList;
import java.util.List;

public class ReceivedOrganization {

    private List<Id> ids = new ArrayList<>();
    private String name;

    public List<Id> getIds() {
        return ids;
    }

    public void setIds(List<Id> ids) {
        this.ids = ids;
    }

    public void setId(String id) {
        if (this.ids == null) {
            this.ids = new ArrayList<>();
        }
        this.ids.add(new Id(id, null));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
