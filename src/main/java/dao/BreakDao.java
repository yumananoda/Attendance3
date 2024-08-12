package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import models.BreakBean;

public class BreakDao extends CommonDao{
	public ArrayList<BreakBean> getStatus(int args_employeeCD) {
		ArrayList<BreakBean> breaks = new ArrayList<BreakBean>();
		System.out.println(args_employeeCD);
		String sql = "SELECT breakCD, break_start_time, break_end_time FROM breaks WHERE employeeCD=?;";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setInt(1, args_employeeCD);
			
			ResultSet rs = statement.executeQuery();
            while (rs.next()) {
            	int breakCD = rs.getInt("breakCD");
				Timestamp breakStartTime = rs.getTimestamp("break_start_time");
				Timestamp breakEndTime = rs.getTimestamp("break_end_time");
            	
				BreakBean breakData = new BreakBean(breakCD, breakStartTime, breakEndTime);
				breaks.add(breakData);
			}
            statement.close();
			con.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // エラーハンドリングを適切に行う
        }
		return breaks;
    }
}
