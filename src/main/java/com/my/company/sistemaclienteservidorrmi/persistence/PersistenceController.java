/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.my.company.sistemaclienteservidorrmi.persistence;

import com.my.company.sistemaclienteservidorrmi.entities.Appointment;
import com.my.company.sistemaclienteservidorrmi.entities.Doctor;
import com.my.company.sistemaclienteservidorrmi.entities.Patient;
import com.my.company.sistemaclienteservidorrmi.persistence.exceptions.NonexistentEntityException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Sebah
 */
public class PersistenceController {
    AppointmentJpaController control = new AppointmentJpaController();
    PatientJpaController controlPatient = new PatientJpaController();
    DoctorJpaController controlDoctor = new DoctorJpaController();
    
    
    //Appointment
    public void createAppointment(Appointment appointment){
        control.create(appointment);
    }
    
    public void editAppointment(Appointment appointment) throws NonexistentEntityException{
        try {
            control.edit(appointment);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void deleteAppointment(int id){
        control.destroy(id);
    }
    

    //Patient
    public void createPatient(Patient patient){
        controlPatient.create(patient);
    }

    public void editPatient(Patient patient){
        try{
            controlPatient.edit(patient);
        }catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void deletePatient(int id){
        controlPatient.destroy(id);
    }

    public Patient findPatient(int id){
        return controlPatient.findPatient(id);
    }

    public List<Patient> findAllPatients() {
        return controlPatient.findAllPatients();
    }   


    // Doctor
    public void createDoctor(Doctor doctor){
        controlDoctor.create(doctor);
    }

    public void editDoctor(Doctor doctor){
        try {
            controlDoctor.edit(doctor);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteDoctor(int id){
        controlDoctor.destroy(id);
    }

    public Doctor findDoctor(int id){
        return controlDoctor.findDoctor(id);
    }

    public List<Doctor> findAllDoctors() {
        return controlDoctor.findAllDoctors();
    }

}
