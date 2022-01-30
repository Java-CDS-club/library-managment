package gui.utils;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class PanelButton extends JButton {
	private static final long serialVersionUID = 1L;
	
	public PanelButton(String title) {
		super(title);
		setFont(new Font("High Tower Text", Font.BOLD, 16));
		setBackground(Color.WHITE);
		setForeground(Color.BLACK);
		setFocusable(false);
		addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	setBorder(null);
		        setBackground(new Color(220, 220, 220));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        setBackground(Color.WHITE);
		    }
		});
	}

}
