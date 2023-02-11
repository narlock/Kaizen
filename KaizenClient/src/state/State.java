package state;

import java.awt.Color;

import javax.swing.JPanel;

public abstract class State extends JPanel {
	public State() {
		this.setBackground(new Color(20, 20, 20));
		initPanelComponents();
		initPanelComponentActions();
		initPanel();
	}
	
	public abstract void initPanelComponents();
	public abstract void initPanelComponentActions();
	public abstract void initPanel();
}
