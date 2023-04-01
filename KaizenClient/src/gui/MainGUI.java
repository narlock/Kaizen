package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import panel.CustomHomePanel;
import state.AntiHabitsState;
import state.CreateHabitState;
import state.HabitsState;
import state.HelpInfoState;
import state.HomeState;
import state.JournalState;
import state.State;
import state.TodoState;
import state.UpdateHabitsState;
import util.Debug;

/**
 * MainGUI
 * @author narlock
 *
 * The main frame of Kaizen. Contains the top menu bar
 * and a state for changing application states.
 */
public class MainGUI extends JFrame {

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
	
	private JMenu helpMenu;
	
	/**
	 * The current state of Kaizen
	 */
	private State state;
	
	public MainGUI() {
		initMemberVariables();
		initComponents();
		initComponentActions();
		addComponentsToFrame();
		initFrame();
	}
	
	private void initMemberVariables() {
		state = new HomeState();
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
		
		helpMenu = new JMenu("Help");
	}
	
	private void initComponentActions() {
		homeMenu.addMenuListener(new MenuListener() {
			@Override
			public void menuSelected(MenuEvent e) {
				if(!(state instanceof HomeState)) {
					debug.print("Home Menu selected, changing to Home State");
					changeState(new HomeState());
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
					changeState(new HomeState());
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
					changeState(new HomeState());
				}
			}
			
		});
		todoMenu.addMenuListener(new MenuListener() {
			@Override
			public void menuSelected(MenuEvent e) {
				if(!(state instanceof TodoState)) {
					debug.print("Todo Menu selected, changing to Todo State");
					changeState(new TodoState());
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
					changeState(new HabitsState());
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
					changeState(new JournalState());
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
	}
	
	private void addComponentsToFrame() {
		//MenuBar
		menuBar.add(homeMenu);
		
		homeMenu.add(customizeHomeMenuItem);
		
		menuBar.add(todoMenu);
		
//		todoMenu.add(printToDoMenuItem);
		
		menuBar.add(habitsMenu);
		
		habitsMenu.add(createHabitMenuItem);
		habitsMenu.add(updateHabitsMenuItem);
		
		menuBar.add(antiHabitsMenu);
		
		menuBar.add(journalMenu);
		
//		journalMenu.add(printJournalMenuItem);
		
//		menuBar.add(relationshipsMenu);
		
//		relationshipsMenu.add(createRelationshipMenuItem);
//		relationshipsMenu.add(updateRelationshipsMenuItem);
		
		menuBar.add(helpMenu);
		
		this.add(menuBar, BorderLayout.NORTH);
		
		//State
		this.add(state);
	}
	
	
	private void initFrame() {
		this.setTitle("Kaizen v1.0.2");
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
