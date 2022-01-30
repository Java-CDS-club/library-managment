package services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface IBibliotheque extends Remote{
	// Admin login
	public boolean login(String email, String motDePasse) throws RemoteException;
	
	// Client login
	public boolean checkIn(String nom) throws RemoteException;
	
	// Usagers
	public ArrayList<Usager> usagersListe() throws RemoteException;
	public boolean ajouterUsager(String nom, String adresse, String categorie) throws RemoteException;
	public boolean supprimerUsager(int usagerId) throws RemoteException;
	public boolean modifierUsager(int usagerId, String nom, String adresse, String categorie) throws RemoteException;
	
	//Livres
	public ArrayList<Livre> livresListe(String keyword) throws RemoteException;
	public ArrayList<Livre> livresListe() throws RemoteException;
	public boolean ajouterLivre(String titre, String auteur, String editeur, String ISBN, String emplacement) throws RemoteException;
	public boolean supprimerLivre(int livreId) throws RemoteException;
	public boolean modifierLivre(int livreId, String titre, String auteur, String editeur, String ISBN, String emplacement) throws RemoteException;
	
	//Emrunts
	public ArrayList<Livre> emruntsListe(int usagerId) throws RemoteException;	
	public boolean ajouterEmprunt(int usagerId, String ISBN) throws RemoteException;	
	public boolean supprimerEmprunt(int usagerId, int livreId) throws RemoteException;
	public boolean modifierEmprunt(int usagerId, String dateEmprunt) throws RemoteException;
}
