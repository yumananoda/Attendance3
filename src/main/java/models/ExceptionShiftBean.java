package models;

import java.sql.Date;
import java.sql.Time;

public class ExceptionShiftBean {
	private int employeeCD, category;
	private java.sql.Date shiftDate;
	private java.sql.Time startTime, endTime;
	public ExceptionShiftBean(int employeeCD, int category, Date shiftDate, Time startTime, Time endTime) {
		super();
		this.employeeCD = employeeCD;
		this.category = category;
		this.shiftDate = shiftDate;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public int getEmployeeCD() {
		return employeeCD;
	}
	public void setEmployeeCD(int employeeCD) {
		this.employeeCD = employeeCD;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public java.sql.Date getShiftDate() {
		return shiftDate;
	}
	public void setShiftDate(java.sql.Date shiftDate) {
		this.shiftDate = shiftDate;
	}
	public java.sql.Time getStartTime() {
		return startTime;
	}
	public void setStartTime(java.sql.Time startTime) {
		this.startTime = startTime;
	}
	public java.sql.Time getEndTime() {
		return endTime;
	}
	public void setEndTime(java.sql.Time endTime) {
		this.endTime = endTime;
	}
	
	
}
