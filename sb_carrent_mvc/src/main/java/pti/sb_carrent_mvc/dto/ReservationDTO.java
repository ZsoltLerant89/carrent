package pti.sb_carrent_mvc.dto;

import java.time.LocalDate;

public class ReservationDTO {

	private int carId;
	private LocalDate beginOfReservation;
	private LocalDate endOfResetvation;
	
	
	public ReservationDTO(	int carId,
							LocalDate beginOfReservation,
							LocalDate endOfResetvation
							) 
	{
		super();
		this.carId = carId;
		this.beginOfReservation = beginOfReservation;
		this.endOfResetvation = endOfResetvation;
	}


	public int getCarId() {
		return carId;
	}


	public void setCarId(int carId) {
		this.carId = carId;
	}

	public LocalDate getBeginOfReservation() {
		return beginOfReservation;
	}

	public void setBeginOfReservation(LocalDate beginOfReservation) {
		this.beginOfReservation = beginOfReservation;
	}

	public LocalDate getEndOfResetvation() {
		return endOfResetvation;
	}

	public void setEndOfResetvation(LocalDate endOfResetvation) {
		this.endOfResetvation = endOfResetvation;
	}

	
}
