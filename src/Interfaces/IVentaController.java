package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IVentaController extends Remote {
    IVenta newInstance() throws RemoteException;
    int add(IVenta venta) throws RemoteException;
    int update(IVenta venta) throws RemoteException;
    int delete (IVenta venta) throws RemoteException;
    int delete(int ventaId) throws RemoteException;
    List<IVenta> list() throws RemoteException;
    IVenta findOne(int ventaId) throws RemoteException;
    List<IVenta> find(IVenta venta) throws RemoteException;
    int getNumVentas() throws RemoteException;
    int cancelarVenta(int ventaId) throws RemoteException;
    
    int ADD_EXITO               = 1;
    int ADD_ID_DUPLICADO        = 2;
    int ADD_SIN_EXITO           = 3;
    
    int UPDATE_EXITO            = 1;
    int UPDATE_ID_INEXISTE      = 2; 
    int UPDATE_ID_NULO          = 3;
    int UPDATE_SIN_EXITO        = 4;
    
    int DELETE_EXITO            = 1;
    int DELETE_ID_INEXISTENTE   = 2;
    int DELETE_ID_NULO          = 3;
    int DELETE_SIN_EXITO        = 4;    
}
