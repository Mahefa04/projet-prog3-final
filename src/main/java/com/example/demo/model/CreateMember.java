package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

public class CreateMember {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;
    private String address;
    private String profession;
    private String phone;
    private String email;
    private String occupation;
    private String collectivityIdentifier;
    private List<String> refereeIds;
    private Boolean registrationFeePaid;
    private Boolean membershipDuesPaid;

    public CreateMember() {
    }

    public CreateMember(
            String firstName,
            String lastName,
            LocalDate birthDate,
            String gender,
            String address,
            String profession,
            String phone,
            String email,
            String occupation,
            String collectivityIdentifier,
            List<String> refereeIds,
            Boolean registrationFeePaid,
            Boolean membershipDuesPaid
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.profession = profession;
        this.phone = phone;
        this.email = email;
        this.occupation = occupation;
        this.collectivityIdentifier = collectivityIdentifier;
        this.refereeIds = refereeIds;
        this.registrationFeePaid = registrationFeePaid;
        this.membershipDuesPaid = membershipDuesPaid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getProfession() {
        return profession;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getCollectivityIdentifier() {
        return collectivityIdentifier;
    }

    public List<String> getRefereeIds() {
        return refereeIds;
    }

    public Boolean getRegistrationFeePaid() {
        return registrationFeePaid;
    }

    public Boolean getMembershipDuesPaid() {
        return membershipDuesPaid;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setCollectivityIdentifier(String collectivityIdentifier) {
        this.collectivityIdentifier = collectivityIdentifier;
    }

    public void setRefereeIds(List<String> refereeIds) {
        this.refereeIds = refereeIds;
    }

    public void setRegistrationFeePaid(Boolean registrationFeePaid) {
        this.registrationFeePaid = registrationFeePaid;
    }

    public void setMembershipDuesPaid(Boolean membershipDuesPaid) {
        this.membershipDuesPaid = membershipDuesPaid;
    }
}