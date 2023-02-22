package widget;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import panel.HabitsPanel;
import util.Constants;

public class HabitWidgetPanel extends JPanel {
	
	private static final long serialVersionUID = 2456894465204691767L;
	
	private JPanel titlePanel;
	private JLabel titleLabel;
	
	private JPanel habitContainerPanel;
	private JScrollPane scrollPane;
	private HabitsPanel habitsPanel;
	
	public HabitWidgetPanel() {
		this.setBackground(Constants.GUI_BACKGROUND_COLOR);
		titlePanel = new JPanel();
		titlePanel.setBackground(Constants.GUI_BACKGROUND_COLOR);
		titleLabel = new JLabel("Today's Habits");
		titleLabel.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		titleLabel.setFont(Constants.COMPONENT_FONT_SMALL);
		titlePanel.add(titleLabel);
		
		habitContainerPanel = new JPanel();
		habitContainerPanel.setBackground(Constants.GUI_BACKGROUND_COLOR);
        
		habitsPanel = new HabitsPanel(1, false);
		scrollPane = new JScrollPane(habitsPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(Constants.HABIT_SCROLL_PANE_SMALL);
		scrollPane.setBorder(null);
		habitContainerPanel.add(scrollPane);
		this.add(titlePanel);
		this.add(habitContainerPanel);
	}

}
