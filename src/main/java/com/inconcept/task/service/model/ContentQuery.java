package com.inconcept.task.service.model;


import java.util.List;


public class ContentQuery<C> {
    private int total;
    private List<C> cList;

    public ContentQuery(int total, List<C> cList) {
        this.total = total;
        this.cList = cList;
    }

    public ContentQuery() {

    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<C> getcList() {
        return cList;
    }

    public void setcList(List<C> cList) {
        this.cList = cList;
    }
}
