package pti.sb_carrent_mvc.dto;

import java.util.ArrayList;
import java.util.List;

import pti.sb_carrent_mvc.model.Reservation;

public class AdminDTO {

	private List<Reservation> reservationList;
	private List<CarDTO> carList;

	
	public AdminDTO() {
		super();
		this.reservationList = new ArrayList<>();
		this.carList = new ArrayList<>();
	}
	

	public List<Reservation> getReservationList() {
		return reservationList;
	}

	public void setReservationList(List<Reservation> reservationList) {
		this.reservationList = reservationList;
	}

	public List<CarDTO> getCarList() {
		return carList;
	}

	public void setCarList(List<CarDTO> carList) {
		this.carList = carList;
	}

	public void addReservationToReservationList(Reservation reservation)
	{
		this.reservationList.add(reservation);
	}
	
	public void addCarDTOToCarList(CarDTO carDTO)
	{
		this.carList.add(carDTO);
	}

	
}
