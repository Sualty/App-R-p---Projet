import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by sualty on 21/03/16.
 */
public class Client {
    public static void main(String[] args) {
        if(System.getSecurityManager()==null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            InterfaceDistante distante = (InterfaceDistante) Naming.lookup("rmi://localhost:1098/hello");
            //les deux lignes suivantes sont équivalentes à la ligne ci dessus
/*            Registry registry = LocateRegistry.getRegistry(1098);
            Distante distante = (Distante) registry.lookup("hello");*/
            System.out.println(distante.sayResultat());

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
