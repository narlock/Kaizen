package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OSLADatabase {
	private static Connection mysqlConnection;
	
	public static void establishDatabaseConnection() {
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
	
	private void testQuery() {
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
			
//			String formattedDateStr = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)
//                    .format(LocalDateTime.now());
//			String sql = "INSERT INTO habits (" +
//		            "habit_title," +
//		            "habit_streak," +
//		            "habit_occurrence," +
//		            "habit_status," +
//		            "habit_date" +
//		        ")" +
//		        "VALUES (?, ?, ?, ?, ?)";
//			PreparedStatement statement = mysqlConnection.prepareStatement(sql);
//			statement.setString(1, "Test Title");
//			statement.setInt(2, 0);
//			statement.setString(3, "s");
//			statement.setInt(4, 0);
//			statement.setString(5, formattedDateStr);
//			
//			statement.execute();
			
			statement.close();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
