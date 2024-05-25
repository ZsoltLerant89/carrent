package pti.sb_carrent_rest.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pti.sb_carrent_rest.db.Database;
import pti.sb_carrent_rest.dto.CarDTO;
import pti.sb_carrent_rest.dto.CarDTOList;
import pti.sb_carrent_rest.dto.ReservationDTO;
import pti.sb_carrent_rest.dto.SuccessReservationDTO;
import pti.sb_carrent_rest.model.Car;
import pti.sb_carrent_rest.model.Reservation;

@Service
public class AppService {

	private Database db;
	
	@Autowired
	public AppService(Database db) {
		super();
		this.db = db;
	}
	
	public CarDTOList getFreeCars(LocalDate beginOfReservation, LocalDate endOfReservation) {
		
		CarDTOList carDTOList =  null;
		
		if(beginOfReservation.isBefore(endOfReservation))
		{	
			List<Car> carList = db.getActiveCars();
			
			carDTOList = new CarDTOList();
			
			for(int index = 0; index < carList.size(); index++)
			{
				
				Car currentCar = carList.get(index);
			
				/** Get reservations for current car */
				List<Reservation> reservationList = db.getReservationByCarIdWithinPeriods(currentCar.getCarId(),beginOfReservation,endOfReservation);
			
				if (reservationList.size() == 0)
				{
							CarDTO carDTO = new CarDTO(	currentCar.getCarId(),
														currentCar.getType(),
														currentCar.getReservationAmount(),
														currentCar.isActive()
														);
							
							carDTOList.addToCarList(carDTO);

				}
			}
			
			carDTOList.setBeginOfReservation(beginOfReservation);
			carDTOList.setEndOfReservation(endOfReservation);
		}
		
		return carDTOList;
	}

	public SuccessReservationDTO setReservation(int carId,
												String name, 
												String email, 
												String address, 
												String tel, 
												LocalDate beginOfReservation, 
												LocalDate endOfReservation
												) 
	{
	
		SuccessReservationDTO sucessReservationDTO = null;
		
		Car car = db.getCarById(carId);
		
		if(car != null)
		{
		long priceOfcar = car.getReservationAmount();
		
		long lenghtOfCurrentReservation = (ChronoUnit.DAYS.between(beginOfReservation,endOfReservation));
		long priceOfReservation = (priceOfcar*lenghtOfCurrentReservation);
		
		sucessReservationDTO = new SuccessReservationDTO(name,
								 email, 
								 address, 
								 tel, 
								 car.getType(), 
								 beginOfReservation, 
								 endOfReservation,
								 priceOfReservation
								 );
		
		Reservation reservation = new Reservation(name,email,tel,carId,beginOfReservation,endOfReservation);
		db.saveReservation(reservation);
		}
		
		return sucessReservationDTO;
	}

	public ReservationDTO getReservationDTO(int carId,
											LocalDate beginOfReservation, 
											LocalDate endOfReservation
											) 
	{
		
		ReservationDTO reservationDTO = new ReservationDTO(	carId,
															beginOfReservation, 
															endOfReservation
															);
		
		return reservationDTO;
	}

	


}
