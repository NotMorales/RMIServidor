package proyectormiservidor;

import Interfaces.IDetalleVenta;
import Interfaces.IDetalleVentaController;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetalleVentaController extends UnicastRemoteObject implements IDetalleVentaController{

    private DBManager dbManager;
    private final String TABLE = "detalleVenta";
    
    public DetalleVentaController() throws RemoteException {
        dbManager = new DBManager();
    }
    
    @Override
    public IDetalleVenta newInstance() throws RemoteException {
        return new DetalleVenta();
    }

    @Override
    public int add(IDetalleVenta detalleVenta) throws RemoteException {
        IDetalleVenta detalleVentaEncontrado = findOne(detalleVenta.getDetalleVentaId());
        boolean existe = detalleVentaEncontrado.getDetalleVentaId()!= 0;
        if(existe){
            return ADD_ID_DUPLICADO;
        }
        Map<String, Object> datos = DetalleVenta.toMap(detalleVenta);
        int respuesta = dbManager.insertar(TABLE, datos);
        return (respuesta > 0) ? ADD_EXITO : ADD_SIN_EXITO;
    }

    @Override
    public int update(IDetalleVenta detalleVenta) throws RemoteException {
        if(detalleVenta.getDetalleVentaId()== 0){
            return UPDATE_ID_NULO;
        }
        //Verificar que existe persona con ID recibido
        IDetalleVenta detalleVentaEncontrado = findOne(detalleVenta.getDetalleVentaId());
        if(detalleVentaEncontrado.getDetalleVentaId()== 0){
            return UPDATE_ID_INEXISTE;
        }
        Map<String,Object> datos = DetalleVenta.toMap(detalleVenta);
        Map<String,Object> where = new HashMap<>();
        where.put( "detalleVentaId", detalleVenta.getDetalleVentaId());
        int respuesta = dbManager.actualizar(TABLE, datos, where);
        return (respuesta > 0) ? UPDATE_EXITO :  UPDATE_SIN_EXITO;
    }

    @Override
    public int delete(IDetalleVenta detalleVenta) throws RemoteException {
        IDetalleVenta detalleVentaTemp = findOne(detalleVenta.getDetalleVentaId());
        if(detalleVentaTemp.getDetalleVentaId() == 0){
            return DELETE_ID_INEXISTENTE; //No existe venta
        }
        
        Map<String, Object> where = new HashMap<>();
        where.put("detalleVentaId", detalleVenta.getDetalleVentaId());
        
        int respuesta = dbManager.eliminar(TABLE, where);
        if(respuesta == 0){
            return DELETE_SIN_EXITO; //Retornar No fue posible eliminar
        }else{
            return DELETE_EXITO; //Eliminado con éxito
        }
    }

    @Override
    public int delete(int detalleVentaId) throws RemoteException {
        IDetalleVenta detalleVenta = new DetalleVenta();
        detalleVenta.setVentaId(detalleVentaId);
        return delete(detalleVenta);
    }

    @Override
    public List<IDetalleVenta> list() throws RemoteException {
        List<IDetalleVenta> listaIDetalleVentas = new ArrayList<>();
        List<Map <String, Object> > registros = dbManager.listar(TABLE);
        for(Map<String,Object> registro : registros){
            IDetalleVenta detalleVenta = DetalleVenta.fromMap(registro);
            listaIDetalleVentas.add(detalleVenta);
        }
        return listaIDetalleVentas;
    }

    @Override
    public IDetalleVenta findOne(int detalleVentaId) throws RemoteException {
        Map<String, Object> where = new HashMap<>();
        where.put("detalleVentaId", detalleVentaId);
        Map<String, Object> registro = dbManager.buscarUno(TABLE, where);
        return DetalleVenta.fromMap(registro);
    }

    @Override
    public List<IDetalleVenta> find(IDetalleVenta detalleVenta) throws RemoteException {
        List<IDetalleVenta> listaIDetalleVenta = new ArrayList<>();
        Map<String,Object> where = DetalleVenta.toMap(detalleVenta);
        List<Map <String, Object> > registros = dbManager.listar(TABLE, where);
        for(Map<String,Object> registro : registros){
            IDetalleVenta detalleVentaTemp = DetalleVenta.fromMap(registro);
            listaIDetalleVenta.add(detalleVentaTemp);
        }
        return listaIDetalleVenta;
    }
    
}
