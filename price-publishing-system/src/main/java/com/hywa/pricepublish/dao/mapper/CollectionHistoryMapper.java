package com.hywa.pricepublish.dao.mapper;

import com.hywa.pricepublish.dao.entity.CollectionHistory;
import com.hywa.pricepublish.dao.entity.CollectionHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectionHistoryMapper {
    int countByExample(CollectionHistoryExample example);

    int deleteByExample(CollectionHistoryExample example);

    int deleteByPrimaryKey(String id);

    int insert(CollectionHistory record);

    int insertSelective(CollectionHistory record);

    List<CollectionHistory> selectByExample(CollectionHistoryExample example);

    CollectionHistory selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CollectionHistory record, @Param("example") CollectionHistoryExample example);

    int updateByExample(@Param("record") CollectionHistory record, @Param("example") CollectionHistoryExample example);

    int updateByPrimaryKeySelective(CollectionHistory record);

    int updateByPrimaryKey(CollectionHistory record);
}