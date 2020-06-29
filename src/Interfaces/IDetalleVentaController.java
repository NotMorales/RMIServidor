package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface IDetalleVentaController extends Remote {
    IDetalleVenta newInstance() throws RemoteException;
    int add(IDetalleVenta detalleVenta) throws RemoteException;
    int update(IDetalleVenta detalleVenta) throws RemoteException;
    int delete (IDetalleVenta detalleVenta) throws RemoteException;
    int delete(int detalleVentaId) throws RemoteException;
    List<IDetalleVenta> list() throws RemoteException;
    IDetalleVenta findOne(int detalleVentaId) throws RemoteException;
    List<IDetalleVenta> find(IDetalleVenta detalleVenta) throws RemoteException;
    double getMontoTotal(String[] fechas) throws RemoteException;
    List< Map<String, Object> > getProductosVendidos(String[] fechas) throws RemoteException;
    
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
