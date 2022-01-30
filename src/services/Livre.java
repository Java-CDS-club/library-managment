package services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class Livre extends UnicastRemoteObject implements IResource{
	private static final long serialVersionUID = 1L;
	
	private int livreId;
	private String titre;
	private String auteur;
	private String editeur;
	private String ISBN;
	private String emplacement;
	private int empruntId;
	private Date empruntDate;	
	private Date retourDate;	

	
	public Livre(int livreId, String titre,String auteur, String editeur, String iSBN, String emplacement, int empruntId,
			Date empruntDate, Date retourDate) throws RemoteException {
		this.livreId = livreId;
		this.titre=titre;
		this.auteur = auteur;
		this.editeur = editeur;
		this.ISBN = iSBN;
		this.emplacement = emplacement;
		this.empruntId = empruntId;
		this.empruntDate = empruntDate;
		this.retourDate = retourDate;
	}
	
	public Livre(int livreId, String titre,String auteur, String editeur, String iSBN, String emplacement) throws RemoteException {
		this.livreId = livreId;
		this.titre=titre;
		this.auteur = auteur;
		this.editeur = editeur;
		this.ISBN = iSBN;
		this.emplacement = emplacement;
	}

	public Livre(String titre, String auteur,String editeur, String iSBN, String emplacement, int empruntId, Date empruntDate) throws RemoteException {
		this.titre=titre;
		this.auteur = auteur;
		this.editeur = editeur;
		this.ISBN = iSBN;
		this.emplacement = emplacement;
		this.empruntId = empruntId;
		this.empruntDate = empruntDate;
	}

	public Livre(String titre, String auteur, String editeur, String iSBN, String emplacement) throws RemoteException {
		this.titre=titre;
		this.auteur = auteur;
		this.editeur = editeur;
		this.ISBN = iSBN;
		this.emplacement = emplacement;
	}

	public int getLivreId() {
		return livreId;
	}

	public void setLivreId(int livreId) {
		this.livreId = livreId;
	}
	
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getEditeur() {
		return editeur;
	}

	public void setEditeur(String editeur) {
		this.editeur = editeur;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}

	public int getEmpruntId() {
		return empruntId;
	}

	public void setEmpruntId(int empruntId) {
		this.empruntId = empruntId;
	}

	public Date getEmpruntDate() {
		return empruntDate;
	}

	public void setEmpruntDate(Date empruntDate) {
		this.empruntDate = empruntDate;
	}

	@Override
	public Object getElement(String element) throws RemoteException {
		switch (element) {
		case "livre_id": 
			return livreId;
		case "titre":
			return titre;
		case "auteur":
			return auteur;
		case "editeur":
			return editeur;
		case "ISBN":
			return ISBN;
		case "emplacement":
			return emplacement;
		case "emprunt_id":
			return empruntId;
		case "date_emprunt":
			return empruntDate;
		case "date_retour":
			return retourDate;
		default:
			throw new IllegalArgumentException("Unexpected value: " + element);
	}
	}
}
