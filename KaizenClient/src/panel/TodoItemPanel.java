package panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
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

import domain.Epic;
import domain.Todo;
import domain.TodoItem;
import json.HabitJsonManager;
import json.TodoJsonManager;
import state.TodoState;
import util.Constants;
import util.ErrorPane;
import util.TodoUtils;
import util.Utils;

public class TodoItemPanel extends JPanel {
	
	private TodoState state;
	private Todo todo;
	private List<TodoItem> todoItems;
	private List<Epic> epics;
	
	public TodoItemPanel(Todo todo, TodoState state) {
		// Initialize member attributes
		this.state = state;
		this.todo = todo;
		this.todoItems = todo.getItems();
		this.epics = todo.getEpics();
		
		// Set Panel Layout
		this.setBackground(Constants.GUI_BACKGROUND_COLOR);
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        
        // Add todoItems to panel
        if(state.showCompleted) {
        	for(int i = 0; i < todoItems.size(); i++) {
        		if(todoItems.get(i).getCompletedDate() != null)
        			this.add(createTodoItemPanel(todoItems.get(i)), gbc);
        	}
        } else {
        	for(int i = 0; i < todoItems.size(); i++) {
        		if(todoItems.get(i).getCompletedDate() == null)
        			this.add(createTodoItemPanel(todoItems.get(i)), gbc);
        	}
        }
	}
	
