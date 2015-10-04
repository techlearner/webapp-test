package com.m2p.web.util;


/**
 * Created by sriramk on 10/24/2014.
 */
public class YappayResponse {

    private Object result;
    private YappayException exception;
    private Paginate pagination;
    public YappayResponse() {

    }
    public YappayResponse(Object result, YappayException exception, Paginate pagination) {
        this.result = result;
        this.exception = exception;
        this.pagination = pagination;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public YappayException getException() {
        return exception;
    }

    public void setException(YappayException exception) {
        this.exception = exception;
    }

    public Paginate getPagination() {
        return pagination;
    }

    public void setPagination(Paginate pagination) {
        this.pagination = pagination;
    }
}
