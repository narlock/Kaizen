package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = -6508626185123863757L;
	
	private Connection mysqlConnection;
	
	private JButton button;
	
	public MainGUI() {
		establishDatabaseConnection();
		addComponentsToFrame();
		setUpFrame();
	}
	
	private void establishDatabaseConnection() {
		String[] dbCon = {
			"jdbc:mysql://localhost:3306/osla",
			"root",
			"root"
		};
		
		try {
			mysqlConnection = DriverManager.getConnection(dbCon[0], dbCon[1], dbCon[2]);
			System.out.println("[MainGUI/establishDatabaseConnection] Successful database connection.");
		} catch (SQLException e) {
			System.err.println("[MainGUI/establishDatabaseConnection] FAILURE to connect to database.");
			e.printStackTrace();
		}
		
	}
	
	private void addComponentsToFrame() {
		button = new JButton("Test SQL");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//Test SQL select
				try {
					String sql = "SELECT * FROM habits";
					PreparedStatement statement = mysqlConnection.prepareStatement(sql);
					if(statement.execute()) {
						ResultSet rs = statement.getResultSet();
						
						while(rs.next()) {
							String title = rs.getString(2);
							System.out.println(title);
						}
					}
					
//					String formattedDateStr = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)
//                            .format(LocalDateTime.now());
//					String sql = "INSERT INTO habits (" +
//				            "habit_title," +
//				            "habit_streak," +
//				            "habit_occurrence," +
//				            "habit_status," +
//				            "habit_date" +
//				        ")" +
//				        "VALUES (?, ?, ?, ?, ?)";
//					PreparedStatement statement = mysqlConnection.prepareStatement(sql);
//					statement.setString(1, "Test Title");
//					statement.setInt(2, 0);
//					statement.setString(3, "s");
//					statement.setInt(4, 0);
//					statement.setString(5, formattedDateStr);
//					
//					statement.execute();
					
					statement.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
	}
	
	private void setUpFrame() {
		this.add(button);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setVisible(true);
	}

}
