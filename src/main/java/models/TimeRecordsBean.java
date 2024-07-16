package models;
import java.sql.Timestamp;

public class TimeRecordsBean {
	private int recordCD;
	private Timestamp clockInTime, clockOutTime;
	public TimeRecordsBean(int recordCD, Timestamp clockInTime, Timestamp clockOutTime) {
		super();
		this.recordCD = recordCD;
		this.clockInTime = clockInTime;
		this.clockOutTime = clockOutTime;
	}
	public int getRecordCD() {
		return recordCD;
	}
	public void setRecordCD(int recordCD) {
		this.recordCD = recordCD;
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