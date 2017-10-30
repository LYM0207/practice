package ticket.service;

public class SearchRequest {

	private String carNo;

	public SearchRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SearchRequest(String carNo) {
		this.carNo = carNo;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

}
