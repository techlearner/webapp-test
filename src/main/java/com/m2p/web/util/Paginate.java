package com.m2p.web.util;

/**
 * Created by sriramk on 10/24/2014.
 */
public class Paginate {

    private Boolean isList;
    private Integer pageSize;
    private Integer pageNo;
    private Integer totalPages;
    private Integer totalElements;

    public Boolean getIsList() {
        return isList;
    }

    public void setIsList(Boolean isList) {
        this.isList = isList;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }
}
