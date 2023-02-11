package state;

import java.awt.Color;

import javax.swing.JPanel;

public abstract class State extends JPanel {
	public State() {
		this.setBackground(Color.DARK_GRAY);
		initPanelComponents();
		initPanelComponentActions();
		initPanel();
	}
	
	public abstract void initPanelComponents();
	public abstract void initPanelComponentActions();
	public abstract void initPanel();
}
