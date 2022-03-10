package com.inn.banking.POJO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@NamedQuery(name = "Recipient.findByaccountNumberAndUserId", query = "select r from Recipient r where r.accountNumber=:accountNumber and r.appUser.id=:userId")

@NamedQuery(name = "Recipient.getRecipientByUserId", query = "select r from Recipient r where r.appUser.id=:userId")

@NamedQuery(name = "Recipient.updateRecipient", query = "update Recipient r set r.name=:name,r.email=:email,r.phone=:phone,r.description=:description where r.id=:recipientId")

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "recipient")
public class Recipient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipient_pk")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "accountnumber")
    private String accountNumber;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "appuser_fk")
    @JsonIgnore
    private AppUser appUser;

    public Recipient() {
    }

    public Recipient(int id, String name, String email, String phone, String accountNumber, String description, AppUser appUser) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.accountNumber = accountNumber;
        this.description = description;
        this.appUser = appUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
