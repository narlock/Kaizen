package com.narlock.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import com.narlock.domain.Settings;
import com.narlock.json.SettingsJsonManager;
import com.narlock.panel.CustomHomePanel;
import com.narlock.state.AntiHabitsState;
import com.narlock.state.CreateHabitState;
import com.narlock.state.HabitsState;
import com.narlock.state.HelpInfoState;
import com.narlock.state.HomeState;
import com.narlock.state.JournalState;
import com.narlock.state.State;
import com.narlock.state.TodoState;
import com.narlock.state.UpdateHabitsState;
import com.narlock.util.CheckForUpdates;
import com.narlock.util.Constants;
import com.narlock.util.Debug;
import com.narlock.util.DiscordRP;

/**
 * MainGUI
 * @author narlock
 *
 * The main frame of Kaizen. Contains the top menu bar
 * and a state for changing application states.
 */
public class MainGUI extends JFrame {
	
	private CheckForUpdates checkForUpdates;
	private Settings settings;

	private static final long serialVersionUID = -6508626185123863757L;
	private final Debug debug = new Debug(true);
	
	/**
	 * The top menu bar
	 */
	private JMenuBar menuBar;
	
	/**
	 * Menus and associated items
	 */
	private JMenu homeMenu;
	private JMenuItem customizeHomeMenuItem;
	
	private JMenu todoMenu;
	private JMenuItem printToDoMenuItem;
	
	private JMenu habitsMenu;
	private JMenuItem createHabitMenuItem;
	private JMenuItem updateHabitsMenuItem;
	
	private JMenu antiHabitsMenu;
	
	private JMenu journalMenu;
	private JMenuItem printJournalMenuItem;
	
	private JMenu relationshipsMenu;
	private JMenuItem createRelationshipMenuItem;
	private JMenuItem updateRelationshipsMenuItem;
	
	private JMenu helpMenu;
	private JMenuItem updateSettingsMenuItem;
	
	/**
	 * The current state of Kaizen
	 */
	private State state;
	
	/**
	 * Discord RPC
	 */
	private DiscordRP discordRP;
	
