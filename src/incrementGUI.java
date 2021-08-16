import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * This is the increment GUI
 * 
 * Displays gui thing with increment decrement feature
 */

public class incrementGUI extends JFrame {
	private int value;
	private String title;
	
	private JPanel centerPanel;
	private JButton decrementButton, incrementButton, changeTitleButton;
	private JLabel valueLabel, titleLabel;
	
	public incrementGUI() {
		setUpFrame();
		
		initComponents();
		
		initComponentActions();
		
		setUpGUI();
		
		this.setSize(500,150);
	}
	
	public void setUpFrame() {
		this.setTitle("Increment");
		this.setSize(499,150);
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void initComponents() {
		value = 0;
		title = "- i dont know";
		
		centerPanel = new JPanel();
		
		decrementButton = new JButton("-");
		decrementButton.setFont(new Font("Tahoma", Font.BOLD, 24));
		incrementButton = new JButton("+");
		incrementButton.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		changeTitleButton = new JButton("Change Title");
		changeTitleButton.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		valueLabel = new JLabel(String.valueOf(value));
		valueLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		titleLabel = new JLabel(title);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
	}
	
	public void initComponentActions() {
		decrementButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				value--;
				valueLabel.setText(String.valueOf(value));
				
			}
			
		});
		
		incrementButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				value++;
				valueLabel.setText(String.valueOf(value));
				
			}
			
		});
		
		changeTitleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				title = "- new title";
				titleLabel.setText(title);
				
			}
			
		});
	}
	
	public void setUpGUI() {
		//centerPanel.setLayout(new GridLayout(2,1));
		centerPanel.add(valueLabel);
		centerPanel.add(titleLabel);
		
		centerPanel.setBackground(new Color(255,161,161));
		this.add(decrementButton, BorderLayout.WEST);
		this.add(incrementButton, BorderLayout.EAST);
		this.add(centerPanel);
		this.add(changeTitleButton, BorderLayout.SOUTH);
		
	}
}
