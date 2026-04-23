package com.example.demo.model;

import java.util.List;

public class Collectivity {
    private String id;
    private String locality;
    private Integer number;
    private String name;
    private String specialization;
    private CollectivityStructure structure;
    private List<Member> members;

    public Collectivity() {
    }

    public Collectivity(String id, String locatlity, Integer number, String name, String specialization, CollectivityStructure structure, List<Member> members) {
        this.id = id;
        this.locality = locatlity;
        this.number = number;
        this.name = name;
        this.specialization = specialization;
        this.structure = structure;
        this.members = members;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}