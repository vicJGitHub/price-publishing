package com.hywa.pricepublish.service.dict.impl;

import static com.hywa.pricepublish.common.enums.CommonEnum.TYPE_REFERENCED;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hywa.pricepublish.common.ConstantPool;
import com.hywa.pricepublish.common.exception.GlobalException;
import com.hywa.pricepublish.common.utils.UUIDUtils;
import com.hywa.pricepublish.dao.entity.Dict;
import com.hywa.pricepublish.dao.entity.DictType;
import com.hywa.pricepublish.dao.mapper.DictMapper;
import com.hywa.pricepublish.dao.mapper.DictTypeMapper;
import com.hywa.pricepublish.representation.DictRep;
import com.hywa.pricepublish.representation.DictReps;
import com.hywa.pricepublish.service.dict.DictService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DictServiceImpl implements DictService {

    @Autowired
    public DictMapper dictMapper;

    @Autowired
    public DictTypeMapper dictTypeMapper;

    @Override
    @Transactional
    public void add(String userId, DictRep dictRep, Short level) {
        Dict record = new Dict();
        record.setId(UUIDUtils.randomUUID());
        record.setName(dictRep.getName());
        record.setCode(dictRep.getCode());
        record.setSelectUrl(dictRep.getSelectedUrl());
        record.setIsDel(ConstantPool.NOT_DEL);
        record.setUnSelectUrl(dictRep.getUnSelectedUrl());
        record.setDictTypeId(dictRep.getDictTypeId());
        dictMapper.insert(record);

        if (level == ConstantPool.HAVE_SUBCLASS) {
            DictType dictType = new DictType();
            dictType.setId(record.getId());
            dictType.setName(dictRep.getName());
            dictTypeMapper.insert(dictType);
        }

    }

    @Override
    public DictReps findDictListByDictType(String dictType, int pageNum, int pageSize) {
        String dictTypeId = dictTypeMapper.selectIdByCode(dictType);
        return findDictListByDictTypeId(dictTypeId, pageNum, pageSize);
    }

    @Override
    public DictReps findDictListByDictTypeId(String dictTypeId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize, true);
        List<Dict> dictList = dictMapper.selectByDictType(dictTypeId);
        PageInfo<Dict> pageInfo = new PageInfo<>(dictList);

        List<DictRep> dictRepList = new ArrayList<>();
        dictList.forEach(dict -> {
            DictRep dictRep = new DictRep(dict.getId(), dict.getCode(), dict.getName(),
                    dictTypeId, dict.getSelectUrl(), dict.getUnSelectUrl());
            dictRepList.add(dictRep);
        });
        return new DictReps(pageInfo.getTotal(), dictRepList);
    }

    @Override
    public void update(String userId, DictRep dictRep) {
        Dict record = new Dict();
        record.setId(dictRep.getId());
        record.setName(dictRep.getName());
        record.setCode(dictRep.getCode());
        record.setSelectUrl(dictRep.getSelectedUrl());
        record.setUnSelectUrl(dictRep.getUnSelectedUrl());
        record.setDictTypeId(dictRep.getDictTypeId());
        dictMapper.updateByPrimaryKey(record);
    }

    @Override
    @Transactional
    public void delete(String dictId) {
        List<Dict> dictList = dictMapper.selectByDictType(dictId);
        if (dictList.size() > 0) {
            throw new GlobalException(TYPE_REFERENCED.getIndex(), TYPE_REFERENCED.getValue());
        }
        dictMapper.deleteByPrimaryKey(dictId);
        dictTypeMapper.deleteByPrimaryKey(dictId);
    }
}
