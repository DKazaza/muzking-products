package com.example.muzkingproducts;

public class Product {
    private String id;
    private String name;
    private String count;
    private String buy_price;
    private String rrc_price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getBuy_price() {
        return buy_price;
    }

    public void setBuy_price(String buy_price) {
        this.buy_price = buy_price;
    }

    public String getRrc_price() {
        return rrc_price;
    }

    public void setRrc_price(String rrc_price) {
        this.rrc_price = rrc_price;
    }

    public Product(String name, String count, String buy_price, String rrc_price) {
        this.name = name;
        this.count = count;
        this.buy_price = buy_price;
        this.rrc_price = rrc_price;
    }
    public Product(String id, String name, String count, String buy_price, String rrc_price) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.buy_price = buy_price;
        this.rrc_price = rrc_price;
    }
}
