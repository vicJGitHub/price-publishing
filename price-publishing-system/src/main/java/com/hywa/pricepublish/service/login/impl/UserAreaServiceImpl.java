package com.hywa.pricepublish.service.login.impl;

import com.hywa.pricepublish.dao.entity.UserArea;
import com.hywa.pricepublish.dao.entity.UserAreaExample;
import com.hywa.pricepublish.dao.mapper.UserAreaMapper;
import com.hywa.pricepublish.service.login.UserAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAreaServiceImpl implements UserAreaService {

    @Autowired
    private UserAreaMapper userAreaMapper;

    @Override
    public String findRegionId(String userId) {
        UserAreaExample example = new UserAreaExample();
        example.createCriteria().andUserIdEqualTo(userId);
        UserArea userArea = userAreaMapper.selectByExample(example).get(0);
        return userArea.getRegionId();
    }
}
