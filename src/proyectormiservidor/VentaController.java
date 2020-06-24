package proyectormiservidor;

import Interfaces.IVenta;
import Interfaces.IVentaController;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VentaController extends UnicastRemoteObject implements IVentaController{

    private DBManager dbManager;
    private final String TABLE = "venta";
    
    public VentaController() throws RemoteException {
        dbManager = new DBManager();
    }
    
    @Override
    public IVenta newInstance() throws RemoteException {
        return new Venta();
    }

    @Override
    public int add(IVenta venta) throws RemoteException {
        IVenta ventaEncontrado = findOne(venta.getVentaId());
        boolean existe = ventaEncontrado.getVentaId()!= 0;
        if(existe){
            return ADD_ID_DUPLICADO;
        }
        Map<String, Object> datos = Venta.toMap(venta);
        int respuesta = dbManager.insertar(TABLE, datos);
        return (respuesta > 0) ? ADD_EXITO : ADD_SIN_EXITO;
    }

    @Override
    public int update(IVenta venta) throws RemoteException {
        if(venta.getVentaId() == 0){
            return UPDATE_ID_NULO;
        }
        //Verificar que existe persona con ID recibido
        IVenta ventaEncontrado = findOne(venta.getVentaId());
        if(ventaEncontrado.getVentaId() == 0){
            return UPDATE_ID_INEXISTE;
        }
        Map<String,Object> datos = Venta.toMap(venta);
        Map<String,Object> where = new HashMap<>();
        where.put( "ventaId", venta.getVentaId() );
        int respuesta = dbManager.actualizar(TABLE, datos, where);
        return (respuesta > 0) ? UPDATE_EXITO :  UPDATE_SIN_EXITO;
    }

    @Override
    public int delete(IVenta venta) throws RemoteException {
        IVenta ventaTemp = findOne(venta.getVentaId());
        if(ventaTemp.getVentaId() == 0){
            return DELETE_ID_INEXISTENTE; //No existe venta
        }
        
        Map<String, Object> where = new HashMap<>();
        where.put("ventaId", venta.getVentaId());
        
        int respuesta = dbManager.eliminar(TABLE, where);
        if(respuesta == 0){
            return DELETE_SIN_EXITO; //Retornar No fue posible eliminar
        }else{
            return DELETE_EXITO; //Eliminado con éxito
        }
    }

    @Override
    public int delete(int ventaId) throws RemoteException {
        IVenta venta = new Venta();
        venta.setVentaId(ventaId);
        return delete(venta);
    }

    @Override
    public List<IVenta> list() throws RemoteException {
        List<IVenta> listaIVentas = new ArrayList<>();
        List<Map <String, Object> > registros = dbManager.listar(TABLE);
        for(Map<String,Object> registro : registros){
            IVenta venta = Venta.fromMap(registro);
            listaIVentas.add(venta);
        }
        return listaIVentas;
    }

    @Override
    public IVenta findOne(int ventaId) throws RemoteException {
        Map<String, Object> where = new HashMap<>();
        where.put("ventaId", ventaId);
        Map<String, Object> registro = dbManager.buscarUno(TABLE, where);
        return Venta.fromMap(registro);
    }

    @Override
    public List<IVenta> find(IVenta venta) throws RemoteException {
        List<IVenta> listaIVenta = new ArrayList<>();
        Map<String,Object> where = Venta.toMap(venta);
        List<Map <String, Object> > registros = dbManager.listar(TABLE, where);
        for(Map<String,Object> registro : registros){
            IVenta ventaTemp = Venta.fromMap(registro);
            listaIVenta.add(ventaTemp);
        }
        return listaIVenta;
    }
    
}
