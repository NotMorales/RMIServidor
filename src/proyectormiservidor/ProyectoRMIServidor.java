package proyectormiservidor;

import Interfaces.IProductoController;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProyectoRMIServidor {

    public static void main(String[] args) {
        
        try {
            LocateRegistry.createRegistry(1099);
            IProductoController productoController = new ProductoController();
            Naming.rebind("rmi://localhost/ProductoController", (Remote) productoController);
            System.out.println("Escuchado...");
        } catch (RemoteException ex) {
            Logger.getLogger(ProyectoRMIServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProyectoRMIServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
