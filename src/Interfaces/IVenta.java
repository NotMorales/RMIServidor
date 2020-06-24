package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IVenta extends Remote{
    int getVentaId() throws RemoteException; 
    void setVentaId(int ventaId) throws RemoteException;
    
    int getFolio() throws RemoteException;
    void setFolio(int folio) throws RemoteException;
    
    double getSubTotal() throws RemoteException;
    void setSubTotal(double subTotal) throws RemoteException;
    
    double getIva() throws RemoteException;
    void setIva(double iva) throws RemoteException;
    
    double getTotal() throws RemoteException;
    void setTotal(double total) throws RemoteException;
    
    String getFecha() throws RemoteException;
    void setFecha(String fecha) throws RemoteException;
    
    int getEstado() throws RemoteException;
    void setEstado(int estado) throws RemoteException;
    
    String getString() throws RemoteException;
}
