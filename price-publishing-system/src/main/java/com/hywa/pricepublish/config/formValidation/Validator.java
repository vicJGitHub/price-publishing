package com.hywa.pricepublish.config.formValidation;

import com.hywa.pricepublish.representation.ResponseBase;
import org.springframework.http.ResponseEntity;

public abstract class Validator {

    public abstract ResponseEntity<ResponseBase> validate(Object... t);

}
