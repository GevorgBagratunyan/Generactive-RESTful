package com.generactive.service.criteria;


import com.generactive.service.specification.SearchOperation;

public class SearchCriteria {
    private String key;
    private SearchOperation searchOperation;
    private String argument;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public SearchOperation getSearchOperation() {
        return searchOperation;
    }

    public void setSearchOperation(SearchOperation searchOperation) {
        this.searchOperation = searchOperation;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }
}