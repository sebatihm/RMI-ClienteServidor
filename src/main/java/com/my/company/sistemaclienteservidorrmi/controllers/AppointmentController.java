/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.my.company.sistemaclienteservidorrmi.controllers;

import com.my.company.sistemaclienteservidorrmi.entities.Appointment;
//import com.my.company.sistemaclienteservidorrmi.persistence.AppointmentJpaController;
import com.my.company.sistemaclienteservidorrmi.persistence.PersistenceController;
import com.my.company.sistemaclienteservidorrmi.persistence.exceptions.NonexistentEntityException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sebah
 */
public class AppointmentController {
    PersistenceController controller = new PersistenceController();
    
    public void createAppointment(Appointment appointment){
        controller.createAppointment(appointment);
    }
    
    public void editAppointment(Appointment appointment){
        try {
            controller.editAppointment(appointment);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void destroyAppointment(Appointment appointment){
        controller.createAppointment(appointment);
    }
    
    
}
