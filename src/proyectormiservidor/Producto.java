package proyectormiservidor;

import Interfaces.IProducto;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class Producto extends UnicastRemoteObject implements IProducto{
    private int productoId;
    int codigo;
    String marca;
    String nombre;
    double precio;

    public Producto() throws RemoteException {
    }
    
    public Producto(int productoId, int codigo, String marca, String nombre, double precio) throws RemoteException {
        this.productoId = productoId;
        this.codigo = codigo;
        this.marca = marca;
        this.nombre = nombre;
        this.precio = precio;
    }

    @Override
    public int getProductoId() throws RemoteException {
        return productoId;
    }

    @Override
    public void setProductoId(int productoId) throws RemoteException {
        this.productoId = productoId;
    }

    @Override
    public int getCodigo() throws RemoteException {
        return codigo;
    }

    @Override
    public void setCodigo(int codigo) throws RemoteException {
        this.codigo = codigo;
    }

    @Override
    public String getMarca() throws RemoteException {
        return marca;
    }

    @Override
    public void setMarca(String marca) throws RemoteException {
        this.marca = marca;
    }

    @Override
    public String getNombre() throws RemoteException {
        return nombre;
    }

    @Override
    public void setNombre(String nombre) throws RemoteException {
        this.nombre = nombre;
    }

    @Override
    public double getPrecio() throws RemoteException {
        return precio;
    }

    @Override
    public void setPrecio(double precio) throws RemoteException {
        this.precio = precio;
    }

    @Override
    public String getString() throws RemoteException {
        return String.format( "ProductoId: %d, Codigo: %d, Marca: %s, Nombre: %s, Precio: %d", 
                    productoId, codigo, marca, nombre, precio );
    }
    
    public static IProducto fromMap( Map<String, Object> map ) throws RemoteException{
        IProducto producto = new Producto();
        
        if( map.containsKey("productoId") )
            producto.setProductoId((Integer) map.get("productoId") );
        
        if( map.containsKey("codigo") )
            producto.setCodigo((Integer) map.get("codigo") );
        
        if( map.containsKey("marca") )
            producto.setMarca((String)map.get("marca"));
        
        if( map.containsKey("nombre") )
            producto.setNombre((String)map.get("nombre"));
        
        if( map.containsKey("precio") )
            producto.setPrecio((double)map.get("precio"));
        
        return producto;
    }  
    
    public static Map<String, Object> toMap(IProducto producto) throws RemoteException{
        Map<String, Object> datos = new HashMap<>();
        if ( producto.getProductoId() != 0 ) {
            datos.put("productoId", producto.getProductoId());
        }
        if ( producto.getCodigo() != 0 ) {
            datos.put("codigo", producto.getCodigo());
        }
        if ( producto.getMarca() != null ) {
            datos.put("marca", producto.getMarca());
        }
        if ( producto.getNombre() != null ) {
            datos.put("nombre", producto.getNombre());
        }
        if ( producto.getPrecio() != 0 ) {
            datos.put("precio", producto.getPrecio());
        }
        return datos;
    }
}
