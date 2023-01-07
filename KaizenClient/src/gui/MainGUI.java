package gui;

import javax.swing.JFrame;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = -6508626185123863757L;
	
	public MainGUI() {
		setUpFrame();
	}
	
	private void setUpFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setVisible(true);
	}

}
