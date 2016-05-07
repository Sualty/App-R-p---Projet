package services;

/**
 * Add the 'remoteness' to Door. Notice that you don't have to
 * redeclare the methods in interface Door, just inherit both
 * from Door and java.rmi.Remote.
 * @author M. Jeff Wilson
 * @version 1.0
 */
public interface DoorRemote extends java.rmi.Remote, Door
{
}