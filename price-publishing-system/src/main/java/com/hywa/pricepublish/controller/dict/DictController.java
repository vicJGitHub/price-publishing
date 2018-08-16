package com.hywa.pricepublish.controller.dict;

import com.hywa.pricepublish.representation.DictRep;
import com.hywa.pricepublish.representation.DictReps;
import com.hywa.pricepublish.representation.ResponseBase;
import com.hywa.pricepublish.service.dict.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dict")
public class DictController {

    @Autowired
    public DictService dictService;

    @GetMapping()
    public ResponseEntity<ResponseBase> findNameByCode(
            @RequestParam("type") String type,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        ResponseBase<DictReps> repResponseBase = new ResponseBase<>();

        DictReps dictList = dictService.findDictListByDictType(type, pageNum, pageSize);
        repResponseBase.setRetBody(dictList);
        repResponseBase.success();
        return new ResponseEntity<>(repResponseBase, HttpStatus.OK);
    }

    @RequestMapping(value = "/findList", method = RequestMethod.GET)
    public ResponseEntity<ResponseBase> findDictId(@RequestParam String id,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        DictReps dictList = dictService.findDictListByDictTypeId(id, pageNum, pageSize);

        ResponseBase<DictReps> repResponseBase = new ResponseBase<>();
        repResponseBase.success();
        repResponseBase.setRetBody(dictList);
        return new ResponseEntity<>(repResponseBase, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseBase> add(
            @RequestParam String userId,
            @RequestParam short level,
            @RequestBody DictRep dictRep) {

        dictService.add(userId, dictRep, level);

        return new ResponseEntity<>(new ResponseBase().success(), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseBase> delete(@RequestParam String dictId) {

        dictService.delete(dictId);
        return new ResponseEntity<>(new ResponseBase().success(), HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<ResponseBase> update(
            @RequestParam String userId,
            @RequestBody DictRep dictRep) {

        dictService.update(userId, dictRep);
        return new ResponseEntity<>(new ResponseBase().success(), HttpStatus.OK);
    }
}
