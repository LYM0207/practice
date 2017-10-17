package com.bs.model;

public class ParkingTicketVO {
	private int tno;
	private String carno;
	private String phone;
	private String grade;
	private String tstat;
	private String startdate;
	private String enddate;
	private String note;



	public ParkingTicketVO() {
	}

	public ParkingTicketVO(int tno, String carno, String phone, String grade,
			String tstat, String startdate, String enddate, String note) {
		super();
		this.tno = tno;
		this.carno = carno;
		this.phone = phone;
		this.grade = grade;
		this.tstat = tstat;
		this.startdate = startdate;
		this.enddate = enddate;
		this.note = note;
	}

	public int getTno() {
		return tno;
	}

	public void setTno(int tno) {
		this.tno = tno;
	}

	public String getCarno() {
		return carno;
	}

	public void setCarno(String carno) {
		this.carno = carno;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getTstat() {
		return tstat;
	}

	public void setTstat(String tstat) {
		this.tstat = tstat;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
