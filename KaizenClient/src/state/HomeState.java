package state;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import widget.HabitWidgetPanel;

public class HomeState extends State {
	
	private static final long serialVersionUID = -794674909058043997L;
	
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
		this.add(new JLabel(new ImageIcon(getClass().getClassLoader().getResource("INFO_ERROR_ORANGE.png"))));
		this.add(new JLabel("Widget3"));
		this.add(new JLabel("Widget4"));
	}
}
