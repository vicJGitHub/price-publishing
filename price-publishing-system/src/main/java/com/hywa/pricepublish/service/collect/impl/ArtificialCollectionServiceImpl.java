package com.hywa.pricepublish.service.collect.impl;

import static com.hywa.pricepublish.common.enums.CommonEnum.DATE_TIME_FORMAT_ERR;
import static com.hywa.pricepublish.common.enums.CommonEnum.FILE_CONTENT_NOT_NULL;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hywa.pricepublish.common.exception.GlobalException;
import com.hywa.pricepublish.common.utils.DateUtils;
import com.hywa.pricepublish.common.utils.UUIDUtils;
import com.hywa.pricepublish.dao.entity.ArtificialCollection;
import com.hywa.pricepublish.dao.mapper.ArtificialCollectionMapper;
import com.hywa.pricepublish.representation.ArtificialCollectionRep;
import com.hywa.pricepublish.representation.ArtificialCollectionReps;
import com.hywa.pricepublish.service.collect.ArtificialCollectionService;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ArtificialCollectionServiceImpl implements ArtificialCollectionService {

    @Autowired
    ArtificialCollectionMapper artificialCollectionMapper;

    @Override
    public void save(List<Map<String, String>> maps, String userId) {
        List<ArtificialCollection> artificialCollections = fillingMap(maps, userId);
        artificialCollectionMapper.insertBatch(artificialCollections);
    }

    private List<ArtificialCollection> fillingMap(List<Map<String, String>> list,
            String createUser) {
        List<ArtificialCollection> artificialCollections = new ArrayList<>(list.size());

        for (Map<String, String> map : list) {
            ArtificialCollection art = new ArtificialCollection();
            art.setId(UUIDUtils.randomUUID());
            art.setCreateTime(new Date());
            art.setCreateUser(createUser);
            if (!StringUtils.isEmpty(getValue(map, "采集时间"))) {
                try {
                    art.setCollctionTime(
                            DateUtils
                                    .stringToDate(getValue(map, "采集时间"), DateUtils.DEFAULT_FORMAT));
                } catch (ParseException e) {
                    throw new GlobalException(DATE_TIME_FORMAT_ERR.getIndex(),
                            DATE_TIME_FORMAT_ERR.getValue());
                }
            } else {
                art.setCollctionTime(new Date());
            }
            art.setDataOrigion("EXCEL人工导入");
            art.setMarket(getValue(map, "市场"));
            art.setName(getValue(map, "产品名字"));
            art.setPrice(new BigDecimal(getValue(map, "价格")).setScale(5, BigDecimal.ROUND_HALF_UP));
            art.setIsDel((short) 0);
            art.setSpecification(getValue(map, "规格"));
            art.setUnit(getValue(map, "单位"));
            art.setRegion(getValue(map, "采集区域"));
            art.setPriceType(getValue(map, "价格类型"));
            artificialCollections.add(art);
        }
        return artificialCollections;
    }

    private String getValue(Map<String, String> map, String key) {
        String value;
        try {
            value = StringUtils.isEmpty(map.get(key)) ? "" : map.get(key);
        } catch (Exception e) {
            value = "";
        }
        return value;
    }

    @Override
    public void delete(String id) {
        artificialCollectionMapper.deleteByPrimaryKey(id);

    }

    @Override
    public ArtificialCollectionReps findList(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize, true);
        List<ArtificialCollection> artificialCollections = artificialCollectionMapper
                .selectArtificialCollections();
        PageInfo<ArtificialCollection> pageInfo = new PageInfo<>(artificialCollections);

        List<ArtificialCollectionRep> artificialCollectionReps = new ArrayList<>();
        for (ArtificialCollection artificialCollection : artificialCollections) {
            artificialCollectionReps.add(new ArtificialCollectionRep(artificialCollection));
        }

        return new ArtificialCollectionReps(pageInfo.getTotal(), artificialCollectionReps);
    }
}
