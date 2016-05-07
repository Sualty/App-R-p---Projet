package server;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * We need a class to serve Door objects to clients.
 * First, create the server's remote interface.
 * @author M. Jeff Wilson
 * @version 1.0
 */
public interface DoorServer extends java.rmi.Remote
{
    Door getDoor(String location) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;
}