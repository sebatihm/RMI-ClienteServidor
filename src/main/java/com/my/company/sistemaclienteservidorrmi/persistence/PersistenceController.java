/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.my.company.sistemaclienteservidorrmi.persistence;

import com.my.company.sistemaclienteservidorrmi.entities.Appointment;
import com.my.company.sistemaclienteservidorrmi.persistence.exceptions.NonexistentEntityException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Sebah
 */
public class PersistenceController {
    AppointmentJpaController control = new AppointmentJpaController();
    
    
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
    
}
