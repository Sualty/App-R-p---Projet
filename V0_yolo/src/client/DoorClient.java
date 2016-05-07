package client;

import server.Door;
import server.DoorServer;

import java.rmi.Naming;

/**
 * Created by sualty on 05/05/16.
 */
public class DoorClient{
    public static void main(String[] args) {
        try {

            // get the DoorServer from the RMI registry
            DoorServer server = (DoorServer) Naming.lookup("rmi://localhost:4243/DoorServer");
            // Use DoorServer to get a specific Door
            Door theDoor = server.getDoor("location1");
            // invoke methods on the returned Door
            if (theDoor.isOpen()) {
                // handle the door-open case ...
                System.out.println("The door is open");
            }
            else {
                System.out.println("The door is closed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
