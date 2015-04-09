package com.kblyumkin.skuServices.beans;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(name="Sku", propOrder = {
        "id",
        "skuName",
        "skuDescription",
})
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="SKU")
@SpaceClass
public class Sku implements Serializable{
    @Id
    private Long id;
    private String skuName;
    private String skuDescription;

    public Sku() {
    }

    public Sku(Long id, String skuName, String skuDescription) {
        this.id = id;
        this.skuName = skuName;
        this.skuDescription = skuDescription;
    }

    @SpaceId(autoGenerate = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getSkuDescription() {
        return skuDescription;
    }

    public void setSkuDescription(String skuDescription) {
        this.skuDescription = skuDescription;
    }

    @Override
    public String toString() {
        return "Sku{" +
                "id=" + id +
                ", skuName='" + skuName + '\'' +
                ", skuDescription='" + skuDescription + '\'' +
                '}';
    }
}
