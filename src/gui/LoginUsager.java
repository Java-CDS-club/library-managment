package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import gui.utils.SecondaryButton;
import gui.utils.SubmitButton;
import services.IBibliotheque;

public class LoginUsager{
	IBibliotheque biblio;
	private JFrame frame;
	private JTextField tfNom;
	JLabel Ustar = new JLabel("*");

	
	public LoginUsager() {
		System.setProperty("java.rmi.server.hostname","localhost");
		try {
			this.biblio = (IBibliotheque)Naming.lookup("rmi://localhost:3000/bibliotheque");
			init();
			Ustar.setVisible(false);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	
	private void init() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Times New Roman", Font.BOLD, 23));
		frame.setBounds(50,50, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("NOM");
		lblUsername.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblUsername.setBounds(280, 224, 200, 50);
		frame.getContentPane().add(lblUsername);
		
		
		tfNom = new JTextField();
		tfNom.setFont(new Font("Times New Roman", Font.BOLD, 23));
		tfNom.setBounds(489, 231, 208, 38);
		frame.getContentPane().add(tfNom);
		tfNom.setColumns(10);
		

		
		SubmitButton btnLogin = new SubmitButton("ENTRER");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ustar.setVisible(false);
				if(tfNom.getText().equals("")){
					Ustar.setVisible(true);
				}
				if(!tfNom.getText().equals("")){
					try {
						if(biblio.checkIn(tfNom.getText())) {
							frame.dispose();
							new CatalogueLivres();
						}
						else {
							JOptionPane.showMessageDialog(null, "Usager non enregistré", "Erreur", JOptionPane.ERROR_MESSAGE);
						}
					} catch (Exception e1) {
						System.err.println(e1.getMessage());
					}
				 }
			}
		});
		
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 23));
		btnLogin.setBounds(288, 350, 147, 50);
		frame.getContentPane().add(btnLogin);
		
		JButton btnCancel = new SecondaryButton("ANNULER");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 23));
		btnCancel.setBounds(565, 350, 155, 51);
		frame.getContentPane().add(btnCancel);
		
		
		Ustar.setForeground(Color.RED);
		Ustar.setFont(new Font("Times New Roman", Font.BOLD, 28));
		Ustar.setBounds(699, 347, 46, 21);
		frame.getContentPane().add(Ustar);
		
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("images\\client.png"));
		label.setBounds(50, 134, 314, 283);
		frame.getContentPane().add(label);
		
		JLabel lbGestionBiblio = new JLabel("GESTION DE BIBLIOTHEQUE");
		lbGestionBiblio.setFont(new Font(Font.SERIF, Font.BOLD, 36));
		lbGestionBiblio.setBounds(109, 72, 636, 80);
		frame.getContentPane().add(lbGestionBiblio);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setVisible(true);
	}
}