	private JPanel createTodoItemPanel(TodoItem todoItem) {
		JPanel todoItemMainPanel = new JPanel();
		todoItemMainPanel.setLayout(new BorderLayout());
		todoItemMainPanel.setPreferredSize(Constants.TODO_ITEM_PANEL_DIMENSION);
		todoItemMainPanel.setOpaque(true);
		
		Color itemPanelColor = Constants.COMPONENT_BACKGROUND_COLOR;
		todoItemMainPanel.setBackground(itemPanelColor);
		if(!todoItem.getEpic().equals("")) {
			for(Epic epic : epics) {
				if(todoItem.getEpic().equals(epic.getTitle())) {
					itemPanelColor = epic.getColor();
					todoItemMainPanel.setBackground(itemPanelColor);
					break;
				}
			}
		}
		
		todoItemMainPanel.setBorder(Constants.COMPONENT_BORDER_NORMAL); //TODO Change this to associated epic color.
		
		//Add Circle Button to check off habit
		JPanel todoItemCompletedPanel = new JPanel();
		todoItemCompletedPanel.setBackground(itemPanelColor);
		JButton completeTodoItemButton;
		if(todoItem.isCompleted()) {
			completeTodoItemButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("TODO_BUTTON_COMPLETED.png")));
		} else {
			completeTodoItemButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("TODO_BUTTON.png")));
		}
		
		completeTodoItemButton.setBorderPainted(false);
		completeTodoItemButton.setFocusPainted(false);
		completeTodoItemButton.setPreferredSize(new Dimension(20, 20));
		todoItemCompletedPanel.add(completeTodoItemButton);
		todoItemMainPanel.add(todoItemCompletedPanel, BorderLayout.WEST);
		
		completeTodoItemButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(todoItem.isCompleted()) {
					completeTodoItemButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("TODO_BUTTON.png")));
					todoItem.setCompletedDate(null);
					TodoJsonManager.writeTodoJsonToFile(todo);
					state.revalidateItemPanel(state.showCompleted);
				} else {
					completeTodoItemButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("TODO_BUTTON_COMPLETED.png")));
					todoItem.setCompletedDate(Utils.today());
					TodoJsonManager.writeTodoJsonToFile(todo);
					state.revalidateItemPanel(state.showCompleted);
				}
				
			}
			
		});
		
		JPanel todoItemTitlePanel = new JPanel();
		todoItemTitlePanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;   
		todoItemTitlePanel.setBackground(itemPanelColor);
		JLabel todoTitleLabel = new JLabel(todoItem.getTitle());
		todoTitleLabel.setFont(Constants.COMPONENT_FONT_NORMAL_BOLD);
		todoTitleLabel.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		todoItemTitlePanel.add(todoTitleLabel, gbc);
		JLabel dueDateLabel = new JLabel("Due: " + Utils.dateAsString(todoItem.getDueDate()));
		dueDateLabel.setFont(Constants.COMPONENT_FONT_SMALL);
		dueDateLabel.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		todoItemTitlePanel.add(dueDateLabel, gbc);
		todoItemMainPanel.add(todoItemTitlePanel, BorderLayout.CENTER);
		
		
		//Create priority panel
		JPanel editAndPriorityPanel = new JPanel();
		editAndPriorityPanel.setBackground(itemPanelColor);
		JLabel priorityLabel = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(TodoUtils.getPriorityImage(todoItem.getPriority()))));
		priorityLabel.setOpaque(false);
		priorityLabel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		priorityLabel.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		editAndPriorityPanel.add(priorityLabel);
		
		JButton editButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("EDIT.png")));
		editButton.setOpaque(false);
		editButton.setContentAreaFilled(false); 
		editButton.setBorderPainted(false); 
		editButton.setFocusPainted(false);
		if(state.showCompleted == false) {
			editAndPriorityPanel.add(editButton);
		}
		todoItemMainPanel.add(editAndPriorityPanel, BorderLayout.EAST);
		
		editButton.addActionListener(new ActionListener() {
			JPanel panel = new JPanel(new GridLayout(0, 1));
			JLabel titleLabel = new JLabel("Title");
			JTextField titleTextField = new JTextField(todoItem.getTitle()); //TODO Add name of associated todo item
			JLabel priorityLabel = new JLabel("Priority");
			JComboBox<String> priorityBox = new JComboBox<>(); // Future todo, add icons here
			JLabel dueDateLabel = new JLabel("Due Date (yyyy-MM-dd)");
			JTextField dueDateTextField = new JTextField(Utils.dateAsString(todoItem.getDueDate()));
			JLabel epicAssignLabel = new JLabel("Epic");
			JComboBox<String> epicAssignBox = new JComboBox<>();

			@Override
			public void actionPerformed(ActionEvent e) {
				titleTextField.setText(todoItem.getTitle());
				priorityBox.removeAllItems();
				dueDateTextField.setText(Utils.dateAsString(todoItem.getDueDate()));
				epicAssignBox.removeAllItems();
				
				priorityBox.addItem("Low");
				priorityBox.addItem("Medium");
				priorityBox.addItem("High");
				priorityBox.addItem("Critical");
				priorityBox.setSelectedIndex((int) todoItem.getPriority());
				
				// TODO populate epic box with epics
				epicAssignBox.addItem("");
				for(Epic epic : epics) {
					epicAssignBox.addItem(epic.getTitle());
				}
				epicAssignBox.setSelectedItem(todoItem.getEpic());
				
				panel.add(titleLabel);
				panel.add(titleTextField);
				panel.add(priorityLabel);
				panel.add(priorityBox);
				panel.add(dueDateLabel);
				panel.add(dueDateTextField);
				panel.add(epicAssignLabel);
				panel.add(epicAssignBox);
				
				int result = JOptionPane.showConfirmDialog(
						getRootPane(), 
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
					
					// Update todo item
					todoItem.setTitle(todoTitle);
					todoItem.setDueDate(Utils.stringToDate(dateString));
					todoItem.setPriority(priority);
					todoItem.setEpic(epicString);
					
					// Update Json
					TodoJsonManager.writeTodoJsonToFile(todo);
					
					// Revalidate GUI
					state.revalidateItemPanel(state.showCompleted);
				}
				else if(result == JOptionPane.OK_OPTION &&
						titleTextField.getText().equals("")) {
					// Display Validation Error on Title
					ErrorPane.displayError(getRootPane(), "Could not update Todo Item: a title must be given to the item.");
				} 
				else if(result == JOptionPane.OK_OPTION &&
						!Utils.validateDateString(dueDateTextField.getText())
						) {
					// Display Validation Error on Due Date
					ErrorPane.displayError(getRootPane(), "Could not update Todo Item: invalid date format.");
				}
				else {
					System.out.println("Cancel");
				}
			}
			
		});
			
		return todoItemMainPanel;
	}

}
