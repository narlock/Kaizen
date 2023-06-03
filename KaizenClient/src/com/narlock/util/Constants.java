package com.narlock.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public final class Constants {
	// Version Constant
	public final static String VERSION = "v1.0.5";
	
	//Date Formatter
	public final static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	//Color Constants
	public final static Color GUI_BACKGROUND_COLOR = new Color(20, 20, 20);
	public final static Color COMPONENT_BACKGROUND_COLOR = new Color(84, 84, 84);
	public final static Color COMPONENT_BORDER_COLOR = new Color(217, 217, 217);
	public final static Color BUTTON_DEFAULT_COLOR = new Color(16, 140, 255);
	public final static Color BUTTON_ALERT_COLOR = new Color(244, 59, 52);
	public final static Color BUTTON_CONFIRM_COLOR = new Color(50, 203, 88);
	public final static Color COMPONENT_FOREGROUND_COLOR = new Color(217, 217, 217);
	public final static Color COMPONENT_BUTTON_SELECTED_COLOR = new Color(255, 0, 0);
	public final static Color KAIZEN_GREEN = new Color(0, 235, 123);
	public final static Color KAIZEN_GOLD = new Color(255, 215, 0);
	public final static Color KAIZEN_DIAMOND = Color.CYAN;
	public final static Color KAIZEN_PINK = new Color(255, 105, 180);
	public final static Color KAIZEN_RED = new Color(255, 58, 64);
	
	//Font Constants
	public final static Font COMPONENT_FONT_NORMAL_BOLD = new Font("Tahoma", Font.BOLD, 16);
	public final static Font COMPONENT_FONT_SMALL_BOLD = new Font("Tahoma", Font.BOLD, 12);
	public final static Font COMPONENT_FONT_NORMAL = new Font("Tahoma", Font.PLAIN, 16);
	public final static Font COMPONENT_FONT_SMALL = new Font("Tahoma", Font.PLAIN, 12);
	
	//Dimensions Constants
	public final static Dimension HABIT_MAIN_PANEL_DIMENSION_LARGE = new Dimension(550, 85);
	public final static Dimension HABIT_MAIN_PANEL_DIMENSION_NORMAL = new Dimension(400, 65);
	public final static Dimension HABIT_MAIN_PANEL_DIMENSION_SMALL = new Dimension(300, 49);
	public final static Dimension COMPLETE_HABIT_DIMENSION_NORMAL = new Dimension(20, 20);
	public final static Dimension COMPLETE_HABIT_DIMENSION_SMALL = new Dimension(15, 15);
	public final static Dimension HABIT_SCROLL_PANE_LARGE = new Dimension(600, 415);
	public final static Dimension HABIT_SCROLL_PANE_NORMAL = new Dimension(450, 520);
	public final static Dimension HABIT_SCROLL_PANE_SMALL = new Dimension(338, 240);
	public final static Dimension HABIT_UPDATE_BUTTON_SIZE = new Dimension(100, 40);
	public final static Dimension JOURNAL_PAGE_BUTTON_SIZE = new Dimension(180, 40); 
	public final static Dimension TODO_EPIC_SCROLL_PANE_DIMENSION = new Dimension(280, 235);
	public final static Dimension TODO_EPIC_PANEL_DIMENSION = new Dimension(225, 49);
	public final static Dimension TODO_ITEM_SCROLL_PANE_DIMENSION = new Dimension(500, 520);
	public final static Dimension TODO_ITEM_SCROLL_PANE_DIMENSION_SMALL = new Dimension(350, 250);
	public final static Dimension TODO_ITEM_PANEL_DIMENSION = new Dimension(450, 65);
	public final static Dimension TODO_ITEM_PANEL_DIMENSION_SMALL = new Dimension(330, 50);
	public final static Dimension ANTIHABIT_SCROLL_PANE_NORMAL = new Dimension(540, 420);
	public final static Dimension ANTIHABIT_SCROLL_PANE_SMALL = new Dimension(338, 255);
	public final static Dimension ANTIHABIT_MAIN_PANEL_DIMENSION = new Dimension(490, 70);
	public final static Dimension ANTIHABIT_MAIN_PANEL_DIMENSION_SMALL = new Dimension(300, 52);
	
	//Border Constants
	public final static RoundedBorder COMPONENT_BORDER_NORMAL = new RoundedBorder(COMPONENT_BORDER_COLOR, 3, 10, 10, true);
	public final static NormalBorder COMPONENT_BORDER_NORMAL_RECTANGULAR = new NormalBorder(COMPONENT_BORDER_COLOR, 3, 10, 10, true);
	public final static RoundedBorder COMPONENT_BORDER_SMALL = new RoundedBorder(COMPONENT_BORDER_COLOR, 2, 7, 7, true);
	public final static RoundedBorder HABIT_COMPLETE_BORDER_NORMAL = new RoundedBorder(COMPONENT_BORDER_COLOR, 2, 20, 0, true);
	public final static RoundedBorder HABIT_COMPLETE_BORDER_SMALL = new RoundedBorder(COMPONENT_BORDER_COLOR, 1, 15, 0, true);
	public final static NormalBorder TODO_COMPLETE_BORDER_NORMAL = new NormalBorder(COMPONENT_BORDER_COLOR, 2, 20, 0, true);
	public final static NormalBorder TODO_COMPLETE_BORDER_SMALL = new NormalBorder(COMPONENT_BORDER_COLOR, 1, 15, 0, true);
	public final static RoundedBorder BUTTON_BORDER = new RoundedBorder(Color.WHITE, 2, 15, 0, true);
	public final static Border RIGHT_BORDER = BorderFactory.createMatteBorder(0, 0, 0, 3, COMPONENT_BORDER_COLOR);
	
	//String constants
	public final static String HELP_HOME_MESSAGE = 
			  "The home allows the user to keep all of their productivity<br>"
			+ "tools inside of one place.<br><br>"
			+ "Select \"Customize Home\" to customize the widgets that<br>"
			+ "appear on the home screen.";
	public final static String HELP_TODO_MESSAGE = 
			  "The todo feature is an advanced todo list to help<br>"
			+ "users get their tasks completed efficiently.<br><br>"
			+ "The user can create new tasks with the \"+\" on<br>"
			+ "the right-hand side, create a todo item, complete<br>"
			+ "the task, edit task, and more.<br><br>"
			+ "The user can categorize tasks utilizing epics.<br>"
			+ "These can be created similarly to todo items.<br><br>"
			+ "The left-hand side provides sorting options to the user<br>"
			+ "so they can decide how to view their tasks.";
	public final static String HELP_HABITS_MESSAGE = 
			  "The habit feature is great for users looking to<br>"
			+ "build new habits.<br><br>"
			+ "The user can select \"Start New Habit\" to begin.<br><br>"
			+ "Check your habits off by clicking the left-hand side button<br>"
			+ "corresponding to the habit.<br><br>"
			+ "Users can achieve streaks for completing habits consistently!";
	public final static String HELP_ANTIHABITS_MESSAGE = 
			"The anti-habits feature is a great way for users<br>"
			+ "to rid bad habits.<br><br>"
			+ "Simply hit the \"+\" button and begin to get rid<br>"
			+ "of your bad habits!";
	public final static String HELP_JOURNAL_MESSAGE =
			  "The journal allows the user to make journaling a daily habit.<br><br>"
			+ "Simply fill out the prompts and select the \"Save Entry\" button!<br><br>"
			+ "Widgets for this feature include a singluar prompt on the home screen.";
}
