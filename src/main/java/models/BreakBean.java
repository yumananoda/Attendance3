package models;

import java.sql.Timestamp;

public class BreakBean {
	private int breakCD;
	private Timestamp breakStartTime, breakEndTime;
	public BreakBean(int breakCD, Timestamp breakStartTime, Timestamp breakEndTime) {
		super();
		this.breakCD = breakCD;
		this.breakStartTime = breakStartTime;
		this.breakEndTime = breakEndTime;
	}
	public int getBreakCD() {
		return breakCD;
	}
	public void setBreakCD(int breakCD) {
		this.breakCD = breakCD;
	}
	public Timestamp getBreakStartTime() {
		return breakStartTime;
	}
	public void setBreakStartTime(Timestamp breakStartTime) {
		this.breakStartTime = breakStartTime;
	}
	public Timestamp getBreakEndTime() {
		return breakEndTime;
	}
	public void setBreakEndTime(Timestamp breakEndTime) {
		this.breakEndTime = breakEndTime;
	}
}
