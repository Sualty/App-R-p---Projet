import classes_necessaire_client.Resultat;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by sualty on 21/03/16.
 */
public interface InterfaceDistante extends Remote {
    void echo() throws RemoteException;
    String sayHello() throws  RemoteException;
    Resultat sayResultat() throws  RemoteException;
}
