package models;

import java.sql.Date;
import java.sql.Timestamp;

public class ApplicationBean {
	private int employeeCD, position, approvalStatus;
	private Timestamp date;
	private java.sql.Date startDate, endDate;
	private String reason, note, name;
	
	public ApplicationBean(int employeeCD, int position, int approvalStatus, Timestamp date, Date startDate,
			Date endDate, String reason, String note, String name) {
		super();
		this.employeeCD = employeeCD;
		this.position = position;
		this.approvalStatus = approvalStatus;
		this.date = date;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reason = reason;
		this.note = note;
		this.name = name;
	}
	public ApplicationBean(int employeeCD) {
		super();
		this.employeeCD = employeeCD;
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
	public int getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(int approvalStatus) {
		this.approvalStatus = approvalStatus;
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
