package com.hywa.pricepublish.controller.collect;

import com.hywa.pricepublish.representation.ArtificialCollectionReps;
import com.hywa.pricepublish.representation.ResponseBase;
import com.hywa.pricepublish.service.collect.ArtificialCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collect/artificial")
public class ArtificialCollectionController {

    @Autowired
    private ArtificialCollectionService artificialcollectionService;

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResponseEntity<ResponseBase> deleteArtificialCollection(
            @RequestParam String id) {
        artificialcollectionService.delete(id);
        ResponseBase responseBase = new ResponseBase();
        return new ResponseEntity<>(responseBase, HttpStatus.OK);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponseEntity<ResponseBase> select(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        ArtificialCollectionReps artificialCollectionReps = artificialcollectionService
                .findList(pageNum, pageSize);
        ResponseBase<ArtificialCollectionReps> responseBase = new ResponseBase<>();
        responseBase.success();
        responseBase.setRetBody(artificialCollectionReps);
        return new ResponseEntity<>(responseBase, HttpStatus.OK);
    }
}
