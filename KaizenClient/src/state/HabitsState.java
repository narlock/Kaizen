package state;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import panel.HabitsPanel;
import util.Constants;

public class HabitsState extends State {
	
	private static final long serialVersionUID = 6371695672562881139L;
	
	private JPanel titlePanel;
	private JLabel titleLabel;
	
	private JScrollPane scrollPane;
	private HabitsPanel habitsPanel;
	
	public HabitsState() {
		super();
	}

	@Override
	public void initPanelComponents() {
		//Set up title panel
		titlePanel = new JPanel();
		titlePanel.setBackground(Constants.GUI_BACKGROUND_COLOR);
		titleLabel = new JLabel("Today's Habits");
		titleLabel.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		titleLabel.setFont(Constants.COMPONENT_FONT_NORMAL);
		titlePanel.add(titleLabel);
		
		//Set up habits panel
		habitsPanel = new HabitsPanel(0, false);
		scrollPane = new JScrollPane(habitsPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(Constants.HABIT_SCROLL_PANE_NORMAL);
		scrollPane.setBorder(null);
	}

	@Override
	public void initPanelComponentActions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initPanel() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;   
        
        this.add(titlePanel, gbc);
        this.add(Box.createVerticalStrut(20), gbc);
		this.add(scrollPane, gbc);
	}
}
