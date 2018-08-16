package com.hywa.pricepublish.dao.mapper;

import com.hywa.pricepublish.dao.entity.ProductTypeLevel;
import com.hywa.pricepublish.dao.entity.ProductTypeLevelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductTypeLevelMapper {
    int countByExample(ProductTypeLevelExample example);

    int deleteByExample(ProductTypeLevelExample example);

    int deleteByPrimaryKey(String id);

    int insert(ProductTypeLevel record);

    int insertSelective(ProductTypeLevel record);

    List<ProductTypeLevel> selectByExample(ProductTypeLevelExample example);

    ProductTypeLevel selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ProductTypeLevel record, @Param("example") ProductTypeLevelExample example);

    int updateByExample(@Param("record") ProductTypeLevel record, @Param("example") ProductTypeLevelExample example);

    int updateByPrimaryKeySelective(ProductTypeLevel record);

    int updateByPrimaryKey(ProductTypeLevel record);
}