package models;

import java.sql.Date;

public class EmployeeBean {
	private Integer employeeCD;
	private int storeCD,position,agreements,isAdmin;
	private String name, password, email;
	private java.sql.Date hire_date;
	public EmployeeBean(Integer employeeCD, int storeCD, int position, int agreements, int isAdmin, String name,
			String password, String email, Date hire_date) {
		super();
		this.employeeCD = employeeCD;
		this.storeCD = storeCD;
		this.position = position;
		this.agreements = agreements;
		this.isAdmin = isAdmin;
		this.name = name;
		this.password = password;
		this.email = email;
		this.hire_date = hire_date;
	}
	public Integer getEmployeeCD() {
		return employeeCD;
	}
	public void setEmployeeCD(Integer employeeCD) {
		this.employeeCD = employeeCD;
	}
	public int getStoreCD() {
		return storeCD;
	}
	public void setStoreCD(int storeCD) {
		this.storeCD = storeCD;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getAgreements() {
		return agreements;
	}
	public void setAgreements(int agreements) {
		this.agreements = agreements;
	}
	public int getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public java.sql.Date getHire_date() {
		return hire_date;
	}
	public void setHire_date(java.sql.Date hire_date) {
		this.hire_date = hire_date;
	}
	
}
	