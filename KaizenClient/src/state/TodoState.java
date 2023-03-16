package state;

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
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import panel.EpicItemPanel;
import panel.TodoItemPanel;
import util.Constants;

public class TodoState extends State {
	
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
		
		
		//Component Utils
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER; 
		
		//Side Panel
		sidePanel = new JPanel();
		sidePanel.setLayout(new GridBagLayout());
		sidePanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		sidePanel.setBorder(Constants.RIGHT_BORDER);
//		sidePanel.setPreferredSize(new Dimension(200, 800));
		
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
		epicItemPanel = new EpicItemPanel(numberOfItems); //TODO
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
		todoItemPanel = new TodoItemPanel(); //TODO
		itemsScrollPane = new JScrollPane(todoItemPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		itemsScrollPane.getVerticalScrollBar().setUnitIncrement(16);
		itemsScrollPane.setPreferredSize(Constants.HABIT_SCROLL_PANE_NORMAL);
		itemsScrollPane.setBorder(null);
		
		centerPanel.add(titleAddPanel, gbc);
		centerPanel.add(itemsScrollPane, gbc);
	}

	@Override
	public void initPanelComponentActions() {
		// TODO Auto-generated method stub
		addEpicButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				incrementNumberOfItems();
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
		
		epicItemPanel = new EpicItemPanel(numberOfItems); //TODO
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
