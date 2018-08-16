package com.hywa.pricepublish.dao.mapper;

import com.hywa.pricepublish.dao.entity.Params;
import com.hywa.pricepublish.dao.entity.ParamsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ParamsMapper {
    int countByExample(ParamsExample example);

    int deleteByExample(ParamsExample example);

    int deleteByPrimaryKey(String groupCode);

    int insert(Params record);

    int insertSelective(Params record);

    List<Params> selectByExample(ParamsExample example);

    Params selectByPrimaryKey(String groupCode);

    int updateByExampleSelective(@Param("record") Params record, @Param("example") ParamsExample example);

    int updateByExample(@Param("record") Params record, @Param("example") ParamsExample example);

    int updateByPrimaryKeySelective(Params record);

    int updateByPrimaryKey(Params record);
}