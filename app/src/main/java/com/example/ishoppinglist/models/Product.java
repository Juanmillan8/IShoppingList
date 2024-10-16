package com.example.ishoppinglist.models;

import java.io.Serializable;

public class Product implements Serializable {

    //Atributos privados de la clase Product
    private int id;
    private String name;
    private String informativeNote;
    private Boolean needToBuy;
    private Boolean containsLactose;
    private Boolean containsGluten;

    //Constructor con parámetros
    public Product(int id, String name, String informativeNote, Boolean needToBuy, Boolean containsGluten, Boolean containsLactose) {
        this.id = id;
        this.name = name;
        this.informativeNote = informativeNote;
        this.containsGluten = containsGluten;
        this.containsLactose = containsLactose;
        this.needToBuy = needToBuy;
    }

    // Constructor vacío
    public Product() {
    }

    // Métodos "getter" y "setter" para obtener y modificar los atributos del producto
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

    public Boolean getContainsLactose() {
        return containsLactose;
    }

    public void setContainsLactose(Boolean containsLactose) {
        this.containsLactose = containsLactose;
    }

    public Boolean getContainsGluten() {
        return containsGluten;
    }

    public void setContainsGluten(Boolean containsGluten) {
        this.containsGluten = containsGluten;
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
                ", containsLactose=" + containsLactose +
                ", containsGluten=" + containsGluten +
                '}';
    }
}
