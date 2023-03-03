package state;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import domain.Habit;
import panel.UpdateHabitsPanel;
import util.Constants;
import util.Debug;
import util.HabitJsonManager;
import util.HabitUtils;

public class UpdateHabitsState extends State {

	private static final long serialVersionUID = 4172163218164088572L;
	private final Debug debug = new Debug(true);
	
	private List<Habit> habits;
	
	private JPanel titlePanel;
	private JLabel titleLabel;
	
	private JScrollPane scrollPane;
	private UpdateHabitsPanel habitsPanel;

	@Override
	public void initPanelComponents() {
		//Get habits
		this.habits = HabitJsonManager.readHabits();
		
		//Set up title panel
		titlePanel = new JPanel();
		titlePanel.setBackground(Constants.GUI_BACKGROUND_COLOR);
		titleLabel = new JLabel();
		titleLabel.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		titleLabel.setFont(Constants.COMPONENT_FONT_NORMAL);
		titlePanel.add(titleLabel);
		
		//Set up habits panel
		habitsPanel = new UpdateHabitsPanel(habits);
		scrollPane = new JScrollPane(habitsPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.setPreferredSize(Constants.HABIT_SCROLL_PANE_LARGE);
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
