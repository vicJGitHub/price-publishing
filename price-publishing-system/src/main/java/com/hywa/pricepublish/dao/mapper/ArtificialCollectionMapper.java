package com.hywa.pricepublish.dao.mapper;

import com.hywa.pricepublish.dao.entity.ArtificialCollection;
import com.hywa.pricepublish.dao.entity.ArtificialCollectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArtificialCollectionMapper {

    int countByExample(ArtificialCollectionExample example);

    int deleteByExample(ArtificialCollectionExample example);

    int deleteByPrimaryKey(String id);

    int insert(ArtificialCollection record);

    int insertSelective(ArtificialCollection record);

    List<ArtificialCollection> selectByExample(ArtificialCollectionExample example);

    ArtificialCollection selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ArtificialCollection record,
            @Param("example") ArtificialCollectionExample example);

    int updateByExample(@Param("record") ArtificialCollection record,
            @Param("example") ArtificialCollectionExample example);

    int updateByPrimaryKeySelective(ArtificialCollection record);

    int updateByPrimaryKey(ArtificialCollection record);

    List<ArtificialCollection> selectArtificialCollections();

    void insertBatch(List<ArtificialCollection> artificialCollections);
}