package com.hywa.pricepublish.common.utils;

import static com.hywa.pricepublish.common.enums.CommonEnum.COORDINATE_PARSE_ERROR;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hywa.pricepublish.common.ConstantPool;
import com.hywa.pricepublish.common.exception.GlobalException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReverseGeocodeUtils {

    public static Map<String, String> getAddress(String coordinate, String gdKey) {
        Map<String, String> params = new HashMap<>();
        params.put("key", gdKey);
        params.put("location", coordinate);
        String json = HttpUtils.get("http://restapi.amap.com/v3/geocode/regeo", params);

        JSONObject parse = JSON.parseObject(json);
        String status = (String) parse.get("status");
        if (status.equals(ConstantPool.GD_STATUS_SUCCESS)) {
            JSONObject regeoCode = (JSONObject) parse.get("regeocode");
            String formatted_address = (String) regeoCode.get("formatted_address");
            JSONObject addressComponent = (JSONObject) regeoCode.get("addressComponent");

            Map<String, String> map = new HashMap<>();
            map.put("country", (String) addressComponent.get("country"));
            map.put("province", (String) addressComponent.get("province"));
            //当所在城市为北京、上海、天津、重庆四个直辖市时，该字段返回为空;当所在城市属于县级市的时候，此字段为空
            map.put("city", (String) addressComponent.get("city"));
            map.put("district", (String) addressComponent.get("district"));
            map.put("detailedProcess", formatted_address);
            return map;
        } else {
            log.error("坐标解析错误" + parse.get("info"));
            throw new GlobalException(
                    COORDINATE_PARSE_ERROR.getIndex(),
                    COORDINATE_PARSE_ERROR.getValue());
        }
    }
}
