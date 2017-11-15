package com.example.kentec.myapplicationmysql;

/**
 * Created by dfreer on 11/9/2017.
 */

public class Product {
    private int id, qty;
    private String name, description;

    public Product(int id, int qty, String name, String description) {
        this.id = id;
        this.qty = qty;
        this.name = name;
        this.description = description;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
