package car.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Car {

	private int parkno;
	private String carno;
	private String grade;
	private String tstat;
	private String indate =getCurrentDate();
	private String outdate=getCurrentDate();
	private String bigo;

	public Car() {
		// TODO Auto-generated constructor stub
	}

	public Car(int parkno, String carno, String grade,
			String tstat, String indate, String outdate) {
		super();
		this.parkno = parkno;
		this.carno = carno;
		this.grade = grade;
		this.tstat = tstat;
		this.indate = indate;
		this.outdate = outdate;

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
