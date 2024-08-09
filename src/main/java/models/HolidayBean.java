package models;

import java.sql.Date;

public class HolidayBean {
	private java.sql.Date applicationStartDate, applicationEndDate;

	public HolidayBean(Date applicationStartDate, Date applicationEndDate) {
		super();
		this.applicationStartDate = applicationStartDate;
		this.applicationEndDate = applicationEndDate;
	}

	public java.sql.Date getApplicationStartDate() {
		return applicationStartDate;
	}

	public void setApplicationStartDate(java.sql.Date applicationStartDate) {
		this.applicationStartDate = applicationStartDate;
	}

	public java.sql.Date getApplicationEndDate() {
		return applicationEndDate;
	}

	public void setApplicationEndDate(java.sql.Date applicationEndDate) {
		this.applicationEndDate = applicationEndDate;
	}


	
}
