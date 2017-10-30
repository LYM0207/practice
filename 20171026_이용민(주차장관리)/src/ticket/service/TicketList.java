package ticket.service;

public class TicketList {
	private int tno;
	private String carno;
	private String phone;
	private String grade;
	private String tstat;
	private String startdate;
	private String enddate;

	public TicketList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TicketList(int tno, String carno, String phone, String grade,
			String tstat, String startdate, String enddate) {
		super();
		this.tno = tno;
		this.carno = carno;
		this.phone = phone;
		this.grade = convertGradeWord(grade);
		this.tstat = tstat;
		this.startdate = startdate;
		this.enddate = enddate;
	}

	public String convertGradeWord(String grade){
		String convertGrade="";
		
		if(grade.equalsIgnoreCase("M")){
			convertGrade="월회원";
		}
		if(grade.equalsIgnoreCase("Y")){
			convertGrade="연회원";
		}
		return convertGrade; 
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

}
