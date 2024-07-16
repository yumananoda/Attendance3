/** 出勤・退勤時刻をテーブルに格納する */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class RegistrationClockDao extends CommonDao {
	
	public void registrationClockIn(LocalDateTime now, int employeeCD) {
		String sql = "INSERT INTO time_records(employeeCD, clock_in_time, clock_out_time) VALUES(?,?,?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement statement = con.prepareStatement(sql)) {
            
            statement.setInt(1, employeeCD);
            statement.setObject(2, now);
            statement.setObject(3, null);
            statement.executeQuery();
            
            statement.close();
			con.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // エラーハンドリングを適切に行う
        }
    }
	
	public void registrationClockOut(LocalDateTime now, int employeeCD) {
		String sql = "UPDATE time_records SET clock_out_time=? WHERE employeeCD=? ORDER BY recordCD DESC LIMIT 1;";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setObject(1, now);
            statement.setInt(2, employeeCD);
            statement.executeQuery();
            
            statement.close();
			con.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // エラーハンドリングを適切に行う
        }
    }
	
	public void registrationBreakIn(LocalDateTime now, int employeeCD) {
		String sql = "INSERT INTO breaks(employeeCD, break_start_time, break_end_time) VALUES(?,?,?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement statement = con.prepareStatement(sql)) {

            System.out.println("breakIn");
            statement.setInt(1, employeeCD);
            statement.setObject(2, now);
            statement.setObject(3, null);
            statement.executeQuery();
            
            statement.close();
			con.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // エラーハンドリングを適切に行う
        }
    }
	
	public void registrationBreakOut(LocalDateTime now, int employeeCD) {
		String sql = "UPDATE breaks SET break_end_time=? WHERE employeeCD=? ORDER BY breakCD DESC LIMIT 1;";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement statement = con.prepareStatement(sql)) {

            System.out.println("breakOut");
            statement.setObject(1, now);
            statement.setInt(2, employeeCD);
            statement.executeQuery();
            
            statement.close();
			con.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // エラーハンドリングを適切に行う
        }
    }
}