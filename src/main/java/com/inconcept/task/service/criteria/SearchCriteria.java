package com.inconcept.task.service.criteria;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class SearchCriteria {
    private Integer size;
    private Integer page;
    private String sortField = "title";

    public SearchCriteria(Integer size, Integer page, String sortField) {
        this.size = size;
        this.page = page;
        this.sortField = sortField;
    }

    public SearchCriteria() {

    }


    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }


    public Pageable composePageRequest() {
        if (size != null && size == Integer.MAX_VALUE) {
            return null;
        }

        int page = this.page == null ? 0 : this.page;
        int size = this.size == null ? 20 : this.size;

        return PageRequest.of(page, size);
    }
}
