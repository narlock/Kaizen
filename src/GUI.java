/**
 * 
 * @author Anthony
 *
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import static javax.swing.ScrollPaneConstants.*;

/*
 * GUI
 * The GUI for OpenLife
 */

public class GUI extends JFrame {
	
	/*
	 * Components
	 */
	
	private JLabel title, habitTitle;
	private JPanel centerPanel, insideCenter1, topPanel, bottomPanel, textPanel, habitPanel;
	private JPanel taskPanel;
	private JScrollPane taskScrollPane, textScrollPane;
	
	private JTextArea textArea;
	private JButton testButton, addTaskButton;
	
	private Date currentDate;
	private SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
	private String currentDateString;
	
	private File file;
	private boolean existingFile;
	
	private ArrayList<Task> taskList;
	private ArrayList<Task> habitList;
	
	/*
	 * Default Constructor
	 */
	
	public GUI() {
		setUpFrame();
		
		initComponents();
		
		initComponentActions();
		
		setUpGUI();
		
		this.setSize(600,600);
	}
	
	public void setUpFrame() {
		this.setTitle("OpenLife indev 0.0.2");
		this.setSize(599,600);
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void initComponents() {
		taskList = new ArrayList<Task>();
		habitList = new ArrayList<Task>();
		
		taskScrollPane = new JScrollPane();
		taskScrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		
		textScrollPane = new JScrollPane();
		textScrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		
		//Temporary Habit List
		habitList.add(new Task(0, "Workout: Weight/Cardio", this));
		habitList.add(new Task(0, ">2200 calories", this));
		habitList.add(new Task(0, "In Bed by 10:00 PM", this));
		
		
		topPanel = new JPanel();
		taskPanel = new JPanel();
		taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
		
		centerPanel = new JPanel();
		insideCenter1 = new JPanel();
		bottomPanel = new JPanel();
		textPanel = new JPanel();
		habitPanel = new JPanel();
		
		initPanelColors();
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		Dimension newSize = new Dimension((textArea.getPreferredSize().width * 2), textArea.getPreferredSize().height);
		textArea.setSize(newSize);
		
		
		currentDate = new Date();
		currentDateString = formatter.format(currentDate);
		
		title = new JLabel("OpenLife —— " + currentDateString);
		habitTitle = new JLabel("Daily Habits");
		
		file = new File("today/OpenLife-"+ currentDateString +".txt");
		existingFile = file.exists();
		
		if(existingFile) {
			System.out.println("exists");
			try {
				openFile(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Does not exist");
			textArea.setText("Welcome to OpenLife - " + currentDateString);
		}
		
		
		
		testButton = new JButton("Save Life");
		addTaskButton = new JButton("+");
	}
	
	public void initPanelColors() {
		topPanel.setBackground(new Color(204,255,255));
		taskPanel.setBackground(new Color(204,255,255));
		centerPanel.setBackground(new Color(204,255,255));
		insideCenter1.setBackground(new Color(204,255,255));
		bottomPanel.setBackground(new Color(204,255,255));
		textPanel.setBackground(new Color(204,255,255));
		habitPanel.setBackground(new Color(204,255,255));
	}
	
	public void initComponentActions() {
		taskScrollPane.setViewportView(taskPanel);
		textScrollPane.setViewportView(textArea);
		
		testButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!existingFile) {
					try {
						writeTextToNewFile();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				else {
					saveCurrentFile(file);
				}
			}
		});
		
		
		addTaskButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Task temp = new Task();
				taskList.add(temp);
				taskPanel.add(temp);
				
				System.out.println(getTaskList());
				updateGUI();
				
			}
			
		});
	}
	
	public void setUpGUI() {
		this.add(topPanel, BorderLayout.NORTH);
		
		this.add(centerPanel, BorderLayout.CENTER);
		
		//this.add(taskPanel, BorderLayout.CENTER);
		
		this.add(bottomPanel, BorderLayout.SOUTH);
		
		topPanel.add(title);
		
		centerPanel.setLayout(new GridLayout(1,2));
		centerPanel.add(taskScrollPane);
		centerPanel.add(textPanel);
		
		textPanel.setLayout(new GridLayout(2,1));
		textPanel.add(textScrollPane);
		textPanel.add(habitPanel);
		
		
		habitPanel.add(habitTitle);
		for(int i = 0; i < habitList.size(); i++) {
			habitPanel.add(habitList.get(i));
		}
		
		bottomPanel.add(testButton);
		bottomPanel.add(addTaskButton);
		
		for(int i = 0; i < taskList.size(); i++) {
			taskPanel.add(taskList.get(i));
		}
		
	}
	
	public void updateGUI() {
		this.setSize(599,600);
		this.setSize(600, 600);
	}
	
	public void writeTextToNewFile() throws IOException {
		
		
		//File file = new File("OpenLife - " + currentDateString + ".txt");
		
		//testButton.setText(file.getAbsolutePath());
		
		
		JFileChooser SaveAs = new JFileChooser();
		SaveAs.setCurrentDirectory(file.getAbsoluteFile());
		
		SaveAs.setSelectedFile(new File("OpenLife-" + currentDateString));
		
		int ret = SaveAs.showSaveDialog(null);
		
		if(ret == JFileChooser.APPROVE_OPTION) {
			try (FileWriter writer = new FileWriter(SaveAs.getSelectedFile()+".txt")) {
				writer.write(getTaskList());
				
				writer.close();
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	public void saveCurrentFile(File file) {
		File fnew = new File(file.getAbsolutePath());
		String source = getTaskList();
		
		try {
			FileWriter f2 = new FileWriter(fnew, false);
			f2.write(source);
			f2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void openFile(File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String info = "";
		String line;
		while ((line = (br.readLine())) != null) {
			info = info + line + "\n";
		}
		System.out.println(info);
		String[] readTaskInfo = info.split("\n");
		
		for(int i = 0; i < readTaskInfo.length; i++) {
			String[] specTaskInfo = readTaskInfo[i].split(",");
			this.taskList.add(new Task(Integer.parseInt(specTaskInfo[0]), specTaskInfo[1], this));
			
		}
		
		br.close();
	}
	
	public String getTaskList() {
		String taskListInfo = "";
		for(int i = 0; i < taskList.size(); i++) {
			if(i != taskList.size() - 1) {
				String tempString = taskList.get(i).toString();
				taskListInfo = taskListInfo + tempString + "\n";
			}
			else {
				String tempString = taskList.get(i).toString();
				taskListInfo = taskListInfo + tempString;
			}
		}
		
		return taskListInfo;
	}
	
	public void removeTask(Task task) {
		taskList.remove(task);
		this.remove(task);
		this.validate();
		this.repaint();
		this.revalidate();
	}
}
