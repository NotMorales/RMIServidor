package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IProductoController extends Remote {
    IProducto newInstance() throws RemoteException;
    int add(IProducto producto) throws RemoteException;
    int update(IProducto producto) throws RemoteException;
    int delete (IProducto producto) throws RemoteException;
    int delete(int productoId) throws RemoteException;
    List<IProducto> list() throws RemoteException;
    IProducto findOne(int productoId) throws RemoteException;
    IProducto findOneCodigo(int codigo) throws RemoteException;
    List<IProducto> find(IProducto producto) throws RemoteException;
    
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
