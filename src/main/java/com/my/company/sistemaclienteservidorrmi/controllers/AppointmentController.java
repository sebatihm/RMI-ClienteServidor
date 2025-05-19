/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.my.company.sistemaclienteservidorrmi.controllers;

import com.my.company.sistemaclienteservidorrmi.entities.Appointment;
//import com.my.company.sistemaclienteservidorrmi.persistence.AppointmentJpaController;
import com.my.company.sistemaclienteservidorrmi.persistence.PersistenceController;
import com.my.company.sistemaclienteservidorrmi.persistence.exceptions.NonexistentEntityException;
import interfaces.IAppointmentInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppointmentController extends UnicastRemoteObject implements IAppointmentInterface {
    
    public AppointmentController() throws RemoteException {
        super();
    }
    
    PersistenceController controller = new PersistenceController();
    
    
    public void createAppointment(Appointment appointment)throws RemoteException{
        controller.createAppointment(appointment);
    }
        
    public void updateAppointment(Appointment appointment) throws RemoteException{
        try {
            controller.editAppointment(appointment);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteAppointment(int id) throws RemoteException{
        controller.deleteAppointment(id);
    }
    
    public Appointment getAppointment(int id) throws RemoteException{
        return controller.findAppointment(id);
    }
    
    public List<Appointment> getAllAppointments() throws RemoteException{
        return controller.findAppointments();
    }
    
}
