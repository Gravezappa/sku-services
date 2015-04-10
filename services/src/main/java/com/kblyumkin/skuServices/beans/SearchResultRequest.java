package com.kblyumkin.skuServices.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType(name= "SearchResultRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchResultRequest {
    private long resultId;

    public SearchResultRequest() {
    }

    public SearchResultRequest(Long resultId) {
        this.resultId = resultId;
    }

    public Long getResultId() {
        return resultId;
    }

    public void setResultId(long resultId) {
        this.resultId = resultId;
    }

    @Override
    public String toString() {
        return "Search{" +
                "resultId='" + resultId + '\'' +
                '}';
    }
}
