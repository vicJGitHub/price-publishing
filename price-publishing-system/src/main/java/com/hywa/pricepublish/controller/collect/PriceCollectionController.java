package com.hywa.pricepublish.controller.collect;

import com.hywa.pricepublish.controller.collect.validate.priceCollectionValidate.AddPriceValidate;
import com.hywa.pricepublish.controller.collect.validate.priceCollectionValidate.FindCollectHistoryValidate;
import com.hywa.pricepublish.controller.collect.validate.priceCollectionValidate.FindCollectValidate;
import com.hywa.pricepublish.controller.collect.validate.priceCollectionValidate.UpdateCollectValidate;
import com.hywa.pricepublish.config.formValidation.JsonValidate;
import com.hywa.pricepublish.representation.CollectionHistoryReps;
import com.hywa.pricepublish.representation.PriceCollectStatisticsReps;
import com.hywa.pricepublish.representation.PriceCollectionReps;
import com.hywa.pricepublish.representation.PriceCrawlerDataReps;
import com.hywa.pricepublish.representation.ProductReps;
import com.hywa.pricepublish.representation.ResponseBase;
import com.hywa.pricepublish.service.collect.CollectionHistoryService;
import com.hywa.pricepublish.service.collect.PriceCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collect/price")
public class PriceCollectionController {

    @Autowired
    private PriceCollectionService priceCollectionService;

    @Autowired
    private CollectionHistoryService collectionHistoryService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @JsonValidate(AddPriceValidate.class)
    public ResponseEntity<ResponseBase> addPrice(
            @RequestParam String userId,
            @RequestParam String dateTime,
            @RequestParam String marketId,
            @RequestParam String marketName,
            @RequestBody ProductReps reps) {
        priceCollectionService.save(userId, dateTime, marketId, marketName, reps.getList());
        return new ResponseEntity<>(new ResponseBase<>().success(), HttpStatus.OK);
    }

    @RequestMapping(value = "/findCollectHistory", method = RequestMethod.GET)
    @JsonValidate(FindCollectHistoryValidate.class)
    public ResponseEntity<ResponseBase> collectHistory(
            @RequestParam String userId,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        CollectionHistoryReps collectionHistoryReps = collectionHistoryService
                .collectHistory(userId, pageNum, pageSize);
        ResponseBase<CollectionHistoryReps> repResponseBase = new ResponseBase<>();
        repResponseBase.success();
        repResponseBase.setRetBody(collectionHistoryReps);
        return new ResponseEntity<>(repResponseBase, HttpStatus.OK);
    }

    @RequestMapping(value = "/findCollectInfo", method = RequestMethod.GET)
    @JsonValidate(FindCollectValidate.class)
    public ResponseEntity<ResponseBase> findCollect(@RequestParam String collectHistoryId) {
        PriceCollectionReps priceCollectionReps = priceCollectionService
                .findCollect(collectHistoryId);
        ResponseBase<PriceCollectionReps> repResponseBase = new ResponseBase<>();
        repResponseBase.success();
        repResponseBase.setRetBody(priceCollectionReps);
        return new ResponseEntity<>(repResponseBase, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @JsonValidate(UpdateCollectValidate.class)
    public ResponseEntity<ResponseBase> updateCollect(
            @RequestParam String collectHistoryId,
            @RequestBody PriceCollectionReps priceCollectionReps) {
        priceCollectionService.updateCollects(collectHistoryId, priceCollectionReps);
        return new ResponseEntity<>(new ResponseBase<>().success(), HttpStatus.OK);
    }

    @RequestMapping(value = "/findCollects", method = RequestMethod.GET)
    public ResponseEntity<ResponseBase> findCollects(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String productNameOrMarketName,
            @RequestParam(required = false) String priceType,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime) {

        PriceCollectStatisticsReps priceCollectionReps = priceCollectionService.findCollect(
                pageNum, pageSize, productNameOrMarketName, priceType, startTime, endTime);

        ResponseBase<PriceCollectStatisticsReps> repResponseBase = new ResponseBase<>();
        repResponseBase.success();
        repResponseBase.setRetBody(priceCollectionReps);
        return new ResponseEntity<>(repResponseBase, HttpStatus.OK);
    }

    @RequestMapping(value = "/findCrawlerData", method = RequestMethod.GET)
    public ResponseEntity<ResponseBase> findCrawlerData(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String productNameOrMarketName,
            @RequestParam(required = false) String priceType,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime) {

        PriceCrawlerDataReps priceCrawlerDataReps = priceCollectionService.findCrawlerCollectData(
                pageNum, pageSize, productNameOrMarketName, priceType, startTime, endTime);

        ResponseBase<PriceCrawlerDataReps> repResponseBase = new ResponseBase<>();
        repResponseBase.success();
        repResponseBase.setRetBody(priceCrawlerDataReps);
        return new ResponseEntity<>(repResponseBase, HttpStatus.OK);
    }
}
