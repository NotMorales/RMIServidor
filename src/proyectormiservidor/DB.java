package proyectormiservidor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class DB extends UnicastRemoteObject{
    private static DBManager dbManager = null;
    
    private DB() throws RemoteException {
        dbManager = new DBManager();
    }
    public static DBManager getInstance() throws RemoteException {
        if(dbManager == null){
            new DB();
        }
        return dbManager;
    }
}
