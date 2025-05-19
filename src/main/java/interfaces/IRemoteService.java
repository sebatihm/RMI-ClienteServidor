package interfaces;

import java.rmi.RemoteException;

public interface IRemoteService extends java.rmi.Remote {
    public IPatientInterface getPatientService() throws RemoteException;
    public IDoctorInterface getDoctorService() throws RemoteException; 
    public IAppointmentInterface getAppointmentService() throws RemoteException;
}
