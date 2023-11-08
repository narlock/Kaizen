package main;

import javax.swing.JFrame;

import util.AppConstants;

/**
 * The main window of the Kaizen application.
 * @author narlock
 *
 */
public class Window extends JFrame {
	
	private static final long serialVersionUID = -4810618286807932601L;

	public Window() {
		setTitle("Kaizen v" + AppConstants.VERSION);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setResizable(false);
		
		// add panel?
		
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
