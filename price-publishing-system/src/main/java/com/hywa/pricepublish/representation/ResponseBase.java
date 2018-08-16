package com.hywa.pricepublish.representation;

import java.io.Serializable;
import lombok.ToString;

import static com.hywa.pricepublish.common.enums.CommonEnum.SUCCESS;

@ToString
public class ResponseBase<T> implements Serializable {

    private static final long serialVersionUID = -4963266899668807475L;
    private ResponseHead retHead;
    private T retBody;

    public ResponseHead getRetHead() {
        return retHead;
    }

    public void setRetHead(ResponseHead retHead) {
        this.retHead = retHead;
    }

    public void setRetHead(Short code, String msg) {
        this.retHead = new ResponseHead(code, msg);
    }

    public T getRetBody() {
        return retBody;
    }

    public void setRetBody(T retBody) {
        this.retBody = retBody;
    }

    //成功的使用频繁提出为方法
    public ResponseBase<T> success(){this.setRetHead(SUCCESS.getIndex(), SUCCESS.getValue());

        return this;
    }
}

