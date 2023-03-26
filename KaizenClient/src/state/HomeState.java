package state;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import widget.HabitWidgetPanel;
import widget.JournalWidgetPanel;

public class HomeState extends State {
	
	private static final long serialVersionUID = -794674909058043997L;
	
	private JPanel widget;
	private JPanel widget2;
	private JPanel widget3;
	private JPanel widget4;
	
	public HomeState() {
		super();
	}

	@Override
	public void initPanelComponents() {
		//Set up habits panel
		widget = new HabitWidgetPanel();
		widget2 = new JournalWidgetPanel(1);
		widget3 = new JournalWidgetPanel(2);
		widget4 = new JournalWidgetPanel(3);
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
		this.add(widget2);
//		this.add(widget3);
//		this.add(widget4);
		this.add(new JLabel(new ImageIcon(getClass().getClassLoader().getResource("INFO_ERROR_ORANGE.png"))));
		this.add(new JLabel(new ImageIcon(getClass().getClassLoader().getResource("INFO_ERROR_ORANGE.png"))));
	}
}
