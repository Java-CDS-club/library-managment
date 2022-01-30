package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import gui.utils.VerticalPanel;

public class BiblioDashboard extends JFrame{
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	public static void main(String[] args) {
		new BiblioDashboard();
	}

	public BiblioDashboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 150, 700, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnGestionLivres = new VerticalPanel("GESTION DES LIVRES");
		btnGestionLivres.setIcon(new ImageIcon("images\\books.png"));
		btnGestionLivres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new GestionLivres();
			}
		});
		btnGestionLivres.setBounds(230, 210, 400, 100);
		contentPane.add(btnGestionLivres);
		
		JButton btnGestionUsagers = new VerticalPanel("GESTION DES USAGERS");
		btnGestionUsagers.setIcon(new ImageIcon("images\\team.png"));
		btnGestionUsagers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new GestionUsagers();
			}
		});
		btnGestionUsagers.setBounds(230, 350, 400, 100);
		contentPane.add(btnGestionUsagers);
		
		JButton btnGestionEmrunts = new VerticalPanel("GESTION DES EMPRUNTS");
		btnGestionEmrunts.setIcon(new ImageIcon("images\\reading.png"));
		btnGestionEmrunts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new GestionEmrunts();
			}
		});
		btnGestionEmrunts.setBounds(230, 490, 400, 100);
		contentPane.add(btnGestionEmrunts);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("images\\user.png"));
		label.setBounds(0, 0, 253, 221);
		contentPane.add(label);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 153, 204));
		panel.setBounds(23, 172, 183, 415);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 153, 204));
		panel_1.setBounds(168, 30, 460, 150);
		contentPane.add(panel_1);
		this.setVisible(true);
	}
}

