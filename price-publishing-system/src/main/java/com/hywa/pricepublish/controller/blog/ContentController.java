package com.hywa.pricepublish.controller.blog;

import com.hywa.pricepublish.common.ConstantPool;
import com.hywa.pricepublish.config.interceptor.TokenManager;
import com.hywa.pricepublish.config.interceptor.TokenModel;
import com.hywa.pricepublish.representation.PrContentInfoRep;
import com.hywa.pricepublish.representation.ResponseBase;
import com.hywa.pricepublish.service.blog.PrContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;


@RequestMapping("content")
@RestController
public class ContentController {

    @Autowired
    PrContentService prContentService;

    @Autowired
    TokenManager tokenManager;

    @PostMapping
    public ResponseEntity saveOrUpdate(@RequestBody @Valid PrContentInfoRep prContentInfoRep, HttpServletRequest req) {
        prContentInfoRep.setIsDel(ConstantPool.NOT_DEL);
        prContentInfoRep.setBrowsingNum(0);
        if (StringUtils.isEmpty(prContentInfoRep.getId())){
            prContentInfoRep.setCreateUser(getUserId(req));
            prContentInfoRep.setCreateTime(new Date());
            prContentService.save(prContentInfoRep);
        }else{
            prContentInfoRep.setUpdateUser(getUserId(req));
            prContentInfoRep.setUpdateTime(new Date());
            prContentService.update(prContentInfoRep);
        }
        ResponseBase responseBase = new ResponseBase();
        return new ResponseEntity(responseBase.success(), HttpStatus.OK);
    }

    @DeleteMapping
    //可只接受俩id
    public ResponseEntity del(@RequestBody PrContentInfoRep prContentInfoRep,HttpServletRequest req){
        prContentInfoRep.setUpdateTime(new Date());
        prContentInfoRep.setUpdateUser(getUserId(req));
        prContentInfoRep.setIsDel(ConstantPool.DEL);
        prContentService.update(prContentInfoRep);
        ResponseBase responseBase = new ResponseBase();
        return new ResponseEntity(responseBase.success(), HttpStatus.OK);
    }

    //留prContentInfoRep做条件查询用
    @GetMapping("findContentInfoAll")
    public ResponseEntity findArticleInfoAll(@RequestParam(defaultValue = "1") int pageNum,
                                             @RequestParam(defaultValue = "10") int pageSize,
                                             PrContentInfoRep prContentInfoRep){
        ResponseBase responseBase = new ResponseBase();
        responseBase.setRetBody(prContentService.findArticleInfoAll(pageNum,pageSize,prContentInfoRep));
        return new ResponseEntity(responseBase.success(), HttpStatus.OK);
    }

    @GetMapping("findContentById")
    public ResponseEntity findContentById(PrContentInfoRep prContentInfoRep){
        ResponseBase responseBase = new ResponseBase();
        responseBase.setRetBody(prContentService.findContentById(prContentInfoRep.getId()));
        return new ResponseEntity(responseBase.success(), HttpStatus.OK);
    }

    private String getUserId(HttpServletRequest req) {
        TokenModel accessToken = tokenManager.getToken(req.getHeader("accessToken"));
        return accessToken.getUserId().split("&")[1];
    }
}
