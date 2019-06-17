package com.easybuy.other;

import java.io.Serializable;

public class Pager implements Serializable {

    private int currentPage;//当前页
    private int rowCount;//总条数
    private int rowPerPage;//每页显示条数
    private int pageCount;//总页数
    private String url;

    public Pager()
    {

    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getRowPerPage() {
        return rowPerPage;
    }

    public void setRowPerPage(int rowPerPage) {
        this.rowPerPage = rowPerPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
