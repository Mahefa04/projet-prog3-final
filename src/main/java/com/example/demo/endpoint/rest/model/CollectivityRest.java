package com.example.demo.endpoint.rest.model;

import java.util.List;

public class CollectivityRest {
    private String id;
    private String location;
    private String number;
    private String name;
    private CollectivityStructureRest structure;
    private List<MemberRest> members;

    public CollectivityRest() {
    }

    public CollectivityRest(String id, String location, String number, String name, CollectivityStructureRest structure, List<MemberRest> members) {
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

    public CollectivityStructureRest getStructure() {
        return structure;
    }

    public void setStructure(CollectivityStructureRest structure) {
        this.structure = structure;
    }

    public List<MemberRest> getMembers() {
        return members;
    }

    public void setMembers(List<MemberRest> members) {
        this.members = members;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}