package ticket.model;

public class Ticket {

	private int tno;
	private String carno;
	private String phone;
	private String grade;
	private String tstat;
	private String startdate;
	private String enddate;
	private String note;
	
	//정기권회원이 아닐때 사용
	public Ticket( String carno, String grade){
		this.carno = carno;
		this.grade = grade;
		convertGradeText(grade);
	}

	// ticket 등록할떄 사용
	public Ticket(String carno, String phone, String grade, String tstat,
			String startdate, String enddate, String note) {
		super();
		this.carno = carno;
		this.phone = phone;
		this.grade = grade;
		this.tstat = tstat;
		this.startdate = startdate;
		this.enddate = enddate;
		this.note = note;
	}

	// ticket정보 가져올때 사용
	public Ticket(int tno, String carno, String phone, String grade,
			String tstat, String startdate, String enddate) {
		super();
		this.tno = tno;
		this.carno = carno;
		this.phone = phone;
		this.grade = grade;
		this.tstat = tstat;
		this.startdate = startdate;
		this.enddate = enddate;

		convertGradeText(grade);
	}
	
	public void convertGradeText(String grade){

		if (this.grade.equalsIgnoreCase("M")) {
			this.note = "월회원입니다";
		} else if (this.grade.equalsIgnoreCase("Y")) {
			this.note = "연회원입니다";
		} else if(this.grade.equalsIgnoreCase("D")) {
			this.note = "일일회원입니다";
		}
	}

	public Ticket() {
		// TODO Auto-generated constructor stub
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
