package com.example.demo.model;

import java.util.List;

public class CreateCollectivity {
    private String location;
    private List<String> memberIds;
    private Boolean federationApproval;
    private CreateCollectivityStructure structure;

    public CreateCollectivity() {
    }

    public CreateCollectivity(String location, List<String> memberIds, Boolean federationApproval, CreateCollectivityStructure structure) {
        this.location = location;
        this.memberIds = memberIds;
        this.federationApproval = federationApproval;
        this.structure = structure;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(List<String> memberIds) {
        this.memberIds = memberIds;
    }

    public Boolean getFederationApproval() {
        return federationApproval;
    }

    public void setFederationApproval(Boolean federationApproval) {
        this.federationApproval = federationApproval;
    }

    public CreateCollectivityStructure getStructure() {
        return structure;
    }

    public void setStructure(CreateCollectivityStructure structure) {
        this.structure = structure;
    }
}