package panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import domain.Epic;
import util.Constants;

public class EpicItemPanel extends JPanel {
	private GridBagConstraints gbc;
	
	private List<Epic> epics;
	
	public EpicItemPanel(List<Epic> epics) {
		this.epics = epics;
		
		this.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		this.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		createEpicItemPanels();
	}
	
	private void createEpicItemPanels() {
		for(Epic epic : epics) {
			this.add(createEpicItemPanel(epic), gbc);
		}
	}
	
	private JPanel createEpicItemPanel(Epic epic) {
		GridBagConstraints newgbc = new GridBagConstraints();
		newgbc.gridheight = GridBagConstraints.REMAINDER;
		
		JPanel epicMainPanel = new JPanel();
		epicMainPanel.setLayout(new GridBagLayout());
		epicMainPanel.setPreferredSize(Constants.TODO_EPIC_PANEL_DIMENSION);
		epicMainPanel.setOpaque(true);
		epicMainPanel.setBackground(new Color(0, 40, 171));
		epicMainPanel.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		epicMainPanel.setBorder(Constants.COMPONENT_BORDER_NORMAL);
		
		JButton epicSwitchButton = new JButton(epic.getTitle());
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
		
		JButton deleteButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("DELETE.png")));
		deleteButton.setOpaque(false);
		deleteButton.setContentAreaFilled(false); 
		deleteButton.setBorderPainted(false); 
		deleteButton.setFocusPainted(false);
		
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		epicMainPanel.add(epicSwitchButton, newgbc);
		epicMainPanel.add(editButton, newgbc);
		epicMainPanel.add(deleteButton, newgbc);
		
		return epicMainPanel;
	}
}
