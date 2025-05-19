package com.my.company.sistemaclienteservidorrmi.controllers;

import com.my.company.sistemaclienteservidorrmi.entities.Doctor;
import com.my.company.sistemaclienteservidorrmi.persistence.PersistenceController;
import interfaces.IDoctorInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class DoctorController extends UnicastRemoteObject implements IDoctorInterface {

    public DoctorController() throws RemoteException {
        super();
    }

    PersistenceController controller = new PersistenceController();

    @Override
    public void createDoctor(Doctor doctor) throws RemoteException {
        controller.createDoctor(doctor);
    }

    @Override
    public void updateDoctor(Doctor doctor) throws RemoteException {
        controller.editDoctor(doctor);
    }

    @Override
    public void deleteDoctor(int id) throws RemoteException {
        controller.deleteDoctor(id);
    }

    @Override
    public Doctor getDoctor(int id) throws RemoteException {
        return controller.findDoctor(id);
    }

    @Override
    public List<Doctor> getAllDoctors() throws RemoteException {
        return controller.findAllDoctors();
    }
}
