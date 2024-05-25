package pti.sb_carrent_rest.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pti.sb_carrent_rest.dto.CarDTOList;
import pti.sb_carrent_rest.dto.ReservationDTO;
import pti.sb_carrent_rest.dto.SuccessReservationDTO;
import pti.sb_carrent_rest.service.AppService;

@RestController
public class AppController {

	private AppService service;
	
	@Autowired
	public AppController(AppService service)
	{
		this.service = service;
	}
	
	@GetMapping("/getfreecars")
	public CarDTOList getFreeCars (	@RequestParam("beginofreservation") LocalDate beginOfReservation,
									@RequestParam("endofreservation") LocalDate endOfReservation
									)
	{
		CarDTOList carDTOList = service.getFreeCars(beginOfReservation,endOfReservation);
		
		return carDTOList;
	}
	
	@GetMapping("/reservation")
	public ReservationDTO getReservations (	@RequestParam("carid") int carId,
											@RequestParam("beginofreservation") LocalDate beginOfReservation,
											@RequestParam("endofreservation") LocalDate endOfReservation
											)
	{
		ReservationDTO reservationDTO = service.getReservationDTO(carId,beginOfReservation,endOfReservation);
		
		return reservationDTO;
	}
	
	@GetMapping("/setreservation")
	public SuccessReservationDTO successReservationDTO(	@RequestParam("carid") int carId,
														@RequestParam("name") String name,
														@RequestParam("email") String email,
														@RequestParam("address") String address,
														@RequestParam("tel") String tel,
														@RequestParam("beginofreservation") LocalDate beginOfReservation,
														@RequestParam("endofreservation") LocalDate endOfReservation
														)
	{
		SuccessReservationDTO successReservationDTO = service.setReservation(carId,
																			 name, 
																			 email, 
																			 address, 
																			 tel, 
																			 beginOfReservation, 
																			 endOfReservation
																			 );	
		return successReservationDTO;
	}
}
