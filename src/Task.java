/**
 * 
 * @author Anthony
 *
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;

public class Task extends JPanel {
	private JButton deleteTask;
	
	private JButton completeButton;
	private int completedStatus;
	
	private JTextField infoField;
	private Date dueDate;
	private String dateString;
	private SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
	
	public Task() {
		initComponents();
		
		initComponentActions();
		
		setUpTaskPanel();
	}
	
	public Task(String title) {
		initComponents();
		
		this.infoField.setText("Enter New Task");
		
		initComponentActions();
		
		setUpTaskPanel();
	}
	
	public Task(int completionStatus, String title) {
		initComponents();
		
		this.completedStatus = completionStatus;
		this.infoField.setText(title);
		setCompletionImage(completionStatus);
		
		initComponentActions();
		
		setUpTaskPanel();
	}
	
	public void initComponents() {
		this.setBackground(new Color(204,255,255));
		
		deleteTask = new JButton(new ImageIcon("assets/delete.png"));
		
		completeButton = new JButton(new ImageIcon("assets/blue.png"));
		completedStatus = 0;
		
		infoField = new JTextField(15);
		dueDate = new Date();
		
		
		this.setPreferredSize(new Dimension(250, 30));
		completeButton.setPreferredSize(new Dimension(16,16));
		deleteTask.setPreferredSize(new Dimension(16,16));
		
	}
	
	public void initComponentActions() {
		completeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(completedStatus == 1) {
					completeButton.setIcon(new ImageIcon("assets/blue.png"));
					completedStatus = 0;
				} else {
					completeButton.setIcon(new ImageIcon("assets/green.png"));
					completedStatus = 1;
				}
				
			}
			
		});
		
		deleteTask.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
			
		});
	}
	
	public void setUpTaskPanel() {
		this.add(completeButton);
		this.add(infoField);
		this.add(deleteTask);
	}
	
	public String getInfoText() {
		return infoField.getText();
	}
	
	public void setCompletionImage(int status) {
		if(status == 1)
			completeButton.setIcon(new ImageIcon("assets/green.png"));
		else
			completeButton.setIcon(new ImageIcon("assets/blue.png"));
	}
	
	public String toString() {
		String info = completedStatus + "," + infoField.getText();
		return info;
	}
	
}
