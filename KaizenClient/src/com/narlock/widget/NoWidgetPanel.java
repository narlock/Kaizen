package com.narlock.widget;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import static com.narlock.util.Constants.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class NoWidgetPanel extends JPanel {

	private static final long serialVersionUID = 2847983737757500543L;

	public NoWidgetPanel() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		this.setBackground(GUI_BACKGROUND_COLOR);
		this.add(new JLabel(new ImageIcon(getClass().getClassLoader().getResource("INFO_ERROR_ORANGE.png"))), gbc);
		
		JLabel textLabel = new JLabel("No widget selected");
		textLabel.setFont(COMPONENT_FONT_NORMAL_BOLD);
		textLabel.setForeground(COMPONENT_FOREGROUND_COLOR);
		
		this.add(Box.createVerticalStrut(20), gbc);
		this.add(textLabel, gbc);
	}
	
}
