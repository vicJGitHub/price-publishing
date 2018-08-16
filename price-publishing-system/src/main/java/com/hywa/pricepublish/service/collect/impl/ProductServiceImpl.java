package com.hywa.pricepublish.service.collect.impl;

import static com.hywa.pricepublish.common.enums.CommonEnum.ID_NOT_NULL;
import static com.hywa.pricepublish.common.enums.CommonEnum.PRODUCT_NAME_REPEATED;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hywa.pricepublish.common.ConstantPool;
import com.hywa.pricepublish.common.exception.GlobalException;
import com.hywa.pricepublish.common.utils.StringUtils;
import com.hywa.pricepublish.common.utils.UUIDUtils;
import com.hywa.pricepublish.dao.entity.Product;
import com.hywa.pricepublish.dao.entity.ProductRecentUse;
import com.hywa.pricepublish.dao.entity.ProductRecentUseExample;
import com.hywa.pricepublish.dao.mapper.ProductMapper;
import com.hywa.pricepublish.dao.mapper.ProductRecentUseMapper;
import com.hywa.pricepublish.event.ProductDeletedEvent;
import com.hywa.pricepublish.representation.ProductRep;
import com.hywa.pricepublish.representation.ProductReps;
import com.hywa.pricepublish.service.collect.ProductService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    ProductRecentUseMapper productRecentUseMapper;

    @Autowired
    ApplicationContext context;

    @Override
    public void save(ProductRep productRep, String userId) {

        List<Product> products = productMapper
                .selectByProductName(productRep.getProductName().trim());

        if (products.size() > 0) {
            throw new GlobalException(PRODUCT_NAME_REPEATED.getIndex(),
                    PRODUCT_NAME_REPEATED.getValue());
        }

        Product product = new Product();
        product.setCreateTime(new Date());
        product.setCreateUser(userId);
        product.setId(UUIDUtils.randomUUID());
        product.setIsDel(ConstantPool.NOT_DEL);
        product.setName(productRep.getProductName());
        product.setProductBigTypeId(productRep.getBigTypeId());
        product.setProductTypeId(productRep.getTypeId());
        product.setSpecification(productRep.getSpecification());
        product.setUnit(productRep.getUnit());
        product.setEnable((short) 0);

        productMapper.insert(product);
    }

    @Override
    public ProductReps findByType(String typeId, String bigTypeId, String productName,
            Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, true);
        List<Product> products = productMapper.findByType(typeId, bigTypeId, productName);
        PageInfo<Product> pageInfo = new PageInfo<>(products);

        List<ProductRep> productReps = new ArrayList<>();
        products.forEach(product -> {
            ProductRep productRep = new ProductRep(product);
            productReps.add(productRep);
        });
        return new ProductReps(productReps, pageInfo.getTotal());
    }

    @Override
    @Transactional
    public void update(ProductRep productRep, String userId) {
        String productId = productRep.getProductId();
        if (StringUtils.isEmpty(productId)) {
            throw new GlobalException(ID_NOT_NULL.getIndex(), ID_NOT_NULL.getValue());
        }

        Product product = new Product();
        product.setId(productId);
        product.setUpdateTime(new Date());
        product.setUpdateUser(userId);
        product.setId(productId);
        product.setName(productRep.getProductName());
        product.setProductBigTypeId(productRep.getBigTypeId());
        product.setProductTypeId(productRep.getTypeId());
        product.setSpecification(productRep.getSpecification());
        product.setUnit(productRep.getUnit());

        productMapper.updateByPrimaryKey(product);
    }

    @Override
    public ProductReps findRecentUse(String userId) {
        ProductRecentUseExample example = new ProductRecentUseExample();
        example.createCriteria()
                .andUserIdEqualTo(userId);
        List<ProductRecentUse> productRecentUses = productRecentUseMapper.selectByExample(example);
        if (productRecentUses.size() == 0) {
            return null;
        } else {
            List<ProductRep> productReps = new ArrayList<>();
            productRecentUses.forEach(productRecentUse -> {
                Product product = productMapper.findById(productRecentUse.getProductId());
                productReps.add(new ProductRep(product));
            });
            return new ProductReps(productReps);
        }
    }

    @Override
    @Transactional
    public void delete(String productId) {
        //TODO 伪删除
        productMapper.deleteByPrimaryKey(productId);

        //删除最近使用的商品
        productRecentUseMapper.deleteByProductId(productId);

        //删除模版中引用的商品
        context.publishEvent(new ProductDeletedEvent(productId));

    }
}
