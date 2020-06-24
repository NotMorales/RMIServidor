package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDetalleVenta extends Remote{
    int getDetalleVentaId() throws RemoteException; 
    void setDetalleVentaId(int detalleVentaId) throws RemoteException;
    
    int getVentaId() throws RemoteException; 
    void setVentaId(int ventaId) throws RemoteException;
    
    int getProductoId() throws RemoteException; 
    void setProductoId(int productoId) throws RemoteException;
    
    int getUnidades() throws RemoteException; 
    void setUnidades(int unidades) throws RemoteException;
    
    double getPrecioUnidad() throws RemoteException; 
    void setPrecioUnidad(double precioUnidad) throws RemoteException;
    
    double getTotal() throws RemoteException;
    void setTotal(double total) throws RemoteException;
    
    String getString() throws RemoteException;
    
}
