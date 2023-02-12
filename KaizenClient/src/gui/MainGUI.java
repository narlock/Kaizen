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
	private JMenuItem homeMenuItem;
	private JMenuItem customizeHomeMenuItem;
	
	private JMenu todoMenu;
	private JMenuItem todoMenuItem;
	private JMenuItem printToDoMenuItem;
	private JMenuItem customizeToDoMenuItem;
	
	private JMenu habitsMenu;
	private JMenuItem habitsMenuItem;
	private JMenuItem createHabitMenuItem;
	private JMenuItem updateHabitsMenuItem;
	
	private JMenu journalMenu;
	private JMenuItem journalMenuItem;
	private JMenuItem printJournalMenuItem;
	
	private JMenu relationshipsMenu;
	private JMenuItem relationshipsMenuItem;
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
		homeMenuItem = new JMenuItem("Home");
		customizeHomeMenuItem = new JMenuItem("Customize Home");
		
		todoMenu = new JMenu("TODO");
		todoMenuItem = new JMenuItem("TODO");
		printToDoMenuItem = new JMenuItem("Print TODO");
		customizeToDoMenuItem = new JMenuItem("Customize TODO");
		
		habitsMenu = new JMenu("Habits");
		habitsMenuItem = new JMenuItem("Habits");
		createHabitMenuItem = new JMenuItem("Start Habit");
		updateHabitsMenuItem = new JMenuItem("Update Habits");
		
		journalMenu = new JMenu("Journal");
		journalMenuItem = new JMenuItem("Journal");
		printJournalMenuItem = new JMenuItem("Print Journal");
		
		relationshipsMenu = new JMenu("Relationships");
		relationshipsMenuItem = new JMenuItem("Relationships");
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
		homeMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeState(new HomeState());
			}
		});
		habitsMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeState(new HabitsState());
			}
		});
	}
	
	private void addComponentsToFrame() {
		//MenuBar
		menuBar.add(homeMenu);
		
		homeMenu.add(homeMenuItem);
		homeMenu.add(customizeHomeMenuItem);
		
		menuBar.add(todoMenu);
		
		todoMenu.add(todoMenuItem);
		todoMenu.add(printToDoMenuItem);
		todoMenu.add(customizeToDoMenuItem);
		
		menuBar.add(habitsMenu);
		
		habitsMenu.add(habitsMenuItem);
		habitsMenu.add(createHabitMenuItem);
		habitsMenu.add(updateHabitsMenuItem);
		
		menuBar.add(journalMenu);
		
		journalMenu.add(journalMenuItem);
		journalMenu.add(printJournalMenuItem);
		
		menuBar.add(relationshipsMenu);
		
		relationshipsMenu.add(relationshipsMenuItem);
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
