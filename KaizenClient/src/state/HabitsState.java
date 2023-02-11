package state;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.Habit;
import util.JsonReader;
import util.NormalBorder;
import util.RoundedBorder;

public class HabitsState extends State {
	
	public HabitsState() {
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
		JPanel panel = new JPanel();
		panel.setOpaque(true);
		panel.setBackground(Color.GRAY);
		panel.setBorder(new RoundedBorder(Color.BLACK, 3, 10, 10, true));
		JButton button = new JButton();
		button.setPreferredSize(new Dimension(20, 20));
		button.setOpaque(true);
		button.setBorder(new NormalBorder(Color.BLACK, 2, 0, 0, true));
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				button.setBackground(Color.GREEN);
				button.setEnabled(false);
			}
			
		});
		JLabel title = new JLabel("Test Habit");
		title.setForeground(Color.WHITE);
		JLabel streak = new JLabel("🔥 10");
		streak.setForeground(Color.WHITE);
		
		panel.add(button, BorderLayout.WEST);
		panel.add(Box.createHorizontalStrut(20));
		panel.add(title);
		panel.add(Box.createHorizontalStrut(20));
		panel.add(streak, BorderLayout.EAST);
		
		this.add(panel);
	}
}