	public MainGUI() {
		initMemberVariables();
		initComponents();
		initComponentActions();
		addComponentsToFrame();
		initFrame();
		
		checkForUpdates = new CheckForUpdates();
		try {
			if(checkForUpdates.checkForUpdates()) {
			    JLabel label = new JLabel("<html>New Update is ready to be downloaded.<br><br>"
			                            + "Go <a href=\"https://github.com/narlock/Kaizen/releases/\">here</a> to download!</html>");
			    label.setForeground(Color.BLUE);
			    label.setFont(Constants.COMPONENT_FONT_NORMAL_BOLD);
			    label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			    label.addMouseListener(new MouseAdapter() {
			        @Override
			        public void mouseClicked(MouseEvent e) {
			            if (e.getClickCount() > 0) {
		                    try {
								Desktop.getDesktop().browse(new URI("https://github.com/narlock/Kaizen/releases/"));
							} catch (IOException | URISyntaxException e1) {
								e1.printStackTrace();
							}
			            }
			        }
			    });
			    label.addMouseMotionListener(new MouseAdapter() {
			        @Override
			        public void mouseMoved(MouseEvent e) {
			            label.setText("<html><a href='' style='color:blue;'>New Update is ready to be downloaded.<br><br>"
			                            + "Go here to download!</a></html>");
			        }

			        @Override
			        public void mouseExited(MouseEvent e) {
			            label.setText("<html>New Update is ready to be downloaded.<br><br>"
			                            + "Go <a href=\"https://github.com/narlock/Kaizen/releases/\">here</a> to download!</html>");
			        }
			    });

			    JOptionPane.showMessageDialog(
			            getRootPane(), 
			            label,
			            "Kaizen Update Available", 
			            JOptionPane.INFORMATION_MESSAGE, 
			            new ImageIcon(getClass().getClassLoader().getResource("INFO_ERROR_ORANGE.png")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initMemberVariables() {
		settings = SettingsJsonManager.readJson();
		state = new HomeState(settings);
		
		if(settings.isEnableDiscordRPC()) {
			debug.print("Enabling Discord RPC");
			discordRP = new DiscordRP();
			discordRP.start();
		}
	}
	
	private void initComponents() {
		menuBar = new JMenuBar();
		
		homeMenu = new JMenu("Home");
		customizeHomeMenuItem = new JMenuItem("Customize Home");
		
		todoMenu = new JMenu("Todo");
		printToDoMenuItem = new JMenuItem("Print Todo");
		printToDoMenuItem.setEnabled(false);	
		
		habitsMenu = new JMenu("Habits");
		createHabitMenuItem = new JMenuItem("Start Habit");
		updateHabitsMenuItem = new JMenuItem("Update Habits");
		
		antiHabitsMenu = new JMenu("AntiHabits");
		
		journalMenu = new JMenu("Journal");
		printJournalMenuItem = new JMenuItem("Print Journal");
		printJournalMenuItem.setEnabled(false);
		
		relationshipsMenu = new JMenu("Relationships");
		relationshipsMenu.setEnabled(false);
		createRelationshipMenuItem = new JMenuItem("Add Relationship");
		updateRelationshipsMenuItem = new JMenuItem("Update Relationships");
		
		helpMenu = new JMenu("Help");
		updateSettingsMenuItem = new JMenuItem("Settings");
	}
	
	private void initComponentActions() {
		homeMenu.addMenuListener(new MenuListener() {
			@Override
			public void menuSelected(MenuEvent e) {
				if(!(state instanceof HomeState)) {
					debug.print("Home Menu selected, changing to Home State");
					changeState(new HomeState(settings));
				} else {
					debug.print("Home Menu selected, but already in state. Will not reload state.");
				}
			}
			@Override
			public void menuDeselected(MenuEvent e) {}
			@Override
			public void menuCanceled(MenuEvent e) {}
			
		});
		customizeHomeMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!(state instanceof HomeState)) {
					debug.print("Home Menu selected, changing to Home State");
					changeState(new HomeState(settings));
				} else {
					debug.print("Home Menu selected, but already in state. Will not reload state.");
				}
				
				// Open Customization Menu
				int result = JOptionPane.showConfirmDialog(
						getRootPane(), 
						new CustomHomePanel(), 
						"Customize Home", 
						JOptionPane.OK_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						new ImageIcon(getClass().getClassLoader().getResource("INFO_ERROR_ORANGE.png")));
				if(result == JOptionPane.OK_OPTION) {
					changeState(new HomeState(settings));
				}
			}
			
		});
		todoMenu.addMenuListener(new MenuListener() {
			@Override
			public void menuSelected(MenuEvent e) {
				if(!(state instanceof TodoState)) {
					debug.print("Todo Menu selected, changing to Todo State");
					changeState(new TodoState(settings));
				} else {
					debug.print("Todo Menu selected, but already in state. Will not reload state.");
				}
			}
			@Override
			public void menuDeselected(MenuEvent e) {}
			@Override
			public void menuCanceled(MenuEvent e) {}
			
		});
		habitsMenu.addMenuListener(new MenuListener() {
			@Override
			public void menuSelected(MenuEvent e) {
				if(!(state instanceof HabitsState)) {
					debug.print("Habits Menu selected, changing to Habits State");
					changeState(new HabitsState(settings));
				} else {
					debug.print("Habits Menu selected, but already in state. Will not reload state.");
				}
			}
			@Override
			public void menuDeselected(MenuEvent e) {}
			@Override
			public void menuCanceled(MenuEvent e) {}
			
		});
		createHabitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!(state instanceof CreateHabitState)) {
					debug.print("CreateHabit Menu selected, changing to CreateHabit State");
					changeState(new CreateHabitState());
				} else {
					debug.print("CreateHabit Menu selected, but already in state. Will not reload state.");
				}
			}
		});
		updateHabitsMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!(state instanceof UpdateHabitsState)) {
					debug.print("UpdateHabit Menu selected, changing to UpdateHabit State");
					changeState(new UpdateHabitsState());
				} else {
					debug.print("UpdateHabit Menu selected, but already in state. Will not reload state.");
				}
			}
		});
		antiHabitsMenu.addMenuListener(new MenuListener() {

			@Override
			public void menuSelected(MenuEvent e) {
				if(!(state instanceof AntiHabitsState)) {
					debug.print("AntiHabitsState Menu selected, changing to AntiHabitsState State");
					changeState(new AntiHabitsState());
				} else {
					debug.print("AntiHabitsState Menu selected, but already in state. Will not reload state.");
				}
			}
			@Override
			public void menuDeselected(MenuEvent e) {}
			@Override
			public void menuCanceled(MenuEvent e) {}
			
		});
		journalMenu.addMenuListener(new MenuListener() {

			@Override
			public void menuSelected(MenuEvent e) {
				if(!(state instanceof JournalState)) {
					debug.print("JournalState Menu selected, changing to JournalState State");
					changeState(new JournalState(settings));
				} else {
					debug.print("JournalState Menu selected, but already in state. Will not reload state.");
				}
			}
			@Override
			public void menuDeselected(MenuEvent e) {}
			@Override
			public void menuCanceled(MenuEvent e) {}
		});
		
		helpMenu.addMenuListener(new MenuListener() {
			
			@Override
			public void menuSelected(MenuEvent e) {
				if(!(state instanceof HelpInfoState)) {
					debug.print("HelpInfoState Menu selected, changing to HelpInfoState State");
					changeState(new HelpInfoState());
				} else {
					debug.print("HelpInfoState Menu selected, but already in state. Will not reload state.");
				}
			}
			
			@Override
			public void menuDeselected(MenuEvent e) {}
			@Override
			public void menuCanceled(MenuEvent e) {}
		});
		
		updateSettingsMenuItem.addActionListener(new ActionListener() {
			
			JPanel panel = new JPanel();
			
			JLabel generalSettingsLabel = new JLabel("General Settings");
			JCheckBox enableDiscordRPCCheckBox = new JCheckBox("Discord Presence (Windows only, requires restart)");
			
			JLabel todoSettingsLabel = new JLabel("Todo Settings");
			JCheckBox enableTodoEpicOnClipboard = new JCheckBox("Show Epics on Share");
			
			JLabel habitSettingsLabel = new JLabel("Habit Settings");
			JCheckBox enableShowStreakOnClipboard = new JCheckBox("Show Streaks on Share");
			
			JLabel journalSettingsLabel = new JLabel("Journal Settings");
			JComboBox<String> journalModeBox = new JComboBox<String>();
			JCheckBox enableShowHowWasDay = new JCheckBox("Show \"How was you day?\" Prompt");
			JTextField journalText1AreaPrompt = new JTextField(25);
			JTextField journalText2AreaPrompt = new JTextField(25);
			JTextField journalText3AreaPrompt = new JTextField(25);
			JTextField journalText4AreaPrompt = new JTextField(25);

			@Override
			public void actionPerformed(ActionEvent e) {
				enableDiscordRPCCheckBox.setSelected(settings.isEnableDiscordRPC());
				enableTodoEpicOnClipboard.setSelected(settings.isShowTodoEpicOnClipboard());
				enableShowStreakOnClipboard.setSelected(settings.isShowStreakOnClipboard());
				journalModeBox.removeAllItems();
				journalModeBox.addItem("4 Prompt");
				journalModeBox.addItem("2 Prompt");
				enableShowHowWasDay.setSelected(settings.isShowHowWasDay());
				journalText1AreaPrompt.setText(settings.getJournalText1AreaPrompt());
				journalText2AreaPrompt.setText(settings.getJournalText2AreaPrompt());
				journalText3AreaPrompt.setText(settings.getJournalText3AreaPrompt());
				journalText4AreaPrompt.setText(settings.getJournalText4AreaPrompt());
				
				if(!(state instanceof HelpInfoState)) {
					debug.print("HelpInfoStateselected, changing to HelpInfoState State");
					changeState(new HelpInfoState());
				} else {
					debug.print("HelpInfoState selected, but already in state. Will not reload state.");
				}
				
				journalModeBox.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if(journalModeBox.getSelectedIndex() == 0) {
							journalText2AreaPrompt.setEnabled(true);
							journalText2AreaPrompt.setForeground(Color.BLACK);
							journalText4AreaPrompt.setEnabled(true);
							journalText4AreaPrompt.setForeground(Color.BLACK);
						} else {
							journalText2AreaPrompt.setEnabled(false);
							journalText2AreaPrompt.setForeground(Color.LIGHT_GRAY);
							journalText4AreaPrompt.setEnabled(false);
							journalText4AreaPrompt.setForeground(Color.LIGHT_GRAY);
						}
					}
					
				});
				journalModeBox.setSelectedIndex((int) settings.getJournalMode());
				
				panel.setLayout(new GridBagLayout());
				GridBagConstraints gbc = new GridBagConstraints();
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				
				generalSettingsLabel.setFont(Constants.COMPONENT_FONT_SMALL_BOLD);
				panel.add(generalSettingsLabel, gbc);
				panel.add(enableDiscordRPCCheckBox, gbc);
				panel.add(Box.createVerticalStrut(50));
				
				todoSettingsLabel.setFont(Constants.COMPONENT_FONT_SMALL_BOLD);
				panel.add(todoSettingsLabel, gbc);
				panel.add(enableTodoEpicOnClipboard, gbc);
				panel.add(Box.createVerticalStrut(50));
				
				habitSettingsLabel.setFont(Constants.COMPONENT_FONT_SMALL_BOLD);
				panel.add(habitSettingsLabel, gbc);
				panel.add(enableShowStreakOnClipboard, gbc);
				panel.add(Box.createVerticalStrut(50));
				
				journalSettingsLabel.setFont(Constants.COMPONENT_FONT_SMALL_BOLD);
				panel.add(journalSettingsLabel, gbc);
				panel.add(journalModeBox, gbc);
				panel.add(enableShowHowWasDay, gbc);
				panel.add(journalText1AreaPrompt, gbc);
				panel.add(journalText2AreaPrompt, gbc);
				panel.add(journalText3AreaPrompt, gbc);
				panel.add(journalText4AreaPrompt, gbc);
				
				
				
				// Open Customization Menu
				int result = JOptionPane.showConfirmDialog(
					    getRootPane(), 
					    panel, 
					    "Settings", 
					    JOptionPane.OK_CANCEL_OPTION,
					    JOptionPane.QUESTION_MESSAGE,
					    new ImageIcon(getClass().getClassLoader().getResource("INFO_ERROR_ORANGE.png"))
					);
				if(result == JOptionPane.OK_OPTION) {
					// Modify settings object
					settings.setEnableDiscordRPC(enableDiscordRPCCheckBox.isSelected());
					settings.setShowTodoEpicOnClipboard(enableTodoEpicOnClipboard.isSelected());
					settings.setShowStreakOnClipboard(enableShowStreakOnClipboard.isSelected());
					settings.setJournalMode((long) journalModeBox.getSelectedIndex());
					settings.setShowHowWasDay(enableShowHowWasDay.isSelected());
					settings.setJournalText1AreaPrompt(journalText1AreaPrompt.getText());
					settings.setJournalText2AreaPrompt(journalText2AreaPrompt.getText());
					settings.setJournalText3AreaPrompt(journalText3AreaPrompt.getText());
					settings.setJournalText4AreaPrompt(journalText4AreaPrompt.getText());
					
					// Write new settings object to file
					SettingsJsonManager.writeSettingsJsonToFile(settings);
				}
			}
			
		});
	}
	
	private void addComponentsToFrame() {
		//MenuBar
		menuBar.add(homeMenu);
		
		homeMenu.add(customizeHomeMenuItem);
		
		menuBar.add(todoMenu);
		
		todoMenu.add(printToDoMenuItem);
		
		menuBar.add(habitsMenu);
		
		habitsMenu.add(createHabitMenuItem);
		habitsMenu.add(updateHabitsMenuItem);
		
		menuBar.add(antiHabitsMenu);
		
		menuBar.add(journalMenu);
		
		journalMenu.add(printJournalMenuItem);
		
		menuBar.add(relationshipsMenu);
		
		relationshipsMenu.add(createRelationshipMenuItem);
		relationshipsMenu.add(updateRelationshipsMenuItem);
		
		menuBar.add(helpMenu);
		
		helpMenu.add(updateSettingsMenuItem);
		
		this.add(menuBar, BorderLayout.NORTH);
		
		//State
		this.add(state);
	}
	
	
	private void initFrame() {
		this.setTitle("Kaizen v1.0.4");
		this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("KaizenIcon.png")).getImage());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(900, 700);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	/**
	 * changeState
	 * @param newState
	 * @brief Changes the current state to a new state
	 */
	public void changeState(State newState) {
		this.remove(state);
		this.setSize(900, 699);
		state = newState;
		this.add(state);
		this.repaint();
		this.setSize(900, 700);
	}

}
