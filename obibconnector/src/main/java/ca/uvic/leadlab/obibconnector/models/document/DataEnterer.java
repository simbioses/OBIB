package ca.uvic.leadlab.obibconnector.models.document;

import ca.uvic.leadlab.obibconnector.models.common.Id;
import ca.uvic.leadlab.obibconnector.models.registry.Provider;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class DataEnterer extends Provider {

    private Id id;
    private String time;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    @JsonIgnore
    @Override
    public void setId(String id) {
        this.id = new Id(id, null);
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
