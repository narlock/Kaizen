package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import state.CreateHabitState;
import state.HabitsState;
import state.HomeState;
import state.State;
import util.Debug;

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
	private JMenuItem customizeToDoMenuItem;
	
	private JMenu habitsMenu;
	private JMenuItem createHabitMenuItem;
	private JMenuItem updateHabitsMenuItem;
	
	private JMenu journalMenu;
	private JMenuItem printJournalMenuItem;
	
	private JMenu relationshipsMenu;
	private JMenuItem createRelationshipMenuItem;
	private JMenuItem updateRelationshipsMenuItem;
	
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
		
		todoMenu = new JMenu("TODO");
		printToDoMenuItem = new JMenuItem("Print TODO");
		customizeToDoMenuItem = new JMenuItem("Customize TODO");
		
		habitsMenu = new JMenu("Habits");
		createHabitMenuItem = new JMenuItem("Start Habit");
		updateHabitsMenuItem = new JMenuItem("Update Habits");
		
		journalMenu = new JMenu("Journal");
		printJournalMenuItem = new JMenuItem("Print Journal");
		
		relationshipsMenu = new JMenu("Relationships");
		createRelationshipMenuItem = new JMenuItem("Add Relationship");
		updateRelationshipsMenuItem = new JMenuItem("Update Relationships");
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
	}
	
	private void addComponentsToFrame() {
		//MenuBar
		menuBar.add(homeMenu);
		
		homeMenu.add(customizeHomeMenuItem);
		
		menuBar.add(todoMenu);
		
		todoMenu.add(printToDoMenuItem);
		todoMenu.add(customizeToDoMenuItem);
		
		menuBar.add(habitsMenu);
		
		habitsMenu.add(createHabitMenuItem);
		habitsMenu.add(updateHabitsMenuItem);
		
		menuBar.add(journalMenu);
		
		journalMenu.add(printJournalMenuItem);
		
		menuBar.add(relationshipsMenu);
		
		relationshipsMenu.add(createRelationshipMenuItem);
		relationshipsMenu.add(updateRelationshipsMenuItem);
		
		this.add(menuBar, BorderLayout.NORTH);
		
		//State
		this.add(state);
	}
	
	
	private void initFrame() {
		this.setTitle("Kaizen Alpha v0.1");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(900, 700);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void changeState(State newState) {
		this.remove(state);
		this.setSize(900, 699);
		state = newState;
		this.add(state);
		this.repaint();
		this.setSize(900, 700);
	}

}
