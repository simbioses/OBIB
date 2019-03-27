package ca.uvic.leadlab.obibconnector.models.clinic;

import ca.uvic.leadlab.obibconnector.models.common.Address;
import ca.uvic.leadlab.obibconnector.models.common.Id;
import ca.uvic.leadlab.obibconnector.models.common.Template;
import ca.uvic.leadlab.obibconnector.models.provider.Provider;

import java.util.List;

public class Clinic {

    private String status;
    private List<Id> id;
    private String name;
    private Address address;
    private List<Provider> providers;
    private List<Template> templates;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Provider> getProviders() {
        return providers;
    }

    public void setProviders(List<Provider> providers) {
        this.providers = providers;
    }

    public List<Template> getTemplates() {
        return templates;
    }

    public void setTemplates(List<Template> templates) {
        this.templates = templates;
    }
}
