package serveur;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import services.Bibliotheque;

public class Serveur {
	private static int PORT=3000;
	public static void main(String[] args) {
		try {
			Bibliotheque bibiotheque = new Bibliotheque();
			LocateRegistry.createRegistry(PORT);
			Naming.rebind("rmi://localhost:"+PORT+"/bibliotheque", bibiotheque);
			System.out.println("Serveur lancé sur le port "+PORT);
		} catch (Exception exception) {
			System.err.println("Erreur de liaison de l'objet bibliotheqe");
			System.err.println(exception.toString());
		}

	}

}
