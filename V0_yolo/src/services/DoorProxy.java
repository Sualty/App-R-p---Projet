package services;

import server.Door;
import server.DoorRemote;

/**
 * Define a proxy for Door. In this version, the name field
 * is cached in the proxy, and I've overridden equals() and
 * hashCode().
 * @author M. Jeff Wilson
 * @version 1.3
 */
public class DoorProxy implements java.io.Serializable, Door
{
    // store a copy of the remote interface to a DoorImpl
    private DoorRemote impl = null;
    private final String name;
    /**
     * Construct a DoorProxy.
     * @param impl - the remote reference to delegate to.
     */
    public DoorProxy(DoorRemote impl) throws java.rmi.RemoteException
    {
        this.impl = impl;
        name = impl.getLocation();
    }
    public String getLocation() throws java.rmi.RemoteException
    {
        // return the cached value
        return name;
    }
    public boolean isOpen() throws java.rmi.RemoteException
    {
        // delegate to impl
        return impl.isOpen();
    }
    public boolean equals(Object obj)
    {
        if (obj instanceof DoorProxy)
        {
            return impl.equals(((DoorProxy)obj).impl);
        }
        return false;
    }
    public int hashCode()
    {
        return impl.hashCode();
    }
}