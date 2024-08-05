package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import models.ApplicationBean;

public class HolidayDao extends CommonDao{
	public void registerHoliday(int employeeCD, int storeCD, Date startDate, Date endDate, String reason, String note) {
		String query = "INSERT INTO holiday(employeeCD, storeCD,application_start_date, application_end_date, reason, note) VALUES(?,?,?,?,?,?)";
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement statement = con.prepareStatement(query)) {

			statement.setInt(1, employeeCD);
			statement.setInt(2, storeCD);
			statement.setDate(3, startDate);
			statement.setDate(4, endDate);
			statement.setString(5, reason);
			statement.setString(6, note);

			statement.executeUpdate();
			statement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public ArrayList<ApplicationBean> getHoridayApplicationList(int storeCD) {
		ArrayList<ApplicationBean> applications = new ArrayList<ApplicationBean>();
		String query = "SELECT h.*, (SELECT u.name FROM users u WHERE u.employeeCD = h.employeeCD) AS name,  (SELECT u.position FROM users u WHERE u.employeeCD = h.employeeCD) AS position FROM holiday h WHERE storeCD=? AND approval_status=1";
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement statement = con.prepareStatement(query)) {
			statement.setInt(1, storeCD);
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					int employeeCD = rs.getInt("employeeCD");
					int approvalStatus = rs.getInt("approval_status");
					Timestamp date = rs.getTimestamp("application_date");
 					java.sql.Date startDate = rs.getDate("application_start_date");
					java.sql.Date endDate = rs.getDate("application_end_date");
					String reason = rs.getString("reason");
					String note = rs.getString("note");
					String name = rs.getString("name");
					int position = rs.getInt("position");
	
					ApplicationBean application = new ApplicationBean(employeeCD, position, approvalStatus, date, startDate, endDate, reason, note, name);
					System.out.println(application.getReason());
					applications.add(application);
				}
			statement.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			// エラーハンドリングを適切に行う
		}
		return applications;
	}
}
