package com.hywa.pricepublish.controller.region;

import com.hywa.pricepublish.common.enums.CommonEnum;
import com.hywa.pricepublish.common.exception.GlobalException;
import com.hywa.pricepublish.common.utils.StringUtils;
import com.hywa.pricepublish.representation.ResponseBase;
import com.hywa.pricepublish.service.region.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("region")
public class RegionController {

    @Autowired
    RegionService regionService;

    @RequestMapping(value = "/province/index")
    public ResponseEntity<ResponseBase> provinceIndex( @RequestParam(name = "rows",defaultValue = "1000") int rows) {
        ResponseBase responseBase = new ResponseBase();
            responseBase.setRetBody(regionService.findProvince(rows));
            responseBase.success();
            return new ResponseEntity<>(responseBase, HttpStatus.OK);
    }
    @RequestMapping(value = "/city/index")
    public ResponseEntity<ResponseBase> cityIndex(@RequestParam(name = "id") String id, @RequestParam(name="rows",defaultValue = "1000") int rows) {
        ResponseBase responseBase = new ResponseBase();
            if(StringUtils.isEmpty(id)){
                throw new GlobalException(CommonEnum.PARAMETER_NOT_NULL.getIndex(),CommonEnum.PARAMETER_NOT_NULL.getValue());
            }
            responseBase.setRetBody(regionService.findCity(id, rows));
            responseBase.success();
            return new ResponseEntity<>(responseBase, HttpStatus.OK);
}
    @RequestMapping(value = "/county/index")
    public ResponseEntity<ResponseBase> countyIndex(@RequestParam(name = "id") String id, @RequestParam(name = "rows",defaultValue = "1000") int rows) {
        ResponseBase responseBase = new ResponseBase();
            if(StringUtils.isEmpty(id)){
                throw new GlobalException(CommonEnum.PARAMETER_NOT_NULL.getIndex(),CommonEnum.PARAMETER_NOT_NULL.getValue());
            }
            responseBase.setRetBody(regionService.findCounty(id, rows));
            responseBase.success();
            return new ResponseEntity<>(responseBase, HttpStatus.OK);
    }
}
