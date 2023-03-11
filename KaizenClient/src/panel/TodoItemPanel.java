package panel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import json.HabitJsonManager;
import util.Constants;

public class TodoItemPanel extends JPanel {
	public TodoItemPanel() {
		this.setBackground(Constants.GUI_BACKGROUND_COLOR);
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        
        for(int i = 0; i < 10; i++) {
        	this.add(createTodoItemPanel(), gbc);
        }
	}
	
	private JPanel createTodoItemPanel() {
		JPanel todoItemMainPanel = new JPanel();
		todoItemMainPanel.setLayout(new BorderLayout());
		todoItemMainPanel.setPreferredSize(Constants.HABIT_MAIN_PANEL_DIMENSION_NORMAL);
		todoItemMainPanel.setOpaque(true);
		todoItemMainPanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		todoItemMainPanel.setBorder(Constants.COMPONENT_BORDER_NORMAL); //TODO Change this to associated epic color.
		
		//Add Circle Button to check off habit
		JPanel todoItemCompletedPanel = new JPanel();
		todoItemCompletedPanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		JButton completeTodoItemButton = new JButton();
		completeTodoItemButton.setPreferredSize(Constants.COMPLETE_HABIT_DIMENSION_NORMAL);
		completeTodoItemButton.setOpaque(true);
		completeTodoItemButton.setBorder(Constants.TODO_COMPLETE_BORDER_NORMAL);
		todoItemCompletedPanel.add(completeTodoItemButton);
		todoItemMainPanel.add(todoItemCompletedPanel, BorderLayout.WEST);
		
		JPanel todoItemTitlePanel = new JPanel();
		todoItemTitlePanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;   
		todoItemTitlePanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		JLabel todoTitleLabel = new JLabel("This is a test TodoItemPanel");
		todoTitleLabel.setFont(Constants.COMPONENT_FONT_NORMAL_BOLD);
		todoTitleLabel.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		todoItemTitlePanel.add(todoTitleLabel, gbc);
		JLabel dueDateLabel = new JLabel("2023-03-11");
		dueDateLabel.setFont(Constants.COMPONENT_FONT_SMALL);
		dueDateLabel.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		todoItemTitlePanel.add(dueDateLabel, gbc);
		todoItemMainPanel.add(todoItemTitlePanel, BorderLayout.CENTER);
		
		
		//Create Streak panel
		JPanel editAndPriorityPanel = new JPanel();
		editAndPriorityPanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		JLabel priorityLabel = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("PRIORITY_MEDIUM.png")));
		priorityLabel.setOpaque(false);
		priorityLabel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		priorityLabel.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		editAndPriorityPanel.add(priorityLabel);
		
		JButton editButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("EDIT.png")));
		editButton.setOpaque(false);
		editButton.setContentAreaFilled(false); 
		editButton.setBorderPainted(false); 
		editButton.setFocusPainted(false);
		editAndPriorityPanel.add(editButton);
		todoItemMainPanel.add(editAndPriorityPanel, BorderLayout.EAST);
			
		return todoItemMainPanel;
	}
}
