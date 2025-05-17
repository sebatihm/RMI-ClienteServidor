package com.my.company.sistemaclienteservidorrmi.controllers;


import java.rmi.RemoteException;
import java.util.List;

import com.my.company.sistemaclienteservidorrmi.entities.Patient;
import com.my.company.sistemaclienteservidorrmi.persistence.PersistenceController;

public class PatientController extends java.rmi.server.UnicastRemoteObject implements interfaces.IPatientInterface {

    public PatientController() throws RemoteException {
        super();
    }

    PersistenceController controller = new PersistenceController();
    
    @Override
    public void createPatient(Patient patient) throws RemoteException {
        controller.createPatient(patient);
    }


    @Override
    public void updatePatient(Patient patient) throws RemoteException {
        controller.editPatient(patient);
    }

    @Override
    public void deletePatient(int id) throws RemoteException {
        controller.deletePatient(id);
    }

    @Override
    public Patient getPatient(int id) throws RemoteException {
        return controller.findPatient(id);
    }

    @Override
    public List<Patient> getAllPatients() throws RemoteException {
        return controller.findAllPatients();
    }
}
