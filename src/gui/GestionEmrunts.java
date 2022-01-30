package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
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
import gui.utils.UsagerElement;
import services.IBibliotheque;
import services.IResource;

public class GestionEmrunts extends JFrame{
	private static final long serialVersionUID = 1L;
	IBibliotheque biblio;

	private JPanel contentPane;
	private JComboBox<UsagerElement> cbUsagers;
	private JTextField tfISBN;
	private JTable table;
	
	public GestionEmrunts() {
		System.setProperty("java.rmi.server.hostname","localhost");
		try {
			this.biblio = (IBibliotheque)Naming.lookup("rmi://localhost:3000/bibliotheque");
			init();
		} catch (MalformedURLException | NotBoundException | RemoteException e) {
			System.err.println(e.getMessage());
		}	
	}
	
	public static void main(String[] args) {
		new GestionEmrunts();
	}
	
	public UsagerElement[] cbUsagers() {
		UsagerElement[] result=null;
		try {
			result=new UsagerElement[biblio.usagersListe().size()+1];
			result[0]=null;
			for(int i=1;i<=biblio.usagersListe().size();i++) {
				IResource resource=biblio.usagersListe().get(i-1);
				result[i]=new UsagerElement(Integer.parseInt(resource.getElement("usager_id").toString()), resource.getElement("nom").toString());
			}
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
		}
		return result;
	}

