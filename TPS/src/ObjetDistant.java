import classes_necessaire_client.Resultat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by sualty on 21/03/16.
 */
public class ObjetDistant extends UnicastRemoteObject implements InterfaceDistante {

    public ObjetDistant(int numero_port) throws RemoteException {
        super(numero_port);
    }

    @Override
    public void echo() throws RemoteException {
        System.out.println("Coucou");
    }

    @Override
    public String sayHello() throws RemoteException {
        System.out.println("saying Hello");
        return "Hello";
    }

    @Override
    public Resultat sayResultat() throws RemoteException {
        return new Resultat();
    }
}
