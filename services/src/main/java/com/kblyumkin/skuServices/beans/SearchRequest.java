package com.kblyumkin.skuServices.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType(name= "SearchRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchRequest {
    private String searchDescription;

    public SearchRequest() {
    }

    public SearchRequest(String searchDescription) {
        this.searchDescription = searchDescription;
    }

    public String getSearchDescription() {
        return searchDescription;
    }

    public void setSearchDescription(String searchDescription) {
        this.searchDescription = searchDescription;
    }

    @Override
    public String toString() {
        return "Search{" +
                "searchDescription='" + searchDescription + '\'' +
                '}';
    }
}
