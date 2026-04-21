package com.example.demo.endpoint.rest.model;

import com.example.demo.endpoint.rest.model.CreateCollectivityStructureRest;

import java.util.List;

public class CreateCollectivityRest {
    private String location;
    private List<String> members;
    private Boolean federationApproval;
    private CreateCollectivityStructureRest structure;

    public CreateCollectivityRest() {
    }

    public CreateCollectivityRest(String location, List<String> members, Boolean federationApproval, CreateCollectivityStructureRest structure) {
        this.location = location;
        this.members = members;
        this.federationApproval = federationApproval;
        this.structure = structure;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public Boolean getFederationApproval() {
        return federationApproval;
    }

    public void setFederationApproval(Boolean federationApproval) {
        this.federationApproval = federationApproval;
    }

    public CreateCollectivityStructureRest getStructure() {
        return structure;
    }

    public void setStructure(CreateCollectivityStructureRest structure) {
        this.structure = structure;
    }
}