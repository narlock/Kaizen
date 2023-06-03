package com.narlock.state;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.narlock.domain.AntiHabit;
import com.narlock.json.AntiHabitJsonManager;
import com.narlock.panel.AntiHabitsPanel;
import com.narlock.util.Constants;
import com.narlock.util.ErrorPane;
import com.narlock.util.Utils;
import com.toedter.calendar.JDateChooser;

public class AntiHabitsState extends State {
	
	private static final long serialVersionUID = -5898974247614071603L;

	private List<AntiHabit> antiHabits;
	
	private JPanel titlePanel;
	private JLabel titleLabel;
	private JButton createAntiHabitButton;
	
	private JScrollPane scrollPane;
	private AntiHabitsPanel antiHabitsPanel;
	
	public AntiHabitsState() {
		super();
	}

	@Override
	public void initPanelComponents() {
		// Get antiHabits
		this.antiHabits = AntiHabitJsonManager.readJson();
		
		//Set up title panel
		titlePanel = new JPanel();
		titlePanel.setBackground(Constants.GUI_BACKGROUND_COLOR);
		titleLabel = new JLabel("Anti Habits");
		titleLabel.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		titleLabel.setFont(Constants.COMPONENT_FONT_NORMAL_BOLD);
		
		createAntiHabitButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("ADD.png")));
		createAntiHabitButton.setOpaque(false);
		createAntiHabitButton.setContentAreaFilled(false); 
		createAntiHabitButton.setBorderPainted(false); 
		createAntiHabitButton.setFocusPainted(false);
		
		titlePanel.add(titleLabel);
		titlePanel.add(createAntiHabitButton);
		
		antiHabitsPanel = new AntiHabitsPanel(antiHabits, this);
		scrollPane = new JScrollPane(antiHabitsPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.setPreferredSize(Constants.ANTIHABIT_SCROLL_PANE_NORMAL);
		scrollPane.setBorder(null);
	}

	@Override
	public void initPanelComponentActions() {
		createAntiHabitButton.addActionListener(new ActionListener() {
			JPanel panel = new JPanel(new GridLayout(0, 1));
			JLabel titleLabel = new JLabel("Anti Habit Title");
			JTextField titleTextField = new JTextField();
			JLabel dateLabel = new JLabel("Start Date");
			JDateChooser dateChooser = new JDateChooser(null, "yyyy-MM-dd");
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					titleTextField.setText("");
					dateChooser.setDate(null);
					
					panel.add(titleLabel);
					panel.add(titleTextField);
					panel.add(dateLabel);
					panel.add(dateChooser);
					
					int result = JOptionPane.showConfirmDialog(
							getRootPane(), 
							panel, 
							"Create Anti Habit", 
							JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							new ImageIcon(getClass().getClassLoader().getResource("INFO_ERROR_ORANGE.png")));
					if(result == JOptionPane.YES_OPTION &&
							!titleTextField.getText().equals("") &&
							Utils.validateDateString(Utils.dateAsString(dateChooser.getDate()))
						) {
						// Happy Path
						
						// Create Anti Habit
						String title = titleTextField.getText();
						Date date = dateChooser.getDate();
						AntiHabit antiHabit = new AntiHabit(title, date);
						
						// Add antiHabit to list
						antiHabits.add(antiHabit);
						
						// Update Json
						AntiHabitJsonManager.writeAntiHabitsToFile(antiHabits);
						
						// Revalidate GUI
						revalidateHabitsPanel();
					}
					else if(result == JOptionPane.YES_OPTION &&
							titleTextField.getText().equals("")
						) {
						// Display Validation Error on Title
						ErrorPane.displayError(getRootPane(), "Could not create Anti Habit Item: a title must be given to the item.");
					}
					else if(result == JOptionPane.YES_OPTION &&
							!Utils.validateDateString(Utils.dateAsString(dateChooser.getDate()))
						) {
						// Display Validation Error on Due Date
						ErrorPane.displayError(getRootPane(), "Could not create Anti Habit Item: invalid date format.");
					} else {
						System.out.println("Cancel");
					}
				} catch (Exception ex) {
					// If date format is not correct
					ErrorPane.displayError(getRootPane(), "Unexpected date format provided. Please try again.");
				}
			}
			
		});
		
	}
	
	public void revalidateHabitsPanel() {
		// Remove old panel
		this.remove(scrollPane);
		this.revalidate();
		this.repaint();
		
		// Construct new panel
		antiHabitsPanel = new AntiHabitsPanel(antiHabits, this);
		scrollPane = new JScrollPane(antiHabitsPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.setPreferredSize(Constants.ANTIHABIT_SCROLL_PANE_NORMAL);
		scrollPane.setBorder(null);
		
		// Add new panel
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER; 
		this.add(scrollPane, gbc);
		
	}

	@Override
	public void initPanel() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;   
        
        this.add(titlePanel, gbc);
        this.add(Box.createVerticalStrut(20), gbc);
		this.add(scrollPane, gbc);
	}

}
