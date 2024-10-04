package com.example.ishoppinglist.models;

import java.io.Serializable;

public class Product implements Serializable {

    private int id;
    private String name;
    private String informativeNote;
    private Boolean needToBuy;

    public Product(int id, String name, String informativeNote, Boolean needToBuy) {
        this.id = id;
        this.name = name;
        this.informativeNote = informativeNote;
        this.needToBuy = needToBuy;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInformativeNote() {
        return informativeNote;
    }

    public void setInformativeNote(String informativeNote) {
        this.informativeNote = informativeNote;
    }

    public Boolean getNeedToBuy() {
        return needToBuy;
    }

    public void setNeedToBuy(Boolean needToBuy) {
        this.needToBuy = needToBuy;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", informativeNote='" + informativeNote + '\'' +
                ", needToBuy=" + needToBuy +
                '}';
    }
}
