package models;

public class SelectEmployeeBean {
	private int employeeCD;
	private String name;
	
	public SelectEmployeeBean(int employeeCD, String name) {
		super();
		this.employeeCD = employeeCD;
		this.name = name;
	}
	public int getEmployeeCD() {
		return employeeCD;
	}
	public void setEmployeeCD(int employeeCD) {
		this.employeeCD = employeeCD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
