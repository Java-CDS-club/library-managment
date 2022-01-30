package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.Naming;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import gui.utils.ActionButton;
import services.IBibliotheque;
import services.IResource;


public class CatalogueLivres extends JFrame {
	private static final long serialVersionUID = 1L;
	IBibliotheque biblio;

	private JPanel contentPane;
	private JTable table;
	private JTextField tfRecherche;
	
	public CatalogueLivres() {
		System.setProperty("java.rmi.server.hostname","localhost");
		try {
			this.biblio = (IBibliotheque)Naming.lookup("rmi://localhost:3000/bibliotheque");
			init();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}	
	}
	
	public static void main(String[] args) {
		new CatalogueLivres();
	}

	public void init() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				fetchUsagers(null);
			}
		});
		
		setTitle("Catalogue de livres");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 260, 575, 260);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblRecherche = new JLabel("Recherche");
		lblRecherche.setFont(new Font("Lato", Font.BOLD, 20));
		lblRecherche.setBounds(130, 215, 155, 30);
		contentPane.add(lblRecherche);
		
		tfRecherche = new JTextField();
		tfRecherche.setFont(new Font("Lato", Font.PLAIN, 18));
		tfRecherche.setBounds(230, 215, 230, 30);
		contentPane.add(tfRecherche);
		tfRecherche.setColumns(10);
		
		JButton btnRecherche = new ActionButton();
		btnRecherche.setIcon(new ImageIcon("images\\search.png"));
		btnRecherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fetchUsagers(tfRecherche.getText());
			}
		});
		btnRecherche.setBounds(460, 210, 40, 40);
		contentPane.add(btnRecherche);
		
		
		JLabel label = new JLabel("");
		label.setBounds(879, 100, 239, 163);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("images\\collage2.jpg"));
		lblNewLabel.setBounds(0, 0, 947, 202);
		contentPane.add(lblNewLabel);
		this.setVisible(true);
	}
	
	
	public void fetchUsagers(String keyword)
	{
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("LIVRE ID");
		model.addColumn("TITRE");
		model.addColumn("AUTEUR");
		model.addColumn("EDITEUR");
		model.addColumn("ISBN");
		model.addColumn("EMPLACEMENT");
	
		try {
			if(keyword!=null) {
				for (IResource livre: biblio.livresListe(keyword)) {
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
			else {
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
}
