/** ログイン時に利用する */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import models.EmployeeBean;
import models.SelectEmployeeBean;

public class EmployeeDao extends CommonDao {
	public EmployeeBean findUser(int args_employeeCD, String args_password) {
		String query = "SELECT * FROM users WHERE employeeCD=? AND password=?";
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement statement = con.prepareStatement(query)) {

			statement.setInt(1, args_employeeCD);
			statement.setString(2, args_password);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int employeeCD = rs.getInt("employeeCD");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				int storeCD = rs.getInt("storeCD");
				java.sql.Date hire_date = rs.getDate("hire_date");
				int position = rs.getInt("position");

				EmployeeBean user = new EmployeeBean(employeeCD, storeCD, position, name, password, email, hire_date);
				System.out.println(user.getName());
				return user;
			}
			statement.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			// エラーハンドリングを適切に行う
		}
		return null;
	}

	public void Register(EmployeeBean EmployeeRegister) {
		String query = "INSERT INTO users(name, email, password, storeCD, hire_date, position) VALUES(?,?,?,?,?,?)";
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement statement = con.prepareStatement(query)) {

			statement.setString(1, EmployeeRegister.getName());
			statement.setString(2, EmployeeRegister.getEmail());
			statement.setString(3, EmployeeRegister.getPassword());
			statement.setInt(4, EmployeeRegister.getStoreCD());
			statement.setDate(5, EmployeeRegister.getHire_date());
			statement.setInt(6, EmployeeRegister.getPosition());

			statement.executeUpdate();
			statement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			// エラーハンドリングを適切に行う
		}
	}

	public Integer findStoreCD(int args_managerCD) {
		String query = "SELECT storeCD FROM storeManagers WHERE managerCD = ?";
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement statement = con.prepareStatement(query)) {
			statement.setInt(1, args_managerCD);
			ResultSet rs = statement.executeQuery();
			int storeCD;
			while (rs.next()) {
				storeCD = rs.getInt("storeCD");
				statement.close();
				con.close();
				return storeCD;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// エラーハンドリングを適切に行う
		}
		return null;
	}

	public ArrayList<SelectEmployeeBean> findEmployeeCDOfShiftRegister(int args_storeCD) {
		ArrayList<SelectEmployeeBean> employees = new ArrayList<SelectEmployeeBean>();
		String query = "SELECT employeeCD, name FROM users WHERE storeCD = ?";
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement statement = con.prepareStatement(query)) {
			statement.setInt(1, args_storeCD);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				int employeeCD = rs.getInt("employeeCD");
				String name = rs.getString("name");

				SelectEmployeeBean employee = new SelectEmployeeBean(employeeCD, name);
				employees.add(employee);
			}
			statement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}

	public Timestamp getClockIn(int args_employeeCD) {
		Timestamp clockInTime = null;
		String query = "SELECT clock_in_time FROM time_records WHERE employeeCD = ? AND clock_out_time IS NULL;";
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement statement = con.prepareStatement(query)) {
			statement.setInt(1, args_employeeCD);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				clockInTime = (Timestamp) rs.getObject("clock_in_time");
				statement.close();
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// エラーハンドリングを適切に行う
		}
		return clockInTime;
	}
	
	public EmployeeBean getInfo(int args_employeeCD) { 
		String query = "SELECT * FROM users WHERE employeeCD = ?"; 
		try (Connection con = DriverManager.getConnection(URL, USER, PASS); 
			PreparedStatement statement = con.prepareStatement(query)) { 
			statement.setInt(1, args_employeeCD); 
			ResultSet rs = statement.executeQuery(); 
			while (rs.next()) { 
				int employeeCD = rs.getInt("employeeCD"); 
				String name = rs.getString("name"); 
				String email = rs.getString("email"); 
				String password = null; 
				int storeCD = rs.getInt("storeCD"); 
				java.sql.Date hire_date = rs.getDate("hire_date"); 
				int position = rs.getInt("position"); 
				EmployeeBean employeeInfo = new EmployeeBean(employeeCD, storeCD, position, name, password, email, hire_date); 
				System.out.println(employeeInfo.getName()); 
				return employeeInfo; 
			} 
			statement.close(); 
			con.close(); 
		} catch (SQLException e) { 
			e.printStackTrace(); 
		} 
		return null; 
	} 
}