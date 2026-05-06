package com.example.demo.model;

public class Statistics {

    private String collectivityId;
    private String collectivityName;
    private Integer totalMembers;
    private Integer upToDateMembers;
    private Double upToDatePercentage;
    private Integer newMembers;

    public Statistics() {
    }

    public String getCollectivityId() {
        return collectivityId;
    }

    public String getCollectivityName() {
        return collectivityName;
    }

    public Integer getTotalMembers() {
        return totalMembers;
    }

    public Integer getUpToDateMembers() {
        return upToDateMembers;
    }

    public Double getUpToDatePercentage() {
        return upToDatePercentage;
    }

    public Integer getNewMembers() {
        return newMembers;
    }

    public void setCollectivityId(String collectivityId) {
        this.collectivityId = collectivityId;
    }

    public void setCollectivityName(String collectivityName) {
        this.collectivityName = collectivityName;
    }

    public void setTotalMembers(Integer totalMembers) {
        this.totalMembers = totalMembers;
    }

    public void setUpToDateMembers(Integer upToDateMembers) {
        this.upToDateMembers = upToDateMembers;
    }

    public void setUpToDatePercentage(Double upToDatePercentage) {
        this.upToDatePercentage = upToDatePercentage;
    }

    public void setNewMembers(Integer newMembers) {
        this.newMembers = newMembers;
    }
}