package com.example.demo.endpoint.rest.model;

public class CollectivityStructureRest {
    private MemberRest president;
    private MemberRest vicePresident;
    private MemberRest treasurer;
    private MemberRest secretary;

    public CollectivityStructureRest() {
    }

    public CollectivityStructureRest(MemberRest president, MemberRest vicePresident, MemberRest treasurer, MemberRest secretary) {
        this.president = president;
        this.vicePresident = vicePresident;
        this.treasurer = treasurer;
        this.secretary = secretary;
    }

    public MemberRest getPresident() {
        return president;
    }

    public void setPresident(MemberRest president) {
        this.president = president;
    }

    public MemberRest getVicePresident() {
        return vicePresident;
    }

    public void setVicePresident(MemberRest vicePresident) {
        this.vicePresident = vicePresident;
    }

    public MemberRest getTreasurer() {
        return treasurer;
    }

    public void setTreasurer(MemberRest treasurer) {
        this.treasurer = treasurer;
    }

    public MemberRest getSecretary() {
        return secretary;
    }

    public void setSecretary(MemberRest secretary) {
        this.secretary = secretary;
    }
}