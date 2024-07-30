package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HolidayApplicationDao extends CommonDao{
	public void registerHoliday(int employeeCD, Date date, String reason, String note) {
		String query = "INSERT INTO holiday(employeeCD, application_date, reason, note) VALUES(?,?,?,?,?)";
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement statement = con.prepareStatement(query)) {

			statement.setInt(1, employeeCD);
			statement.setDate(2, date);
			statement.setString(3, reason);
			statement.setString(4, note);

			statement.executeUpdate();
			statement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			// エラーハンドリングを適切に行う
		}
	}
}
