package panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import util.Constants;

public class EpicItemPanel extends JPanel {
	private GridBagConstraints gbc;
	
	private int numberOfItems;
	
	public EpicItemPanel(int numberOfItems) {
		this.numberOfItems = numberOfItems;
		
		this.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		this.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		createEpicItemPanels();
	}
	
	private void createEpicItemPanels() {
		for(int i = 0; i < numberOfItems; i++) {
			this.add(createEpicItemPanel(), gbc);
		}
	}
	
	private JButton createEpicItemPanel() {
		JButton habitMainPanel = new JButton("This is a test Epic");
		habitMainPanel.setPreferredSize(Constants.TODO_EPIC_PANEL_DIMENSION);
		habitMainPanel.setOpaque(true);
		habitMainPanel.setBackground(new Color(0, 40, 171));
		habitMainPanel.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		habitMainPanel.setBorder(Constants.COMPONENT_BORDER_NORMAL);
		
		return habitMainPanel;
	}
}
