package com.example.demo.endpoint.rest.model;

import java.util.List;

public class CollectivityRest {
    private String id;
    private Integer number;
    private String name;
    private String locality;
    private String specialization;
    private CollectivityStructureRest structure;
    private List<MemberRest> members;

    public CollectivityRest() {
    }

    public String getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getLocality() {
        return locality;
    }

    public String getSpecialization() {
        return specialization;
    }

    public CollectivityStructureRest getStructure() {
        return structure;
    }

    public List<MemberRest> getMembers() {
        return members;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setStructure(CollectivityStructureRest structure) {
        this.structure = structure;
    }

    public void setMembers(List<MemberRest> members) {
        this.members = members;
    }
}