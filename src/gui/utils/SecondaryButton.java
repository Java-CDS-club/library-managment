package gui.utils;

import java.awt.Color;

import javax.swing.JButton;

public class SecondaryButton extends JButton {
	private static final long serialVersionUID = 1L;

	public SecondaryButton(String title) {
		super(title);
		setBackground(new Color(180, 180, 180));
    	setBorder(null);
		setForeground(Color.WHITE);
		addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	setBorder(null);
		        setBackground(new Color(200, 200, 200));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        setBackground(new Color(180, 180, 180));
		    }
		});
	}

}
