package server;

import classserver.ClassFileServer;
import classserver.ClassServer;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.rmi.RemoteException;

/**
 * Define the class to implement the DoorServer interface.
 * @author M. Jeff Wilson
 * @version 1.1
 */
public class DoorServerImpl extends java.rmi.server.UnicastRemoteObject
        implements DoorServer
{
    private ClassServer classServer;
    /**
     * HashMap used to store instances of DoorImpl. The map will be keyed
     * by each DoorImpl's name attribute, so it is implied that two Doors
     * with the same name are equivalent.
     */
    private java.util.Hashtable hash = new java.util.Hashtable();

    public DoorServerImpl() throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        // add a door to the hashmap
        Class<Door> DoorImpl = (Class<Door>) findClass("services/DoorImpl.class");//TODO verif
        Door impl = (Door) DoorImpl.getConstructor(String.class).newInstance("location1");
        hash.put(impl.getLocation(), impl);
    }
    /**
     * Changed to return the proxy.
     * @param location - String value of the Door's location
     * @return an object that implements Door, given the location
     */
    public Door getDoor (String location) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Class<Door> DoorImpl = (Class<Door>) findClass("services/DoorImpl.class");//TODO verif
        DoorRemote doorImpl = (DoorRemote) hash.get(location);

        Class<Door> DoorProxy = (Class<Door>) findClass("services/DoorProxy.class");//TODO verif
        Door doorProxy = (Door) DoorImpl.getConstructor(DoorImpl).newInstance(doorImpl);

        return doorProxy;
    }


    public Object findClass(String path) throws IOException, ClassNotFoundException {//récupération d'une classe en particulier, en utilisant son chemin
        this.classServer = new ClassFileServer(4242, path);
        classServer.run();
        int port = classServer.getServerSocketPort();
        String hostName = classServer.getHostname();

        Socket socket = new Socket(hostName, port);

        DataInputStream dIn = new DataInputStream(socket.getInputStream());

        int lengthIn = dIn.readInt();                    // read length of incoming message
        if (lengthIn > 0) {
            String status = dIn.readLine();
            if (status.equals("HTTP/1.0 200 OK")) {
                String lengthLine = dIn.readLine();
                int lengthMsg = Integer.parseInt(lengthLine.replace("Content-Length: ", ""));
                dIn.readLine();//passing next line

                byte[] message = new byte[lengthMsg];
                dIn.readFully(message, 0, message.length); // read the message

                ByteArrayInputStream in = new ByteArrayInputStream(message);
                ObjectInputStream is = new ObjectInputStream(in);

                return is.readObject();
            }
        }
        return null;
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