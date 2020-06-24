package proyectormiservidor;

import Interfaces.IVenta;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class Venta extends UnicastRemoteObject implements IVenta{
    
    private int ventaId;
    int folio;
    double subTotal;
    double iva;
    double total;
    String fecha;
    int estado;
    
    public Venta() throws RemoteException {
    }
    
    public Venta(int ventaId, int folio, double subTotal, double iva, double total, String fecha, int estado) throws RemoteException {
        this.ventaId = ventaId;
        this.folio = folio;
        this.subTotal = subTotal;
        this.iva = iva;
        this.total = total;
        this.fecha = fecha;
        this.estado = estado;
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
    public int getFolio() throws RemoteException {
        return folio;
    }

    @Override
    public void setFolio(int folio) throws RemoteException {
        this.folio = folio;
    }

    @Override
    public double getSubTotal() throws RemoteException {
        return subTotal;
    }

    @Override
    public void setSubTotal(double subTotal) throws RemoteException {
        this.subTotal = subTotal;
    }

    @Override
    public double getIva() throws RemoteException {
        return iva;
    }

    @Override
    public void setIva(double iva) throws RemoteException {
        this.iva = iva;
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
    public String getFecha() throws RemoteException {
        return fecha;
    }

    @Override
    public void setFecha(String fecha) throws RemoteException {
        this.fecha = fecha;
    }

    @Override
    public int getEstado() throws RemoteException {
        return estado;
    }

    @Override
    public void setEstado(int estado) throws RemoteException {
        this.estado = estado;
    }

    @Override
    public String getString() throws RemoteException {
        return String.format( "VentaId: %d, Folio: %d, SubTotal: %d, Iva: %d, Total: %d, Fecha: %s", 
                    ventaId, folio, subTotal, iva, total, fecha );
    }
    
    public static IVenta fromMap( Map<String, Object> map ) throws RemoteException{
        IVenta venta = new Venta();
        
        if( map.containsKey("ventaId") )
            venta.setVentaId((Integer) map.get("ventaId") );
        
        if( map.containsKey("folio") )
            venta.setFolio((Integer) map.get("folio") );
        
        if( map.containsKey("subTotal") )
            venta.setSubTotal((double)map.get("subTotal"));
        
        if( map.containsKey("iva") )
            venta.setIva((double)map.get("iva"));
        
        if( map.containsKey("total") )
            venta.setTotal((double)map.get("total"));
        
        if( map.containsKey("fecha") )
            venta.setFecha((String)map.get("fecha"));
        
        if( map.containsKey("estado") )
            venta.setEstado((Integer) map.get("estado"));
        
        return venta;
    }  
    
    public static Map<String, Object> toMap(IVenta venta) throws RemoteException{
        Map<String, Object> datos = new HashMap<>();
        if ( venta.getVentaId()!= 0 ) {
            datos.put("ventaId", venta.getVentaId());
        }
        if ( venta.getFolio()!= 0 ) {
            datos.put("folio", venta.getFolio());
        }
        if ( venta.getSubTotal()!= 0 ) {
            datos.put("subTotal", venta.getSubTotal());
        }
        if ( venta.getIva()!= 0 ) {
            datos.put("iva", venta.getIva());
        }
        if ( venta.getTotal()!= 0 ) {
            datos.put("total", venta.getTotal());
        }
        if ( venta.getFecha()!= null ) {
            datos.put("fecha", venta.getFecha());
        }
        if ( venta.getEstado()!= 0 ) {
            datos.put("estado", venta.getEstado());
        }
        return datos;
    }
}
