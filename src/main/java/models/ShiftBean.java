package models;

import java.sql.Time;

public class ShiftBean {
	private int employeeCD, shift_duration, shift_day;
	private java.sql.Time start_time, end_time;
	public ShiftBean(int employeeCD, int shift_duration, int shift_day, Time start_time, Time end_time) {
		super();
		this.employeeCD = employeeCD;
		this.shift_duration = shift_duration;
		this.shift_day = shift_day;
		this.start_time = start_time;
		this.end_time = end_time;
	}
	public int getEmployeeCD() {
		return employeeCD;
	}
	public void setEmployeeCD(int employeeCD) {
		this.employeeCD = employeeCD;
	}
	public int getShift_duration() {
		return shift_duration;
	}
	public void setShift_duration(int shift_duration) {
		this.shift_duration = shift_duration;
	}
	public int getShift_day() {
		return shift_day;
	}
	public void setShift_day(int shift_day) {
		this.shift_day = shift_day;
	}
	public java.sql.Time getStart_time() {
		return start_time;
	}
	public void setStart_time(java.sql.Time start_time) {
		this.start_time = start_time;
	}
	public java.sql.Time getEnd_time() {
		return end_time;
	}
	public void setEnd_time(java.sql.Time end_time) {
		this.end_time = end_time;
	}
	
}
