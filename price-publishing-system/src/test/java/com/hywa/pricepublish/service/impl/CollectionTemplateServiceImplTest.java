package com.hywa.pricepublish.service.impl;

import com.hywa.pricepublish.common.utils.UUIDUtils;
import com.hywa.pricepublish.representation.CollectionTemplateRep;
import com.hywa.pricepublish.representation.CollectionTemplateReps;
import com.hywa.pricepublish.representation.ProductRep;
import com.hywa.pricepublish.service.collect.CollectionTemplateService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CollectionTemplateServiceImplTest {
    CollectionTemplateRep templateRep;
    String userId = "1af634b62499449d86f271b8107c4ac1";

    @Autowired
    CollectionTemplateService collectionTemplateService;

    @Before
    public void initData() {
        String[] units = {"斤/元", "克/元", "条/元"};
        String[] names = {"西红柿", "八角", "鱼"};
        List<ProductRep> productReps = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ProductRep productRep = new ProductRep();
            productRep.setUnit(units[i]);
            productRep.setProductName(names[i]);
            productRep.setProductId(UUIDUtils.randomUUID());
            productReps.add(productRep);
        }

        templateRep = new CollectionTemplateRep();
        templateRep.setTemplateName("测试模版");
        templateRep.setProductReps(productReps);
    }

    @Test
    public void findByTemplateId() {
        CollectionTemplateReps collectionTemplateReps = collectionTemplateService.findByUserId(userId, 1, 1);
//        Assert.assertEquals(collectionTemplateReps.getList().get(0).getTemplateName(), templateRep.getTemplateName());
    }

    @Test
    public void save() {
//        collectionTemplateService.save(templateRep, userId);
    }
}