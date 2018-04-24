package com.kong.frameapp.bean;

import java.io.Serializable;


public class SimpleResponse implements Serializable {

    private static final long serialVersionUID = -1477609349345966116L;

    public int code;
    public String msg;

    public BaseResponse toLzyResponse() {
        BaseResponse lzyResponse = new BaseResponse(1);
//        lzyResponse.code = code;
//        lzyResponse.msg = msg;
        return lzyResponse;
    }
}
