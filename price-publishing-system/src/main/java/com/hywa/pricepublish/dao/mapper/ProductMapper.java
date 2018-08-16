package com.hywa.pricepublish.dao.mapper;

import com.hywa.pricepublish.dao.entity.Product;
import com.hywa.pricepublish.dao.entity.ProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper {

    int countByExample(ProductExample example);

    int deleteByExample(ProductExample example);

    int deleteByPrimaryKey(String id);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Product record,
            @Param("example") ProductExample example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    Product selectNameAndUnitById(String id);

    List<Product> findByType(
            @Param("typeId") String typeId,
            @Param("bigTypeId") String bigTypeId,
            @Param("productName") String productName);

    Product findById(String productId);

    List<Product> selectByProductName(String productName);
}