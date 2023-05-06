package com.narlock.state;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.narlock.domain.Settings;
import com.narlock.util.RoundedBorder;

public abstract class State extends JPanel {
	
	protected Settings settings;
	private static final long serialVersionUID = -910452205672441980L;
	
	public State() {
		this.setBackground(new Color(20, 20, 20));
		initPanelComponents();
		initPanelComponentActions();
		initPanel();
	}
	
	public State(Settings settings) {
		this.settings = settings;
		this.setBackground(new Color(20, 20, 20));
		initPanelComponents();
		initPanelComponentActions();
		initPanel();
	}
	
	public boolean setButtonAttributes(JButton button, String indicator) {
		button.setText(indicator);
		button.setOpaque(true);
		button.setFocusPainted(false);
		switch(indicator) {
			case "New Habit":
				button.setBackground(new Color(120, 120, 255));
				button.setForeground(new Color(60, 60, 60));
				button.setFont(new Font("Tahoma", Font.PLAIN, 18));
				button.setBorder(new RoundedBorder(Color.WHITE, 3, 10, 10, true));
				return true;
			case "Update Habits":
				button.setBackground(new Color(120, 255, 120));
				button.setForeground(new Color(60, 60, 60));
				button.setFont(new Font("Tahoma", Font.PLAIN, 18));
				button.setBorder(new RoundedBorder(Color.WHITE, 3, 10, 10, true));
				return true;
				
		}
		return false;
	}
	
	public abstract void initPanelComponents();
	public abstract void initPanelComponentActions();
	public abstract void initPanel();

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}
}
