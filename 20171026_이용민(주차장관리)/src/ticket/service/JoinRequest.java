package ticket.service;

public class JoinRequest {
	private String carNo;
	private String phone;
	private String grade;
	private String tstat;
	private String startDate;
	private String endDate;

	public JoinRequest(String carNo, String phone, String grade, String tstat,
			String startDate, String endDate) {
		super();
		this.carNo = carNo;
		this.phone = phone;
		this.grade = grade;
		this.tstat = tstat;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public JoinRequest() {
		// TODO Auto-generated constructor stub
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
