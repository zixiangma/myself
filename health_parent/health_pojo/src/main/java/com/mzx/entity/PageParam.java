package com.mzx.entity;

import java.io.Serializable;

public class PageParam implements Serializable {
    private Integer currentPage;
    private Integer pageSize;
    private Integer total;
    private String queryString;
    private Integer startIndex;

    @Override
    public String toString() {
        return "PageParam{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", queryString='" + queryString + '\'' +
                '}';
    }

    public Integer getStartIndex(){
        return (currentPage - 1) * pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }
}
