package panel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import json.HabitJsonManager;
import util.Constants;
import util.ErrorPane;
import util.Utils;

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
		todoItemMainPanel.setPreferredSize(Constants.TODO_ITEM_PANEL_DIMENSION);
		todoItemMainPanel.setOpaque(true);
		todoItemMainPanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		todoItemMainPanel.setBorder(Constants.COMPONENT_BORDER_NORMAL); //TODO Change this to associated epic color.
		
		//Add Circle Button to check off habit
		JPanel todoItemCompletedPanel = new JPanel();
		todoItemCompletedPanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		JButton completeTodoItemButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("TODO_BUTTON.png")));
		completeTodoItemButton.setBorderPainted(false);
		completeTodoItemButton.setFocusPainted(false);
		completeTodoItemButton.setPreferredSize(new Dimension(20, 20));
		todoItemCompletedPanel.add(completeTodoItemButton);
		todoItemMainPanel.add(todoItemCompletedPanel, BorderLayout.WEST);
		
		completeTodoItemButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				completeTodoItemButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("TODO_BUTTON_COMPLETED.png")));
			}
			
		});
		
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
		
		editButton.addActionListener(new ActionListener() {
			JPanel panel = new JPanel(new GridLayout(0, 1));
			JLabel titleLabel = new JLabel("Title");
			JTextField titleTextField = new JTextField(); //TODO Add name of associated todo item
			JLabel priorityLabel = new JLabel("Priority");
			JComboBox<String> priorityBox = new JComboBox<>(); // Future todo, add icons here
			JLabel dueDateLabel = new JLabel("Due Date (yyyy-MM-dd)");
			JTextField dueDateTextField = new JTextField();
			JLabel epicAssignLabel = new JLabel("Epic");
			JComboBox<String> epicAssignBox = new JComboBox<>();

			@Override
			public void actionPerformed(ActionEvent e) {
				priorityBox.addItem("Low");
				priorityBox.addItem("Medium");
				priorityBox.addItem("High");
				priorityBox.addItem("Critical");
				
				// TODO populate epic box with epics
				epicAssignBox.addItem("");
				
				panel.add(titleLabel);
				panel.add(titleTextField);
				panel.add(priorityLabel);
				panel.add(priorityBox);
				panel.add(dueDateLabel);
				panel.add(dueDateTextField);
				panel.add(epicAssignLabel);
				panel.add(epicAssignBox);
				
				Container parentComponent = editButton.getParent().getParent().getParent().getParent().getParent().getParent().getParent();
				
				int result = JOptionPane.showConfirmDialog(
						parentComponent, 
						panel, 
						"Update Todo Item", 
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						new ImageIcon(getClass().getClassLoader().getResource("INFO_ERROR_ORANGE.png")));
				if(result == JOptionPane.YES_OPTION &&
						!titleTextField.getText().equals("") &&
						Utils.validateDateString(dueDateTextField.getText())
					) {
					String todoTitle = titleTextField.getText();
					String dateString = dueDateTextField.getText();
					String epicString = (String) epicAssignBox.getSelectedItem();
					long priority = priorityBox.getSelectedIndex();
					System.out.println("[todoTitle=" + todoTitle + ", dateString=" + dateString + ", epicString=" 
								+ epicString + ", priority=" + priority + "]");
				}
				else if(result == JOptionPane.OK_OPTION &&
						titleTextField.getText().equals("")) {
					// Display Validation Error on Title
					ErrorPane.displayError(parentComponent, "Could not update Todo Item: a title must be given to the item.");
				} 
				else if(result == JOptionPane.OK_OPTION &&
						!Utils.validateDateString(dueDateTextField.getText())
						) {
					// Display Validation Error on Due Date
					ErrorPane.displayError(parentComponent, "Could not update Todo Item: invalid date format.");
				}
				else {
					System.out.println("Cancel");
				}
			}
			
		});
			
		return todoItemMainPanel;
	}
	
	
}
