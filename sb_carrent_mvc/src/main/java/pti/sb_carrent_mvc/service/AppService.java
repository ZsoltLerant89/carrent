package pti.sb_carrent_mvc.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pti.sb_carrent_mvc.db.Database;
import pti.sb_carrent_mvc.dto.CarDTO;
import pti.sb_carrent_mvc.dto.CarDTOList;
import pti.sb_carrent_mvc.dto.ReservationDTO;
import pti.sb_carrent_mvc.dto.SuccessReservationDTO;
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
		
		CarDTOList carDTOList =  null;
		
		if(beginOfReservation.isBefore(endOfReservation))
		{	
			List<Car> carList = new ArrayList<>();
			
			carList = db.getCars();
			
			carDTOList = new CarDTOList();
			
			List<Reservation> reservationList = new ArrayList<>();
			
			for(int index = 0; index < carList.size(); index++)
			{
				
				Car currentCar = carList.get(index);
				/** Check if the car is active */
				if (currentCar.isActive() == true)
				{
					/** Get reservations for current car */
					reservationList = db.getReservation(currentCar.getCarId());
				
					if (reservationList != null)
					{
						for(int reservationIndex = 0; reservationIndex < reservationList.size(); reservationIndex++)
						{
							
							Reservation currentReservation = reservationList.get(reservationIndex);
							
							/** Compare current reservation date */ 
							if(beginOfReservation.isAfter(currentReservation.getEndOfReservation()) || endOfReservation.isBefore(currentReservation.getBeginOfReservation()))
							{
								CarDTO carDTO = new CarDTO(currentCar.getCarId(),currentCar.getType(),currentCar.getReservationAmount());
								
								carDTOList.addToCarList(carDTO);
								
								carDTOList.setBeginOfReservation(beginOfReservation);
								carDTOList.setEndOfReservation(endOfReservation);
							}
						}
					}
					else
					{
						CarDTO carDTO = new CarDTO(currentCar.getCarId(),currentCar.getType(),currentCar.getReservationAmount());
						carDTOList.addToCarList(carDTO);
					}
				}
			}
		}
		
		return carDTOList;
	}

	public ReservationDTO getReservationDTO(int carId, LocalDate beginOfReservation, LocalDate endOfReservation) {
		
		ReservationDTO reservationDTO = new ReservationDTO(carId, beginOfReservation, endOfReservation);
		
		return reservationDTO;
	}
	
	public SuccessReservationDTO setReservation(int carId, String name, String email, String address, String tel, LocalDate beginOfReservation, LocalDate endOfReservation) {
		SuccessReservationDTO sucessReservationDTO = null;
		
		Car car = db.getCarById(carId);
		
		if(car != null)
		{
			long priceOfcar = car.getReservationAmount();
			
			long lenghtOfCurrentFlight = (ChronoUnit.DAYS.between(beginOfReservation,endOfReservation))+1;
			long priceOfReservation = (priceOfcar*lenghtOfCurrentFlight);
			
			sucessReservationDTO = new SuccessReservationDTO(name, email, address, tel, car.getType(), beginOfReservation, endOfReservation,priceOfReservation);
		}
		
		return sucessReservationDTO;
	}

	
	
	
}
