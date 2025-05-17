package com.my.company.sistemaclienteservidorrmi.controllers;

import java.rmi.RemoteException;

import interfaces.IPatientInterface;

public class RemoteServiceImpl extends java.rmi.server.UnicastRemoteObject implements interfaces.IRemoteService {

    private IPatientInterface patientService;
    public RemoteServiceImpl() throws java.rmi.RemoteException {
        super();
        this.patientService = new PatientController();
    }

    @Override
    public IPatientInterface getPatientService() throws RemoteException {
        return patientService;
    }


    
}
