package panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import domain.AntiHabit;
import json.AntiHabitJsonManager;
import state.AntiHabitsState;
import util.AntiHabitUtils;
import util.Utils;

import static util.Constants.*;

public class AntiHabitsPanel extends JPanel {
	
	private AntiHabitsState state;
	private List<AntiHabit> antiHabits;
	
	public AntiHabitsPanel(List<AntiHabit> antiHabits, AntiHabitsState state) {
		// Initialize variables
		this.state = state;
		this.antiHabits = antiHabits;
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		// Create each individual panel
		for(AntiHabit antiHabit : antiHabits) {
			this.add(createAntiHabitPanel(antiHabit), gbc);
		}
		
		this.setBackground(GUI_BACKGROUND_COLOR);
	}
	
	private JPanel createAntiHabitPanel(AntiHabit antiHabit) {
		long daysSince = AntiHabitUtils.getDaysSince(antiHabit.getDate());
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setPreferredSize(ANTIHABIT_MAIN_PANEL_DIMENSION);
		mainPanel.setOpaque(true);
		mainPanel.setBackground(COMPONENT_BACKGROUND_COLOR);
		mainPanel.setBorder(COMPONENT_BORDER_NORMAL);
		
		JPanel daysSincePanel = new JPanel();
		daysSincePanel.setBackground(COMPONENT_BACKGROUND_COLOR);
		JLabel daysSinceLabel = new JLabel("" + daysSince);
		daysSinceLabel.setFont(COMPONENT_FONT_NORMAL_BOLD);
		/*
		 * 0-7 days, color of text is green
		 * 8-30 days, color of text is gold
		 * 30-90 days, color of text is diamond
		 * 90-300 days, color of text is pink
		 * 300+ days, color of text is red
		 */
		if(daysSince <= 7) {
			daysSinceLabel.setForeground(Color.GREEN);
		} 
		else if(daysSince >= 8 && daysSince <= 30) {
			daysSinceLabel.setForeground(Color.YELLOW);
		}
		else if(daysSince >= 31 && daysSince <= 90) {
			daysSinceLabel.setForeground(Color.CYAN);
		}
		else if(daysSince >= 91 && daysSince <= 300) {
			daysSinceLabel.setForeground(Color.PINK);
		}
		else if(daysSince >= 301) {
			daysSinceLabel.setForeground(Color.RED);
		}
		daysSincePanel.add(daysSinceLabel);
		mainPanel.add(daysSincePanel, BorderLayout.WEST);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        titlePanel.setBackground(COMPONENT_BACKGROUND_COLOR);
        JLabel titleTopLabel = new JLabel("days since I");
        titleTopLabel.setFont(COMPONENT_FONT_SMALL);
        titleTopLabel.setForeground(COMPONENT_FOREGROUND_COLOR);
        titlePanel.add(titleTopLabel, gbc);
		JLabel titleLabel = new JLabel(antiHabit.getTitle());
		titleLabel.setFont(COMPONENT_FONT_NORMAL_BOLD);
		titleLabel.setForeground(COMPONENT_FOREGROUND_COLOR);
		titlePanel.add(titleLabel, gbc);
		mainPanel.add(titlePanel, BorderLayout.CENTER);
		
		JPanel editPanel = new JPanel();
		editPanel.setBackground(COMPONENT_BACKGROUND_COLOR);
		JButton resetButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("RESET.png")));
		resetButton.setOpaque(false);
		resetButton.setContentAreaFilled(false); 
		resetButton.setBorderPainted(false); 
		resetButton.setFocusPainted(false);
		
		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(
						resetButton.getParent().getParent().getParent().getParent().getParent().getParent(), 
						"You are about to reset " + antiHabit.getTitle() + ". Confirm?", 
						"Reset Anti Habit", 
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						new ImageIcon(getClass().getClassLoader().getResource("INFO_ERROR_ORANGE.png")));
				if(result == JOptionPane.YES_OPTION) {
					// Remove the anti habit
					antiHabit.setDate(Utils.today());
					
					// Update Json
					AntiHabitJsonManager.writeAntiHabitsToFile(antiHabits);
					
					// Revalidate GUI
					state.revalidateHabitsPanel();
				}
			}
			
		});
		
		JButton deleteButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("DELETE.png"))); //TODO add image
		deleteButton.setOpaque(false);
		deleteButton.setContentAreaFilled(false); 
		deleteButton.setBorderPainted(false); 
		deleteButton.setFocusPainted(false);
		
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(
						deleteButton.getParent().getParent().getParent().getParent().getParent().getParent(), 
						"Are you sure you want to delete " + antiHabit.getTitle() + "?", 
						"Delete Anti Habit", 
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						new ImageIcon(getClass().getClassLoader().getResource("INFO_ERROR_ORANGE.png")));
				if(result == JOptionPane.YES_OPTION) {
					// Remove the anti habit
					antiHabits.remove(antiHabit);
					
					// Update Json
					AntiHabitJsonManager.writeAntiHabitsToFile(antiHabits);
					
					// Revalidate GUI
					state.revalidateHabitsPanel();
				}
			}
			
		});
		
		editPanel.add(resetButton);
		editPanel.add(deleteButton);
		
		mainPanel.add(editPanel, BorderLayout.EAST);
		return mainPanel;
	}
	
}
