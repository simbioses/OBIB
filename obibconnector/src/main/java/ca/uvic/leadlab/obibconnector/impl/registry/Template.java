package ca.uvic.leadlab.obibconnector.impl.registry;

import ca.uvic.leadlab.obibconnector.facades.registry.ITemplate;

public class Template implements ITemplate {

    private String templateId;
    private String templateName;

    public Template(ca.uvic.leadlab.obibconnector.models.common.Template template) {
        this.templateId = template.getTemplateId();
        this.templateName = template.getTemplateName();
    }

    @Override
    public String getTemplateId() {
        return templateId;
    }

    @Override
    public String getTemplateName() {
        return templateName;
    }
}
