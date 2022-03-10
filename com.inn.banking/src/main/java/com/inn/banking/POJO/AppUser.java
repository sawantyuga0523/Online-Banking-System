package com.inn.banking.POJO;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NamedQuery(name = "AppUser.updateProfile", query = "update AppUser a set a.username=:username,a.firstName=:firstname,a.lastName=:lastname,a.phone=:phone,a.email=:email where a.id=:userId")

@NamedQuery(name = "AppUser.enableDisableUser", query = "update AppUser a set a.enabled=:status where a.id=:userId")

//@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "appuser")
public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appuser_pk")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "role")
    private String role;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "primaryaccount_fk", nullable = false)
//    @JsonIgnore
    private PrimaryAccount primaryAccount;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "savingaccount_fk", nullable = false)
//    @JsonIgnore
    private SavingsAccount savingsAccount;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonIgnore
//    private List<Appointment> appointmentList;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Recipient> recipientList;

    public AppUser() {

    }

    public AppUser(Integer id, String username, String password, String firstName, String lastName, String email, String phone, Boolean enabled, String role, PrimaryAccount primaryAccount, SavingsAccount savingsAccount, List<Recipient> recipientList) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.enabled = enabled;
        this.role = role;
        this.primaryAccount = primaryAccount;
        this.savingsAccount = savingsAccount;
        this.recipientList = recipientList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public PrimaryAccount getPrimaryAccount() {
        return primaryAccount;
    }

    public void setPrimaryAccount(PrimaryAccount primaryAccount) {
        this.primaryAccount = primaryAccount;
    }

    public SavingsAccount getSavingsAccount() {
        return savingsAccount;
    }

    public void setSavingsAccount(SavingsAccount savingsAccount) {
        this.savingsAccount = savingsAccount;
    }

    public List<Recipient> getRecipientList() {
        return recipientList;
    }

    public void setRecipientList(List<Recipient> recipientList) {
        this.recipientList = recipientList;
    }
}
