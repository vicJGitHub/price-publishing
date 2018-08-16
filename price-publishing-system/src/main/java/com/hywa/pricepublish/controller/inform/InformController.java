package com.hywa.pricepublish.controller.inform;

import com.hywa.pricepublish.common.enums.CommonEnum;
import com.hywa.pricepublish.common.exception.GlobalException;
import com.hywa.pricepublish.common.utils.FileUtils;
import com.hywa.pricepublish.config.EnvProperties;
import com.hywa.pricepublish.config.interceptor.TokenManager;
import com.hywa.pricepublish.config.interceptor.TokenModel;
import com.hywa.pricepublish.representation.PrInformRep;
import com.hywa.pricepublish.representation.PrInformReps;
import com.hywa.pricepublish.representation.ResponseBase;
import com.hywa.pricepublish.service.collect.FileService;
import com.hywa.pricepublish.service.inform.PrInformEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.util.Date;

@RestController
@RequestMapping("inform")
public class InformController {

    @Autowired
    PrInformEventService prInformEventService;

    @Autowired
    TokenManager tokenManager;

    @Autowired
    EnvProperties envProperties;

    @Autowired
    FileService fileService;

    /**
     * 保存/修改
     */
    @PostMapping
    public ResponseEntity saveOrUpdate(@RequestBody @Valid PrInformRep prInformRep, HttpServletRequest req) {
        if (StringUtils.isEmpty(prInformRep.getId())) {
            prInformRep.setCreateUser(getUserId(req));
            prInformRep.setCreateTime(new Date());
            prInformRep = prInformEventService.save(prInformRep);
        } else {
            prInformRep.setUpdateUser(getUserId(req));
            prInformRep.setUpdateTime(new Date());
            prInformEventService.update(prInformRep);
        }
        ResponseBase responseBase = new ResponseBase();
        responseBase.setRetBody(prInformRep);
        return new ResponseEntity(responseBase.success(), HttpStatus.OK);
    }

    /**
     * 提交证据
     */
    @RequestMapping(value = "/upload")
    @Transactional
    public ResponseEntity upload(@RequestParam("id") String id, @RequestParam("file") MultipartFile file, HttpServletRequest req) {
        if (new File(envProperties.getEvidence() + id + "\\" + file.getOriginalFilename()).exists()) {
            throw new GlobalException(CommonEnum.FILE_REPEATED.getIndex(), CommonEnum.FILE_REPEATED.getValue());
        }
        File mkdiers = new File(envProperties.getEvidence() + id + "\\");
        mkdiers.mkdirs();
        CommonEnum upload = FileUtils.upload(file, envProperties.getEvidence() + id + "\\");
        fileService.saveEvidenceFile(getUserId(req), envProperties.getEvidence() + id + "/", file.getOriginalFilename(), file.getSize(), id);
        ResponseBase responseBase = new ResponseBase();
        responseBase.setRetHead(upload.getIndex(), upload.getValue());
        return new ResponseEntity<>(responseBase, HttpStatus.OK);
    }

    /**
     * 按条目查询,prInforRep作为条件查询
     */
    @GetMapping("findAll")
    public ResponseEntity findAll(@RequestParam(defaultValue = "1") int pageNum,
                                  @RequestParam(defaultValue = "10") int pageSize,
                                  PrInformRep prInformRep) {
        PrInformReps prInformReps = prInformEventService.findAll(pageNum, pageSize, prInformRep);
        ResponseBase<Object> responseBase = new ResponseBase<>();
        responseBase.setRetBody(prInformReps);
        return new ResponseEntity(responseBase.success(), HttpStatus.OK);
    }

    /**
     * 根据id查询
     */
    @GetMapping("findById")
    public ResponseEntity findById(@RequestParam("id") String id) {
        ResponseBase<Object> responseBase = new ResponseBase<>();
        responseBase.setRetBody(prInformEventService.findById(id));
        return new ResponseEntity(responseBase.success(), HttpStatus.OK);
    }

    /**
     * 查询文件名称
     */
    @GetMapping("findFileInfoById")
    public ResponseEntity findFileInfoById(@RequestParam(defaultValue = "1") int pageNum,
                                           @RequestParam(defaultValue = "10") int pageSize,
                                           @RequestParam("id") String id) {
        ResponseBase<Object> responseBase = new ResponseBase<>();
        responseBase.setRetBody(fileService.findFileInfoByDescription(pageNum,pageSize,id));
        return new ResponseEntity(responseBase.success(), HttpStatus.OK);
    }

    /**
     * 删除文件
     */
    @DeleteMapping("delFile")
    public ResponseEntity delFile(@RequestBody com.hywa.pricepublish.dao.entity.File file) {
        boolean delete =false;
        if (null != file) {
             delete=new File(file.getPath() + file.getName()).delete();
        }
        ResponseBase responseBase = new ResponseBase();
        if (delete) {
            responseBase.setRetHead(CommonEnum.SUCCESS.getIndex(), CommonEnum.SUCCESS.getValue());
        } else {
            responseBase.setRetHead(CommonEnum.FAILURE.getIndex(), CommonEnum.FAILURE.getValue());
        }
        return new ResponseEntity(responseBase, HttpStatus.OK);
    }

    /**
     * 返回当前登录人
     */
    private String getUserId(HttpServletRequest req) {
        TokenModel accessToken = tokenManager.getToken(req.getHeader("accessToken"));
        return accessToken.getUserId().split("&")[1];
    }

}
