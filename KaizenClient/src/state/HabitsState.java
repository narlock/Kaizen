package state;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;

import domain.Habit;
import domain.Settings;
import json.HabitJsonManager;
import json.SettingsJsonManager;
import panel.HabitsPanel;
import util.Constants;
import util.HabitUtils;
import util.Utils;

public class HabitsState extends State {
	
	private Settings settings;
	
	private static final long serialVersionUID = 6371695672562881139L;
	
	private List<Habit> habits;
	
	private JPanel titlePanel;
	public JLabel titleLabel;
	private JButton shareButton;
	
	private JScrollPane scrollPane;
	private HabitsPanel habitsPanel;
	
	public HabitsState() {
		super();
	}

	@Override
	public void initPanelComponents() {
		settings = SettingsJsonManager.readJson();
		
		//Get habits
		this.habits = HabitJsonManager.readHabits();
		HabitUtils.updateHabits(habits);
		HabitJsonManager.writeHabitJsonToFile(habits);
		
		//Set up title panel
		titlePanel = new JPanel();
		titlePanel.setBackground(Constants.GUI_BACKGROUND_COLOR);
		titleLabel = new JLabel();
		titleLabel.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		titleLabel.setFont(Constants.COMPONENT_FONT_NORMAL_BOLD);
		shareButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("SHARE.png")));
		shareButton.setOpaque(false);
		shareButton.setContentAreaFilled(false);
		shareButton.setBorderPainted(false);
		shareButton.setFocusPainted(false);
		titlePanel.add(titleLabel);
		titlePanel.add(shareButton);
		
		habitsPanel = new HabitsPanel(habits, titleLabel, 0);
		scrollPane = new JScrollPane(habitsPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.setPreferredSize(Constants.HABIT_SCROLL_PANE_NORMAL);
		scrollPane.setBorder(null);
	}

	@Override
	public void initPanelComponentActions() {
		
		// Copy habits to clip board
		shareButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Construct string to copy to clipboard
				String clipboardString = "**Kaizen Habits • " + Utils.dateAsString(Utils.today()) + "**\n\n";
				for(Habit habit : habits) {
					// Occurs today
					if(HabitUtils.occursToday(habit)) {
						// Add Status
						if(habit.getStatus() == 0) {
							clipboardString += "◻️ ";
						}
						else {
							clipboardString += "✅ ";
						}
						
						clipboardString += habit.getTitle();
						if(settings.isShowStreakOnClipboard()) {
							clipboardString += " | " + habit.getStreak() + "🔥";
						}
						clipboardString += "\n";
					}
				}
				
				StringSelection selection = new StringSelection(clipboardString);
		        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		        clipboard.setContents(selection, null);
			}
			
		});
		
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
