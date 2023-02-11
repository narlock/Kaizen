package state;

import javax.swing.JButton;

public class HomeState extends State {
	
	public HomeState() {
		super();
	}

	@Override
	public void initPanelComponents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initPanelComponentActions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initPanel() {
		// TODO Auto-generated method stub
		this.add(new JButton("Hello Home"));
	}
}
