package proyectormiservidor;

import Interfaces.IDetalleVenta;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class DetalleVenta extends UnicastRemoteObject implements IDetalleVenta{

    private int detalleVentaId;
    int ventaId;
    int productoId;
    int unidades;
    double precioUnidad;
    double total;
    
    public DetalleVenta() throws RemoteException {
    }
    
    public DetalleVenta(int detalleVentaId, int ventaId, int productoId, int unidades, double precioUnidad, double total) throws RemoteException {
        this.detalleVentaId = detalleVentaId;
        this.ventaId = ventaId;
        this.productoId = productoId;
        this.unidades = unidades;
        this.precioUnidad = precioUnidad;
        this.total = total;
    }
    
    @Override
    public int getDetalleVentaId() throws RemoteException {
        return detalleVentaId;
    }

    @Override
    public void setDetalleVentaId(int detalleVentaId) throws RemoteException {
        this.detalleVentaId = detalleVentaId;
    }

    @Override
    public int getVentaId() throws RemoteException {
        return ventaId;
    }

    @Override
    public void setVentaId(int ventaId) throws RemoteException {
        this.ventaId = ventaId;
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
    public int getUnidades() throws RemoteException {
        return unidades;
    }

    @Override
    public void setUnidades(int unidades) throws RemoteException {
        this.unidades = unidades;
    }

    @Override
    public double getPrecioUnidad() throws RemoteException {
        return precioUnidad;
    }

    @Override
    public void setPrecioUnidad(double precioUnidad) throws RemoteException {
        this.precioUnidad = precioUnidad;
    }

    @Override
    public double getTotal() throws RemoteException {
        return total;
    }

    @Override
    public void setTotal(double total) throws RemoteException {
        this.total = total;
    }

    @Override
    public String getString() throws RemoteException {
        return String.format( "Detalle Venta: %d, Venta: %d, Producto: %d, Unidades: %d, Total: %d", 
                    detalleVentaId, ventaId, productoId, unidades, total );
    }
    
    public static IDetalleVenta fromMap( Map<String, Object> map ) throws RemoteException{
        IDetalleVenta detalleVenta = new DetalleVenta();
        
        if( map.containsKey("detalleVentaId") )
            detalleVenta.setDetalleVentaId((Integer) map.get("detalleVentaId") );
        
        if( map.containsKey("ventaId") )
            detalleVenta.setVentaId((Integer) map.get("ventaId") );
        
        if( map.containsKey("productoId") )
            detalleVenta.setProductoId((Integer)map.get("productoId"));
        
        if( map.containsKey("unidades") )
            detalleVenta.setUnidades((Integer)map.get("unidades"));
        
        if( map.containsKey("precioUnidad") )
            detalleVenta.setPrecioUnidad((double)map.get("precioUnidad"));
        
        if( map.containsKey("total") )
            detalleVenta.setTotal((double)map.get("total"));
        
        
        return detalleVenta;
    }  
    
    public static Map<String, Object> toMap(IDetalleVenta detalleVenta) throws RemoteException{
        Map<String, Object> datos = new HashMap<>();
        if ( detalleVenta.getDetalleVentaId()!= 0 ) {
            datos.put("detalleVentaId", detalleVenta.getDetalleVentaId());
        }
        if ( detalleVenta.getVentaId()!= 0 ) {
            datos.put("ventaId", detalleVenta.getVentaId());
        }
        if ( detalleVenta.getProductoId()!= 0 ) {
            datos.put("productoId", detalleVenta.getProductoId());
        }
        if ( detalleVenta.getUnidades()!= 0 ) {
            datos.put("unidades", detalleVenta.getUnidades());
        }
        if ( detalleVenta.getPrecioUnidad()!= 0 ) {
            datos.put("precioUnidad", detalleVenta.getPrecioUnidad());
        }
        if ( detalleVenta.getTotal()!= 0 ) {
            datos.put("total", detalleVenta.getTotal());
        }
        return datos;
    }
    
}
