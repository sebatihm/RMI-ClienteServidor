package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import com.my.company.sistemaclienteservidorrmi.entities.Patient;

public interface IPatientInterface extends Remote {
    void createPatient(Patient patient) throws RemoteException;
    void updatePatient(Patient patient) throws RemoteException;
    void deletePatient(int id) throws RemoteException;
    Patient getPatient(int id) throws RemoteException;
    List<Patient> getAllPatients() throws RemoteException;
    
}