package com.hywa.pricepublish.dao.mapper;

import com.hywa.pricepublish.dao.entity.Product;
import com.hywa.pricepublish.dao.entity.TemplateProduct;
import com.hywa.pricepublish.dao.entity.TemplateProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TemplateProductMapper {
    int countByExample(TemplateProductExample example);

    int deleteByExample(TemplateProductExample example);

    int deleteByPrimaryKey(String id);

    int insert(TemplateProduct record);

    int insertSelective(TemplateProduct record);

    List<TemplateProduct> selectByExample(TemplateProductExample example);

    TemplateProduct selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TemplateProduct record, @Param("example") TemplateProductExample example);

    int updateByExample(@Param("record") TemplateProduct record, @Param("example") TemplateProductExample example);

    int updateByPrimaryKeySelective(TemplateProduct record);

    int updateByPrimaryKey(TemplateProduct record);

    List<Product> selectProductByTemplateId(String templateId);

    void insertBatch(List<TemplateProduct> templateProducts);
}