package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.Naming;
import java.rmi.RemoteException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import gui.utils.ActionButton;
import services.IBibliotheque;
import services.IResource;


public class GestionLivres extends JFrame {
	private static final long serialVersionUID = 1L;
	IBibliotheque biblio;

	private JPanel contentPane;
	private JTextField tfTitre;
	private JTextField tfAuteur;
	private JTextField tfEditeur;
	private JTextField tfISBN;
	private JTextField tfEmplacement;
	private JTable table;
	
	public GestionLivres() {
		System.setProperty("java.rmi.server.hostname","localhost");
		try {
			this.biblio = (IBibliotheque)Naming.lookup("rmi://localhost:3000/bibliotheque");
			init();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}	
	}
	
	public static void main(String[] args) {
		new GestionLivres();
	}

	public void init() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				fetchUsagers();
			}
		});
		setTitle("Gestion Livres");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbNom = new JLabel("Titre");
		lbNom.setFont(new Font("Lato", Font.BOLD, 20));
		lbNom.setBounds(15, 210, 155, 30);
		contentPane.add(lbNom);
		
		JLabel lbAdresse = new JLabel("Auteur");
		lbAdresse.setFont(new Font("Lato", Font.BOLD, 20));
		lbAdresse.setBounds(15, 260, 155, 30);
		contentPane.add(lbAdresse);
		
		JLabel lbCategorie = new JLabel("Editeur");
		lbCategorie.setFont(new Font("Lato", Font.BOLD, 20));
		lbCategorie.setBounds(15, 310, 155, 30);
		contentPane.add(lbCategorie);
		
		JLabel lbISBN = new JLabel("ISBN");
		lbISBN.setFont(new Font("Lato", Font.BOLD, 20));
		lbISBN.setBounds(15, 360, 155, 30);
		contentPane.add(lbISBN);
		
		JLabel lbEmplacement = new JLabel("Emplacement");
		lbEmplacement.setFont(new Font("Lato", Font.BOLD, 20));
		lbEmplacement.setBounds(15, 410, 155, 30);
		contentPane.add(lbEmplacement);
		
		tfTitre = new JTextField();
		tfTitre.setFont(new Font("Lato", Font.BOLD, 20));
		tfTitre.setBounds(185, 215, 230, 30);
		contentPane.add(tfTitre);
		tfTitre.setColumns(10);
		
		tfAuteur = new JTextField();
		tfAuteur.setFont(new Font("Lato", Font.BOLD, 20));
		tfAuteur.setBounds(185, 265, 230, 30);
		contentPane.add(tfAuteur);
		tfAuteur.setColumns(10);
		
		tfEditeur = new JTextField();
		tfEditeur.setFont(new Font("Lato", Font.BOLD, 20));
		tfEditeur.setBounds(185, 315, 230, 30);
		tfEditeur.setColumns(10);
		contentPane.add(tfEditeur);
		
		tfISBN = new JTextField();
		tfISBN.setFont(new Font("Lato", Font.BOLD, 20));
		tfISBN.setBounds(185, 365, 230, 30);
		tfISBN.setColumns(10);
		contentPane.add(tfISBN);
		
		tfEmplacement = new JTextField();
		tfEmplacement.setFont(new Font("Lato", Font.BOLD, 20));
		tfEmplacement.setBounds(185, 415, 230, 30);
		tfEmplacement.setColumns(10);
		contentPane.add(tfEmplacement);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(456, 215, 418, 260);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnAddLivre = new ActionButton();
		btnAddLivre.setIcon(new ImageIcon("images\\plus (1).png"));
		btnAddLivre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addLivre();
			}
		});
		btnAddLivre.setBounds(25, 490, 50, 50);
		contentPane.add(btnAddLivre);
		
		JButton btnDeleteLivre = new ActionButton();
		btnDeleteLivre.setIcon(new ImageIcon("images\\iconfinder_f-cross_256_282471 (1).png"));
		btnDeleteLivre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteLivre();
			}
		});
		btnDeleteLivre.setBounds(225, 490, 50, 50);
		contentPane.add(btnDeleteLivre);
		
		JButton btnUpdateLivre = new ActionButton();
		btnUpdateLivre.setIcon(new ImageIcon("images\\updated.png"));
		btnUpdateLivre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                 updateLivre();
			}
		});
		btnUpdateLivre.setBounds(425, 490, 50, 50);
		contentPane.add(btnUpdateLivre);
		
		JButton btnBack = new ActionButton();
		btnBack.setIcon(new ImageIcon("images\\back.png"));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new BiblioDashboard();
			}
		});
		
		btnBack.setBounds(800, 490, 50, 50);
		contentPane.add(btnBack);
		
		JLabel label = new JLabel("");
		label.setBounds(879, 100, 239, 163);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("images\\collage2.jpg"));
		lblNewLabel.setBounds(0, 0, 947, 202);
		contentPane.add(lblNewLabel);
		this.setVisible(true);
	}
	
	
	public void fetchUsagers()
	{
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("LIVRE ID");
		model.addColumn("TITRE");
		model.addColumn("AUTEUR");
		model.addColumn("EDITEUR");
		model.addColumn("ISBN");
		model.addColumn("EMPLACEMENT");
	
		try {
			for (IResource livre: biblio.livresListe()) {
				model.addRow(new Object[] {
						livre.getElement("livre_id"),
						livre.getElement("titre"),
						livre.getElement("auteur"),
						livre.getElement("editeur"),
						livre.getElement("ISBN"),
						livre.getElement("emplacement"),						
				});
			}		
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		
		table.setModel(model);
		table.setAutoResizeMode(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(130);
		table.getColumnModel().getColumn(3).setPreferredWidth(130);
	}
	
	
	public void addLivre()
	{
	     if(!tfTitre.getText().equals("") && !tfAuteur.getText().equals("") && !tfEditeur.getText().equals("") && !tfISBN.getText().equals("") && !tfEmplacement.getText().equals("")) {
	    	 try {
				if(biblio.ajouterLivre(tfTitre.getText(), tfAuteur.getText(), tfEditeur.getText(), tfISBN.getText(), tfEmplacement.getText())) {
					fetchUsagers();
					tfTitre.setText("");
					tfAuteur.setText("");
					tfEditeur.setText("");
					tfISBN.setText("");
					tfEmplacement.setText("");
					JOptionPane.showMessageDialog(null, "Livre ajouté");
				 }
			} catch (RemoteException e) {
				System.err.println(e.getMessage());
			}
	     }
	     else {
    		 JOptionPane.showMessageDialog(null, "Il faut remplir tous les champs", "Erreur",JOptionPane.ERROR_MESSAGE);
	     }
	}
	
	public void updateLivre()
	{
		int rowIndex=table.getSelectedRow();
		if(rowIndex >= 0) {
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			int LivreId= Integer.parseInt(model.getValueAt(rowIndex, 0).toString());
			String titre=model.getValueAt(rowIndex, 1).toString();
			String auteur=model.getValueAt(rowIndex, 2).toString();
			String editeur=model.getValueAt(rowIndex, 3).toString();
			String ISBN=model.getValueAt(rowIndex, 4).toString();
			String emplacement=model.getValueAt(rowIndex, 5).toString();
			
			try {
				if(biblio.modifierLivre(LivreId, titre, auteur, editeur, ISBN, emplacement)){
					fetchUsagers();
					JOptionPane.showMessageDialog(null, "Livre modifié");
				 }
			} catch (RemoteException e) {
				System.err.println(e.getMessage());
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Il faut selectionner un element", "Information",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void deleteLivre()
	{
		int rowIndex=table.getSelectedRow();
		if(rowIndex >= 0) {
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			int livreId= Integer.parseInt(model.getValueAt(rowIndex, 0).toString());
			try {
				if(biblio.supprimerLivre(livreId)){
					fetchUsagers();
					JOptionPane.showMessageDialog(null, "Livre supprimé");
				 }
			} catch (RemoteException e) {
				System.err.println(e.getMessage());
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Il faut selectionner un element", "Information",JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
