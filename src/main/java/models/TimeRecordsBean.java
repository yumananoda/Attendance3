package models;
import java.sql.Timestamp;

public class TimeRecordsBean {
	private Timestamp clockInTime, clockOutTime;
	public TimeRecordsBean(Timestamp timestamp1, Timestamp timestamp2) {
		super();
		this.clockInTime = timestamp1;
		this.clockOutTime = timestamp2;
	}

	public Timestamp getClockInTime() {
		return clockInTime;
	}

	public void setClockInTime(Timestamp clockInTime) {
		this.clockInTime = clockInTime;
	}

	public Timestamp getClockOutTime() {
		return clockOutTime;
	}

	public void setClockOutTime(Timestamp clockOutTime) {
		this.clockOutTime = clockOutTime;
	}

}