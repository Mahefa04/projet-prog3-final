package com.example.demo.endpoint.rest.model;

public class CollectivityInformationRest {
    private String name;
    private Integer number;

    public CollectivityInformationRest() {
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