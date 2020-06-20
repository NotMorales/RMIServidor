package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IProducto extends Remote{
    int getProductoId() throws RemoteException; 
    void setProductoId(int productoId) throws RemoteException;
    
    int getCodigo() throws RemoteException;
    void setCodigo(int codigo) throws RemoteException;
    
    String getMarca() throws RemoteException;
    void setMarca(String marca) throws RemoteException;
    
    String getNombre() throws RemoteException;
    void setNombre(String nombre) throws RemoteException;
    
    double getPrecio() throws RemoteException;
    void setPrecio(double precio) throws RemoteException;
    
    String getString() throws RemoteException;
}
