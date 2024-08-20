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
import models.HolidayBean;

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

	
	public ArrayList<ApplicationBean> getholidayApplicationListOfUnapproved(int args_storeCD) {
		ArrayList<ApplicationBean> applications = new ArrayList<ApplicationBean>();
		String query = "SELECT h.*,(SELECT u.name FROM users u WHERE u.employeeCD = h.employeeCD) AS name, "
				+ "(SELECT u.position FROM users u WHERE u.employeeCD = h.employeeCD) AS position, "
				+ "DATEDIFF(h.application_end_date, h.application_start_date) AS days_between "
				+ "FROM holiday h WHERE h.storeCD = ? AND h.approval_status = 0";		
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement statement = con.prepareStatement(query)) {
			statement.setInt(1, args_storeCD);
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					int holidayCD = rs.getInt("holidayCD");
					int employeeCD = rs.getInt("employeeCD");
					int storeCD = rs.getInt("storeCD");
					Timestamp date = rs.getTimestamp("application_date");
					java.sql.Date startDate = rs.getDate("application_start_date");
					java.sql.Date endDate = rs.getDate("application_end_date");
					String reason = rs.getString("reason");
					String note = rs.getString("note");
					int holidayStatus = rs.getInt("holiday_status");
					int approvalStatus = rs.getInt("approval_status");
					String name = rs.getString("name");
					int position = rs.getInt("position");
					int holidayDays = rs.getInt("days_between");
	
					ApplicationBean application = new ApplicationBean(holidayCD, employeeCD, storeCD, position, holidayStatus, approvalStatus, holidayDays, date, startDate, endDate, reason, note, name);
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
	
	public ArrayList<ApplicationBean> getholidayApplicationListOfApproved(int args_storeCD) {
		ArrayList<ApplicationBean> applications = new ArrayList<ApplicationBean>();
		String query = "SELECT h.*,(SELECT u.name FROM users u WHERE u.employeeCD = h.employeeCD) AS name, "
				+ "(SELECT u.position FROM users u WHERE u.employeeCD = h.employeeCD) AS position, "
				+ "DATEDIFF(h.application_end_date, h.application_start_date) AS days_between "
				+ "FROM holiday h WHERE h.storeCD = ? AND h.approval_status = 1";		
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement statement = con.prepareStatement(query)) {
			statement.setInt(1, args_storeCD);
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					int holidayCD = rs.getInt("holidayCD");
					int employeeCD = rs.getInt("employeeCD");
					int storeCD = rs.getInt("storeCD");
					Timestamp date = rs.getTimestamp("application_date");
					java.sql.Date startDate = rs.getDate("application_start_date");
					java.sql.Date endDate = rs.getDate("application_end_date");
					String reason = rs.getString("reason");
					String note = rs.getString("note");
					int holidayStatus = rs.getInt("holiday_status");
					int approvalStatus = rs.getInt("approval_status");
					String name = rs.getString("name");
					int position = rs.getInt("position");
					int holidayDays = rs.getInt("days_between");
	
					ApplicationBean application = new ApplicationBean(holidayCD, employeeCD, storeCD, position, holidayStatus, approvalStatus, holidayDays, date, startDate, endDate, reason, note, name);
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
	
	public ArrayList<ApplicationBean> getholidayApplicationListOfRejected(int args_storeCD) {
		ArrayList<ApplicationBean> applications = new ArrayList<ApplicationBean>();
		String query = "SELECT h.*,(SELECT u.name FROM users u WHERE u.employeeCD = h.employeeCD) AS name, "
				+ "(SELECT u.position FROM users u WHERE u.employeeCD = h.employeeCD) AS position, "
				+ "DATEDIFF(h.application_end_date, h.application_start_date) AS days_between "
				+ "FROM holiday h WHERE h.storeCD = ? AND h.approval_status = 2";		
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement statement = con.prepareStatement(query)) {
			statement.setInt(1, args_storeCD);
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					int holidayCD = rs.getInt("holidayCD");
					int employeeCD = rs.getInt("employeeCD");
					int storeCD = rs.getInt("storeCD");
					Timestamp date = rs.getTimestamp("application_date");
					java.sql.Date startDate = rs.getDate("application_start_date");
					java.sql.Date endDate = rs.getDate("application_end_date");
					String reason = rs.getString("reason");
					String note = rs.getString("note");
					int holidayStatus = rs.getInt("holiday_status");
					int approvalStatus = rs.getInt("approval_status");
					String name = rs.getString("name");
					int position = rs.getInt("position");
					int holidayDays = rs.getInt("days_between");
	
					ApplicationBean application = new ApplicationBean(holidayCD, employeeCD, storeCD, position, holidayStatus, approvalStatus, holidayDays, date, startDate, endDate, reason, note, name);
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
	

	public void updateapprovalStatus(int holidayCD, int status) {
		String sql = "UPDATE holiday SET approval_status=? WHERE holidayCD=? ";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setInt(1, status);
            statement.setInt(2, holidayCD);
            statement.executeQuery();
            
            statement.close();
			con.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // エラーハンドリングを適切に行う
        }
	}
	
	public ArrayList<HolidayBean> findHoliDayByEmployeeCD(int args_employeeCD) {
		ArrayList<HolidayBean> holidays = new ArrayList<HolidayBean>();
		System.out.println(args_employeeCD);
		String sql = "SELECT application_start_date, application_end_date FROM holiday WHERE employeeCD=? AND holiday_status=1 AND approval_status=2;";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setInt(1, args_employeeCD);
			
			ResultSet rs = statement.executeQuery();
            while (rs.next()) {
            	java.sql.Date applicationStartDate = rs.getDate("application_start_date");
            	java.sql.Date applicationEndDate = rs.getDate("application_end_date");
            	
            	HolidayBean holiday  = new HolidayBean(applicationStartDate, applicationEndDate);
            	holidays.add(holiday);
			}
            statement.close();
			con.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // エラーハンドリングを適切に行う
        }
		return holidays;
    }
	public int getRestDays(int args_employeeCD, String currentYear) {
		System.out.println(args_employeeCD);
		String sql = "SELECT COUNT(*) as count FROM holiday WHERE employeeCD=? AND holiday_status=1 AND approval_status=1 AND application_start_date LIKE ?;";
       System.out.println("ok");
       int count=0;
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setInt(1, args_employeeCD);
			statement.setString(2, currentYear + "%");
			
			ResultSet rs = statement.executeQuery();
			System.out.println(rs);;
            while (rs.next()) {
            	count = rs.getInt("count");
            	System.out.println(count);
			}
            statement.close();
			con.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // エラーハンドリングを適切に行う
        }
        return count;
    }
	
}