package com.peng.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

public class Response implements Serializable{
    private int code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errmsg;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object ctn;

    public Response(int code,String errmsg){
        this.code = code;
        this.errmsg = errmsg;
    }

    public Response(int code){
        this.code = code;
    }

    public Response(int code,Object ctn){
        this.code = code;
        this.ctn = ctn;
    }

    public Response(){}

    @Override
    public String toString() {
        return "Response{" +
                "code='" + code + '\'' +
                ", errmsg='" + errmsg + '\'' +
                ", ctn=" + ctn +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Object getCtn() {
        return ctn;
    }

    public void setCtn(Object ctn) {
        this.ctn = ctn;
    }
}
