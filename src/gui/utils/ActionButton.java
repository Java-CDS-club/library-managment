package gui.utils;

import java.awt.Color;

import javax.swing.JButton;

public class ActionButton extends JButton {
	private static final long serialVersionUID = 1L;

	public ActionButton() {
		setBackground(Color.WHITE);
		setForeground(Color.WHITE);
		setBorder(null);
		addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	setBorder(null);
		        setBackground(new Color(225, 225, 225));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        setBackground(Color.WHITE);
		    }
		});
	}
}
