package gui.utils;

import java.awt.Color;

import javax.swing.JButton;

public class SubmitButton extends JButton {
	private static final long serialVersionUID = 1L;
	
	public SubmitButton(String title) {
		super(title);
		setBackground(new Color(80, 80, 200));
		setForeground(Color.WHITE);
		addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	setBorder(null);
		        setBackground(new Color(100, 100, 220));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        setBackground(new Color(80, 80, 200));
		    }
		});
	}
}
