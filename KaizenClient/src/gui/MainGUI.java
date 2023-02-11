package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import panel.HabitsPanel;
import state.HabitsState;
import state.HomeState;
import state.State;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = -6508626185123863757L;
	
	/**
	 * The top menu bar
	 */
	private JMenuBar menuBar;
	
	/**
	 * The navigation items: changes state
	 */
	private JMenuItem homeMenuItem;
	private JMenuItem habitsMenuItem;
	private JMenuItem journalMenuItem;
	private JMenuItem relationshipsMenuItem;
	
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
		homeMenuItem = new JMenuItem("Home");
		habitsMenuItem = new JMenuItem("Habits");
		journalMenuItem = new JMenuItem("Journal");
		relationshipsMenuItem = new JMenuItem("Relationships");
	}
	
	private void initComponentActions() {
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
		menuBar.add(homeMenuItem);
		menuBar.add(habitsMenuItem);
		menuBar.add(journalMenuItem);
		menuBar.add(relationshipsMenuItem);
		this.add(menuBar, BorderLayout.NORTH);
		
		//State
		this.add(state);
	}
	
	
	private void initFrame() {
		this.setTitle("Kaizen Alpha v0.1");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setVisible(true);
	}
	
	public void changeState(State newState) {
		this.remove(state);
		this.setSize(800, 599);
		state = newState;
		this.add(state);
		this.repaint();
		this.setSize(800, 600);
	}

}
