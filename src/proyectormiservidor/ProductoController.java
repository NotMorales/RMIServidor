package proyectormiservidor;

import Interfaces.IProducto;
import Interfaces.IProductoController;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductoController extends UnicastRemoteObject implements IProductoController{

    private DBManager dbManager;
    private final String TABLE = "producto";
    
    public ProductoController() throws RemoteException {
        DBManager db = DB.getInstance();
        dbManager = db;
    }
    
    @Override
    public IProducto newInstance() throws RemoteException {
        return new Producto();
    }

    @Override
    public int add(IProducto producto) throws RemoteException {
        IProducto productoEncontrado = findOne(producto.getProductoId());
        boolean existe = productoEncontrado.getProductoId()!= 0;
        if(existe){
            return ADD_ID_DUPLICADO;
        }
        Map<String, Object> datos = Producto.toMap(producto);
        int respuesta = dbManager.insertar(TABLE, datos);
        return (respuesta > 0) ? ADD_EXITO : ADD_SIN_EXITO;
    }

    @Override
    public int update(IProducto producto) throws RemoteException {
        if(producto.getProductoId() == 0){
            return UPDATE_ID_NULO;
        }
        //Verificar que existe persona con ID recibido
        IProducto productoEncontrado = findOne(producto.getProductoId());
        if(productoEncontrado.getProductoId()== 0){
            return UPDATE_ID_INEXISTE;
        }
        Map<String,Object> datos = Producto.toMap(producto);
        Map<String,Object> where = new HashMap<>();
        where.put( "productoId", producto.getProductoId() );
        int respuesta = dbManager.actualizar(TABLE, datos, where);
        return (respuesta > 0) ? UPDATE_EXITO :  UPDATE_SIN_EXITO;
    }

    @Override
    public int delete(IProducto producto) throws RemoteException {
        IProducto productoTemp = findOne(producto.getProductoId());
        if(productoTemp.getProductoId() == 0){
            return DELETE_ID_INEXISTENTE; //No existe persona
        }
        
        Map<String, Object> where = new HashMap<>();
        where.put("productoId", producto.getProductoId());
        
        int respuesta = dbManager.eliminar(TABLE, where);
        if(respuesta == 0){
            return DELETE_SIN_EXITO; //Retornar No fue posible eliminar
        }else{
            return DELETE_EXITO; //Eliminado con éxito
        }
    }

    @Override
    public int delete(int productoId) throws RemoteException {
        IProducto producto = new Producto();
        producto.setProductoId(productoId);
        return delete(producto);
    }

    @Override
    public List<IProducto> list() throws RemoteException {
        List<IProducto> listaIProductos = new ArrayList<>();
        List<Map <String, Object> > registros = dbManager.listar(TABLE);
        for(Map<String,Object> registro : registros){
            IProducto producto = Producto.fromMap(registro);
            listaIProductos.add(producto);
        }
        return listaIProductos;
    }

    @Override
    public IProducto findOne(int productoId) throws RemoteException {
        Map<String, Object> where = new HashMap<>();
        where.put("productoId", productoId);
        Map<String, Object> registro = dbManager.buscarUno(TABLE, where);
        return Producto.fromMap(registro);
    }

    @Override
    public List<IProducto> find(IProducto producto) throws RemoteException {
        List<IProducto> listaIProducto = new ArrayList<>();
        Map<String,Object> where = Producto.toMap(producto);
        List<Map <String, Object> > registros = dbManager.listar(TABLE, where);
        for(Map<String,Object> registro : registros){
            IProducto productoTemp = Producto.fromMap(registro);
            listaIProducto.add(productoTemp);
        }
        return listaIProducto;
    }
    
}
