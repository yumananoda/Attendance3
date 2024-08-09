package models;

import java.sql.Date;
import java.sql.Timestamp;

public class ApplicationBean {
	private int holidayCD, employeeCD, position, storeCD, holidayStatus, approvalStatus, holidayDays;
	private Timestamp date;
	private java.sql.Date startDate, endDate;
	private String reason, note, name;
	
	public ApplicationBean(int holidayCD, int employeeCD, int position, int storeCD, int holidayStatus,
			int approvalStatus, int holidayDays, Timestamp date, Date startDate, Date endDate, String reason,
			String note, String name) {
		super();
		this.holidayCD = holidayCD;
		this.employeeCD = employeeCD;
		this.position = position;
		this.storeCD = storeCD;
		this.holidayStatus = holidayStatus;
		this.approvalStatus = approvalStatus;
		this.holidayDays = holidayDays;
		this.date = date;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reason = reason;
		this.note = note;
		this.name = name;
	}
	public int getHolidayCD() {
		return holidayCD;
	}
	public void setHolidayCD(int holidayCD) {
		this.holidayCD = holidayCD;
	}
	public int getEmployeeCD() {
		return employeeCD;
	}
	public void setEmployeeCD(int employeeCD) {
		this.employeeCD = employeeCD;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getStoreCD() {
		return storeCD;
	}
	public void setStoreCD(int storeCD) {
		this.storeCD = storeCD;
	}
	public int getHolidayStatus() {
		return holidayStatus;
	}
	public void setHolidayStatus(int holidayStatus) {
		this.holidayStatus = holidayStatus;
	}
	public int getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(int approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public int getHolidayDays() {
		return holidayDays;
	}
	public void setHolidayDays(int holidayDays) {
		this.holidayDays = holidayDays;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public java.sql.Date getStartDate() {
		return startDate;
	}
	public void setStartDate(java.sql.Date startDate) {
		this.startDate = startDate;
	}
	public java.sql.Date getEndDate() {
		return endDate;
	}
	public void setEndDate(java.sql.Date endDate) {
		this.endDate = endDate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
