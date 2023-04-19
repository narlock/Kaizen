package panel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.Epic;
import domain.Todo;
import json.TodoJsonManager;
import state.TodoState;
import util.ErrorPane;

import static util.Constants.*;

public class AddEpicButton extends JButton {

	private static final long serialVersionUID = 5868914221761896285L;
	
	private JPanel panel;
	private JLabel titleLabel;
	private JTextField titleTextField;
	private JLabel colorLabel;
	private JButton setColorButton;
	private Color color;
	
	private JColorChooser colorChooser;
	
	public AddEpicButton(ImageIcon imageIcon, Todo todo, TodoState state) {
		super(imageIcon);
		
		this.panel = new JPanel(new GridLayout(0, 1));
		this.titleLabel = new JLabel("Epic Title");
		this.titleTextField = new JTextField();
		this.colorLabel = new JLabel("Color");
		this.setColorButton = new JButton("Set Color");
		
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				color = COMPONENT_BACKGROUND_COLOR;
				titleTextField.setText("");
				
				setColorButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						colorChooser = new JColorChooser(color);
						color = colorChooser.showDialog(getRootPane(), "Associate an epic with a color", COMPONENT_BACKGROUND_COLOR);
						if(color == null) {
							color = COMPONENT_BACKGROUND_COLOR;
						}
					}
					
				});
				
				panel.add(titleLabel);
				panel.add(titleTextField);
				panel.add(colorLabel);
				panel.add(setColorButton);
				
				int result = JOptionPane.showConfirmDialog(
						getRootPane(), 
						panel, 
						"Create Epic", 
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						new ImageIcon(getClass().getClassLoader().getResource("INFO_ERROR_ORANGE.png")));
				if(result == JOptionPane.YES_OPTION &&
						!titleTextField.getText().equals("")
					) {
					Epic epic = new Epic(titleTextField.getText(), color);
					todo.getEpics().add(epic);
					TodoJsonManager.writeTodoJsonToFile(todo);
					state.revalidateEpicPanel();
				} 
				else if(result == JOptionPane.YES_OPTION &&
						titleTextField.getText().equals("")
					) {
					ErrorPane.displayError(getRootPane(), "Could not create habit. Please provide a title.");
				}
				else {
					System.out.println("Cancel");
				}
			}
			
		});
		
		
	}
	
}
