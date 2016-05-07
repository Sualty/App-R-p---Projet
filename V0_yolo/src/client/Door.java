package client;

/**
 * Define the Door interface.
 * @author M. Jeff Wilson
 * @version 1.1
 */
public interface Door /* don't extend java.rmi.Remote */
{
    String getLocation() throws java.rmi.RemoteException;
    boolean isOpen() throws java.rmi.RemoteException;
}