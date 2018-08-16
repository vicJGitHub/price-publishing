package com.hywa.pricepublish.dao.mapper;

import com.hywa.pricepublish.dao.entity.CollectionTemplate;
import com.hywa.pricepublish.dao.entity.CollectionTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectionTemplateMapper {
    int countByExample(CollectionTemplateExample example);

    int deleteByExample(CollectionTemplateExample example);

    int deleteByPrimaryKey(String id);

    int insert(CollectionTemplate record);

    int insertSelective(CollectionTemplate record);

    List<CollectionTemplate> selectByExample(CollectionTemplateExample example);

    CollectionTemplate selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CollectionTemplate record, @Param("example") CollectionTemplateExample example);

    int updateByExample(@Param("record") CollectionTemplate record, @Param("example") CollectionTemplateExample example);

    int updateByPrimaryKeySelective(CollectionTemplate record);

    int updateByPrimaryKey(CollectionTemplate record);

    void logicDelete(String templateId);

    List<CollectionTemplate> selectByName(String templateName);

    void deleteProduct(String productId);
}