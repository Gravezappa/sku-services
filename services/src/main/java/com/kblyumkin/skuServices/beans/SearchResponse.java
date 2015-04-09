package com.kblyumkin.skuServices.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@XmlType(name= "SearchResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchResponse implements Serializable {
    private List<Sku> result;

    public SearchResponse() {
    }

    public SearchResponse(List<Sku> result) {
        this.result = result;
    }

    public List<Sku> getResult() {
        return result;
    }

    public void setResult(List<Sku> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Search{" +
                "result='" + Arrays.toString(result.toArray()) + '\'' +
                '}';
    }
}
