package gui.utils;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class NumberField extends JTextField {
	private static final long serialVersionUID = 1L;

	@Override
	protected Document createDefaultModel() {
	    return new Numberdocument();
	}
	
	class Numberdocument extends PlainDocument
	{
		private static final long serialVersionUID = 1L;
		 
	    @Override
	    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
	    	String regexNumber = "[0-9]*\\.?[0-9]*";
	    	if(str.matches(regexNumber)) super.insertString(offs, str, a);
	    }
	}
}