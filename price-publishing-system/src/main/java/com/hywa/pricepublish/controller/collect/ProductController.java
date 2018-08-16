package com.hywa.pricepublish.controller.collect;

import static com.hywa.pricepublish.common.enums.CommonEnum.PARAMETER_NOT_NULL;

import com.hywa.pricepublish.common.exception.GlobalException;
import com.hywa.pricepublish.common.utils.StringUtils;
import com.hywa.pricepublish.representation.ProductRep;
import com.hywa.pricepublish.representation.ProductReps;
import com.hywa.pricepublish.representation.ResponseBase;
import com.hywa.pricepublish.service.collect.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collect/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ResponseBase> find(
            @RequestParam(required = false) String typeId,
            @RequestParam(required = false) String bigTypeId,
            @RequestParam(required = false) String productName,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {

        ProductReps productReps = productService
                .findByType(typeId, bigTypeId, productName, pageNum, pageSize);
        ResponseBase<ProductReps> responseBase = new ResponseBase<>();
        responseBase.success();
        responseBase.setRetBody(productReps);
        return new ResponseEntity<>(responseBase, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<ResponseBase> update(
            @RequestBody ProductRep productRep,
            @RequestParam String userId) {

        if (StringUtils.isEmpty(userId)) {
            throw new GlobalException(PARAMETER_NOT_NULL.getIndex(),
                    PARAMETER_NOT_NULL.getValue() + ":" + "userId");
        }

        productService.update(productRep, userId);
        return new ResponseEntity<>(new ResponseBase().success(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResponseBase> save(
            @Validated @RequestBody ProductRep productRep,
            @RequestParam String userId) {

        if (StringUtils.isEmpty(userId)) {
            throw new GlobalException(PARAMETER_NOT_NULL.getIndex(),
                    PARAMETER_NOT_NULL.getValue() + ":" + "userId");
        }

        productService.save(productRep, userId);
        return new ResponseEntity<>(new ResponseBase().success(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<ResponseBase> delete(@RequestParam String productId) {
        if (StringUtils.isEmpty(productId)) {
            throw new GlobalException(PARAMETER_NOT_NULL.getIndex(),
                    PARAMETER_NOT_NULL.getValue() + ":" + "productId");
        }

        productService.delete(productId);
        return new ResponseEntity<>(new ResponseBase().success(), HttpStatus.OK);
    }

    @RequestMapping(value = "/findRecent", method = RequestMethod.GET)
    public ResponseEntity<ResponseBase> findRecent(@RequestParam String userId) {
        if (StringUtils.isEmpty(userId)) {
            throw new GlobalException(PARAMETER_NOT_NULL.getIndex(),
                    PARAMETER_NOT_NULL.getValue() + ":" + "userId");
        }

        ProductReps recentUse = productService.findRecentUse(userId);
        ResponseBase<ProductReps> responseBase = new ResponseBase<>();
        responseBase.success();
        responseBase.setRetBody(recentUse);
        return new ResponseEntity<>(responseBase, HttpStatus.OK);
    }
}
