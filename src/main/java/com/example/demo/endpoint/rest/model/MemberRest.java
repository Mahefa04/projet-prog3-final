package com.example.demo.endpoint.rest.model;

import java.time.LocalDate;
import java.util.List;

public class MemberRest {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Gender gender;
    private String address;
    private String profession;
    private Integer phoneNumber;
    private String email;
    private MemberOccupation occupation;
    private List<MemberRest> referees;

    public MemberRest() {
    }

    public MemberRest(
            String id,
            String firstName,
            String lastName,
            LocalDate birthDate,
            Gender gender,
            String address,
            String profession,
            Integer phoneNumber,
            String email,
            MemberOccupation occupation,
            List<MemberRest> referees
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.profession = profession;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.occupation = occupation;
        this.referees = referees;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
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

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MemberOccupation getOccupation() {
        return occupation;
    }

    public void setOccupation(MemberOccupation occupation) {
        this.occupation = occupation;
    }

    public List<MemberRest> getReferees() {
        return referees;
    }

    public void setReferees(List<MemberRest> referees) {
        this.referees = referees;
    }
}