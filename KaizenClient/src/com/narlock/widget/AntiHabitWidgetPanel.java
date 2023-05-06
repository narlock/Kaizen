package com.narlock.widget;

import static com.narlock.util.Constants.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.narlock.domain.AntiHabit;
import com.narlock.json.AntiHabitJsonManager;
import com.narlock.util.AntiHabitUtils;
import com.narlock.util.Utils;

public class AntiHabitWidgetPanel extends JPanel {
	
	private List<AntiHabit> antiHabits;
	private JScrollPane scrollPane;

	public AntiHabitWidgetPanel() {
		antiHabits = AntiHabitJsonManager.readJson();
		
		// Title panel
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(GUI_BACKGROUND_COLOR);
		JLabel titleLabel = new JLabel("Anti Habits");
		titleLabel.setForeground(COMPONENT_FOREGROUND_COLOR);
		titleLabel.setFont(COMPONENT_FONT_SMALL_BOLD);
		titlePanel.add(titleLabel);
		
		// Anti Habits Panel
		JPanel antiHabitsPanel = createAntiHabitsPanel();
		scrollPane = new JScrollPane(antiHabitsPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.setPreferredSize(ANTIHABIT_SCROLL_PANE_SMALL);
		scrollPane.setBorder(null);
		
		// Initialize Panel
		this.setBackground(GUI_BACKGROUND_COLOR);
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER; 
        this.add(titlePanel, gbc);
        this.add(Box.createVerticalStrut(20), gbc);
        this.add(scrollPane, gbc);
	}
	
	private JPanel createAntiHabitsPanel() {
		JPanel antiHabitsPanel = new JPanel();
		
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER; 
        antiHabitsPanel.setLayout(new GridBagLayout());
        
        // Create each individual panel
        for(AntiHabit antiHabit : antiHabits) {
        	antiHabitsPanel.add(createAntiHabitPanel(antiHabit), gbc);
        }
        
        antiHabitsPanel.setBackground(GUI_BACKGROUND_COLOR);
        return antiHabitsPanel;
	}
	
	private JPanel createAntiHabitPanel(AntiHabit antiHabit) {
		long daysSince = AntiHabitUtils.getDaysSince(antiHabit.getDate());
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setPreferredSize(ANTIHABIT_MAIN_PANEL_DIMENSION_SMALL);
		mainPanel.setOpaque(true);
		mainPanel.setBackground(COMPONENT_BACKGROUND_COLOR);
		mainPanel.setBorder(COMPONENT_BORDER_SMALL);
		
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
			daysSinceLabel.setForeground(KAIZEN_GREEN);
		} 
		else if(daysSince >= 8 && daysSince <= 30) {
			daysSinceLabel.setForeground(KAIZEN_GOLD);
		}
		else if(daysSince >= 31 && daysSince <= 90) {
			daysSinceLabel.setForeground(KAIZEN_DIAMOND);
		}
		else if(daysSince >= 91 && daysSince <= 300) {
			daysSinceLabel.setForeground(KAIZEN_PINK);
		}
		else if(daysSince >= 301) {
			daysSinceLabel.setForeground(KAIZEN_RED);
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
		titleLabel.setFont(COMPONENT_FONT_SMALL_BOLD);
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
						getRootPane(), 
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
					revalidateWidget();
				}
			}
			
		});
		
		editPanel.add(resetButton);
		
		mainPanel.add(editPanel, BorderLayout.EAST);
		return mainPanel;
	}
	
	public void revalidateWidget() {
		// Remove old panel
		this.remove(scrollPane);
		this.revalidate();
		this.repaint();
		
		// Construct new panel
		JPanel antiHabitsPanel = createAntiHabitsPanel();
		scrollPane = new JScrollPane(antiHabitsPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.setPreferredSize(ANTIHABIT_SCROLL_PANE_SMALL);
		scrollPane.setBorder(null);
		
		// add new panel
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
		this.add(scrollPane, gbc);
	}
}
