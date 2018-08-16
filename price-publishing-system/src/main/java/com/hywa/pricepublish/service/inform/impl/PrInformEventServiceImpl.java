package com.hywa.pricepublish.service.inform.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hywa.pricepublish.common.enums.CommonEnum;
import com.hywa.pricepublish.common.exception.GlobalException;
import com.hywa.pricepublish.common.utils.IDUtils;
import com.hywa.pricepublish.common.utils.UUIDUtils;
import com.hywa.pricepublish.dao.entity.PrInformEvent;
import com.hywa.pricepublish.dao.entity.PrInformEventExample;
import com.hywa.pricepublish.dao.entity.PrInformer;
import com.hywa.pricepublish.dao.mapper.PrInformEventMapper;
import com.hywa.pricepublish.dao.mapper.PrInformerMapper;
import com.hywa.pricepublish.representation.PrInformRep;
import com.hywa.pricepublish.representation.PrInformReps;
import com.hywa.pricepublish.service.inform.PrInformEventService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PrInformEventServiceImpl implements PrInformEventService {

    @Autowired
    PrInformEventMapper prInformEventMapper;

    @Autowired
    PrInformerMapper prInformerMapper;

    @Override
    public PrInformRep save(PrInformRep prInformRep) {
        String prInformEventId = UUIDUtils.randomUUID();
        String prInformerId = UUIDUtils.randomUUID();
        prInformRep.setId(prInformEventId);
        prInformRep.setCode(codeIsEmpty());
        prInformRep.setSearchCode(IDUtils.getSearchId());
        prInformRep.setInformerId(prInformerId);
        prInformRep.setState((short)0);
        prInformRep.setEvaluate((short)0);

        PrInformEvent prInformEvent = new PrInformEvent();
        BeanUtils.copyProperties(prInformRep, prInformEvent);
        int prInformEventInsertCount = prInformEventMapper.insert(prInformEvent);
        PrInformer prInformer = new PrInformer();
        BeanUtils.copyProperties(prInformRep, prInformer, "id","name");
        prInformer.setId(prInformerId);
        prInformer.setName(prInformRep.getInformerName());
        int prInformerInsertCount = prInformerMapper.insert(prInformer);
        if (prInformEventInsertCount + prInformerInsertCount < 2) {
            throw new GlobalException(CommonEnum.FAILURE.getIndex(), CommonEnum.FAILURE.getValue());
        }
        return prInformRep;
    }

    @Override
    public void update(PrInformRep prInformRep) {
        PrInformEvent prInformEvent = new PrInformEvent();
        BeanUtils.copyProperties(prInformRep, prInformEvent);
        int prInformEventInsertCount = prInformEventMapper.updateByPrimaryKeySelective(prInformEvent);
        PrInformer prInformer = new PrInformer();
        BeanUtils.copyProperties(prInformRep, prInformer, "name");
        prInformer.setName(prInformRep.getInformerName());
        prInformer.setId(prInformRep.getInformerId());
        int prInformerInsertCount = prInformerMapper.updateByPrimaryKeySelective(prInformer);
        if (prInformEventInsertCount + prInformerInsertCount < 2) {
            throw new GlobalException(CommonEnum.FAILURE.getIndex(), CommonEnum.FAILURE.getValue());
        }
    }

    @Override
    public PrInformReps findAll(int pageNum, int pageSize, PrInformRep prInformRep) {
        PageHelper.startPage(pageNum,pageSize,true);
        List<PrInformRep> list = prInformEventMapper.findAll(prInformRep);
        return new PrInformReps(new PageInfo<>(list).getTotal(),list);
    }

    @Override
    public PrInformRep findById(String id) {
        return  prInformEventMapper.findById(id);
    }

    @Override
    public Long codeIsEmpty() {
        Long firstCode = Long.parseLong(new SimpleDateFormat("yyyyMMdd").format(new Date()) + "00001");
        List<PrInformEvent> prInformEvents = prInformEventMapper.selectByExample(new PrInformEventExample());
        if (CollectionUtils.isEmpty(prInformEvents)) {
            return firstCode;
        }
        List<Long> collect = prInformEvents.stream().map(PrInformEvent::getCode).collect(Collectors.toList());
        collect.sort(Comparator.reverseOrder());
        return collect.contains(firstCode) ? collect.get(0) + 1 : firstCode;
    }
}

