package com.kblyumkin.skuServices.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(name= "SearchResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchResponse implements Serializable {
    private long resultId;

    public SearchResponse() {
    }

    public SearchResponse(long resultId) {
        this.resultId = resultId;
    }

    public long getResultId() {
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
