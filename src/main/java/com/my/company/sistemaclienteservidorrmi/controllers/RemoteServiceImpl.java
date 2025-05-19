package com.my.company.sistemaclienteservidorrmi.controllers;

import interfaces.IAppointmentInterface;
import java.rmi.RemoteException;

import interfaces.IDoctorInterface;
import interfaces.IPatientInterface;

public class RemoteServiceImpl extends java.rmi.server.UnicastRemoteObject implements interfaces.IRemoteService {

    private IPatientInterface patientService;
    private IDoctorInterface doctorService;
    private IAppointmentInterface appointmentService;
    public RemoteServiceImpl() throws java.rmi.RemoteException {
        super();
        this.patientService = new PatientController();
        this.doctorService = new DoctorController();
        this.appointmentService = new AppointmentController();
    }

    @Override
    public IPatientInterface getPatientService() throws RemoteException {
        return patientService;
    }

    @Override
    public IDoctorInterface getDoctorService() throws RemoteException {
        return doctorService;
    }
    
    @Override
    public IAppointmentInterface getAppointmentService() throws RemoteException {
        return appointmentService;
    }


}
