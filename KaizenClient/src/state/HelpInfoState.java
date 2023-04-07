package state;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import panel.HelpPanel;
import util.ErrorPane;

import static util.Constants.*;

public class HelpInfoState extends State {
	
	private JLabel logoIconLabel;
	private JLabel versionLabel;
	private JLabel byNarlockLabel;
	
	private JLabel helpLabel;
	private JPanel helpPanel;
	private JButton helpHome;
	private JButton helpTodo;
	private JButton helpHabits;
	private JButton helpAntiHabits;
	private JButton helpJournal;
	
	private JLabel additionalHelpLabel;
	
	private JPanel socialPanel;
	private JButton youtubeButton;
	private JButton discordButton;
	private JButton patreonButton;

	@Override
	public void initPanelComponents() {
		logoIconLabel = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("KaizenLogo.png")));
		versionLabel = new JLabel("v1.0.4");
		versionLabel.setForeground(COMPONENT_FOREGROUND_COLOR);
		versionLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		byNarlockLabel = new JLabel("by narlock", new ImageIcon(getClass().getClassLoader().getResource("NARLOCK_LOGO.png")), SwingConstants.LEADING);
		byNarlockLabel.setHorizontalTextPosition(SwingConstants.LEADING);
		byNarlockLabel.setForeground(COMPONENT_FOREGROUND_COLOR);
		byNarlockLabel.setFont(COMPONENT_FONT_NORMAL_BOLD);
		
		helpLabel = new JLabel("Explore Kaizen's features");
		helpLabel.setForeground(COMPONENT_FOREGROUND_COLOR);
		helpLabel.setFont(COMPONENT_FONT_NORMAL_BOLD);
		
		helpPanel = new JPanel();
		helpPanel.setBackground(GUI_BACKGROUND_COLOR);
		helpHome = new JButton("Home");
		helpTodo = new JButton("Todo");
		helpHabits = new JButton("Habits");
		helpAntiHabits = new JButton("Anti Habits");
		helpJournal = new JButton("Journal");
		
		helpPanel.add(helpHome);
		helpPanel.add(helpTodo);
		helpPanel.add(helpHabits);
		helpPanel.add(helpAntiHabits);
		helpPanel.add(helpJournal);
		
		additionalHelpLabel = new JLabel("Join our community");
		additionalHelpLabel.setForeground(COMPONENT_FOREGROUND_COLOR);
		additionalHelpLabel.setFont(COMPONENT_FONT_NORMAL_BOLD);
		
		socialPanel = new JPanel();
		socialPanel.setBackground(GUI_BACKGROUND_COLOR);
		youtubeButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("YOUTUBE.png")));
		discordButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("DISCORD.png")));
		patreonButton = new JButton(new ImageIcon(getClass().getClassLoader().getResource("PATREON.png")));
		
		socialPanel.add(youtubeButton);
		initButtonVisual(youtubeButton);
		socialPanel.add(discordButton);
		initButtonVisual(discordButton);
		socialPanel.add(patreonButton);
		initButtonVisual(patreonButton);
	}

	@Override
	public void initPanelComponentActions() {
		setActionForLink(youtubeButton, "https://youtube.com/narlock");
		setActionForLink(discordButton, "https://discord.gg/eEbEYbXaNS");
		setActionForLink(patreonButton, "https://patreon.com/narlock");
		
		helpHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(getRootPane(), 
						new HelpPanel(HELP_HOME_MESSAGE), 
						"Home Feature", 
						JOptionPane.INFORMATION_MESSAGE,
						new ImageIcon(ErrorPane.class.getClassLoader().getResource("INFO_ERROR.png")));
			}
			
		});
		helpTodo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(getRootPane(), 
						new HelpPanel(HELP_TODO_MESSAGE), 
						"Todo Feature", 
						JOptionPane.INFORMATION_MESSAGE,
						new ImageIcon(ErrorPane.class.getClassLoader().getResource("INFO_ERROR.png")));
			}
			
		});
		helpHabits.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(getRootPane(), 
						new HelpPanel(HELP_HABITS_MESSAGE), 
						"Habits Feature", 
						JOptionPane.INFORMATION_MESSAGE,
						new ImageIcon(ErrorPane.class.getClassLoader().getResource("INFO_ERROR.png")));
			}
			
		});
		helpAntiHabits.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(getRootPane(), 
						new HelpPanel(HELP_ANTIHABITS_MESSAGE), 
						"AntiHabits Feature", 
						JOptionPane.INFORMATION_MESSAGE,
						new ImageIcon(ErrorPane.class.getClassLoader().getResource("INFO_ERROR.png")));
			}
			
		});
		helpJournal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(getRootPane(), 
						new HelpPanel(HELP_JOURNAL_MESSAGE), 
						"Journal Feature", 
						JOptionPane.INFORMATION_MESSAGE,
						new ImageIcon(ErrorPane.class.getClassLoader().getResource("INFO_ERROR.png")));
			}
			
		});
	}

	@Override
	public void initPanel() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		this.add(logoIconLabel, gbc);
		this.add(versionLabel, gbc);
		this.add(byNarlockLabel, gbc);
		
		this.add(Box.createVerticalStrut(100), gbc);
		this.add(helpLabel, gbc);
		this.add(helpPanel, gbc);
		
		this.add(Box.createVerticalStrut(100), gbc);
		this.add(additionalHelpLabel, gbc);
		this.add(socialPanel, gbc);
	}
	
	public void initButtonVisual(JButton button) {
		button.setOpaque(false);
		button.setContentAreaFilled(false); 
		button.setBorderPainted(false); 
		button.setFocusPainted(false);
	}
	
	public void setActionForLink(JButton button, String url) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try { Desktop.getDesktop().browse(new URL(url).toURI()); } 
				catch (Exception e1) { e1.printStackTrace(); }
			}
		});
	}

}
