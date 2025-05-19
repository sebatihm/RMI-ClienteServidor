package com.my.company.sistemaclienteservidorrmi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Doctor implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String specialty;
    private String license;
    private String email;
    
    @OneToMany(mappedBy= "doctor", fetch = FetchType.EAGER)
    private List<Appointment> appointments = new ArrayList<>();
    

    public Doctor() {
    }

    public Doctor(String name, String specialty, String license, String email) {
        this.name = name;
        this.specialty = specialty;
        this.license = license;
        this.email = email;
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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }
    
    
}
