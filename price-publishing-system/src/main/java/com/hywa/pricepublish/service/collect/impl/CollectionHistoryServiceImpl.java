package com.hywa.pricepublish.service.collect.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hywa.pricepublish.common.ConstantPool;
import com.hywa.pricepublish.common.utils.DateUtils;
import com.hywa.pricepublish.dao.entity.CollectionHistory;
import com.hywa.pricepublish.dao.entity.CollectionHistoryExample;
import com.hywa.pricepublish.dao.mapper.CollectionHistoryMapper;
import com.hywa.pricepublish.representation.CollectionHistoryRep;
import com.hywa.pricepublish.representation.CollectionHistoryReps;
import com.hywa.pricepublish.service.collect.CollectionHistoryService;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CollectionHistoryServiceImpl implements CollectionHistoryService {

    @Autowired
    private CollectionHistoryMapper collectionHistoryMapper;


    @Override
    @Transactional
    public void save(String marketName, String historyId, String dateTime, String userId) {
        CollectionHistory collectionHistory = new CollectionHistory();
        collectionHistory.setId(historyId);
        collectionHistory.setIsDel(ConstantPool.NOT_DEL);
        collectionHistory.setMarketName(marketName);
        collectionHistory.setUserId(userId);
        collectionHistory.setCreateTime(new Date());
        try {
            collectionHistory
                    .setCollectionTime(DateUtils.stringToDate(dateTime, DateUtils.DEFAULT_FORMAT));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        collectionHistoryMapper.insert(collectionHistory);
    }

    @Override
    public CollectionHistoryReps collectHistory(String userId, Integer pageNum, Integer pageSize) {
        CollectionHistoryExample example = new CollectionHistoryExample();
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andIsDelEqualTo(ConstantPool.NOT_DEL);
        example.setOrderByClause("collection_time desc, create_time desc");

        PageHelper.startPage(pageNum, pageSize, true);
        List<CollectionHistory> collectionHistories = collectionHistoryMapper
                .selectByExample(example);
        PageInfo<CollectionHistory> pageInfo = new PageInfo<>(collectionHistories);

        List<CollectionHistoryRep> collectionHistoryReps = new ArrayList<>();
        collectionHistories.forEach(collectionHistory -> {
            CollectionHistoryRep collectionHistoryRep = new CollectionHistoryRep(collectionHistory);
            collectionHistoryReps.add(collectionHistoryRep);
        });
        return new CollectionHistoryReps(collectionHistoryReps, pageInfo.getTotal());
    }
}
