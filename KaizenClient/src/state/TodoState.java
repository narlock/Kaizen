package state;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import domain.Epic;
import domain.Settings;
import domain.Todo;
import domain.TodoItem;
import json.SettingsJsonManager;
import json.TodoJsonManager;
import panel.EpicItemPanel;
import panel.TodoItemPanel;
import util.Constants;
import util.Debug;
import util.ErrorPane;
import util.FadingLabel;
import util.Utils;

import static util.Constants.*;

public class TodoState extends State {
	
	private Settings settings;
	
	private static final long serialVersionUID = -2176625400803315013L;

	private static final Debug debug = new Debug(true);
	
	// Member attributes
	private Todo todo;
	
	//UI Components
	private JPanel sidePanel;
	
	private JButton sortDateButton;
	private JButton sortPriorityButton;
	private JButton viewTodoItemsButton;
	private JButton viewCompletedItemsButton;
	
	private JPanel epicsTitlePanel;
	private JLabel epicsLabel;
	
	private JButton viewAllItemsButton;
	private JButton addEpicButton;
	
	private EpicItemPanel epicItemPanel;
	private JScrollPane epicsScrollPane;
	
	private JPanel messagePanel;
	private FadingLabel messageLabel;
	
	private JPanel centerPanel;
	private JPanel titleAddPanel;
	private JLabel titleLabel;
	private JButton addItemButton;
	private JButton shareButton;
	private JButton removeAllCompletedItemsButton;
	private TodoItemPanel todoItemPanel;
	private JScrollPane itemsScrollPane;
	
	// Public Boolean representing if we want to show completed todo items or not
	public boolean showCompleted;
	public String epic;

