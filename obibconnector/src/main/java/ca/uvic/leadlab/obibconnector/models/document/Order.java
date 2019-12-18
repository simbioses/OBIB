package ca.uvic.leadlab.obibconnector.models.document;

import ca.uvic.leadlab.obibconnector.models.common.Id;
import ca.uvic.leadlab.obibconnector.utils.OBIBConnectorHelper;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Id> ids = new ArrayList<>();
    private String statusCode;

    public List<Id> getIds() {
        return ids;
    }

    public void setIds(List<Id> ids) {
        this.ids = ids;
    }

    public void setId(String id, String type) {
        if (this.ids == null) {
            this.ids = new ArrayList<>();
        }
        this.ids.add(new Id(id, type));
    }

    public void setId(String id) {
        this.setId(id, OBIBConnectorHelper.getClinicLocationId());
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
