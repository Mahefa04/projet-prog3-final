package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

public class Member {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;
    private String address;
    private String profession;
    private String phone;
    private String email;
    private MemberOccupation occupation;
    private Collectivity collectivity;
    private List<Member> referees;

    public Member() {
    }

    public Member(String id, String firstName, String lastName, LocalDate birthDate, String gender, String address, String profession, String phone, String email, MemberOccupation occupation, Collectivity collectivity, List<Member> referees) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.profession = profession;
        this.phone = phone;
        this.email = email;
        this.occupation = occupation;
        this.collectivity = collectivity;
        this.referees = referees;
    }

    public Member(String id, String firstName, String lastName, LocalDate birthDate, String gender, String address, String profession, String phone, String email, String occupation, Collectivity collectivity, List<Member> referees) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MemberOccupation getOccupation(String occupation) {
        return this.occupation;
    }

    public static MemberOccupation setOccupation(int i, MemberOccupation occupation) {
        return occupation;
    }

    public Collectivity getCollectivity() {
        return collectivity;
    }

    public void setCollectivity(Collectivity collectivity) {
        this.collectivity = collectivity;
    }

    public List<Member> getReferees() {
        return referees;
    }

    public void setReferees(List<Member> referees) {
        this.referees = referees;
    }
}