package util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;

public final class Constants {
	//Date Formatter
	public final static SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
	
	//String Constants
	public final static String FIRE_EMOJI = "🔥";
	public final static String FIRE_EMOJI_SPACE = " 🔥";
	
	//Color Constants
	public final static Color GUI_BACKGROUND_COLOR = new Color(20, 20, 20);
	public final static Color COMPONENT_BACKGROUND_COLOR = new Color(84, 84, 84);
	public final static Color COMPONENT_BORDER_COLOR = new Color(217, 217, 217);
	public final static Color BUTTON_DEFAULT_COLOR = new Color(120, 120, 255);
	public final static Color COMPONENT_FOREGROUND_COLOR = new Color(217, 217, 217);
	
	//Font Constants
	public final static Font COMPONENT_FONT_NORMAL = new Font("Tahoma", Font.BOLD, 16);
	public final static Font COMPONENT_FONT_SMALL = new Font("Tahoma", Font.BOLD, 12);
	
	//Dimensions Constants
	public final static Dimension HABIT_MAIN_PANEL_DIMENSION_NORMAL = new Dimension(400, 65);
	public final static Dimension HABIT_MAIN_PANEL_DIMENSION_SMALL = new Dimension(300, 49);
	public final static Dimension COMPLETE_HABIT_DIMENSION_NORMAL = new Dimension(20, 20);
	public final static Dimension COMPLETE_HABIT_DIMENSION_SMALL = new Dimension(15, 15);
	public final static Dimension HABIT_SCROLL_PANE_NORMAL = new Dimension(450, 385);
	public final static Dimension HABIT_SCROLL_PANE_SMALL = new Dimension(338, 240);
	
	//Border Constants
	public final static RoundedBorder COMPONENT_BORDER_NORMAL = new RoundedBorder(COMPONENT_BORDER_COLOR, 3, 10, 10, true);
	public final static RoundedBorder COMPONENT_BORDER_SMALL = new RoundedBorder(COMPONENT_BORDER_COLOR, 2, 7, 7, true);
	public final static RoundedBorder HABIT_COMPLETE_BORDER_NORMAL = new RoundedBorder(COMPONENT_BORDER_COLOR, 2, 20, 0, true);
	public final static RoundedBorder HABIT_COMPLETE_BORDER_SMALL = new RoundedBorder(COMPONENT_BORDER_COLOR, 1, 15, 0, true);
}
