package com.narlock.util;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ErrorPane extends JOptionPane {
	private static final long serialVersionUID = -8480045449677402003L;

	public static void displayError(Component root, String errorMessage) {
		showMessageDialog(root, 
				errorMessage, 
				"An error occurred", 
				JOptionPane.INFORMATION_MESSAGE,
				new ImageIcon(ErrorPane.class.getClassLoader().getResource("INFO_ERROR.png")));
	}
}
