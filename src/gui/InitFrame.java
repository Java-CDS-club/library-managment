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

import gui.utils.PanelButton;


public class InitFrame extends JFrame{
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	public static void main(String[] args) {
		new InitFrame();
	}

	public InitFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 150, 870, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnClient = new PanelButton("CLIENT");
		btnClient.setIcon(new ImageIcon("images\\client.png"));
		btnClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginUsager();
			}
		});
		btnClient.setBounds(230, 210, 275, 200);
		contentPane.add(btnClient);
		
		JButton btnAdmin = new PanelButton("ADMIN");
		btnAdmin.setIcon(new ImageIcon("images\\admin.png"));
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginBiblio();
			}
		});
		btnAdmin.setBounds(550, 210, 275, 200);
		contentPane.add(btnAdmin);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("images\\user.png"));
		label.setBounds(0, 0, 253, 221);
		contentPane.add(label);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 153, 204));
		panel.setBounds(23, 172, 183, 260);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 153, 204));
		panel_1.setBounds(168, 30, 655, 150);
		contentPane.add(panel_1);
		this.setVisible(true);
	}
}
