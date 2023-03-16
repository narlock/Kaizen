package panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import util.Constants;

public class EpicItemPanel extends JPanel {
	private GridBagConstraints gbc;
	
	private int numberOfItems;
	
	public EpicItemPanel(int numberOfItems) {
		this.numberOfItems = numberOfItems;
		
		this.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		this.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		createEpicItemPanels();
	}
	
	private void createEpicItemPanels() {
		for(int i = 0; i < numberOfItems; i++) {
			this.add(createEpicItemPanel(), gbc);
		}
	}
	
	private JPanel createEpicItemPanel() {
		GridBagConstraints newgbc = new GridBagConstraints();
		newgbc.gridheight = GridBagConstraints.REMAINDER;
		
		JPanel epicMainPanel = new JPanel();
		epicMainPanel.setLayout(new GridBagLayout());
		epicMainPanel.setPreferredSize(Constants.TODO_EPIC_PANEL_DIMENSION);
		epicMainPanel.setOpaque(true);
		epicMainPanel.setBackground(new Color(0, 40, 171));
		epicMainPanel.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		epicMainPanel.setBorder(Constants.COMPONENT_BORDER_NORMAL);
		
		JButton epicSwitchButton = new JButton("This is a test Epic");
		epicSwitchButton.setOpaque(false);
		epicSwitchButton.setContentAreaFilled(false); 
		epicSwitchButton.setBorderPainted(false); 
		epicSwitchButton.setFocusPainted(false);
		epicSwitchButton.setFont(Constants.COMPONENT_FONT_SMALL_BOLD);
		epicSwitchButton.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		
		epicSwitchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("hello world");
			}
			
		});
		
		JButton editButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("EDIT.png")));
		editButton.setOpaque(false);
		editButton.setContentAreaFilled(false); 
		editButton.setBorderPainted(false); 
		editButton.setFocusPainted(false);
		
		editButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("hello world");
			}
			
		});
		
		epicMainPanel.add(epicSwitchButton, newgbc);
		epicMainPanel.add(editButton, newgbc);
		
		return epicMainPanel;
	}
}
