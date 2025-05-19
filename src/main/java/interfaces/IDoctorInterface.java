package interfaces;

import com.my.company.sistemaclienteservidorrmi.entities.Doctor;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IDoctorInterface extends Remote {
    void createDoctor(Doctor doctor) throws RemoteException;

    void updateDoctor(Doctor doctor) throws RemoteException;

    void deleteDoctor(int id) throws RemoteException;

    Doctor getDoctor(int id) throws RemoteException;

    List<Doctor> getAllDoctors() throws RemoteException;
}
