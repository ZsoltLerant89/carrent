package pti.sb_carrent_mvc.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pti.sb_carrent_mvc.db.Database;
import pti.sb_carrent_mvc.dto.CarDTO;
import pti.sb_carrent_mvc.dto.CarDTOList;
import pti.sb_carrent_mvc.model.Car;
import pti.sb_carrent_mvc.model.Reservation;

@Service
public class AppService {

	private Database db;

	@Autowired
	public AppService(Database db) {
		super();
		this.db = db;
	}

	public CarDTOList getCarDTOList(LocalDate beginOfReservation, LocalDate endOfReservation) {
		CarDTOList carDTOList =  new CarDTOList();
		List<Car> carList = new ArrayList<>();
		
		carList = db.getCars(beginOfReservation, endOfReservation);
		carDTOList = new CarDTOList();
		List<Reservation> reservationList = new ArrayList<>();
		for(int index = 0; index < carList.size(); index++)
		{
			
			Car currentCar = carList.get(index);
			/** Get reservations for current car */
			
			reservationList = db.getReservation(currentCar.getCarId());
		
			if (reservationList != null)
			{
				for(int reservationIndex = 0; reservationIndex < reservationList.size(); reservationIndex++)
				{
					Reservation currentReservation = reservationList.get(reservationIndex);
					/** Compare current reservation date */
					
////				if(currentReservation.getEndOfReservation().compareTo(beginingOfReservation) > 0 || beginingOfReservation.compareTo(currentReservation.getEndOfReservation()) > 0) 

					if(beginOfReservation.isAfter(currentReservation.getEndOfReservation()) || endOfReservation.isBefore(currentReservation.getBeginOfReservation()))
					{
						CarDTO carDTO = new CarDTO(currentCar.getCarId(),currentCar.getType(),currentCar.getReservationAmount());
						
						carDTOList.addToCarList(carDTO);
					}
				}
			}
			else
			{
				CarDTO carDTO = new CarDTO(currentCar.getCarId(),currentCar.getType(),currentCar.getReservationAmount());
				carDTOList.addToCarList(carDTO);
			}
		}
		
		
		return carDTOList;
	}
	
	
}
