package state;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

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
	

	@Override
	public void initPanelComponents() {
		//TODO Import Todo from JSON file
		
		//Side Panel
		sidePanel = new JPanel();
		sidePanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		sidePanel.setBorder(Constants.RIGHT_BORDER);
		sidePanel.setPreferredSize(new Dimension(200, 800));
		
		sortDateButton = new JButton("Date", new ImageIcon(getClass().getClassLoader().getResource("DATE.png")));
		sortPriorityButton = new JButton("Priority", new ImageIcon(getClass().getClassLoader().getResource("PRIORITY_CRITICAL.png")));
		viewAllItemsButton = new JButton("All Items");
		epicsTitlePanel = new JPanel();
		epicsLabel = new JLabel("Epics");
		addEpicButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("ADD.png")));
		epicItemPanel = new EpicItemPanel(); //TODO
		epicsTitlePanel.add(epicsLabel);
		epicsTitlePanel.add(addEpicButton);
		epicsScrollPane = new JScrollPane(epicItemPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		sidePanel.add(sortDateButton);
		sidePanel.add(sortPriorityButton);
		sidePanel.add(viewAllItemsButton);
		sidePanel.add(epicsTitlePanel);
		sidePanel.add(epicsScrollPane);
		
		//Center Panel
		centerPanel = new JPanel();
		centerPanel.setBackground(Constants.GUI_BACKGROUND_COLOR);
		titleAddPanel = new JPanel();
		titleLabel = new JLabel("Todo List");
		addItemButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("ADD.png")));
		titleAddPanel.add(titleLabel);
		titleAddPanel.add(addItemButton);
		todoItemPanel = new TodoItemPanel(); //TODO
		itemsScrollPane = new JScrollPane(todoItemPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		centerPanel.add(titleAddPanel);
		centerPanel.add(itemsScrollPane);
	}

	@Override
	public void initPanelComponentActions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initPanel() {
		// TODO Auto-generated method stub
		this.setLayout(new BorderLayout());
		this.add(sidePanel, BorderLayout.WEST);
		this.add(centerPanel, BorderLayout.CENTER);
	}

}
