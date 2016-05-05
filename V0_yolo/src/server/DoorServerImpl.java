package server;

import java.rmi.RemoteException;

/**
 * Define the class to implement the DoorServer interface.
 * @author M. Jeff Wilson
 * @version 1.1
 */
public class DoorServerImpl extends java.rmi.server.UnicastRemoteObject
        implements DoorServer
{
    /**
     * HashMap used to store instances of DoorImpl. The map will be keyed
     * by each DoorImpl's name attribute, so it is implied that two Doors
     * with the same name are equivalent.
     */
    private java.util.Hashtable hash = new java.util.Hashtable();
    public DoorServerImpl() throws java.rmi.RemoteException
    {
        // add a door to the hashmap
        DoorImpl impl = new DoorImpl("location1");
        hash.put(impl.getName(), impl);
    }
    /**
     * Changed to return the proxy.
     * @param location - String value of the Door's location
     * @return an object that implements Door, given the location
     */
    public Door getDoor (String location) throws RemoteException {
        DoorImpl impl = (DoorImpl)hash.get(location);
        return new DoorProxy(impl);
    }
    /**
     * Bootstrap the server by creating an instance of DoorServer and
     * binding its name in the RMI registry.
     */
    public static void main(String[] args)
    {
        System.setSecurityManager(new java.rmi.RMISecurityManager());
        // make the remote object available to clients
        try
        {
            DoorServerImpl server = new DoorServerImpl();
            java.rmi.Naming.rebind("rmi://localhost/DoorServer", server);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }
}