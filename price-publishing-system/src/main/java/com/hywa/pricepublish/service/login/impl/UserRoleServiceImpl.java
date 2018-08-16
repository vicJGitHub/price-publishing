package com.hywa.pricepublish.service.login.impl;


import com.hywa.pricepublish.dao.entity.UserRoleKey;
import com.hywa.pricepublish.dao.mapper.UserRoleMapper;
import com.hywa.pricepublish.service.login.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {


    @Autowired
    UserRoleMapper userRoleMapper;


    @Override
    public void insertBatch(List<UserRoleKey> userRoleKeys) {
        userRoleMapper.insertBatch(userRoleKeys);
    }
}
