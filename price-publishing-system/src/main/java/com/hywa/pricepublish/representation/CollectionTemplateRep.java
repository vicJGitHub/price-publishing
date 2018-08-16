package com.hywa.pricepublish.representation;

import com.hywa.pricepublish.common.utils.DateUtils;
import com.hywa.pricepublish.dao.entity.CollectionTemplate;
import java.util.List;
import java.util.Objects;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CollectionTemplateRep {

    private String templateId;
    private String templateName;
    private String updateTime;
    private List<ProductRep> productReps;

    public CollectionTemplateRep(CollectionTemplate collectionTemplate) {
        this.setTemplateId(collectionTemplate.getId());
        this.setTemplateName(collectionTemplate.getName());
        this.setUpdateTime(
                DateUtils.formatDate(collectionTemplate.getUpdateTime(), DateUtils.DETAIL));
    }

    public CollectionTemplateRep(String templateId, String templateName, String updateTime) {
        this.setTemplateId(templateId);
        this.setTemplateName(templateName);
        this.setUpdateTime(updateTime);
    }
}
