package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.ShiftBean;

public class ShiftDao extends CommonDao {

	public ArrayList<ShiftBean> findShiftByEmployeeCD(int args_employeeCD) {
		ArrayList<ShiftBean> shifts = new ArrayList<ShiftBean>();
		String query = "SELECT employeeCD, shift_day, start_time, end_time  FROM shift WHERE employeeCD=?";
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement statement = con.prepareStatement(query)) {

			statement.setInt(1, args_employeeCD);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int employeeCD = rs.getInt("employeeCD");
				int shift_day = rs.getInt("shift_day");
				java.sql.Time start_time = rs.getTime("start_time");
				java.sql.Time end_time = rs.getTime("end_time");

				ShiftBean shift = new ShiftBean(employeeCD, shift_day, start_time, end_time);
				System.out.println(shift.getShift_day());
				shifts.add(shift);
			}
			statement.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			// エラーハンドリングを適切に行う
		}
		return shifts;
	}

	public ShiftBean findShiftDay(ShiftBean shift) {
		System.out.println("shift:");
		System.out.println(shift.getEmployeeCD());
		System.out.println(shift.getShift_day());
		String query = "SELECT * FROM shift WHERE employeeCD=? AND shift_day=?";
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement statement = con.prepareStatement(query)) {
			statement.setInt(1, shift.getEmployeeCD());
			statement.setInt(2, shift.getShift_day());

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				System.out.println(rs);
				int employeeCD = rs.getInt("employeeCD");
				System.out.println(employeeCD);
				int shift_day = rs.getInt("shift_day");
				System.out.println(shift_day);
				java.sql.Time start_time = rs.getTime("start_time");
				System.out.println(start_time);
				java.sql.Time end_time = rs.getTime("end_time");
				System.out.println(end_time);
				ShiftBean shiftBean = new ShiftBean(employeeCD, shift_day, start_time, end_time);
				
				statement.close();
				con.close();
				return shiftBean;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// エラーハンドリングを適切に行う
		}
		return null;
	}

	public void shiftUpdate(ShiftBean shift) {
		String sql = "UPDATE shift SET start_time=?, end_time=? WHERE employeeCD=? AND shift_day=?;";
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement statement = con.prepareStatement(sql)) {
			
			statement.setTime(1, shift.getStart_time());
			statement.setTime(2, shift.getEnd_time());
			statement.setInt(3, shift.getEmployeeCD());
			statement.setInt(4, shift.getShift_day());
			statement.executeQuery();

			statement.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			// エラーハンドリングを適切に行う
		}
	}

	public void shiftRegister(ShiftBean shift) {
		String sql = "INSERT INTO shift(employeeCD, shift_day, start_time, end_time) VALUES(?,?,?,?)";
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement statement = con.prepareStatement(sql)) {

			statement.setInt(1, shift.getEmployeeCD());
			statement.setInt(2, shift.getShift_day());
			statement.setTime(3, shift.getStart_time());
			statement.setTime(4, shift.getEnd_time());
			statement.executeUpdate();

			statement.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			// エラーハンドリングを適切に行う
		}
	}

}
