package pti.sb_carrent_mvc.dto;

import java.util.ArrayList;
import java.util.List;

import pti.sb_carrent_mvc.model.Reservation;

public class ReservationListDTO {

	private List<Reservation> reservationList;

	public ReservationListDTO() {
		super();
		this.reservationList = new ArrayList<>();
	}

	public List<Reservation> getReservationList() {
		return reservationList;
	}

	public void setReservationList(List<Reservation> reservationList) {
		this.reservationList = reservationList;
	}
	
	public void addReservationToReservationList(Reservation reservation)
	{
		this.reservationList.add(reservation);
	}

	@Override
	public String toString() {
		return "ReservationListDTO [reservationList=" + reservationList + "]";
	}
	
	
	
	
}
