package com.hywa.pricepublish.controller;

import com.hywa.pricepublish.common.enums.CommonEnum;
import com.hywa.pricepublish.common.enums.Module;
import com.hywa.pricepublish.common.exception.GlobalException;
import com.hywa.pricepublish.config.interceptor.PermissionModule;
import com.hywa.pricepublish.representation.ResponseBase;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 此类为权限测试类
 */
@RestController
@RequestMapping("/test")
public class AuthTestControlller {

    /**
     * Module.ALL类型表示所有用户都能访问该接口
     */
    @PermissionModule(belong = {Module.ALL})
    @RequestMapping(value = "/sayhello", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
    public Map<String, Object> sayHello() {
        Map<String, Object> map = new HashMap<>();
        map.put("所有用户权限接口", "成功");
        return map;
    }

    /**
     * Module.TEST表示该接口只属于TEST所代表的模块， 如果用户没有TEST的权限，将无法访问该接口的内容
     */
    @PermissionModule(belong = {Module.TEST})
    @RequestMapping(value = "/everyhello", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
    public Map<String, Object> sayEveryHello() {
        Map<String, Object> map = new HashMap<>();
        map.put("测试权限接口", "成功");
        return map;
    }

    /**
     * Module.COLLECT表示该接口只属于COLLECT所代表的模块， 如果用户没有COLLECT的权限，将无法访问该接口的内容
     */
    @PermissionModule(belong = {Module.COLLECT})
    @RequestMapping(value = "/prehello", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
    public Map<String, Object> sayPreHello() {
        Map<String, Object> map = new HashMap<>();
        map.put("采集模块权限接口", "成功");
        return map;
    }

    @GetMapping(value = "/hello")
    public ResponseEntity hello(@RequestParam int a) {
        if (a == 1) {
            throw new GlobalException((short) 1, "测试全局异常");
        }
        ResponseBase responseBase = new ResponseBase();
        responseBase.setRetHead(CommonEnum.SUCCESS.getIndex(), CommonEnum.SUCCESS.getValue());
        return new ResponseEntity<>(responseBase, HttpStatus.OK);
    }

}
