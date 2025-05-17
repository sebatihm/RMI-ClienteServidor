/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.my.company.sistemaclienteservidorrmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.my.company.sistemaclienteservidorrmi.controllers.RemoteServiceImpl;
import interfaces.IRemoteService;

/**
 *
 * @author Sebah
 */
public class SistemaClienteServidorRMI {

    public static void main(String[] args) {
       
        try {
            // Crear instancia del controlador remoto
            IRemoteService remoteService = new RemoteServiceImpl();

            // Crear el registro RMI en el puerto 1099
            Registry registry = LocateRegistry.createRegistry(1099);

            // Publicar el servicio con un nombre accesible para el cliente
            registry.rebind("RemoteService", remoteService);

            System.out.println("🟢 RMI server running. Service 'PacientService' is ready.");
        } catch (Exception e) {
            System.err.println("🔴 Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
