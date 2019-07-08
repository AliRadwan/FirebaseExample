package com.example.firebase;

import java.io.Serializable;

/**
 * this POJO class  have data that we need to work with it and send it to firebase
 */
public class TravelDeal  implements Serializable {



    private String id;
    private String title;
    private String description;
    private String price;
    private String imgUrl;


    public TravelDeal() {
    }

    public TravelDeal(String title, String description, String price, String imgUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
