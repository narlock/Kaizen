package panel;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class HelpPanel extends JPanel {
	
	private JLabel descriptionLabel;
	
	public String description;
	
	
	public HelpPanel(String description) {
		this.description = description;
		
		String labelText = String.format("<html><div style=\"width:%dpx;\">%s</div></html>", 200, description);
	
		descriptionLabel = new JLabel(labelText);

		this.add(descriptionLabel);
	}
}
