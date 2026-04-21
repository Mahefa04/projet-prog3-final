package com.example.demo.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Member {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Gender gender;
    private String address;
    private String profession;
    private int phoneNumber;
    private String email;
    private MemberOccupation occupation;
    private List<Member> referees;
    private String collectivityId;
    private boolean registrationFeePaid;
    private boolean membershipDuesPaid;
    private LocalDate joiningDate;

    public Member() {
        this.referees = new ArrayList<>();
        this.joiningDate = LocalDate.now();
    }

    public Member(String id, String firstName, String lastName, LocalDate birthDate,
                  Gender gender, String email, MemberOccupation occupation) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.email = email;
        this.occupation = occupation;
        this.referees = new ArrayList<>();
        this.joiningDate = LocalDate.now();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getProfession() { return profession; }
    public void setProfession(String profession) { this.profession = profession; }

    public int getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(int phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public MemberOccupation getOccupation() { return occupation; }
    public void setOccupation(MemberOccupation occupation) { this.occupation = occupation; }

    public List<Member> getReferees() { return referees; }
    public void setReferees(List<Member> referees) { this.referees = referees; }

    public void addReferee(Member referee) { this.referees.add(referee); }

    public String getCollectivityId() { return collectivityId; }
    public void setCollectivityId(String collectivityId) { this.collectivityId = collectivityId; }

    public boolean isRegistrationFeePaid() { return registrationFeePaid; }
    public void setRegistrationFeePaid(boolean registrationFeePaid) { this.registrationFeePaid = registrationFeePaid; }

    public boolean isMembershipDuesPaid() { return membershipDuesPaid; }
    public void setMembershipDuesPaid(boolean membershipDuesPaid) { this.membershipDuesPaid = membershipDuesPaid; }

    public LocalDate getJoiningDate() { return joiningDate; }
    public void setJoiningDate(LocalDate joiningDate) { this.joiningDate = joiningDate; }

    public boolean isConfirmed() {
        return this.occupation == MemberOccupation.SENIOR ||
                this.occupation == MemberOccupation.PRESIDENT ||
                this.occupation == MemberOccupation.VICE_PRESIDENT ||
                this.occupation == MemberOccupation.TREASURER ||
                this.occupation == MemberOccupation.SECRETARY;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
}
