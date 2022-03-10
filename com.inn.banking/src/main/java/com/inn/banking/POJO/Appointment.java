package com.inn.banking.POJO;


import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NamedQuery(name = "Appointment.confirmAppointment", query = "update Appointment a set a.confirmed=:status where a.id=:appointmentId")


//@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "appointment")
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_pk")
    private Integer id;

    @Column(name = "date")
    private Date date;

    @Column(name = "location")
    private String location;

    @Column(name = "description")
    private String description;

    @Column(name = "confirmed")
    private boolean confirmed;

    @ManyToOne
    @JoinColumn(name = "appuser_fk")
    private AppUser user;

    public Appointment() {

    }

    public Appointment(Integer id, Date date, String location, String description, boolean confirmed, AppUser user) {
        this.id = id;
        this.date = date;
        this.location = location;
        this.description = description;
        this.confirmed = confirmed;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}
