package com.my.company.sistemaclienteservidorrmi.controllers;

import java.rmi.RemoteException;

import interfaces.IDoctorInterface;
import interfaces.IPatientInterface;

public class RemoteServiceImpl extends java.rmi.server.UnicastRemoteObject implements interfaces.IRemoteService {

    private IPatientInterface patientService;
    private IDoctorInterface doctorService;
    public RemoteServiceImpl() throws java.rmi.RemoteException {
        super();
        this.patientService = new PatientController();
        this.doctorService = new DoctorController();
    }

    @Override
    public IPatientInterface getPatientService() throws RemoteException {
        return patientService;
    }

    @Override
    public IDoctorInterface getDoctorService() throws RemoteException {
        return doctorService;
    }


}
