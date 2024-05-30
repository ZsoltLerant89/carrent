package pti.sb_carrent_mvc.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CarDTOList {

	private List<CarDTO> carList;
	private LocalDate beginOfReservation;
	private LocalDate endOfReservation;
	
	
	public CarDTOList() {
		super();
		this.carList = new ArrayList<>();
		
	}


	public List<CarDTO> getCarList() {
		return carList;
	}

	public void setCarList(List<CarDTO> carList) {
		this.carList = carList;
	}
	
	public void addToCarList(CarDTO carDTO)
	{
		this.carList.add(carDTO);
	}

	public LocalDate getBeginOfReservation() {
		return beginOfReservation;
	}


	public void setBeginOfReservation(LocalDate beginOfReservation) {
		this.beginOfReservation = beginOfReservation;
	}


	public LocalDate getEndOfReservation() {
		return endOfReservation;
	}


	public void setEndOfReservation(LocalDate endOfReservation) {
		this.endOfReservation = endOfReservation;
	}


}
