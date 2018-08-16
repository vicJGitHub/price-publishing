package com.hywa.pricepublish.service.dict.impl;

import com.hywa.pricepublish.dao.mapper.DictTypeMapper;
import com.hywa.pricepublish.service.dict.DictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DictTypeServiceImpl implements DictTypeService {

    @Autowired
    DictTypeMapper dictTypeMapper;

    @Override
    public String findDictTypeIdByCode(String code) {
        return dictTypeMapper.selectIdByCode(code);
    }
}
