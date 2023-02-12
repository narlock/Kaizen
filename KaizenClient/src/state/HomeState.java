package state;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import widget.HabitWidgetPanel;

public class HomeState extends State {
	
	private JPanel widget;
	
	public HomeState() {
		super();
	}

	@Override
	public void initPanelComponents() {
		//Set up habits panel
		widget = new HabitWidgetPanel();
	}

	@Override
	public void initPanelComponentActions() {
		// TODO Auto-generated method stub
	}

	@Override
	public void initPanel() {
		// TODO Auto-generated method stub
		this.setLayout(new GridLayout(2,2));
		this.add(widget);
		this.add(new JButton("Widget2"));
		this.add(new JButton("Widget3"));
		this.add(new JButton("Widget4"));
	}
}
