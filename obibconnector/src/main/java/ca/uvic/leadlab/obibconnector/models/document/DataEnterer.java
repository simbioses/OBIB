package ca.uvic.leadlab.obibconnector.models.document;

import ca.uvic.leadlab.obibconnector.models.common.Id;
import ca.uvic.leadlab.obibconnector.models.registry.Provider;
import ca.uvic.leadlab.obibconnector.utils.OBIBConnectorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DataEnterer extends Provider {

    @JsonProperty // force jackson to use this field
    private Id id;
    private String time;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    @JsonIgnore // to avoid conflict with the other setter
    @Override
    public void setId(String id) {
        this.id = new Id(id, OBIBConnectorHelper.getClinicPersonnelId());
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
