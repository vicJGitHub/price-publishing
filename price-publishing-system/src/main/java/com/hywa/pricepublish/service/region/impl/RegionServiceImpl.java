package com.hywa.pricepublish.service.region.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hywa.pricepublish.dao.entity.*;
import com.hywa.pricepublish.dao.mapper.CityMapper;
import com.hywa.pricepublish.dao.mapper.CountyMapper;
import com.hywa.pricepublish.dao.mapper.ProvinceMapper;
import com.hywa.pricepublish.representation.CityReps;
import com.hywa.pricepublish.representation.CountyReps;
import com.hywa.pricepublish.representation.ProvinceReps;
import com.hywa.pricepublish.service.region.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    ProvinceMapper provinceMapper;

    @Autowired
    CityMapper cityMapper;

    @Autowired
    CountyMapper countyMapper;

    @Override
    public ProvinceReps findProvince(int rows) {
        PageHelper.startPage(1, rows, true);
        List<Province> provinces = provinceMapper.selectByExample(new ProvinceExample());
        return new ProvinceReps(new PageInfo(provinces).getTotal(),provinces);
    }

    @Override
    public CityReps findCity(String id, int rows) {
        PageHelper.startPage(1, rows, true);
        CityExample cityExample = new CityExample();
        cityExample.createCriteria().andProvinceIdEqualTo(id);
        List<City> cities = cityMapper.selectByExample(cityExample);
        return new CityReps(new PageInfo(cities).getTotal(),cities);
    }

    @Override
    public CountyReps findCounty(String id, int rows) {
        PageHelper.startPage(1, rows, true);
        CountyExample countyExample = new CountyExample();
        countyExample.createCriteria().andCityIdEqualTo(id);
        List<County> counties = countyMapper.selectByExample(countyExample);
        return new CountyReps(new PageInfo(counties).getTotal(),counties);
    }
}
