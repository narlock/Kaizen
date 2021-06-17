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
import java.util.Date;

import javax.swing.*;

/*
 * GUI
 * The GUI for OpenLife
 */

public class GUI extends JFrame {
	
	/*
	 * Components
	 */
	
	private JPanel centerPanel, insideCenter1, bottomPanel;
	private JTextArea textArea;
	private JButton testButton;
	
	private Date currentDate;
	private SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
	private String currentDateString;
	
	private File file;
	private boolean existingFile;
	
	/*
	 * Default Constructor
	 */
	
	public GUI() {
		setUpFrame();
		
		initComponents();
		
		initComponentActions();
		
		setUpGUI();
		
		this.setSize(600,400);
	}
	
	public void setUpFrame() {
		this.setTitle("OpenLife indev 0.0.1");
		this.setSize(600,399);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void initComponents() {
		centerPanel = new JPanel();
		insideCenter1 = new JPanel();
		bottomPanel = new JPanel();
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		Dimension newSize = new Dimension((textArea.getPreferredSize().width * 2), textArea.getPreferredSize().height);
		textArea.setSize(newSize);
		
		
		currentDate = new Date();
		currentDateString = formatter.format(currentDate);
		
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
	}
	
	public void initComponentActions() {
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
	}
	
	public void setUpGUI() {
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);
		
		
		centerPanel.add(insideCenter1, BorderLayout.CENTER);
		insideCenter1.add(textArea);
		
		bottomPanel.add(testButton);
		
		
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
				writer.write(textArea.getText());
				
				writer.close();
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	public void saveCurrentFile(File file) {
		File fnew = new File(file.getAbsolutePath());
		String source = textArea.getText();
		
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
		
		String line;
		while ((line = (br.readLine())) != null) {
			textArea.append(line);
			textArea.append("\n");
		}
		br.close();
	}
}
