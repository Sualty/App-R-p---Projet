package services;

import server.DoorRemote;

/**
 * Define the remote object that implements the Door interface.
 * @author M. Jeff Wilson
 * @version 1.1
 */
public class DoorImpl extends java.rmi.server.UnicastRemoteObject
        implements DoorRemote
{
    private final String name;
    private boolean open = false;
    public DoorImpl(String name) throws java.rmi.RemoteException
    {
        super();
        this.name = name;
    }
    // in this implementation, each Door's name is the same as its location.
    // we're also assuming the location will be unique
    public String getLocation() { return name; }
    public boolean isOpen() { return open; }
    // assume the server side can call this method to set the
    // state of this door at any time
    public void setOpen(boolean open) { this.open = open; }
    // convenience method for server code
    // override various Object utility methods
    public String toString() { return "DoorImpl:["+ name +"]"; }
    // DoorImpls are equivalent if they are in the same location
    public boolean equals(Object obj)
    {
        if (obj instanceof DoorImpl)
        {
            DoorImpl other = (DoorImpl)obj;
            return name.equals(other.name);
        }
        return false;
    }
    public int hashCode() { return toString().hashCode(); }
}