package com.bs.model;

public class ParkingCarVO {

	private int parkno;
	private String carno;
	private String grade;
	private String tstat;
	private String indate;
	private String outdate;
	private String bigo;

	public ParkingCarVO() {
		// TODO Auto-generated constructor stub
	}

	public ParkingCarVO(int parkno, String carno, String grade, String tstat,
			String indate, String outdate, String bigo) {
		super();
		this.parkno = parkno;
		this.carno = carno;
		this.grade = grade;
		this.tstat = tstat;
		this.indate = indate;
		this.outdate = outdate;
		this.bigo = bigo;
	}

	public int getParkno() {
		return parkno;
	}

	public void setParkno(int parkno) {
		this.parkno = parkno;
	}

	public String getCarno() {
		return carno;
	}

	public void setCarno(String carno) {
		this.carno = carno;
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

	public String getIndate() {
		return indate;
	}

	public void setIndate(String indate) {
		this.indate = indate;
	}

	public String getOutdate() {
		return outdate;
	}

	public void setOutdate(String outdate) {
		this.outdate = outdate;
	}

	public String getBigo() {
		return bigo;
	}

	public void setBigo(String bigo) {
		this.bigo = bigo;
	}

}
