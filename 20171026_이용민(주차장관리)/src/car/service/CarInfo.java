package car.service;

import ticket.model.Ticket;
import car.model.Car;

public class CarInfo {

	private Ticket ticketInfo;
	private int parkNo;
	private Car car;

	public CarInfo() {
		// TODO Auto-generated constructor stub
	}

	public CarInfo(Ticket ticketInfo, int parkNo, Car car) {
		super();
		this.ticketInfo = ticketInfo;
		this.parkNo = parkNo;
		this.car = car;
	}

	public Ticket getTicketInfo() {
		return ticketInfo;
	}

	public void setTicketInfo(Ticket ticketInfo) {
		this.ticketInfo = ticketInfo;
	}

	public int getParkNo() {
		return parkNo;
	}

	public void setParkNo(int parkNo) {
		this.parkNo = parkNo;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	
	

}
