package services;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IResource extends Remote {
	public Object getElement(String element) throws RemoteException;
}
