package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import models.ExceptionShiftBean;
import models.ShiftBean;

public class ShiftDao extends CommonDao {

	public ArrayList<ShiftBean> findShiftByEmployeeCD(int args_employeeCD) {
		ArrayList<ShiftBean> shifts = new ArrayList<ShiftBean>();
		String query = "SELECT * FROM shift WHERE employeeCD=?";
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement statement = con.prepareStatement(query)) {

			statement.setInt(1, args_employeeCD);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int employeeCD = rs.getInt("employeeCD");
				int shift_day = rs.getInt("shift_day");
				int shift_duration = rs.getInt("shift_duration");
				java.sql.Time start_time = rs.getTime("start_time");
				java.sql.Time end_time = rs.getTime("end_time");

				ShiftBean shift = new ShiftBean(employeeCD, shift_duration, shift_day, start_time, end_time);
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
	
	public ArrayList<ExceptionShiftBean> findexcEptionShiftByEmployeeCD(int args_employeeCD) {
		ArrayList<ExceptionShiftBean> exceptionShifts = new ArrayList<ExceptionShiftBean>();
		String query = "SELECT * FROM exception_shift WHERE employeeCD=?";
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement statement = con.prepareStatement(query)) {

			statement.setInt(1, args_employeeCD);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int employeeCD = rs.getInt("employeeCD");
				java.sql.Date shiftDate = rs.getDate("shift_date");
				java.sql.Time startTime = rs.getTime("start_time");
				java.sql.Time endTime = rs.getTime("end_time");
				int category = rs.getInt("category");

				ExceptionShiftBean exceptionShift = new ExceptionShiftBean(employeeCD, category, shiftDate, startTime, endTime);
				System.out.println(exceptionShift.getShiftDate());
				exceptionShifts.add(exceptionShift);
			}
			statement.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			// エラーハンドリングを適切に行う
		}
		return exceptionShifts;
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
				int shift_duration = rs.getInt("shift_duration");
				System.out.println(shift_duration);
				int shift_day = rs.getInt("shift_day");
				System.out.println(shift_day);
				java.sql.Time start_time = rs.getTime("start_time");
				System.out.println(start_time);
				java.sql.Time end_time = rs.getTime("end_time");
				System.out.println(end_time);
				ShiftBean shiftBean = new ShiftBean(employeeCD, shift_duration, shift_day, start_time, end_time);
				
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

	public void shiftDelete(int employeeCD) {
		String sql = "DELETE FROM shift WHERE employeeCD=? ";
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement statement = con.prepareStatement(sql)) {

			statement.setInt(1, employeeCD);
			statement.executeQuery();
			
			statement.close();
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			// エラーハンドリングを適切に行う
		}
	}

	public void shiftRegister(ShiftBean shift) {
		String sql = "INSERT INTO shift(employeeCD, shift_duration, shift_day, start_time, end_time) VALUES(?,?,?,?,?)";
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement statement = con.prepareStatement(sql)) {

			statement.setInt(1, shift.getEmployeeCD());
			statement.setInt(2, shift.getShift_duration());
			statement.setInt(3, shift.getShift_day());
			statement.setTime(4, shift.getStart_time());
			statement.setTime(5, shift.getEnd_time());
			statement.executeUpdate();

			statement.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			// エラーハンドリングを適切に行う
		}
	}
	
	public void exceptionShiftRegister(int employeeCD, Date shiftDate, Time startTime, Time endTime, int category) {
		String sql = "INSERT INTO exception_shift(employeeCD, shift_date, start_time, end_time, category) VALUES(?,?,?,?,?)";
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement statement = con.prepareStatement(sql)) {

			statement.setInt(1, employeeCD);
			statement.setDate(2, shiftDate);
			statement.setTime(3, startTime);
			statement.setTime(4, endTime);
			statement.setInt(5, category);
			statement.executeUpdate();

			statement.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			// エラーハンドリングを適切に行う
		}
	}
	
	public ArrayList<ExceptionShiftBean> findExceptionAddShiftByEmployeeCD(int args_employeeCD) {
		ArrayList<ExceptionShiftBean> exceptionshifts = new ArrayList<ExceptionShiftBean>();
		String query = "SELECT * FROM exception_shift WHERE employeeCD=? AND category=1";
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement statement = con.prepareStatement(query)) {

			statement.setInt(1, args_employeeCD);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int employeeCD = rs.getInt("employeeCD");
				java.sql.Date shiftDate = rs.getDate("shift_date");
				java.sql.Time startTime = rs.getTime("start_time");
				java.sql.Time endTime = rs.getTime("end_time");
				int category = rs.getInt("category");

				ExceptionShiftBean shift = new ExceptionShiftBean(employeeCD, category, shiftDate, startTime, endTime);
				System.out.println(shift.getShiftDate());
				exceptionshifts.add(shift);
			}
			statement.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			// エラーハンドリングを適切に行う
		}
		return exceptionshifts;
	}
	
	public ArrayList<ExceptionShiftBean> findExceptionRemoveShiftByEmployeeCD(int args_employeeCD) {
		ArrayList<ExceptionShiftBean> exceptionshifts = new ArrayList<ExceptionShiftBean>();
		String query = "SELECT * FROM exception_shift WHERE employeeCD=? AND category=2";
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement statement = con.prepareStatement(query)) {

			statement.setInt(1, args_employeeCD);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int employeeCD = rs.getInt("employeeCD");
				java.sql.Date shiftDate = rs.getDate("shift_date");
				java.sql.Time startTime = rs.getTime("start_time");
				java.sql.Time endTime = rs.getTime("end_time");
				int category = rs.getInt("category");

				ExceptionShiftBean shift = new ExceptionShiftBean(employeeCD, category, shiftDate, startTime, endTime);
				System.out.println(shift.getShiftDate());
				exceptionshifts.add(shift);
			}
			statement.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			// エラーハンドリングを適切に行う
		}
		return exceptionshifts;
	}
	
	public ArrayList<ExceptionShiftBean> findExceptionChangeShiftByEmployeeCD(int args_employeeCD) {
		ArrayList<ExceptionShiftBean> exceptionshifts = new ArrayList<ExceptionShiftBean>();
		String query = "SELECT * FROM exception_shift WHERE employeeCD=? AND category=3";
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement statement = con.prepareStatement(query)) {

			statement.setInt(1, args_employeeCD);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int employeeCD = rs.getInt("employeeCD");
				java.sql.Date shiftDate = rs.getDate("shift_date");
				java.sql.Time startTime = rs.getTime("start_time");
				java.sql.Time endTime = rs.getTime("end_time");
				int category = rs.getInt("category");

				ExceptionShiftBean shift = new ExceptionShiftBean(employeeCD, category, shiftDate, startTime, endTime);
				System.out.println(shift.getShiftDate());
				exceptionshifts.add(shift);
			}
			statement.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			// エラーハンドリングを適切に行う
		}
		return exceptionshifts;
	}
}
