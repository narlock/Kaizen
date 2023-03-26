package panel;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.Epic;
import domain.Todo;
import domain.TodoItem;
import json.TodoJsonManager;
import state.TodoState;
import util.Constants;
import util.ErrorPane;

public class EpicItemPanel extends JPanel {
	private GridBagConstraints gbc;
	
	private TodoState state;
	private Todo todo;
	private List<Epic> epics;
	
	public EpicItemPanel(Todo todo, TodoState state) {
		this.state = state;
		this.todo = todo;
		this.epics = todo.getEpics();
		
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
		epicMainPanel.setBackground(epic.getColor());
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
				System.out.println("TODO for version 1.1");
			}
			
		});
		
		JButton editButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("EDIT.png")));
		editButton.setOpaque(false);
		editButton.setContentAreaFilled(false); 
		editButton.setBorderPainted(false); 
		editButton.setFocusPainted(false);
		
		editButton.addActionListener(new ActionListener() {

			JPanel panel = new JPanel(new GridLayout(0, 1));
			JLabel titleLabel = new JLabel("Epic Title");
			JTextField titleTextField = new JTextField(epic.getTitle()); //TODO Add name of associated todo item
			JLabel colorLabel = new JLabel("Color");
			JButton setColorButton = new JButton("Set Color");
			Color color = epic.getColor();
			Container parentComponent = editButton.getParent();
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setColorButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						color = JColorChooser.showDialog(setColorButton.getParent(), "Choose a color", epic.getColor());
						if(color != null) {
							// TODO Update Color
							System.out.println("Selected a valid color!");
						} else { System.out.println("No color selected!"); }
					}
					
				});
				
				panel.add(titleLabel);
				panel.add(titleTextField);
				panel.add(colorLabel);
				panel.add(setColorButton);
				
				int result = JOptionPane.showConfirmDialog(
						getRootPane(), 
						panel, 
						"Update Epic", 
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						new ImageIcon(getClass().getClassLoader().getResource("INFO_ERROR_ORANGE.png")));
				if(result == JOptionPane.YES_OPTION &&
						!titleTextField.getText().equals("")
					) {
					String oldTitle = epic.getTitle();
					
					// Update epic attributes
					epic.setTitle(titleTextField.getText());
					epic.setColor(color);
					
					// Associate items with new title
					for(TodoItem item : todo.getItems()) {
						if(item.getEpic().equals(oldTitle)) {
							item.setEpic(titleTextField.getText());
						}
					}
					
					// Update Json
					TodoJsonManager.writeTodoJsonToFile(todo);
					
					// Revalidate GUI
					state.revalidateEpicPanel();
					state.revalidateItemPanel(state.showCompleted);
				} 
				else if(result == JOptionPane.YES_OPTION &&
						titleTextField.getText().equals("")
					) {
					ErrorPane.displayError(parentComponent, "Could not create habit. Please provide a title.");
				}
				else {
					System.out.println("Cancel");
				}
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
				int result = JOptionPane.showConfirmDialog(
						getRootPane(), 
						"Are you sure you want to delete " + epic.getTitle() + "?", 
						"Delete Epic", 
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						new ImageIcon(getClass().getClassLoader().getResource("INFO_ERROR_ORANGE.png")));
				if(result == JOptionPane.YES_OPTION) {
					// Remove the epic
					epics.remove(epic);
					
					// All associated items are unassigned from any epic
					for(TodoItem item : todo.getItems()) {
						if(item.getEpic().equals(epic.getTitle())) {
							item.setEpic("");
						}
					}
					
					// Update Json
					TodoJsonManager.writeTodoJsonToFile(todo);
					
					// Revalidate GUI
					state.revalidateEpicPanel();
					state.revalidateItemPanel(state.showCompleted);
				}
			}
			
		});
		
		epicMainPanel.add(epicSwitchButton, newgbc);
		epicMainPanel.add(editButton, newgbc);
		epicMainPanel.add(deleteButton, newgbc);
		
		return epicMainPanel;
	}
}
