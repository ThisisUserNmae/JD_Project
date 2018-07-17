package com.bwei.jd_project.mvp.home.model.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "product")
public class Product {

    @Id(autoincrement = true)
    private Long id;

    private String productName;


    private int pscid;

    @Generated(hash = 89337)
    public Product(Long id, String productName, int pscid) {
        this.id = id;
        this.productName = productName;
        this.pscid = pscid;
    }

    @Generated(hash = 1890278724)
    public Product() {
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPscid() {
        return this.pscid;
    }

    public void setPscid(int pscid) {
        this.pscid = pscid;
    }

    

}
