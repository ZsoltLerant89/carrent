package pti.sb_carrent_mvc.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pti.sb_carrent_mvc.dto.CarDTOList;
import pti.sb_carrent_mvc.dto.SuccessReservationDTO;
import pti.sb_carrent_mvc.service.AppService;


@RestController
public class RestAppController {

	private AppService service;
	
	
	@Autowired
	public RestAppController(AppService service)
	{
		this.service = service;
	}
	
	
	@GetMapping("/rest/getfreecars")
	public CarDTOList getFreeCars (	@RequestParam("beginofreservation") LocalDate beginOfReservation,
									@RequestParam("endofreservation") LocalDate endOfReservation
									)
	{
		CarDTOList carDTOList = service.getCarDTOList(	beginOfReservation,
														endOfReservation
														);
		
		
		return carDTOList;
	}
	
	
	@PostMapping("/rest/setreservation")
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
