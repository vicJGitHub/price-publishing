package com.hywa.pricepublish.controller.collect.validate.priceCollectionValidate;

import com.hywa.pricepublish.common.utils.StringUtils;
import com.hywa.pricepublish.config.formValidation.Validator;
import com.hywa.pricepublish.representation.ResponseBase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static com.hywa.pricepublish.common.enums.CommonEnum.PARAMETER_NOT_NULL;

public class AddPriceValidate extends Validator {
    @Override
    public ResponseEntity<ResponseBase> validate(Object... t) {
        if (StringUtils.isEmpty((String) t[0])) {
            ResponseBase responseBase = new ResponseBase();
            responseBase.setRetHead(PARAMETER_NOT_NULL.getIndex(),
                    PARAMETER_NOT_NULL.getValue() + ":userId");
            return new ResponseEntity<>(responseBase, HttpStatus.OK);
        }
        if (StringUtils.isEmpty((String) t[1])) {
            ResponseBase responseBase = new ResponseBase();
            responseBase.setRetHead(PARAMETER_NOT_NULL.getIndex(),
                    PARAMETER_NOT_NULL.getValue() + ":dateTime");
            return new ResponseEntity<>(responseBase, HttpStatus.OK);
        }
        if (StringUtils.isEmpty((String) t[2])) {
            ResponseBase responseBase = new ResponseBase();
            responseBase.setRetHead(PARAMETER_NOT_NULL.getIndex(),
                    PARAMETER_NOT_NULL.getValue() + ":marketId");
            return new ResponseEntity<>(responseBase, HttpStatus.OK);
        }
        if (StringUtils.isEmpty((String) t[3])) {
            ResponseBase responseBase = new ResponseBase();
            responseBase.setRetHead(PARAMETER_NOT_NULL.getIndex(),
                    PARAMETER_NOT_NULL.getValue() + ":marketName");
            return new ResponseEntity<>(responseBase, HttpStatus.OK);
        }
        return null;
    }
}
