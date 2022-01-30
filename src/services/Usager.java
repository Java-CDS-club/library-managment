package services;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Usager extends UnicastRemoteObject implements IResource{
	private static final long serialVersionUID = 1L;
	
	private int usagerId;
	private String nom;
	private String adresse;
	private String categorie;
	
	public Usager(int usagerId, String nom, String adresse, String categorie) throws RemoteException {
		this.usagerId = usagerId;
		this.nom = nom;
		this.adresse = adresse;
		this.categorie = categorie;
	}

	public Usager(String nom, String adresse, String categorie) throws RemoteException {
		this.nom = nom;
		this.adresse = adresse;
		this.categorie = categorie;
	}

	public int getUsagerId() {
		return usagerId;
	}

	public void setUsagerId(int usagerId) {
		this.usagerId = usagerId;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	@Override
	public Object getElement(String element){
		switch (element) {
			case "usager_id": 
				return usagerId;
			case "nom":
				return nom;
			case "adresse":
				return adresse;
			case "categorie":
				return categorie;
			default:
				throw new IllegalArgumentException("Unexpected value: " + nom);
		}
	}
}
