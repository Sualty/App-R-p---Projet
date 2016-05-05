import javax.naming.CompositeName;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;

/**
 * Created by sualty on 04/04/16.
 */
public class Serveur extends UnicastRemoteObject implements InterfaceDistante {

    protected Serveur() throws RemoteException {
    }

    @Override
    public void echo() throws RemoteException {

    }

    @Override
    public String sayHello() throws RemoteException {
        return null;
    }

    @Override
    public SubResultat sayResultat() throws RemoteException {
        SubResultat r = new SubResultat();
        r.i = 5001;
        return r;
    }

    public static void main(String[] args) {
        try {
            Hashtable hashtableEnvironment = new Hashtable();
            hashtableEnvironment.put(
                    Context.INITIAL_CONTEXT_FACTORY,
                    "com.sun.jndi.rmi.registry.RegistryContextFactory"
            );
            // the service: ldap://localhost:389/
            // the root context: dc=etcee,dc=com
            hashtableEnvironment.put(
                    Context.PROVIDER_URL,
                    "rmi://localhost:1098/"
            );
            Context context = new InitialContext(hashtableEnvironment);

            ObjetDistant objetDistant  = new ObjetDistant(4242);
            context.bind(new CompositeName("hello"), objetDistant);

        }
        catch (NamingException namingexception) {
            namingexception.printStackTrace();
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}