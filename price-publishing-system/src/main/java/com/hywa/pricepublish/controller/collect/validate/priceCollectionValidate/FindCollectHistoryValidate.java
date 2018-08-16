package com.hywa.pricepublish.controller.collect.validate.priceCollectionValidate;

import com.hywa.pricepublish.common.utils.StringUtils;
import com.hywa.pricepublish.config.formValidation.Validator;
import com.hywa.pricepublish.representation.ResponseBase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.hywa.pricepublish.common.enums.CommonEnum.PARAMETER_NOT_NULL;

public class FindCollectHistoryValidate extends Validator{
    @Override
    public ResponseEntity<ResponseBase> validate(Object... t) {
        if (StringUtils.isEmpty((String) t[0])) {
            ResponseBase<?> repResponseBase = new ResponseBase<>();
            repResponseBase.setRetHead(PARAMETER_NOT_NULL.getIndex(),
                    PARAMETER_NOT_NULL.getValue() + ":userId");
            return new ResponseEntity<>(repResponseBase, HttpStatus.OK);
        }
        return null;
    }
}
