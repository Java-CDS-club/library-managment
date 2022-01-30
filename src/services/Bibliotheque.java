package services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;


public class Bibliotheque extends UnicastRemoteObject implements IBibliotheque {
	private static final long serialVersionUID = 1L;

	public Bibliotheque() throws RemoteException {}

	@Override
	public boolean login(String email, String motDePasse) {
		try {
			PreparedStatement ps;
			ps = ConnecteurBD.getConnection().prepareStatement("SELECT * FROM bibliothecaire WHERE 	email= ? AND mot_de_passe= ?");
			ps.setString(1, email);
			ps.setString(2, motDePasse);
			ResultSet rs=ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean checkIn(String nom) {
		try {
			PreparedStatement ps;
			ps = ConnecteurBD.getConnection().prepareStatement("SELECT * FROM usagers WHERE nom=?");
			ps.setString(1, nom);
			ResultSet rs=ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	@Override
	public ArrayList<Usager> usagersListe() throws RemoteException {
		ArrayList<Usager> result=new ArrayList<Usager>();
		
		String query = "SELECT * FROM usagers";
		Statement st;
		try {
			st = ConnecteurBD.getConnection().createStatement();
			ResultSet rs= st.executeQuery(query);
			while(rs.next()){
				Usager item=new Usager(rs.getInt("usager_id"), rs.getString("nom"), rs.getString("adresse"), rs.getString("categorie"));
				result.add(item);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return result;
	}

	@Override
	public boolean ajouterUsager(String nom, String adresse, String categorie) throws RemoteException {
		try {
			PreparedStatement ps = ConnecteurBD.getConnection().prepareStatement("INSERT INTO usagers(nom, adresse, categorie) VALUES (?,?,?)");
		    ps.setString(1, nom);
			ps.setString(2, adresse);
		    ps.setString(3, categorie);
		    if(ps.executeUpdate()>0){
		   	 	return true;
		    }

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	    
	    return false;
	}

	@Override
	public boolean supprimerUsager(int usagerId) throws RemoteException {
		try {
			PreparedStatement ps = ConnecteurBD.getConnection().prepareStatement("DELETE FROM usagers where usager_id=?");
			ps.setInt(1, usagerId);
		     
		    if(ps.executeUpdate()>0){
		   	 	return true;
		    }
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	    
	    return false;
	}

	@Override
	public boolean modifierUsager(int usagerId, String nom, String adresse, String categorie) throws RemoteException {
		try {
			PreparedStatement ps = ConnecteurBD.getConnection().prepareStatement("UPDATE usagers SET nom=?, adresse=?, categorie=? where usager_id=?");
			ps.setString(1, nom);
			ps.setString(2, adresse);
		    ps.setString(3, categorie);
		    ps.setInt(4, usagerId);
		     
		    if(ps.executeUpdate()>0){
		   	 	return true;
		    }
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	    
	    return false;
	}

	@Override
	public ArrayList<Livre> livresListe() throws RemoteException {
		ArrayList<Livre> result=new ArrayList<Livre>();
		
		String query = "SELECT * FROM livres";
		Statement st;
		try {
			st = ConnecteurBD.getConnection().createStatement();
			ResultSet rs= st.executeQuery(query);
			while(rs.next()){
				Livre item=new Livre(rs.getInt("livre_id"), rs.getString("titre"), rs.getString("auteur"), rs.getString("editeur"), rs.getString("ISBN"), rs.getString("emplacement"));
				result.add(item);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return result;
	}
	
	@Override
	public ArrayList<Livre> livresListe(String keyword) throws RemoteException {
		ArrayList<Livre> result=new ArrayList<Livre>();
		try {
			String query = "SELECT * FROM livres WHERE titre LIKE ?";
			PreparedStatement ps=ConnecteurBD.getConnection().prepareStatement(query);
			ps.setString(1, "%"+keyword+"%");
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Livre item=new Livre(rs.getInt("livre_id"), rs.getString("titre"), rs.getString("auteur"), rs.getString("editeur"), rs.getString("ISBN"), rs.getString("emplacement"));
				result.add(item);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage()+" Search problem");
		}
		return result;
	}

	@Override
	public boolean ajouterLivre(String titre, String auteur, String editeur, String ISBN, String emplacement)
			throws RemoteException {
		
		try {
			PreparedStatement ps = ConnecteurBD.getConnection().prepareStatement("INSERT INTO livres(titre, auteur, editeur, ISBN, emplacement) VALUES (?,?,?,?,?)");
		    ps.setString(1, titre);
			ps.setString(2, auteur);
		    ps.setString(3, editeur);
		    ps.setString(4, ISBN);
		    ps.setString(5, emplacement);

		    if(ps.executeUpdate()>0){
		   	 	return true;
		    }

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	    
	    return false;
	}

	@Override
	public boolean supprimerLivre(int livreId) throws RemoteException {
		try {
			PreparedStatement ps = ConnecteurBD.getConnection().prepareStatement("DELETE FROM livres where livre_id=?");
			ps.setInt(1, livreId);
		     
		    if(ps.executeUpdate()>0){
		   	 	return true;
		    }
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	    
	    return false;
	}

	@Override
	public boolean modifierLivre(int livreId, String titre, String auteur, String editeur, String ISBN,
			String emplacement) throws RemoteException {
		try {
			PreparedStatement ps = ConnecteurBD.getConnection().prepareStatement("UPDATE livres SET titre=?, auteur=?, editeur=?, ISBN=?, emplacement=? where livre_id=?");
			ps.setString(1, titre);
			ps.setString(2, auteur);
		    ps.setString(3, editeur);
		    ps.setString(4, ISBN);
		    ps.setString(5, emplacement);
		    ps.setInt(6, livreId);
		     
		    if(ps.executeUpdate()>0){
		   	 	return true;
		    }
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	    
	    return false;
	}

	@Override
	public ArrayList<Livre> emruntsListe(int usagerId) throws RemoteException {
		ArrayList<Livre> result=new ArrayList<Livre>();
		
		String query = "SELECT * FROM livres WHERE emprunteur_id=?";
		try {
			PreparedStatement ps = ConnecteurBD.getConnection().prepareStatement(query);
			ps.setInt(1, usagerId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Livre item=new Livre(rs.getInt("livre_id"), rs.getString("titre"), rs.getString("auteur"), rs.getString("editeur"), rs.getString("ISBN"), rs.getString("emplacement"), rs.getInt("emprunteur_id"), rs.getDate("date_emprunt"), rs.getDate("date_retour"));
				result.add(item);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return result;
	}
	
	private long addDays(int days) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, days);
		return c.getTimeInMillis();
	}

	@Override
	public boolean ajouterEmprunt(int usagerId, String ISBN) throws RemoteException {
		try {
			java.util.Date currentDate=new java.util.Date();
			PreparedStatement ps = ConnecteurBD.getConnection().prepareStatement("UPDATE livres SET emprunteur_id=?, date_emprunt=?, date_retour=? where ISBN=?");
			ps.setInt(1, usagerId);
			ps.setDate(2, new Date(currentDate.getTime()));
			ps.setDate(3, new Date(addDays(15)));
		    ps.setString(4, ISBN);
		     
		    if(ps.executeUpdate()>0){
		   	 	return true;
		    }
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	    
	    return false;
	}
	

	@Override
	public boolean supprimerEmprunt(int usagerId, int livreId) throws RemoteException {
		try {
			PreparedStatement ps = ConnecteurBD.getConnection().prepareStatement("UPDATE livres SET emprunteur_id=?, date_emprunt=? where livre_id=?");
			ps.setNull(1, Types.INTEGER);
			ps.setNull(2, Types.DATE);
		    ps.setInt(3, livreId);
		     
		    if(ps.executeUpdate()>0){
		   	 	return true;
		    }
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	    
	    return false;
	}

	@Override
	public boolean modifierEmprunt(int livreId, String dateRetour) throws RemoteException {
		try {
			PreparedStatement ps = ConnecteurBD.getConnection().prepareStatement("UPDATE livres SET date_retour=? where livre_id=?");
			ps.setString(1, dateRetour);
		    ps.setInt(2, livreId);
		     
		    if(ps.executeUpdate()>0){
		   	 	return true;
		    }
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	    
	    return false;
	}

}
