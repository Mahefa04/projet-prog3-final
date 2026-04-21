package com.example.demo.model;

public class CreateCollectivityStructure {
    private String presidentId;
    private String vicePresidentId;
    private String treasurerId;
    private String secretaryId;

    public CreateCollectivityStructure() {
    }

    public CreateCollectivityStructure(String presidentId, String vicePresidentId, String treasurerId, String secretaryId) {
        this.presidentId = presidentId;
        this.vicePresidentId = vicePresidentId;
        this.treasurerId = treasurerId;
        this.secretaryId = secretaryId;
    }

    public String getPresidentId() {
        return presidentId;
    }

    public void setPresidentId(String presidentId) {
        this.presidentId = presidentId;
    }

    public String getVicePresidentId() {
        return vicePresidentId;
    }

    public void setVicePresidentId(String vicePresidentId) {
        this.vicePresidentId = vicePresidentId;
    }

    public String getTreasurerId() {
        return treasurerId;
    }

    public void setTreasurerId(String treasurerId) {
        this.treasurerId = treasurerId;
    }

    public String getSecretaryId() {
        return secretaryId;
    }

    public void setSecretaryId(String secretaryId) {
        this.secretaryId = secretaryId;
    }
}