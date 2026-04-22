package com.example.demo.model;

import java.util.List;

public class Collectivity {
    private String id;
    private String location;
    private Integer number;
    private String name;
    private CollectivityStructure structure;
    private List<Member> members;

    public Collectivity() {
    }

    public Collectivity(String id, String location, Integer number, String name, CollectivityStructure structure, List<Member> members) {
        this.id = id;
        this.location = location;
        this.number = number;
        this.name = name;
        this.structure = structure;
        this.members = members;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public CollectivityStructure getStructure() {
        return structure;
    }

    public void setStructure(CollectivityStructure structure) {
        this.structure = structure;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}