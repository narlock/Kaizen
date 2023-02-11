package state;

import java.util.List;

import javax.swing.JButton;

import domain.Habit;
import util.JsonReader;

public class HabitsState extends State {
	
	public HabitsState() {
		this.add(new JButton("Hello Habits"));
	}
}
