package com.hywa.pricepublish.controller.login;


import com.hywa.pricepublish.common.enums.CommonEnum;
import com.hywa.pricepublish.common.exception.GlobalException;
import com.hywa.pricepublish.config.EnvProperties;
import com.hywa.pricepublish.config.interceptor.TokenManager;
import com.hywa.pricepublish.config.interceptor.TokenModel;
import com.hywa.pricepublish.representation.ResponseBase;
import com.hywa.pricepublish.representation.RoleReps;
import com.hywa.pricepublish.representation.UserRep;
import com.hywa.pricepublish.representation.UserReps;
import com.hywa.pricepublish.representation.ext.UserRepExt;
import com.hywa.pricepublish.service.login.UserRoleService;
import com.hywa.pricepublish.service.login.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    private static final String MOBILE = "mobile";
    private static final String PC = "pc";
    @Autowired
    EnvProperties envProperties;

    @Autowired
    TokenManager tokenManager;

    @Autowired
    private UserService userService;

    @Autowired
    UserRoleService userRoleService;

    @GetMapping(value = "/login")
    public ResponseEntity<ResponseBase> login(
            @RequestParam String userName,
            @RequestParam String password,
            HttpServletRequest req) {
        ResponseBase<UserRep> userRepResponseBase = getUserRepResponseBase(
                userName, password, req, MOBILE);
        return new ResponseEntity<>(userRepResponseBase, HttpStatus.OK);
    }

    @GetMapping(value = "/pcLogin")
    public ResponseEntity<ResponseBase> pcLogin(
            @RequestParam String userName,
            @RequestParam(name="pwd") String password,
            HttpServletRequest req) {
        ResponseBase<UserRep> userRepResponseBase = getUserRepResponseBase(userName, password, req,
                PC);
        return new ResponseEntity<>(userRepResponseBase, HttpStatus.OK);
    }

    private ResponseBase<UserRep> getUserRepResponseBase(String userName,
            String password, HttpServletRequest req, String equipment) {
        ResponseBase<UserRep> userRepResponseBase = new ResponseBase<>();
        UserRep userRep = userService.userLogin(userName, password);
        TokenModel token = tokenManager.createToken(equipment + "&" + userRep.getUserId());
        req.getSession().setAttribute("userId", userRep.getUserId());
        userRep.setAccessToken(equipment + "&" + userRep.getUserId() + "_" + token.getToken());
        userRepResponseBase.success();
        userRepResponseBase.setRetBody(userRep);
        return userRepResponseBase;
    }

    @GetMapping(value = "/checkToken")
    public ResponseEntity<ResponseBase> tokenVerify(
            @RequestParam String userId,
            @RequestParam String token) {
        ResponseBase<UserRep> userRepResponseBase = checkUserToken(userId, token, MOBILE);
        return new ResponseEntity<>(userRepResponseBase, HttpStatus.OK);
    }

    @GetMapping(value = "/pcCheckToken")
    public ResponseEntity<ResponseBase> pcTokenVerify(
            @RequestParam String userId,
            @RequestParam String token) {
        ResponseBase<UserRep> userRepResponseBase = checkUserToken(userId, token, PC);
        return new ResponseEntity<>(userRepResponseBase, HttpStatus.OK);
    }

    private ResponseBase<UserRep> checkUserToken(String userId, String token, String equipment) {
        String[] split = token.split("_");
        boolean result = tokenManager
                .checkToken(new TokenModel(equipment + "&" + userId, split[1]));
        ResponseBase<UserRep> userRepResponseBase = new ResponseBase<>();
        if (result) {
            UserRep userRep = userService.findById(userId);
            userRep.setAccessToken(token);
            userRepResponseBase.setRetBody(userRep);
            userRepResponseBase.success();
        } else {
            userRepResponseBase.setRetHead(CommonEnum.RE_LOGIN.getIndex(), CommonEnum.RE_LOGIN.getValue());
        }
        return userRepResponseBase;
    }


    @PostMapping(value = "/add")
    public ResponseEntity<ResponseBase> registerUser(@Valid @RequestBody UserRepExt userRep,
            HttpServletRequest req) {
        TokenModel accessToken = tokenManager.getToken(req.getHeader("accessToken"));
        userRep.setCreateUser(accessToken.getUserId().split("&")[1]);
        userService.save(userRep);
        return new ResponseEntity<>(new ResponseBase().success(), HttpStatus.OK);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseBase> logout(@RequestParam(value = "userId") String userId) {
        tokenManager.deleteToken(MOBILE + "&" + userId);
        return new ResponseEntity<>(new ResponseBase().success(), HttpStatus.OK);

    }

    @RequestMapping(value = "/pcLogout", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseBase> pcLogout(@RequestParam(value = "userId") String userId) {
        tokenManager.deleteToken(PC + "&" + userId);
        return new ResponseEntity<>(new ResponseBase().success(), HttpStatus.OK);

    }

    @RequestMapping(value = "/updateRole", method = RequestMethod.PUT)
    public ResponseEntity<ResponseBase> updateUserRole(
            @RequestParam String userId,
            @RequestBody RoleReps roleReps) {
        userService.updateUserRole(userId, roleReps);
        return new ResponseEntity<>(new ResponseBase().success(), HttpStatus.OK);

    }

 /*   @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseBase> deleteUser(@RequestParam String userId,HttpServletRequest req) {
        checkUpdateInfo(userId, req);
        ResponseBase responseBase = new ResponseBase();
        responseBase.setRetHead(CommonEnum.DEL_FAILURE.getIndex(),CommonEnum.DEL_FAILURE.getValue());
        return userService.delete(userId)?
                new ResponseEntity<>(new ResponseBase().success(), HttpStatus.OK):
                new ResponseEntity<>(responseBase, HttpStatus.OK);

    }*/

   /* private void checkUpdateInfo(@RequestParam String userId, HttpServletRequest req) {
        if(tokenManager.getToken(req.getHeader("accessToken")).getUserId().equals(userId)) {
            throw new GlobalException(CommonEnum.DEL_CURRENT_LOGIN_INFO.getIndex(), CommonEnum.DEL_CURRENT_LOGIN_INFO.getValue());
        }
        if(adminId.equals(userId)) {
            throw new GlobalException(CommonEnum.DEL_ADMIN.getIndex(), CommonEnum.DEL_ADMIN.getValue());
        }
    }*/

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<ResponseBase> findAllUser(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String workUnit,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        ResponseBase<UserReps> responseBase = new ResponseBase<>();
        responseBase.success()
                .setRetBody(userService.findUsers(pageNum, pageSize, name, region, workUnit));
        return new ResponseEntity<>(responseBase, HttpStatus.OK);

    }

    @RequestMapping(value = "/findAllById", method = RequestMethod.GET)
    public ResponseEntity< ResponseBase<Map<String, Object>>> findAllById(String id)
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ResponseBase<Map<String, Object>> responseBase = new ResponseBase<>();
        responseBase.success()
                .setRetBody(userService.findAllById(id));
        responseBase.success();
        return new ResponseEntity<>(responseBase, HttpStatus.OK);
    }
}
