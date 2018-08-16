package com.hywa.pricepublish.controller.collect;

import com.hywa.pricepublish.representation.CollectionTemplateRep;
import com.hywa.pricepublish.representation.CollectionTemplateReps;
import com.hywa.pricepublish.representation.ResponseBase;
import com.hywa.pricepublish.service.collect.CollectionTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collect/template")
public class CollectionTemplateController {

    @Autowired
    private CollectionTemplateService collectionTemplateService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseBase> createTemplate(
            @RequestBody CollectionTemplateRep templateRep,
            @RequestParam String userId) {
        collectionTemplateService.save(templateRep, userId);
        return new ResponseEntity<>(new ResponseBase().success(), HttpStatus.OK);
    }

    @RequestMapping(value = "/findList", method = RequestMethod.GET)
    public ResponseEntity<ResponseBase> findTemplates(@RequestParam String userId,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        CollectionTemplateReps templateReps = collectionTemplateService
                .findByUserId(userId, pageNum, pageSize);
        ResponseBase<CollectionTemplateReps> responseBase = new ResponseBase<>();
        responseBase.success();
        responseBase.setRetBody(templateReps);
        return new ResponseEntity<>(responseBase, HttpStatus.OK);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponseEntity<ResponseBase> selectTemplates(@RequestParam String templateId) {
        CollectionTemplateRep templateReps = collectionTemplateService.findByTemplateId(templateId);
        ResponseBase<CollectionTemplateRep> responseBase = new ResponseBase<>();
        responseBase.success();
        responseBase.setRetBody(templateReps);
        return new ResponseEntity<>(responseBase, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<ResponseBase> updateTemplate(
            @RequestBody CollectionTemplateRep templateRep) {
        collectionTemplateService.update(templateRep);
        return new ResponseEntity<>(new ResponseBase().success(), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResponseEntity<ResponseBase> deleteTemplate(@RequestParam String templateId) {
        collectionTemplateService.delete(templateId);
        ResponseBase responseBase = new ResponseBase();
        responseBase.success();
        return new ResponseEntity<>(responseBase, HttpStatus.OK);
    }
}
