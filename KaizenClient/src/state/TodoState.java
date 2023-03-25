package state;

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

import domain.Todo;
import json.TodoJsonManager;
import panel.EpicItemPanel;
import panel.TodoItemPanel;
import util.Constants;
import util.ErrorPane;
import util.Utils;

public class TodoState extends State {
	
	// Member attributes
	private Todo todo;
	
	//UI Components
	private JPanel sidePanel;
	
	private JButton sortDateButton;
	private JButton sortPriorityButton;
	private JButton viewAllItemsButton;
	
	private JPanel epicsTitlePanel;
	private JLabel epicsLabel;
	private JButton addEpicButton;
	
	private EpicItemPanel epicItemPanel;
	private JScrollPane epicsScrollPane;
	
	
	private JPanel centerPanel;
	private JPanel titleAddPanel;
	private JLabel titleLabel;
	private JButton addItemButton;
	private TodoItemPanel todoItemPanel;
	private JScrollPane itemsScrollPane;
	
	private int numberOfItems;
	

	@Override
	public void initPanelComponents() {
		//TODO Import Todo from JSON file
		todo = TodoJsonManager.readJson();
		
		//Component Utils
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER; 
		
		//Side Panel
		sidePanel = new JPanel();
		sidePanel.setLayout(new GridBagLayout());
		sidePanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		sidePanel.setBorder(Constants.RIGHT_BORDER);
		
		sortDateButton = new JButton("  Sort By Date", new ImageIcon(getClass().getClassLoader().getResource("DATE.png")));
		initButtonVisual(sortDateButton);
		
		sortPriorityButton = new JButton("  Sort By Priority", new ImageIcon(getClass().getClassLoader().getResource("PRIORITY_CRITICAL.png")));
		initButtonVisual(sortPriorityButton);
		
		viewAllItemsButton = new JButton("  All Items", new ImageIcon(getClass().getClassLoader().getResource("LIST.png")));
		initButtonVisual(viewAllItemsButton);
		
		epicsTitlePanel = new JPanel();
		epicsTitlePanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		
		epicsLabel = new JLabel("Epics");
		initLabelVisual(epicsLabel);
		
		addEpicButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("ADD.png")));
		initButtonVisual(addEpicButton);
		
		this.numberOfItems = 4;
		epicItemPanel = new EpicItemPanel(todo.getEpics()); //TODO
		epicsTitlePanel.add(epicsLabel);
		epicsTitlePanel.add(addEpicButton);
		
		
		epicsScrollPane = new JScrollPane(epicItemPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		epicsScrollPane.getVerticalScrollBar().setUnitIncrement(16);
		epicsScrollPane.setPreferredSize(Constants.TODO_EPIC_SCROLL_PANE_DIMENSION);
		epicsScrollPane.setBorder(null);
		
		sidePanel.add(sortDateButton, gbc);
		sidePanel.add(sortPriorityButton, gbc);
		sidePanel.add(Box.createVerticalStrut(50), gbc);
		
		JLabel lineLabel1 = new JLabel("____________________________");
		initLabelVisual(lineLabel1);
		sidePanel.add(lineLabel1, gbc);
		
		sidePanel.add(Box.createVerticalStrut(50), gbc);
		
		sidePanel.add(viewAllItemsButton, gbc);
		sidePanel.add(epicsTitlePanel, gbc);
		sidePanel.add(epicsScrollPane, gbc);
		
		//Center Panel
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridBagLayout());
		centerPanel.setBackground(Constants.GUI_BACKGROUND_COLOR);
		titleAddPanel = new JPanel();
		titleAddPanel.setBackground(Constants.GUI_BACKGROUND_COLOR);
		titleLabel = new JLabel("Todo List");
		initLabelVisual(titleLabel);
		addItemButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("ADD.png")));
		initButtonVisual(addItemButton);
		titleAddPanel.add(titleLabel);
		titleAddPanel.add(addItemButton);
		todoItemPanel = new TodoItemPanel(todo.getItems(), todo.getEpics()); //TODO
		itemsScrollPane = new JScrollPane(todoItemPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		itemsScrollPane.getVerticalScrollBar().setUnitIncrement(16);
		itemsScrollPane.setPreferredSize(Constants.TODO_ITEM_SCROLL_PANE_DIMENSION);
		itemsScrollPane.setBorder(null);
		
		centerPanel.add(titleAddPanel, gbc);
		centerPanel.add(itemsScrollPane, gbc);
	}

	@Override
	public void initPanelComponentActions() {
		// TODO Auto-generated method stub
		addEpicButton.addActionListener(new ActionListener() {
			JPanel panel = new JPanel(new GridLayout(0, 1));
			JLabel titleLabel = new JLabel("Epic Title");
			JTextField titleTextField = new JTextField(); //TODO Add name of associated todo item
			JLabel colorLabel = new JLabel("Color");
			JButton setColorButton = new JButton("Set Color");
			Color color;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				setColorButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						color = JColorChooser.showDialog(setColorButton.getParent(), "Choose a color", Color.BLUE);
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
						addEpicButton.getParent().getParent().getParent().getParent().getParent().getParent().getParent(), 
						panel, 
						"Create Epic", 
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						new ImageIcon(getClass().getClassLoader().getResource("INFO_ERROR_ORANGE.png")));
				if(result == JOptionPane.YES_OPTION) {
					System.out.println("Yes");
					// TODO
				} else {
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
				// TODO Auto-generated method stub
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
				
				Container parentComponent = addItemButton.getParent().getParent().getParent().getParent().getParent().getParent().getParent();
				
				int result = JOptionPane.showConfirmDialog(
						parentComponent, 
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
					String epicString = (String) epicAssignBox.getSelectedItem();
					long priority = priorityBox.getSelectedIndex();
					System.out.println("[todoTitle=" + todoTitle + ", dateString=" + dateString + ", epicString=" 
								+ epicString + ", priority=" + priority + "]");
				}
				else if(result == JOptionPane.OK_OPTION &&
						titleTextField.getText().equals("")) {
					// Display Validation Error on Title
					ErrorPane.displayError(parentComponent, "Could not create Todo Item: a title must be given to the item.");
				} 
				else if(result == JOptionPane.OK_OPTION &&
						!Utils.validateDateString(dueDateTextField.getText())
						) {
					// Display Validation Error on Due Date
					ErrorPane.displayError(parentComponent, "Could not create Todo Item: invalid date format.");
				}
				else {
					System.out.println("Cancel");
				}
			}
			
		});
	}
	
	public void incrementNumberOfItems() {
		//TODO rework this logic
		System.out.println("here");
		sidePanel.remove(epicsScrollPane);
		sidePanel.revalidate();
		sidePanel.repaint();
		
		
		this.numberOfItems++;
		sidePanel.remove(epicsScrollPane);
		
		epicItemPanel = new EpicItemPanel(todo.getEpics()); //TODO
		epicsTitlePanel.add(epicsLabel);
		epicsTitlePanel.add(addEpicButton);
		
		
		epicsScrollPane = new JScrollPane(epicItemPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		epicsScrollPane.getVerticalScrollBar().setUnitIncrement(16);
		epicsScrollPane.setPreferredSize(Constants.TODO_EPIC_SCROLL_PANE_DIMENSION);
		epicsScrollPane.setBorder(null);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER; 
		sidePanel.add(epicsScrollPane, gbc);
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
		button.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
	}
	
	private void initLabelVisual(JLabel label) {
		label.setOpaque(false);
		label.setFont(new Font("Arial", Font.BOLD, 20));
		label.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		label.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
	}
}
