package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import gui.utils.SecondaryButton;
import gui.utils.SubmitButton;
import services.IBibliotheque;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;

public class LoginBiblio {
	IBibliotheque biblio;
	private JFrame frame;
	private JTextField tfEmail;
	private JPasswordField tfpwd;
	JLabel Ustar = new JLabel("*");
	JLabel Pstar = new JLabel("*");

	public static void main(String[] args) {
		LoginBiblio window = new LoginBiblio();
		window.frame.setVisible(true);	
	}

	
	public LoginBiblio() {
		System.setProperty("java.rmi.server.hostname","localhost");
		try {
			this.biblio = (IBibliotheque)Naming.lookup("rmi://localhost:3000/bibliotheque");
			init();
			Ustar.setVisible(false);
			Pstar.setVisible(false);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}	
	}

	
	private void init() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Times New Roman", Font.BOLD, 23));
		frame.setBounds(50,50, 800, 620);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setFont(new Font("Times New Roman", Font.BOLD, 32));
		lblLogin.setBounds(369, 190, 200, 67);
		frame.getContentPane().add(lblLogin);
		
		JLabel lblUsername = new JLabel("EMAIL");
		lblUsername.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblUsername.setBounds(280, 324, 200, 50);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("MOT DE PASSE");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblPassword.setBounds(280, 413, 200, 38);
		frame.getContentPane().add(lblPassword);
		
		tfEmail = new JTextField();
		tfEmail.setFont(new Font("Times New Roman", Font.BOLD, 23));
		tfEmail.setBounds(489, 331, 208, 38);
		frame.getContentPane().add(tfEmail);
		tfEmail.setColumns(10);
		
		tfpwd = new JPasswordField();
		tfpwd.setFont(new Font("Times New Roman", Font.BOLD, 23));
		tfpwd.setBounds(489, 414, 208, 38);
		frame.getContentPane().add(tfpwd);
		
		SubmitButton btnLogin = new SubmitButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ustar.setVisible(false);
				Pstar.setVisible(false);
				if(tfEmail.getText().equals("")){
					Ustar.setVisible(true);
				}
				if(String.valueOf(tfpwd.getPassword()).equals("")){
					Pstar.setVisible(true);
				}
				if(!tfEmail.getText().equals("") && !String.valueOf(tfpwd.getPassword()).equals("")){
					try {
						if(biblio.login(tfEmail.getText(), String.valueOf(tfpwd.getPassword()))) {
							frame.dispose();
							new BiblioDashboard();
						}
						else {
							JOptionPane.showMessageDialog(null, "Email ou mot de passe incorrecte", "Erreur", JOptionPane.ERROR_MESSAGE);
						}
					} catch (Exception e1) {
						System.err.println(e1.getMessage());
					}
				 }
			}
		});
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 23));
		btnLogin.setBounds(288, 513, 147, 50);
		frame.getContentPane().add(btnLogin);
		
		JButton btnCancel = new SecondaryButton("ANNULER");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 23));
		btnCancel.setBounds(565, 513, 155, 51);
		frame.getContentPane().add(btnCancel);
		
		
		Ustar.setForeground(Color.RED);
		Ustar.setFont(new Font("Times New Roman", Font.BOLD, 28));
		Ustar.setBounds(699, 347, 46, 21);
		frame.getContentPane().add(Ustar);
		
		
		Pstar.setForeground(Color.RED);
		Pstar.setFont(new Font("Times New Roman", Font.BOLD, 28));
		Pstar.setBounds(699, 430, 46, 21);
		frame.getContentPane().add(Pstar);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("images\\admin.png"));
		label.setBounds(50, 234, 314, 283);
		frame.getContentPane().add(label);
		
		JLabel lblHotelManagementSystem = new JLabel("GESTION DE BIBLIOTHEQUE");
		lblHotelManagementSystem.setFont(new Font(Font.SERIF, Font.BOLD, 36));
		lblHotelManagementSystem.setBounds(109, 72, 636, 80);
		frame.getContentPane().add(lblHotelManagementSystem);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setVisible(true);
	}
}