	public void init() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				fetchLivres(0);
			}
		});
		setTitle("Gestion des emrunts");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbNom = new JLabel("Usager");
		lbNom.setFont(new Font("Lato", Font.BOLD, 20));
		lbNom.setBounds(15, 210, 155, 30);
		contentPane.add(lbNom);
		
		JLabel lbLivre = new JLabel("Affecter Livre");
		lbLivre.setFont(new Font("Lato", Font.BOLD, 20));
		lbLivre.setBounds(15, 300, 155, 30);
		contentPane.add(lbLivre);
		
		JLabel lbLine = new JLabel("________________________");
		lbLine.setFont(new Font("Arial", Font.BOLD, 20));
		lbLine.setForeground(Color.GRAY);
		lbLine.setBounds(150, 300, 280, 30);
		contentPane.add(lbLine);
		
		JLabel lbISBN = new JLabel("ISBN");
		lbISBN.setFont(new Font("Lato", Font.BOLD, 20));
		lbISBN.setBounds(15, 360, 155, 30);
		contentPane.add(lbISBN);
		
		cbUsagers = new JComboBox<UsagerElement>(cbUsagers());
		cbUsagers.setFont(new Font("Lato", Font.BOLD, 20));
		cbUsagers.setBounds(185, 215, 230, 30);
		contentPane.add(cbUsagers);
		cbUsagers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsagerElement selectedElement=(UsagerElement)cbUsagers.getSelectedItem();
				if(selectedElement!=null) {
					fetchLivres(selectedElement.getUsagerId());
				}
				else {
					fetchLivres(0);
				}
			}
		});
		
		tfISBN = new JTextField();
		tfISBN.setFont(new Font("Lato", Font.BOLD, 20));
		tfISBN.setBounds(185, 365, 230, 30);
		tfISBN.setColumns(10);
		contentPane.add(tfISBN);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(456, 215, 418, 260);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnAddEmprunt = new ActionButton();
		btnAddEmprunt.setIcon(new ImageIcon("images\\plus (1).png"));
		btnAddEmprunt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addLivre();
			}
		});
		btnAddEmprunt.setBounds(25, 490, 50, 50);
		contentPane.add(btnAddEmprunt);
		
		JButton btnDeleteEmprunt = new ActionButton();
		btnDeleteEmprunt.setIcon(new ImageIcon("images\\iconfinder_f-cross_256_282471 (1).png"));
		btnDeleteEmprunt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteLivre();
			}
		});
		btnDeleteEmprunt.setBounds(225, 490, 50, 50);
		contentPane.add(btnDeleteEmprunt);
		
		JButton btnUpdateEmprunt = new ActionButton();
		btnUpdateEmprunt.setIcon(new ImageIcon("images\\updated.png"));
		btnUpdateEmprunt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                 updateLivre();
			}
		});
		btnUpdateEmprunt.setBounds(425, 490, 50, 50);
		contentPane.add(btnUpdateEmprunt);
		
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
	
	
	public void fetchLivres(int usagerId)
	{
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("LIVRE ID");
		model.addColumn("TITRE");
		model.addColumn("AUTEUR");
		model.addColumn("EDITEUR");
		model.addColumn("ISBN");
		model.addColumn("EMPLACEMENT");
		model.addColumn("DATE EMPRUNT");
		model.addColumn("DATE RETOUR");
		if(usagerId > 0) {
			try {
				for (IResource livre: biblio.emruntsListe(usagerId)) {
					model.addRow(new Object[] {
							livre.getElement("livre_id"),
							livre.getElement("titre"),
							livre.getElement("auteur"),
							livre.getElement("editeur"),
							livre.getElement("ISBN"),
							livre.getElement("emplacement"),		
							livre.getElement("date_emprunt"),	
							livre.getElement("date_retour")						
					});
				}		
			}
			catch(Exception e)
			{
				System.err.println(e.getMessage());
			}
		}
		table.setModel(model);
		table.setAutoResizeMode(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);
		table.getColumnModel().getColumn(4).setPreferredWidth(130);
		table.getColumnModel().getColumn(5).setPreferredWidth(130);
		table.getColumnModel().getColumn(6).setPreferredWidth(130);
		table.getColumnModel().getColumn(7).setPreferredWidth(130);
	}
	
	
	public void addLivre()
	{
	     if(cbUsagers.getSelectedItem()!=null && !tfISBN.getText().equals("")) {
	    	 UsagerElement selectedElement=(UsagerElement)cbUsagers.getSelectedItem();
	    	 try {
				if(biblio.ajouterEmprunt(selectedElement.getUsagerId(), tfISBN.getText())) {
					 tfISBN.setText("");
					 JOptionPane.showMessageDialog(null, "Emprunté");
					 fetchLivres(selectedElement.getUsagerId());
				 }
				else {
					 JOptionPane.showMessageDialog(null, "ISBN n'est pas correct ou vous avez deja le nombre maximum des livres", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			} catch (HeadlessException | RemoteException e) {
				System.err.println(e.getMessage());
			}
	     }
	}
	
	public void updateLivre()
	{
 		int rowIndex=table.getSelectedRow();

		if(cbUsagers.getSelectedItem()!=null && rowIndex >= 0) {
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			int LivreId= Integer.parseInt(model.getValueAt(rowIndex, 0).toString());
			String dateRetour=model.getValueAt(rowIndex, 7).toString();
	    	 UsagerElement selectedElement=(UsagerElement)cbUsagers.getSelectedItem();
	    	 try {
				if(biblio.modifierEmprunt(LivreId, dateRetour)) {
					 tfISBN.setText("");
					 JOptionPane.showMessageDialog(null, "Emprunt modifié");
					 fetchLivres(selectedElement.getUsagerId());
				 }
				else {
					 JOptionPane.showMessageDialog(null, "ISBN n'est pas correct ou vous avez deja le nombre maximum des livres", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			} catch (HeadlessException | RemoteException e) {
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
		if(cbUsagers.getSelectedItem()!=null && rowIndex >= 0) {
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			int LivreId= Integer.parseInt(model.getValueAt(rowIndex, 0).toString());
	    	UsagerElement selectedElement=(UsagerElement)cbUsagers.getSelectedItem();
	    	 try {
				if(biblio.supprimerEmprunt(selectedElement.getUsagerId(), LivreId)) {
					 JOptionPane.showMessageDialog(null, "Emprunt supprimé");
					 fetchLivres(selectedElement.getUsagerId());
				 }
			} catch (HeadlessException | RemoteException e) {
				System.err.println(e.getMessage());
			}
	     }
		else {
			JOptionPane.showMessageDialog(null, "Il faut selectionner un element", "Information",JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
