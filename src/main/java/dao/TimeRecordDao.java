package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import models.TimeRecordsBean;

public class TimeRecordDao extends CommonDao{
	public ArrayList<TimeRecordsBean> getStatus(int args_employeeCD) {
		ArrayList<TimeRecordsBean> TimeRecords = new ArrayList<TimeRecordsBean>();
		System.out.println(args_employeeCD);
		String sql = "SELECT clock_in_time, clock_out_time FROM time_records WHERE employeeCD=?;";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setInt(1, args_employeeCD);
			statement.executeQuery();
			
			ResultSet rs = statement.executeQuery();
            while (rs.next()) {
            	// Timestampとして取得
				Timestamp timestamp1 = rs.getTimestamp("clock_in_time");
				Timestamp timestamp2 = rs.getTimestamp("clock_out_time");
//            	
				TimeRecordsBean timeRecord  = new TimeRecordsBean(timestamp1, timestamp2);
				TimeRecords.add(timeRecord);
			}
            statement.close();
			con.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // エラーハンドリングを適切に行う
        }
		return TimeRecords;
    }
}
