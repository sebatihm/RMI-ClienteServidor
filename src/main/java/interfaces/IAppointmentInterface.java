
package interfaces;

import com.my.company.sistemaclienteservidorrmi.entities.Appointment;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface IAppointmentInterface extends Remote {
        void createAppointment(Appointment appointment) throws RemoteException;
        void updateAppointment(Appointment appointment) throws RemoteException;
        void deleteAppointment(int id) throws RemoteException;
        Appointment getAppointment(int id) throws RemoteException;
        List<Appointment> getAllAppointments() throws RemoteException;
}
