package car.service;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CarRequest {
	private int parkno;
	private String carNo;
	private String grade;
	private String tstat;
	private String inDate;
	private String outDate;

	public CarRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CarRequest(int parkno, String carNo, String grade, String tstat, String inDate,
			String outDate) {
		super();
		this.parkno = parkno;
		this.carNo = carNo;
		this.grade = grade;
		this.tstat = tstat;
		this.inDate = inDate;
		this.outDate = outDate;
	}

	// 현재 년 월 일 시 분 초 생성
	public String getCurrentDate() {

		SimpleDateFormat fm = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = fm.format(new Date());
		System.out.println("현재시간 시분초 = " + date);

		return date;
	}

	public int getParkno() {
		return parkno;
	}

	public void setParkno(int parkno) {
		this.parkno = parkno;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
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

	public String getInDate() {
		return getCurrentDate();
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
	}

	public String getOutDate() {
		return outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}

}
