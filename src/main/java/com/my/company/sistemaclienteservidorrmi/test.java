/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.my.company.sistemaclienteservidorrmi;

import com.my.company.sistemaclienteservidorrmi.controllers.AppointmentController;
import com.my.company.sistemaclienteservidorrmi.controllers.DoctorController;
import com.my.company.sistemaclienteservidorrmi.controllers.PatientController;
import com.my.company.sistemaclienteservidorrmi.entities.Appointment;
import com.my.company.sistemaclienteservidorrmi.entities.Doctor;
import com.my.company.sistemaclienteservidorrmi.entities.Patient;
import java.rmi.RemoteException;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Sebah
 */
public class test {
    public static void main(String[] args) throws RemoteException {
        AppointmentController citas = new AppointmentController();
        DoctorController doctor = new DoctorController();
        PatientController patients = new PatientController();
        
        List<Doctor> dor= doctor.getAllDoctors();
        for(Doctor d : dor){
            System.out.println(d.getAppointments());
        }
        
    }
            
}
