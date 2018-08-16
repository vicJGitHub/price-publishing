package com.hywa.pricepublish.service.collect.impl;

import static com.hywa.pricepublish.common.enums.CommonEnum.UNIQUE_CHECK_ERROR;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hywa.pricepublish.common.ConstantPool;
import com.hywa.pricepublish.common.exception.GlobalException;
import com.hywa.pricepublish.common.utils.UUIDUtils;
import com.hywa.pricepublish.dao.entity.CollectionTemplate;
import com.hywa.pricepublish.dao.entity.CollectionTemplateExample;
import com.hywa.pricepublish.dao.entity.CollectionTemplateExample.Criteria;
import com.hywa.pricepublish.dao.entity.Product;
import com.hywa.pricepublish.dao.entity.TemplateProduct;
import com.hywa.pricepublish.dao.mapper.CollectionTemplateMapper;
import com.hywa.pricepublish.dao.mapper.TemplateProductMapper;
import com.hywa.pricepublish.representation.CollectionTemplateRep;
import com.hywa.pricepublish.representation.CollectionTemplateReps;
import com.hywa.pricepublish.representation.ProductRep;
import com.hywa.pricepublish.service.collect.CollectionTemplateService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class CollectionTemplateServiceImpl implements CollectionTemplateService {

    @Autowired
    private CollectionTemplateMapper collectionTemplateMapper;

    @Autowired
    private TemplateProductMapper templateProductMapper;

    @Override
    public CollectionTemplateRep findByTemplateId(String templateId) {
        List<Product> products = templateProductMapper.selectProductByTemplateId(templateId);
        CollectionTemplateExample example = new CollectionTemplateExample();
        example.createCriteria()
                .andIdEqualTo(templateId)
                .andIsDelEqualTo(ConstantPool.NOT_DEL);
        CollectionTemplate collectionTemplate = collectionTemplateMapper.selectByExample(example)
                .get(0);

        CollectionTemplateRep collectionTemplateRep = new CollectionTemplateRep(collectionTemplate);
        List<ProductRep> productReps = new ArrayList<>();
        products.forEach(product -> {
            ProductRep productRep = new ProductRep(product);
            productReps.add(productRep);
        });
        collectionTemplateRep.setProductReps(productReps);

        return collectionTemplateRep;
    }

    @Override
    public CollectionTemplateReps findByUserId(String userId, Integer pageNum, Integer pageSize) {
        CollectionTemplateExample example = new CollectionTemplateExample();
        Criteria criteria = example.createCriteria();
        criteria.andIsDelEqualTo(ConstantPool.NOT_DEL);
        criteria.andCreateUserEqualTo(userId);
        example.setOrderByClause("update_time desc");

        PageHelper.startPage(pageNum, pageSize, true);
        List<CollectionTemplate> collectionTemplates = collectionTemplateMapper
                .selectByExample(example);
        PageInfo<CollectionTemplate> page = new PageInfo<>(collectionTemplates);

        List<CollectionTemplateRep> collectionTemplateRepList = new ArrayList<>();
        collectionTemplates.forEach(collectionTemplate -> {
            CollectionTemplateRep collectionTemplateRep = new CollectionTemplateRep(
                    collectionTemplate);
            collectionTemplateRepList.add(collectionTemplateRep);
        });
        return new CollectionTemplateReps(collectionTemplateRepList, page.getTotal());
    }

    @Override
    @Transactional
    public void save(CollectionTemplateRep templateRep, String userId) {
        List<CollectionTemplate> collectionTemplateList = collectionTemplateMapper
                .selectByName(templateRep.getTemplateName().trim());
        if (collectionTemplateList.size() > 0) {
            throw new GlobalException(UNIQUE_CHECK_ERROR.getIndex(),
                    "模版名称" + UNIQUE_CHECK_ERROR.getValue());
        }

        String templateId = UUIDUtils.randomUUID();
        CollectionTemplate collectionTemplate = new CollectionTemplate();
        collectionTemplate.setCreateTime(new Date());
        collectionTemplate.setUpdateTime(new Date());
        collectionTemplate.setCreateUser(userId);
        collectionTemplate.setId(templateId);
        collectionTemplate.setIsDel(ConstantPool.NOT_DEL);
        collectionTemplate.setName(templateRep.getTemplateName());
        collectionTemplateMapper.insert(collectionTemplate);

        addTemplateProduct(templateRep.getProductReps(), templateId);
    }

    private void addTemplateProduct(List<ProductRep> productReps, String templateId) {

        templateProductMapper.deleteByPrimaryKey(templateId);
        List<TemplateProduct> templateProducts = new ArrayList<>();
        productReps.forEach(productRep -> {
            TemplateProduct templateProduct = new TemplateProduct();
            templateProduct.setId(UUIDUtils.randomUUID());
            templateProduct.setProductId(productRep.getProductId());
            templateProduct.setTemplateId(templateId);
            templateProducts.add(templateProduct);
        });
        templateProductMapper.insertBatch(templateProducts);
    }

    @Override
    @Transactional
    public void update(CollectionTemplateRep templateRep) {
        CollectionTemplate collectionTemplate = new CollectionTemplate();
        collectionTemplate.setUpdateTime(new Date());
        collectionTemplate.setName(templateRep.getTemplateName());
        CollectionTemplateExample example = new CollectionTemplateExample();
        example.createCriteria()
                .andIdEqualTo(templateRep.getTemplateId());
        collectionTemplateMapper.updateByExample(collectionTemplate, example);

        addTemplateProduct(templateRep.getProductReps(), templateRep.getTemplateId());
    }

    @Override
    @Transactional
    public void delete(String templateId) {
        try {
            collectionTemplateMapper.logicDelete(templateId);
        } catch (Exception e) {
            log.error("删除失败 Exception" + e.getMessage());
            throw new RuntimeException("删除失败");
        }
    }
}
