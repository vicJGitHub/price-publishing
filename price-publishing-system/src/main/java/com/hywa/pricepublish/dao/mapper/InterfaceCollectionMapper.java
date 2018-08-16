package com.hywa.pricepublish.dao.mapper;

import com.hywa.pricepublish.dao.entity.InterfaceCollection;
import com.hywa.pricepublish.dao.entity.InterfaceCollectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InterfaceCollectionMapper {
    int countByExample(InterfaceCollectionExample example);

    int deleteByExample(InterfaceCollectionExample example);

    int deleteByPrimaryKey(String id);

    int insert(InterfaceCollection record);

    int insertSelective(InterfaceCollection record);

    List<InterfaceCollection> selectByExample(InterfaceCollectionExample example);

    InterfaceCollection selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") InterfaceCollection record, @Param("example") InterfaceCollectionExample example);

    int updateByExample(@Param("record") InterfaceCollection record, @Param("example") InterfaceCollectionExample example);

    int updateByPrimaryKeySelective(InterfaceCollection record);

    int updateByPrimaryKey(InterfaceCollection record);
}