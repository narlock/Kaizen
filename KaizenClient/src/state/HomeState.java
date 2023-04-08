package state;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import domain.Home;
import domain.Settings;
import json.HomeJsonManager;
import widget.AntiHabitWidgetPanel;
import widget.HabitWidgetPanel;
import widget.JournalWidgetPanel;
import widget.NoWidgetPanel;
import widget.TodoWidgetPanel;

public class HomeState extends State {
	
	private static final long serialVersionUID = -794674909058043997L;
	private Home home;
	
	private JPanel widget;
	private JPanel widget2;
	private JPanel widget3;
	private JPanel widget4;
	
	public HomeState(Settings settings) {
		super(settings);
	}

	@Override
	public void initPanelComponents() {
		home = HomeJsonManager.readJson();
		
		
		//Set up habits panel
		widget = getWidgetFromString(home.getWidget1());
		widget2 = getWidgetFromString(home.getWidget2());
		widget3 = getWidgetFromString(home.getWidget3());
		widget4 = getWidgetFromString(home.getWidget4());
	}

	@Override
	public void initPanelComponentActions() {}

	@Override
	public void initPanel() {
		// TODO Auto-generated method stub
		this.setLayout(new GridLayout(2,2));
		this.add(widget);
		this.add(widget2);
		this.add(widget3);
		this.add(widget4);
	}

	public JPanel getWidgetFromString(String widgetString) {
		switch(widgetString) {
		case "todo":
			return new TodoWidgetPanel();
		case "habits":
			return new HabitWidgetPanel();
		case "antiHabits":
			return new AntiHabitWidgetPanel();
		case "journal1":
			return new JournalWidgetPanel(1, settings);
		case "journal2":
			return new JournalWidgetPanel(2, settings);
		case "journal3":
			return new JournalWidgetPanel(3, settings);
		case "journal4":
			return new JournalWidgetPanel(4, settings);
		default:
			return new NoWidgetPanel();
		}
	}
}
