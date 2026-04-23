package com.example.demo.model;

public class CollectivityInformation {
    private String name;
    private Integer number;

    public CollectivityInformation() {
    }

    public CollectivityInformation(String name, Integer number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}