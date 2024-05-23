package pti.sb_carrent_mvc.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pti.sb_carrent_mvc.db.Database;
import pti.sb_carrent_mvc.dto.AdminDTO;
import pti.sb_carrent_mvc.dto.CarDTO;
import pti.sb_carrent_mvc.dto.CarDTOList;
import pti.sb_carrent_mvc.dto.ReservationDTO;
import pti.sb_carrent_mvc.dto.ReservationListDTO;
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

	public ReservationDTO getReservationDTO(int carId, LocalDate beginOfReservation, LocalDate endOfReservation) {
		
		ReservationDTO reservationDTO = new ReservationDTO(	carId,
															beginOfReservation, 
															endOfReservation
															);
		
		return reservationDTO;
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
			
			long lenghtOfCurrentFlight = (ChronoUnit.DAYS.between(beginOfReservation,endOfReservation));
			long priceOfReservation = (priceOfcar*lenghtOfCurrentFlight);
			
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

	public ReservationListDTO getReservations() {
		
		ReservationListDTO reservationListDTO = null;
		
		List<Reservation> reservationList = db.getReservations();
		if(reservationList.size() > 0 )
		{	
			reservationListDTO = new ReservationListDTO();
			
			for(int index = 0; index < reservationList.size(); index++)
			{
				Reservation currentReservation = reservationList.get(index);
				reservationListDTO.addReservationToReservationList(currentReservation);
			}
		}
		

		return reservationListDTO;
	}

	public CarDTOList getCars() {
		
		CarDTOList carListDTO = null;
		
		List<Car> carList = db.getCars();
		
		if(carList.size() > 0)
		{
			carListDTO = new CarDTOList();
			
			for(int index = 0; index < carList.size(); index++)
			{
				Car currentCar = carList.get(index);
				CarDTO currentCarDTO = new CarDTO(	currentCar.getCarId(),
													currentCar.getType(),
													currentCar.getReservationAmount(),
													currentCar.isActive()
													);
				carListDTO.addToCarList(currentCarDTO);
			}
		}
		
		
		return carListDTO;
	}
	
	public AdminDTO getAdminDTO()
	{
		
		AdminDTO adminDTO = null;
	
		List<Car> carList = db.getCars();
		List<Reservation> reservationList = db.getReservations();
		
		if((carList.size() > 0)  && (reservationList.size() > 0))
		{
			adminDTO = new AdminDTO();
			
			for(int index = 0; index < carList.size(); index++)
			{
				Car currentCar = carList.get(index);
				CarDTO currentCarDTO = new CarDTO(	currentCar.getCarId(),
													currentCar.getType(),
													currentCar.getReservationAmount(),
													currentCar.isActive()
													);
				adminDTO.addCarDTOToCarList(currentCarDTO);
			}
						
			for(int index = 0; index < reservationList.size(); index++)
			{
				Reservation currentReservation = reservationList.get(index);
				adminDTO.addReservationToReservationList(currentReservation);
			}
		}
	
		return adminDTO;
		
	}

	public void activateOrDeactivateCar(int carId) {
		
		Car car = db.getCarById(carId);
		
		if(car != null)
		{
			boolean isActive = car.isActive();
			
			if(isActive == true)
			{
				car.setActive(false);
			}
			else
			{
				car.setActive(true);
			}
			
			db.updateCar(car);
		}
	}

	public CarDTO updateCar(int carId, String type, String active, int reservationAmount) {
		CarDTO carDTO = null;
		
		Car car = db.getCarById(carId);
		
		
		if(car != null)
		{
			
			boolean isActive = false;
			
			if(active.equals("true"))
			{
				isActive = true;
			}
			
			car.setType(type);
			car.setActive(isActive);
			car.setReservationAmount(reservationAmount);
			
			carDTO = new CarDTO(car.getCarId(),car.getType(),car.getReservationAmount(),car.isActive());
			
			db.updateCar(car);
		}
		
		return carDTO;
	}

	public CarDTO getCarDTO(int carId) {
		CarDTO carDTO = null;
		
		Car car = db.getCarById(carId);
		
		if(car != null)
		{
			carDTO = new CarDTO(car.getCarId(),car.getType(),car.getReservationAmount(),car.isActive());
		}
		
		return carDTO;
	}
}
