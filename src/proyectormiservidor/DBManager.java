 package proyectormiservidor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level; 
import java.util.logging.Logger;

public class DBManager {
    private Connection conexion;
    
    public DBManager(){
        String url = "jdbc:sqlite:C:\\Users\\Luisa\\OneDrive\\Documentos\\NetBeansProjects\\RMIServidor30\\db.sql";
        //String url = "jdbc:sqlite:C:\\Users\\luis2\\Documents\\NetBeansProjects\\RMIServidor\\db.sql";
        try {
            conexion = DriverManager.getConnection(url);
            System.out.println("Conexión a BD exitosa.");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    } // Fin constructor
    
    /* Insertar */
    public int insertar(String tabla, Map<String, Object> datos){
        String sql = String.format( "INSERT INTO %s (", tabla);
        for( String columna : datos.keySet() ){
            sql += String.format("%s, ", columna );
        } /// Fin for
        
        sql = sql.substring(0, sql.length() -2);
        sql += ") VALUES ( ";
        
        for( String columna : datos.keySet() ){
            Object dato = datos.get( columna );
            if( dato instanceof Number || dato instanceof Boolean  ){
                sql += dato +", ";
            }else{
                sql += String.format("'%s', ", dato );
            }
        } // Fin for
        sql = sql.substring(0, sql.length() -2);
        sql += ");";
        
        System.out.println(sql);
        
        int respuesta = 0;
        try {
            Statement statement = conexion.createStatement();
            respuesta = statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return respuesta;
        }
    }
    
    public double getMontoTotal(String tabla, String[] fechas ){
        String sql = String.format("SELECT SUM(v.total) FROM venta v, %s dv\n" +
                    "WHERE v.ventaId = dv.ventaId AND v.fecha \n" +
                    "BETWEEN ", tabla);
        double monto = 0;
        for (String fecha : fechas) {
            sql += String.format("'%s' AND ", fecha);
        }
        sql = sql.substring(0, sql.length()-4);
        
        try {
            Statement statement = conexion.createStatement();
            ResultSet resultset = statement.executeQuery(sql);
            
            monto = (Double) resultset.getObject(1);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            return monto;
        }
    }
    
    public int getNumVentas(String tabla){
        String sql = String.format("SELECT COUNT(*) AS ventas FROM %s", tabla);
        int numVentas = 0;
        try {
            Statement statement = conexion.createStatement();
            ResultSet resulset = statement.executeQuery(sql);
            
            numVentas = (Integer)resulset.getObject(1);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            return numVentas;
        }
    }
    
    public int cancelarVenta( String tabla, int ventaid ){
        String sql = String.
                format("UPDATE %s SET estado = 0 WHERE ventaId = %s;",tabla,ventaid);
        
        Statement statement;
        int respuesta = 0;
        
        try {
            statement = conexion.createStatement();
            respuesta = statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            return respuesta;
        }
    }
    
    /* Actualizar */
    public int actualizar(String tabla, Map<String, Object> datos){
        return actualizar(tabla, datos, null);
    }
    
    public int actualizar(String tabla, Map<String, Object> datos, Map<String, Object> where){
        String sql = String.format( "UPDATE %s SET ", tabla);
        for( String columna : datos.keySet() ){
            Object dato = datos.get( columna );
            if( dato instanceof Number || dato instanceof Boolean ){
                sql += String.format("%s = %s, ", columna, dato );
            }else{
                sql += String.format("%s = '%s', ", columna, dato );
            }
        } /// Fin for
        
        sql = sql.substring(0, sql.length() -2);
        
        if( where != null && !where.isEmpty() ){
            sql += " WHERE ";
            for( String columna : where.keySet() ){
                Object dato = where.get( columna );
                if( dato instanceof Number || dato instanceof Boolean ){
                    sql += String.format("%s = %s AND ", columna, dato );
                }else{
                    sql += String.format("%s = '%s' AND ", columna, dato );
                }
            } /// Fin for
            sql = sql.substring(0, sql.length() -4);
        } // Fin if
        
        System.out.printf("Ejecutando: %s\n", sql);
        
        Statement statement;
        int respuesta = 0;
        try {
            statement = conexion.createStatement();
            respuesta = statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return respuesta;
        }
    } // Fin actualizar
    
    /* Eliminar */
    public int eliminar( String tabla ){
        return eliminar(tabla, null);
    }
    
    public int eliminar(String tabla, Map<String, Object> where){
        String sql = String.format( "DELETE FROM %s", tabla);
        
        if( where != null && !where.isEmpty() ){
            sql += " WHERE ";
            for( String columna : where.keySet() ){
                Object dato = where.get( columna );
                if( dato instanceof Number || dato instanceof Boolean ){
                    sql += String.format("%s = %s AND ", columna, dato );
                }else{
                    sql += String.format("%s = '%s' AND ", columna, dato );
                }
            } /// Fin for
            sql = sql.substring(0, sql.length() -4);
        } // Fin if
        
        System.out.printf("Ejecutando: %s\n", sql);
        
        int respuesta = 0;
        try {
            Statement statement = conexion.createStatement();
            respuesta = statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return respuesta;
        }
    }
    
    /* LISTAR */
    public List< Map<String, Object> > listar(String tabla ){
        return listar(tabla, null);
    }
    
    public List< Map<String, Object> > getProductosVendidos(String[] fechas){
        String sql ="SELECT p.nombre, SUM(dv.unidades) AS unidades, v.fecha \n" +
                    "FROM producto p,detalleVenta dv, venta v\n" +
                    "WHERE p.productoId = dv.productoId AND \n" +
                    "v.ventaId = dv.ventaId AND \n" +
                    "v.fecha BETWEEN ";
        for (String fecha : fechas) {
            sql += String.format("'%S' AND ", fecha);
        }
        sql = sql.substring(0, sql.length()-4);
        sql = sql + "GROUP BY v.fecha, p.nombre";
        
        List< Map<String, Object> > registros = new ArrayList<>();
        
        try {
            Statement statement = conexion.createStatement();
            ResultSet resultset = statement.executeQuery(sql);
            ResultSetMetaData metaData = resultset.getMetaData();
            final int TOTAL_COLUMNAS = metaData.getColumnCount();
            
            while( resultset.next() ){
                Map<String, Object> registro = new HashMap<>();
                for (int i = 1; i <= TOTAL_COLUMNAS; i++) {
                    registro.put(metaData.getColumnName(i), resultset.getObject(i));
                }
                registros.add( registro );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return registros;
        }
    }
    
    public List< Map<String, Object> > listar(String tabla, Map<String, Object> where){
        String sql = String.format( "SELECT * FROM %s", tabla);
        
        if( where != null && !where.isEmpty() ){
            sql += " WHERE ";
            for( String columna : where.keySet() ){
                Object dato = where.get( columna );
                if( dato instanceof Number || dato instanceof Boolean ){
                    sql += String.format("%s = %s AND ", columna, dato );
                }else{
                    sql += String.format("%s = '%s' AND ", columna, dato );
                }
            } /// Fin for
            sql = sql.substring(0, sql.length() -4);
        } // Fin if
        
        System.out.printf("Consultando: %s\n", sql);
        
        List< Map<String, Object> > registros = new ArrayList<>();
        try{
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            ResultSetMetaData metaData = resultSet.getMetaData();
            final int TOTAL_COLUMNAS = metaData.getColumnCount();

            // Recuperar registros
            while( resultSet.next() ){
                Map<String, Object> registro = new HashMap<>();
                for(int i=1; i<= TOTAL_COLUMNAS; i++){
                    registro.put( metaData.getColumnName(i), resultSet.getObject(i) );
                }
                registros.add( registro );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return registros;
        }
    } // Fin listar(String,Map)
    
    /* BUSCAR UNO */
    public Map<String, Object> buscarUno(String tabla, Map<String, Object> where){
        String sql = String.format( "SELECT * FROM %s", tabla);
        
        if( where != null && !where.isEmpty() ){
            sql += " WHERE ";
            for( String columna : where.keySet() ){
                Object dato = where.get( columna );
                if( dato instanceof Number || dato instanceof Boolean ){
                    sql += String.format("%s = %s AND ", columna, dato );
                }else{
                    sql += String.format("%s = '%s' AND ", columna, dato );
                }
            } /// Fin for
            sql = sql.substring(0, sql.length() -4);
        } // Fin if
        
        System.out.printf("Consultando: %s\n", sql);
        
        Map<String, Object> registro = new HashMap<>();
        try{
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            ResultSetMetaData metaData = resultSet.getMetaData();
            final int TOTAL_COLUMNAS = metaData.getColumnCount();

            // Recuperar registros
            if( resultSet.next() ){
                for(int i=1; i<=TOTAL_COLUMNAS; i++){
                    registro.put( metaData.getColumnName(i), resultSet.getObject(i) );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return registro;
        }
    } // Fin listar(String,Map)
    
}
