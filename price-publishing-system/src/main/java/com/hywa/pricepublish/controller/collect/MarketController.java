package com.hywa.pricepublish.controller.collect;

import static com.hywa.pricepublish.common.enums.CommonEnum.PARAMETER_NOT_NULL;

import com.hywa.pricepublish.common.exception.GlobalException;
import com.hywa.pricepublish.common.utils.StringUtils;
import com.hywa.pricepublish.representation.*;
import com.hywa.pricepublish.service.collect.MarketService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("collect/market")
public class MarketController {

    @Autowired
    private MarketService marketService;

    @RequestMapping(value = "/findMarkets", method = RequestMethod.GET)
    public ResponseEntity<ResponseBase> findMarketByType(
            @RequestParam(required = false) String typeId,
            @RequestParam(required = false) String regionId,
            @RequestParam(required = false) String marketName,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {

        MarketsReps markets = marketService
                .findMarkets(typeId, marketName, regionId, pageNum, pageSize);

        ResponseBase<MarketsReps> repResponseBase = new ResponseBase<>();
        repResponseBase.success();
        repResponseBase.setRetBody(markets);
        return new ResponseEntity<>(repResponseBase, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseBase> addMarket(
            @Valid @RequestBody MarketRep marketRep,
            @RequestParam String userId) {

        marketService.save(marketRep, userId);
        return new ResponseEntity<>(new ResponseBase<>().success(), HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<ResponseBase> updateMarket(
            @RequestBody MarketRep marketRep,
            @RequestParam String userId) {

        marketService.update(marketRep, userId);
        return new ResponseEntity<>(new ResponseBase<>().success(), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseBase> delete(@RequestParam String marketId) {
        if (StringUtils.isEmpty(marketId)) {
            throw new GlobalException(PARAMETER_NOT_NULL.getIndex(),
                    PARAMETER_NOT_NULL.getValue() + ":" + "marketId");
        }

        marketService.deleteById(marketId);
        return new ResponseEntity<>(new ResponseBase<>().success(), HttpStatus.OK);
    }

    @RequestMapping(value = "/findRecentUse", method = RequestMethod.GET)
    public ResponseEntity<ResponseBase> findRecentUse(@RequestParam String userId) {
        if (StringUtils.isEmpty(userId)) {
            throw new GlobalException(PARAMETER_NOT_NULL.getIndex(),
                    PARAMETER_NOT_NULL.getValue() + ":userId");
        }

        ResponseBase<MarketRecentUseReps> repResponseBase = new ResponseBase<>();
        MarketRecentUseReps marketRecentUse = marketService.findMarketRecentUse(userId);
        repResponseBase.success();
        repResponseBase.setRetBody(marketRecentUse);
        return new ResponseEntity<>(repResponseBase, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}