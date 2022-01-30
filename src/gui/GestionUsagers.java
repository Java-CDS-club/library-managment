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
import javax.swing.JComboBox;
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


public class GestionUsagers extends JFrame {
	private static final long serialVersionUID = 1L;
	IBibliotheque biblio;

	private JPanel contentPane;
	private JTextField tfNom;
	private JTextField tfAdresse;
	private String[] categories={ null, "Scolaire", "Etudiant", "Senior", "Travailleur", "Chomeur" };
	private JComboBox<String> cbCategorie;
	private JTable table;
	
	public GestionUsagers() {
		System.setProperty("java.rmi.server.hostname","localhost");
		try {
			this.biblio = (IBibliotheque)Naming.lookup("rmi://localhost:3000/bibliotheque");
			init();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}	
	}

	public void init() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				fetchUsagers();
			}
		});
		setTitle("Gestion Usagers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbNom = new JLabel("Nom");
		lbNom.setFont(new Font("Lato", Font.BOLD, 20));
		lbNom.setBounds(15, 210, 155, 30);
		contentPane.add(lbNom);
		
		JLabel lbAdresse = new JLabel("Adresse");
		lbAdresse.setFont(new Font("Lato", Font.BOLD, 20));
		lbAdresse.setBounds(15, 285, 155, 30);
		contentPane.add(lbAdresse);
		
		JLabel lbCategorie = new JLabel("Categorie");
		lbCategorie.setFont(new Font("Lato", Font.BOLD, 20));
		lbCategorie.setBounds(15, 360, 155, 30);
		contentPane.add(lbCategorie);
		
		tfNom = new JTextField();
		tfNom.setFont(new Font("Lato", Font.BOLD, 20));
		tfNom.setBounds(185, 215, 230, 30);
		contentPane.add(tfNom);
		tfNom.setColumns(10);
		
		tfAdresse = new JTextField();
		tfAdresse.setFont(new Font("Lato", Font.BOLD, 20));
		tfAdresse.setBounds(185, 290, 230, 30);
		contentPane.add(tfAdresse);
		tfAdresse.setColumns(10);
		
		cbCategorie = new JComboBox<String>(categories);
		cbCategorie.setFont(new Font("Lato", Font.BOLD, 20));
		cbCategorie.setBounds(185, 365, 230, 30);
		contentPane.add(cbCategorie);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(456, 215, 418, 260);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnAddUsager = new ActionButton();
		btnAddUsager.setIcon(new ImageIcon("images\\plus (1).png"));
		btnAddUsager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addUsager();
			}
		});
		btnAddUsager.setBounds(25, 490, 50, 50);
		contentPane.add(btnAddUsager);
		
		JButton btnDeleteUsager = new ActionButton();
		btnDeleteUsager.setIcon(new ImageIcon("images\\iconfinder_f-cross_256_282471 (1).png"));
		btnDeleteUsager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteUsager();
			}
		});
		btnDeleteUsager.setBounds(225, 490, 50, 50);
		contentPane.add(btnDeleteUsager);
		
		JButton btnUpdateUsager = new ActionButton();
		btnUpdateUsager.setIcon(new ImageIcon("images\\updated.png"));
		btnUpdateUsager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                 updateUsager();
			}
		});
		btnUpdateUsager.setBounds(425, 490, 50, 50);
		contentPane.add(btnUpdateUsager);
		
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
		model.addColumn("USAGER ID");
		model.addColumn("NOM");
		model.addColumn("ADRESSE");
		model.addColumn("CATEGORIE");
	
		try {
			for (IResource usager: biblio.usagersListe()) {
				model.addRow(new Object[] {
						usager.getElement("usager_id"),
						usager.getElement("nom"),
						usager.getElement("adresse"),
						usager.getElement("categorie"),
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
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(110);
	}
	
	
	public void addUsager()
	{
	     if(!tfNom.getText().equals("") && !tfAdresse.getText().equals("") && cbCategorie.getSelectedItem()!=null) {
	    	 try {
				if(biblio.ajouterUsager(tfNom.getText(), tfAdresse.getText(), cbCategorie.getSelectedItem().toString())) {
					fetchUsagers();
					tfNom.setText("");
					tfAdresse.setText("");
					cbCategorie.setSelectedIndex(0);
					JOptionPane.showMessageDialog(null, "Usager ajouté");
				 }
			} catch (RemoteException e) {
				System.err.println(e.getMessage());
			}
	     }
	     else {
    		 JOptionPane.showMessageDialog(null, "Il faut remplir tous les champs", "Erreur",JOptionPane.ERROR_MESSAGE);
	     }
	}
	
	public void updateUsager()
	{
		int rowIndex=table.getSelectedRow();
		if(rowIndex >= 0) {
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			int usagerId= Integer.parseInt(model.getValueAt(rowIndex, 0).toString());
			String nom=model.getValueAt(rowIndex, 1).toString();
			String adresse=model.getValueAt(rowIndex, 2).toString();
			String categorie=model.getValueAt(rowIndex, 3).toString();
			try {
				if(biblio.modifierUsager(usagerId, nom, adresse, categorie)){
					fetchUsagers();
					JOptionPane.showMessageDialog(null, "Usager modifié");
				 }
			} catch (RemoteException e) {
				System.err.println(e.getMessage());
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Il faut selectionner un element", "Information",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void deleteUsager()
	{
		int rowIndex=table.getSelectedRow();
		if(rowIndex >= 0) {
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			int usagerId= Integer.parseInt(model.getValueAt(rowIndex, 0).toString());
			try {
				if(biblio.supprimerUsager(usagerId)){
					fetchUsagers();
					JOptionPane.showMessageDialog(null, "Usager supprimé");
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
