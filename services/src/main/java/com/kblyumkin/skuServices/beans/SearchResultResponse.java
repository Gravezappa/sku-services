package com.kblyumkin.skuServices.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType(name= "SearchResultResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchResultResponse {
    private SearchResultHolder.Status status;
    private List<Sku> result;

    public SearchResultResponse() {
    }

    public SearchResultResponse(List<Sku> result) {
        this.result = result;
    }

    public SearchResultHolder.Status getStatus() {
        return status;
    }

    public void setStatus(SearchResultHolder.Status status) {
        this.status = status;
    }

    public List<Sku> getResult() {
        return result;
    }

    public void setResult(List<Sku> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "SearchResultResponse{" +
                "result=" + result +
                ", status=" + status +
                '}';
    }

}
