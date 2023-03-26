package widget;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import domain.Epic;
import domain.Todo;
import domain.TodoItem;
import json.TodoJsonManager;
import util.ErrorPane;
import util.TodoUtils;
import util.Utils;

import static util.Constants.*;

public class TodoWidgetPanel extends JPanel {
	
	private Todo todo;
	private List<TodoItem> todoItems;
	
	private JScrollPane itemsScrollPane;
	
	public TodoWidgetPanel() {
		// Initialize member attributes
		todo = TodoJsonManager.readJson();
		sortTodo();
		todoItems = todo.getItems();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		// Panel attributes
		this.setLayout(new GridBagLayout());
		this.setBackground(GUI_BACKGROUND_COLOR);
		
		// Title Panel
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(GUI_BACKGROUND_COLOR);
		JLabel titleLabel = new JLabel("Todo List");
		titleLabel.setOpaque(false);
		titleLabel.setFont(COMPONENT_FONT_SMALL_BOLD);
		titleLabel.setBackground(COMPONENT_BACKGROUND_COLOR);
		titleLabel.setForeground(COMPONENT_FOREGROUND_COLOR);
		JButton addItemButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("ADD.png")));
		addItemButton.setPreferredSize(new Dimension(16, 16));
		addItemButton.setOpaque(false);
		addItemButton.setContentAreaFilled(false); 
		addItemButton.setBorderPainted(false); 
		addItemButton.setFocusPainted(false);
		addItemButton.setFont(COMPONENT_FONT_SMALL_BOLD);
		addItemButton.setForeground(COMPONENT_FOREGROUND_COLOR);
		
		addItemButton.addActionListener(new ActionListener() {
			JPanel panel = new JPanel(new GridLayout(0, 1));
			JLabel titleLabel = new JLabel("Title");
			JTextField titleTextField = new JTextField();
			JLabel priorityLabel = new JLabel("Priority");
			JComboBox<String> priorityBox = new JComboBox<>();
			JLabel dueDateLabel = new JLabel("Due Date (yyyy-MM-dd)");
			JTextField dueDateTextField = new JTextField();
			JLabel epicAssignLabel = new JLabel("Epic");
			JComboBox<String> epicAssignBox = new JComboBox<>();
			
			@Override
			public void actionPerformed(ActionEvent e) {
				titleTextField.setText("");
				dueDateTextField.setText("");
				
				// Auto-generated method stub
				priorityBox.removeAllItems();
				
				priorityBox.addItem("Low");
				priorityBox.addItem("Medium");
				priorityBox.addItem("High");
				priorityBox.addItem("Critical");
				
				// populate epic box with epics
				epicAssignBox.removeAllItems();
				epicAssignBox.addItem("");
				for(Epic epic : todo.getEpics()) {
					epicAssignBox.addItem(epic.getTitle());
				}
				
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
						"Create Todo Item", 
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						new ImageIcon(getClass().getClassLoader().getResource("INFO_ERROR_ORANGE.png")));
				if(result == JOptionPane.YES_OPTION &&
						!titleTextField.getText().equals("") &&
						Utils.validateDateString(dueDateTextField.getText())
					) {
					String todoTitle = titleTextField.getText();
					String dateString = dueDateTextField.getText();
					Date todoDate = Utils.stringToDate(dateString);
					String epicString = (String) epicAssignBox.getSelectedItem();
					long priority = priorityBox.getSelectedIndex();
					
					// Create Item
					TodoItem todoItem = new TodoItem(todoTitle, todoDate, null, (long) priority, epicString);
					
					// Add item to items listd
					todo.getItems().add(todoItem);
					sortTodo();
					
					// Update Json
					TodoJsonManager.writeTodoJsonToFile(todo);
					
					// Revalidate GUI
					revalidateWidget();
				}
				else if(result == JOptionPane.OK_OPTION &&
						titleTextField.getText().equals("")) {
					// Display Validation Error on Title
					ErrorPane.displayError(getRootPane(), "Could not create Todo Item: a title must be given to the item.");
				} 
				else if(result == JOptionPane.OK_OPTION &&
						!Utils.validateDateString(dueDateTextField.getText())
						) {
					// Display Validation Error on Due Date
					ErrorPane.displayError(getRootPane(), "Could not create Todo Item: invalid date format.");
				}
				else {
					System.out.println("Cancel");
				}
			}
			
		});
		
		titlePanel.add(titleLabel);
		titlePanel.add(Box.createHorizontalStrut(5));
		titlePanel.add(addItemButton);
		
		// Todo Panel with Scroll Pane
		JPanel todoItemPanel = createTodoItemPanel();
		itemsScrollPane = new JScrollPane(todoItemPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		itemsScrollPane.getVerticalScrollBar().setUnitIncrement(16);
		itemsScrollPane.setPreferredSize(TODO_ITEM_SCROLL_PANE_DIMENSION_SMALL);
		itemsScrollPane.setBorder(null);
		
		this.add(titlePanel, gbc);
		this.add(Box.createVerticalStrut(23), gbc);
		this.add(itemsScrollPane, gbc);
	}
	
	private JPanel createTodoItemPanel() {
		JPanel todoItemPanel = new JPanel();
		todoItemPanel.setBackground(GUI_BACKGROUND_COLOR);
		todoItemPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		// Add todoItems to panel
    	for(int i = 0; i < todoItems.size(); i++) {
    		if(todoItems.get(i).getCompletedDate() == null) {
    			todoItemPanel.add(createTodoItemPanel(todoItems.get(i)), gbc);
    		}
    			
    	}
    	
    	return todoItemPanel;
	}
	
	private JPanel createTodoItemPanel(TodoItem todoItem) {
		JPanel todoItemMainPanel = new JPanel();
		todoItemMainPanel.setLayout(new BorderLayout());
		todoItemMainPanel.setPreferredSize(TODO_ITEM_PANEL_DIMENSION_SMALL);
		todoItemMainPanel.setOpaque(true);
		
		Color itemPanelColor = COMPONENT_BACKGROUND_COLOR;
		todoItemMainPanel.setBackground(itemPanelColor);
		if(!todoItem.getEpic().equals("")) {
			for(Epic epic : todo.getEpics()) {
				if(todoItem.getEpic().equals(epic.getTitle())) {
					itemPanelColor = epic.getColor();
					todoItemMainPanel.setBackground(itemPanelColor);
					break;
				}
			}
		}
		
		todoItemMainPanel.setBorder(COMPONENT_BORDER_SMALL); //TODO Change this to associated epic color.
		
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
					revalidateWidget();
				} else {
					completeTodoItemButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("TODO_BUTTON_COMPLETED.png")));
					todoItem.setCompletedDate(Utils.today());
					TodoJsonManager.writeTodoJsonToFile(todo);
					revalidateWidget();
				}
				
			}
			
		});
		
		JPanel todoItemTitlePanel = new JPanel();
		todoItemTitlePanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;   
		todoItemTitlePanel.setBackground(itemPanelColor);
		JLabel todoTitleLabel = new JLabel(todoItem.getTitle());
		todoTitleLabel.setFont(COMPONENT_FONT_SMALL_BOLD);
		todoTitleLabel.setForeground(COMPONENT_FOREGROUND_COLOR);
		todoItemTitlePanel.add(todoTitleLabel, gbc);
		JLabel dueDateLabel = new JLabel("Due: " + Utils.dateAsString(todoItem.getDueDate()));
		dueDateLabel.setFont(COMPONENT_FONT_SMALL);
		dueDateLabel.setForeground(COMPONENT_FOREGROUND_COLOR);
		todoItemTitlePanel.add(dueDateLabel, gbc);
		todoItemMainPanel.add(todoItemTitlePanel, BorderLayout.CENTER);
		
		
		//Create priority panel
		JPanel editAndPriorityPanel = new JPanel();
		editAndPriorityPanel.setBackground(itemPanelColor);
		JLabel priorityLabel = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(TodoUtils.getPriorityImage(todoItem.getPriority()))));
		priorityLabel.setOpaque(false);
		priorityLabel.setBackground(COMPONENT_BACKGROUND_COLOR);
		priorityLabel.setForeground(COMPONENT_FOREGROUND_COLOR);
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
				for(Epic epic : todo.getEpics()) {
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
					revalidateWidget();
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
	
	private void revalidateWidget() {
		// Remove old panel
		this.remove(itemsScrollPane);
		this.revalidate();
		this.repaint();
		
		// Construct new panel
		sortTodo();
		JPanel todoItemPanel = createTodoItemPanel();
		itemsScrollPane = new JScrollPane(todoItemPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		itemsScrollPane.getVerticalScrollBar().setUnitIncrement(16);
		itemsScrollPane.setPreferredSize(TODO_ITEM_SCROLL_PANE_DIMENSION_SMALL);
		itemsScrollPane.setBorder(null);
		
		// Add new panel
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER; 
		this.add(itemsScrollPane, gbc);
	}
	
	public void sortTodo() {
		switch(todo.getSortMode()) {
		case "date":
			todo.sortItemsByDate();
			break;
		case "priority":
			todo.sortItemsByPriority();
			break;
		}
	}
}