	@Override
	public void initPanelComponents() {
		settings = SettingsJsonManager.readJson();
		
		//TODO Import Todo from JSON file
		todo = TodoJsonManager.readJson();
		showCompleted = false;
		epic = null;
		
		// Sort based off of sorting
		sortTodo();
		
		//Component Utils
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER; 
		
		//Side Panel
		sidePanel = new JPanel();
		sidePanel.setLayout(new GridBagLayout());
		sidePanel.setBackground(COMPONENT_BACKGROUND_COLOR);
		sidePanel.setBorder(RIGHT_BORDER);
		
		sortDateButton = new JButton("  Sort By Date", new ImageIcon(getClass().getClassLoader().getResource("DATE.png")));
		initButtonVisual(sortDateButton);
		
		sortPriorityButton = new JButton("  Sort By Priority", new ImageIcon(getClass().getClassLoader().getResource("PRIORITY_CRITICAL.png")));
		initButtonVisual(sortPriorityButton);
		
		viewTodoItemsButton = new JButton("  Todo Items", new ImageIcon(getClass().getClassLoader().getResource("LIST.png")));
		initButtonVisual(viewTodoItemsButton);
		
		viewCompletedItemsButton = new JButton("  Completed Items", new ImageIcon(getClass().getClassLoader().getResource("COMPLETED.png")));
		initButtonVisual(viewCompletedItemsButton);
		
		epicsTitlePanel = new JPanel();
		epicsTitlePanel.setBackground(COMPONENT_BACKGROUND_COLOR);
		
		epicsLabel = new JLabel("Epics");
		initLabelVisual(epicsLabel);
		
		viewAllItemsButton = new JButton("   View All Items", new ImageIcon(getClass().getClassLoader().getResource("SCROLL.png")));
		initButtonVisual(viewAllItemsButton);
		
		addEpicButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("ADD.png")));
		initButtonVisual(addEpicButton);
		
		epicItemPanel = new EpicItemPanel(todo, this); //TODO
		epicsTitlePanel.add(epicsLabel);
		epicsTitlePanel.add(addEpicButton);
		
		
		epicsScrollPane = new JScrollPane(epicItemPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		epicsScrollPane.getVerticalScrollBar().setUnitIncrement(16);
		epicsScrollPane.setPreferredSize(TODO_EPIC_SCROLL_PANE_DIMENSION);
		epicsScrollPane.setBorder(null);
		
		sidePanel.add(sortDateButton, gbc);
		sidePanel.add(sortPriorityButton, gbc);
		sidePanel.add(Box.createVerticalStrut(25), gbc);
		
		JLabel lineLabel1 = new JLabel("____________________________");
		initLabelVisual(lineLabel1);
		sidePanel.add(lineLabel1, gbc);
		
		sidePanel.add(Box.createVerticalStrut(25), gbc);
		
		sidePanel.add(viewTodoItemsButton, gbc);
		sidePanel.add(viewCompletedItemsButton, gbc);
		sidePanel.add(Box.createVerticalStrut(15), gbc);
		
		JLabel lineLabel2 = new JLabel("____________________________");
		initLabelVisual(lineLabel2);
		sidePanel.add(lineLabel2, gbc);
		
		sidePanel.add(Box.createVerticalStrut(25), gbc);
		sidePanel.add(viewAllItemsButton, gbc);
		sidePanel.add(epicsTitlePanel, gbc);
		sidePanel.add(epicsScrollPane, gbc);
		
		//Center Panel
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridBagLayout());
		centerPanel.setBackground(GUI_BACKGROUND_COLOR);
		titleAddPanel = new JPanel();
		titleAddPanel.setBackground(GUI_BACKGROUND_COLOR);
		
		messagePanel = new JPanel();
		messagePanel.setBackground(Constants.GUI_BACKGROUND_COLOR);
		messageLabel = new FadingLabel("Copied to clipboard!", 2000);
		messageLabel.setFont(Constants.COMPONENT_FONT_NORMAL_BOLD);
		messageLabel.setForeground(Constants.GUI_BACKGROUND_COLOR);
		messagePanel.add(messageLabel);
		
		titleLabel = new JLabel("Todo List");
		initLabelVisual(titleLabel);
		addItemButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("ADD.png")));
		initButtonVisual(addItemButton);
		shareButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("SHARE_CLIP.png")));
		initButtonVisual(shareButton);
		removeAllCompletedItemsButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("REMOVE.png")));
		initButtonVisual(removeAllCompletedItemsButton);
		titleAddPanel.add(titleLabel);
		titleAddPanel.add(addItemButton);
		titleAddPanel.add(shareButton);
		todoItemPanel = new TodoItemPanel(todo, this);
		itemsScrollPane = new JScrollPane(todoItemPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		itemsScrollPane.getVerticalScrollBar().setUnitIncrement(16);
		itemsScrollPane.setPreferredSize(TODO_ITEM_SCROLL_PANE_DIMENSION);
		itemsScrollPane.setBorder(null);
		
		centerPanel.add(messagePanel, gbc);
		centerPanel.add(titleAddPanel, gbc);
		centerPanel.add(itemsScrollPane, gbc);
	}

	@Override
	public void initPanelComponentActions() {
		sortDateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(todo.getSortMode().equals("date")) {
					todo.setSortMode("dateReverse");
				} else {
					todo.setSortMode("date");
				}
				
				TodoJsonManager.writeTodoJsonToFile(todo);
				
				sortTodo();
				revalidateItemPanel();
			}
			
		});
		
		sortPriorityButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(todo.getSortMode().equals("priority")) {
					todo.setSortMode("priorityReverse");
				} else {
					todo.setSortMode("priority");
				}
				
				TodoJsonManager.writeTodoJsonToFile(todo);
				
				sortTodo();
				revalidateItemPanel();
			}
			
		});
		
		viewTodoItemsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showCompleted = false;
				revalidateItemPanel();
			}
			
		});
		
		viewCompletedItemsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showCompleted = true;
				revalidateItemPanel();
			}
			
		});
		
		viewAllItemsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				epic = null;
				revalidateItemPanel();
			}
			
		});
		
		addEpicButton.addActionListener(new ActionListener() {
			JPanel panel = new JPanel(new GridLayout(0, 1));
			JLabel titleLabel = new JLabel("Epic Title");
			JTextField titleTextField = new JTextField();
			JLabel colorLabel = new JLabel("Color");
			JButton setColorButton = new JButton("Set Color");
			Color color;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				color = COMPONENT_BACKGROUND_COLOR;
				titleTextField.setText("");
				
				setColorButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("THE COLOR IS " + color);
						color = JColorChooser.showDialog(getRootPane(), "Choose a color", COMPONENT_BACKGROUND_COLOR);
						if(color == null) {
							color = COMPONENT_BACKGROUND_COLOR;
						}
					}
					
				});
				
				panel.add(titleLabel);
				panel.add(titleTextField);
				panel.add(colorLabel);
				panel.add(setColorButton);
				
				int result = JOptionPane.showConfirmDialog(
						getRootPane(), 
						panel, 
						"Create Epic", 
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						new ImageIcon(getClass().getClassLoader().getResource("INFO_ERROR_ORANGE.png")));
				if(result == JOptionPane.YES_OPTION &&
						!titleTextField.getText().equals("")
					) {
					Epic epic = new Epic(titleTextField.getText(), color);
					todo.getEpics().add(epic);
					TodoJsonManager.writeTodoJsonToFile(todo);
					revalidateEpicPanel();
				} 
				else if(result == JOptionPane.YES_OPTION &&
						titleTextField.getText().equals("")
					) {
					ErrorPane.displayError(getRootPane(), "Could not create habit. Please provide a title.");
				}
				else {
					System.out.println("Cancel");
				}
				
			}
			
		});
		
		addItemButton.addActionListener(new ActionListener() {
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
					debug.print("[todoTitle=" + todoTitle + ", dateString=" + dateString + ", epicString=" 
								+ epicString + ", priority=" + priority + "]");
					
					// Create Item
					TodoItem todoItem = new TodoItem(todoTitle, todoDate, null, (long) priority, epicString);
					
					// Add item to items listd
					todo.getItems().add(todoItem);
					sortTodo();
					
					// Update Json
					TodoJsonManager.writeTodoJsonToFile(todo);
					
					// Revalidate GUI
					revalidateItemPanel();
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
					ErrorPane.displayError(getRootPane(), "<html>Could not create Todo Item: invalid date format (yyyy-MM-dd).<br>"
							+ "This includes providing the leading zero for the date.<br><br>"
							+ "Example: April 2nd, 2023 would be 2023-04-02.</html>");
				}
				else {
					System.out.println("Cancel");
				}
			}
			
		});
		
		shareButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Construct string to copy to clipboard
				String clipboardString = "**My Kaizen Todo**\n\n";
				int todoCount = 0;
				for(TodoItem item : todo.getItems()) {
					if(item.getCompletedDate() == null) {
						todoCount++;
					}
				}
				if(todo.getItems().size() >= 10) {
					todoCount = 9;
				}
				
				if(todoCount == 0) {
					clipboardString += "All completed! 😃";
				} else {
					for(int i = 0; i < todoCount; i++) {
						TodoItem item = todo.getItems().get(i);
						
						clipboardString += "◻️ " + item.getTitle();
						if(settings.isShowTodoEpicOnClipboard() && !item.getEpic().equals("")) {
							clipboardString += ", " + item.getEpic();
						}
						clipboardString += "\n";
					}
				}
				
				clipboardString += "\n**Completed Today**\n\n";
				for(TodoItem item : todo.getItems()) {
					if(item.getCompletedDate() != null &&
							Utils.dateAsString(item.getCompletedDate())
								.equals(Utils.dateAsString(Utils.today()))) {
						clipboardString += "✅ " + item.getTitle();
						if(settings.isShowTodoEpicOnClipboard() && !item.getEpic().equals("")) {
							clipboardString += ", " + item.getEpic();
						}
						clipboardString += "\n";
					}
				}
				
				
				StringSelection selection = new StringSelection(clipboardString);
		        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		        clipboard.setContents(selection, null);
		        messageLabel.fade();
			}
			
		});
		
		removeAllCompletedItemsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(
						getRootPane(), 
						"Delete all completed items?", 
						"Confirm deletion", 
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						new ImageIcon(getClass().getClassLoader().getResource("INFO_ERROR_ORANGE.png")));
				if(result == JOptionPane.OK_OPTION) {
					// Delete all of the items that contain a completion date
					for(int i = 0; i < todo.getItems().size(); i++) {
						if(todo.getItems().get(i).getCompletedDate() != null) {
							todo.getItems().remove(i);
						}
					}
					
					TodoJsonManager.writeTodoJsonToFile(todo);
					revalidateItemPanel();
				}
			}
			
		});
	}
	
	public void revalidateEpicPanel() {
		// Remove old panel
		sidePanel.remove(epicsScrollPane);
		sidePanel.revalidate();
		sidePanel.repaint();
		
		// Construct new panel
		epicItemPanel = new EpicItemPanel(todo, this);
		
		epicsScrollPane = new JScrollPane(epicItemPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		epicsScrollPane.getVerticalScrollBar().setUnitIncrement(16);
		epicsScrollPane.setPreferredSize(TODO_EPIC_SCROLL_PANE_DIMENSION);
		epicsScrollPane.setBorder(null);
		
		// Add new panel
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER; 
		sidePanel.add(epicsScrollPane, gbc);
	}
	
	/**
	 * revalidateItemPanel
	 * @brief 
	 */
	public void revalidateItemPanel() {
		if(showCompleted) {
			// Remove the 'add todo item' and replace with 'delete all completed'
			titleAddPanel.remove(addItemButton);
			titleAddPanel.remove(shareButton);
			
			// Add the remove button
			titleAddPanel.add(removeAllCompletedItemsButton);
			
			//Revalidate
			titleAddPanel.revalidate();
			titleAddPanel.repaint();
			
		} else {
			// Remove the possibility of 'delete all completed' with 'add todo item'
			titleAddPanel.remove(removeAllCompletedItemsButton);
			
			// Add the remove button
			titleAddPanel.add(addItemButton);
			titleAddPanel.add(shareButton);
			
			//Revalidate
			titleAddPanel.revalidate();
			titleAddPanel.repaint();
		}
		
		
		// Remove old panel
		centerPanel.remove(itemsScrollPane);
		centerPanel.revalidate();
		centerPanel.repaint();
		
		// Construct new panel
		todoItemPanel = new TodoItemPanel(todo, this);
		itemsScrollPane = new JScrollPane(todoItemPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		itemsScrollPane.getVerticalScrollBar().setUnitIncrement(16);
		itemsScrollPane.setPreferredSize(TODO_ITEM_SCROLL_PANE_DIMENSION);
		itemsScrollPane.setBorder(null);
		
		// Add new panel
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER; 
		centerPanel.add(itemsScrollPane, gbc);
	}

	@Override
	public void initPanel() {
		this.setLayout(new BorderLayout());
		this.add(sidePanel, BorderLayout.WEST);
		this.add(centerPanel, BorderLayout.CENTER);
	}

	private void initButtonVisual(JButton button) {
		button.setOpaque(false);
		button.setContentAreaFilled(false); 
		button.setBorderPainted(false); 
		button.setFocusPainted(false);
		button.setFont(new Font("Arial", Font.BOLD, 20));
		button.setForeground(COMPONENT_FOREGROUND_COLOR);
	}
	
	private void initLabelVisual(JLabel label) {
		label.setOpaque(false);
		label.setFont(new Font("Arial", Font.BOLD, 20));
		label.setBackground(COMPONENT_BACKGROUND_COLOR);
		label.setForeground(COMPONENT_FOREGROUND_COLOR);
	}
	
	public void sortTodo() {
		switch(todo.getSortMode()) {
		case "date":
			debug.print("Sorting by Date");
			todo.sortItemsByDate();
			break;
		case "dateReverse":
			debug.print("Sorting by Date Reversed");
			todo.sortItemsByDateReverse();
			break;
		case "priority":
			debug.print("Sorting by Priority");
			todo.sortItemsByPriority();
			break;
		case "priorityReverse":
			debug.print("Sorting by Priority Reversed");
			todo.sortItemsByPriorityReverse();
			break;
		}
	}
}
