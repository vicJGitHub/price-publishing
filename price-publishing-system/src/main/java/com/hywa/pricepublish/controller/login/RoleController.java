package com.hywa.pricepublish.controller.login;

import static com.hywa.pricepublish.common.enums.CommonEnum.ILLEGAL_PARAMETER_ERROR;
import static com.hywa.pricepublish.common.enums.CommonEnum.SUCCESS;

import com.hywa.pricepublish.common.exception.GlobalException;
import com.hywa.pricepublish.common.utils.StringUtils;
import com.hywa.pricepublish.dao.entity.Role;
import com.hywa.pricepublish.representation.ResponseBase;
import com.hywa.pricepublish.representation.RoleRep;
import com.hywa.pricepublish.representation.RoleReps;
import com.hywa.pricepublish.service.login.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<ResponseBase> save(@Validated @RequestBody RoleRep roleRep) {
        roleService.save(roleRep);
        return new ResponseEntity<>(new ResponseBase<>().success(), HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<ResponseBase> update(@Validated @RequestBody RoleRep roleRep) {
        roleService.update(roleRep);
        return new ResponseEntity<>(new ResponseBase<>().success(), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseBase> delete(@RequestParam String roleId) {
        if (!StringUtils.isEmpty(roleId)) {
            try {
                roleService.delete(roleId);
            } catch (Exception e) {
                log.error(e.getMessage());
                return new ResponseEntity<>(new ResponseBase<>().success(), HttpStatus.OK);
            }
        } else {
            throw new GlobalException(ILLEGAL_PARAMETER_ERROR.getIndex(),
                    ILLEGAL_PARAMETER_ERROR.getValue() + ",id为空");
        }
        return new ResponseEntity<>(new ResponseBase<>().success(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<ResponseBase> get(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {

        RoleReps roleReps = roleService.selectAll(pageNum, pageSize,name);
        ResponseBase<RoleReps> responseBase = new ResponseBase<>();
        responseBase.success();
        responseBase.setRetBody(roleReps);
        return new ResponseEntity<>(responseBase, HttpStatus.OK);
    }

    @RequestMapping(value = "/getRoles", method = RequestMethod.GET)
    public ResponseEntity<ResponseBase> getUserRoles(
            @RequestParam String userId,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {

        RoleReps roleReps = roleService.selectRolesByUserId(pageNum, pageSize, userId);
        ResponseBase<RoleReps> responseBase = new ResponseBase<>();
        responseBase.success();
        responseBase.setRetBody(roleReps);
        return new ResponseEntity<>(responseBase, HttpStatus.OK);
    }
}
