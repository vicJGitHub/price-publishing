package com.hywa.pricepublish.service.blog.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hywa.pricepublish.common.ConstantPool;
import com.hywa.pricepublish.common.enums.CommonEnum;
import com.hywa.pricepublish.common.exception.GlobalException;
import com.hywa.pricepublish.common.utils.UUIDUtils;
import com.hywa.pricepublish.dao.entity.PrContent;
import com.hywa.pricepublish.dao.entity.PrContentInfo;
import com.hywa.pricepublish.dao.entity.PrContentInfoExample;
import com.hywa.pricepublish.dao.mapper.PrContentInfoMapper;
import com.hywa.pricepublish.dao.mapper.PrContentMapper;
import com.hywa.pricepublish.representation.PrContentInfoRep;
import com.hywa.pricepublish.representation.PrContentInfoReps;
import com.hywa.pricepublish.service.blog.PrContentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PrContentServiceImpl implements PrContentService {

    @Autowired
    PrContentMapper prContentMapper;

    @Autowired
    PrContentInfoMapper prContentInfoMapper;


    @Override
    @Transactional
    public void save(PrContentInfoRep prContentInfoRep) {
        //TODO 外键关联的主键应该是content中保存contentInfoId
        String contentId = UUIDUtils.randomUUID();
        String contentInfoId = UUIDUtils.randomUUID();
        PrContent prContent = new PrContent();
        prContent.setId(contentId);
        prContent.setContent(prContentInfoRep.getContent());
        prContent.setIsDel(prContentInfoRep.getIsDel());
        int prContentSaveCount =prContentMapper.insert(prContent);
        PrContentInfo prContentInfo = new PrContentInfo();
        BeanUtils.copyProperties(prContentInfoRep, prContentInfo, "id");
        prContentInfo.setId(contentInfoId);
        prContentInfo.setContentId(contentId);
        int prContentInfoSaveCount = prContentInfoMapper.insert(prContentInfo);
        if(prContentInfoSaveCount+prContentSaveCount<2){
            throw new GlobalException(CommonEnum.FAILURE.getIndex(),CommonEnum.FAILURE.getValue());
        }
    }

    @Override
    @Transactional
    public void update(PrContentInfoRep prContentInfoRep) {
        PrContent prContent = new PrContent();
        prContent.setId(prContentInfoRep.getContentId());
        prContent.setContent(prContentInfoRep.getContent());
        prContent.setIsDel(prContentInfoRep.getIsDel());
        int prContentSaveCount =prContentMapper.updateByPrimaryKeySelective(prContent);
        PrContentInfo prContentInfo = new PrContentInfo();
        BeanUtils.copyProperties(prContentInfoRep, prContentInfo);
        int prContentInfoSaveCount = prContentInfoMapper.updateByPrimaryKeySelective(prContentInfo);
        if(prContentInfoSaveCount+prContentSaveCount<2){
            throw new GlobalException(CommonEnum.FAILURE.getIndex(),CommonEnum.FAILURE.getValue());
        }
    }

    @Override
    public PrContentInfoReps findArticleInfoAll(int pageNum, int pageSize, PrContentInfoRep prContentInfoRep) {
        PageHelper.startPage(pageNum,pageSize,true);
        PrContentInfoExample prContentInfoExample = new PrContentInfoExample();
        //TODO 此处需预留条件查询
        PrContentInfoExample.Criteria criteria = prContentInfoExample.createCriteria();
        criteria.andIsDelEqualTo(ConstantPool.NOT_DEL);
        //是否首页推荐
        if(null!=prContentInfoRep.getIsRecommend()&&("0,1").contains(prContentInfoRep.getIsRecommend()+"")){
            criteria.andIsRecommendEqualTo(prContentInfoRep.getIsRecommend());
        }
        //是否首页显示
        if(null!=prContentInfoRep.getIsUserble()&&("0,1").contains(prContentInfoRep.getIsUserble()+"")){
            criteria.andIsUserbleEqualTo(prContentInfoRep.getIsUserble());
        }
        //排序
        prContentInfoExample.setOrderByClause("published_time,is_recommend desc");
        List<PrContentInfo> prContentInfos = prContentInfoMapper.selectByExample(prContentInfoExample);
        return new PrContentInfoReps(PageInfo.of(prContentInfos).getTotal(),prContentInfos);
    }

    @Override
    public PrContentInfoRep findContentById(String id) {
        return prContentInfoMapper.findContentById(id);
    }

}
