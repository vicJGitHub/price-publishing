package com.hywa.pricepublish.dao.mapper;

import com.hywa.pricepublish.dao.entity.ProductRecentUse;
import com.hywa.pricepublish.dao.entity.ProductRecentUseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductRecentUseMapper {
    int countByExample(ProductRecentUseExample example);

    int deleteByExample(ProductRecentUseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductRecentUse record);

    int insertSelective(ProductRecentUse record);

    List<ProductRecentUse> selectByExample(ProductRecentUseExample example);

    ProductRecentUse selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductRecentUse record, @Param("example") ProductRecentUseExample example);

    int updateByExample(@Param("record") ProductRecentUse record, @Param("example") ProductRecentUseExample example);

    int updateByPrimaryKeySelective(ProductRecentUse record);

    int updateByPrimaryKey(ProductRecentUse record);

    void insertBatch(List<ProductRecentUse> productRecentUses);

    void deleteByProductId(String productId);
}