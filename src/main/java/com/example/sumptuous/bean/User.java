package com.example.sumptuous.bean;

import com.example.sumptuous.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "mobile_number")
    private int mobileNumber;

    @Column(name = "emailid")
    private String emailId;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @JsonIgnoreProperties("createdBy")
    @OneToMany(mappedBy = "createdBy")
    private List<Recipe> createdRecipes;

    @JsonIgnoreProperties("updatedBy")
    @OneToMany(mappedBy = "updatedBy")
    private List<Recipe> updatedRecipes;

    public User() {
    }

    public User(long id, String firstName, String middleName, String lastName, int mobileNumber, String emailId, String password, Role role, List<Recipe> createdRecipes, List<Recipe> updatedRecipes) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.emailId = emailId;
        this.password = password;
        this.role = role;
        this.createdRecipes = createdRecipes;
        this.updatedRecipes = updatedRecipes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Recipe> getCreatedRecipes() {
        return createdRecipes;
    }

    public void setCreatedRecipes(List<Recipe> createdRecipes) {
        this.createdRecipes = createdRecipes;
    }

    public List<Recipe> getUpdatedRecipes() {
        return updatedRecipes;
    }

    public void setUpdatedRecipes(List<Recipe> updatedRecipes) {
        this.updatedRecipes = updatedRecipes;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNumber=" + mobileNumber +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", createdRecipes=" + createdRecipes +
                ", updatedRecipes=" + updatedRecipes +
                '}';
    }
}
