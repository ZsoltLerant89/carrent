package pti.sb_carrent_mvc.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pti.sb_carrent_mvc.dto.CarDTO;
import pti.sb_carrent_mvc.dto.CarDTOList;
import pti.sb_carrent_mvc.dto.ReservationDTO;
import pti.sb_carrent_mvc.dto.ReservationListDTO;
import pti.sb_carrent_mvc.dto.SuccessReservationDTO;
import pti.sb_carrent_mvc.service.AppService;

@Controller
public class AppController {

	private AppService service;

	@Autowired
	public AppController(AppService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/")
	private String indexPage()
	{
		return "index.html";
	}
	
	@GetMapping("/car/search")
	public String search(Model model,
						@RequestParam("beginofreservation") LocalDate beginOfReservation,
						@RequestParam("endofreservation") LocalDate endOfReservation
						)
	{
		
		CarDTOList carDTOList = service.getCarDTOList(beginOfReservation, endOfReservation);
		model.addAttribute("carDTOList", carDTOList);
		
		
		return "index.html";
	}
	
	@PostMapping("/car/reservation")
	public String reservation(Model model,
							 @RequestParam("carid") int carId,
							 @RequestParam("beginofreservation") LocalDate beginOfReservation,
							 @RequestParam("endofreservation") LocalDate endOfReservation
							 )
	{	
		ReservationDTO reservationDTO = service.getReservationDTO(	carId,
																	beginOfReservation,
																	endOfReservation
																	);
		model.addAttribute("reservationDTO", reservationDTO);
		
		return "reservation.html";
	}
	
	@PostMapping("/car/reservation/submit")
	public String submitReservation(Model model,
									@RequestParam("carid") int carId,
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
		model.addAttribute("successReservationDTO", successReservationDTO);
		
		return "successreservation.html";
	}
	
	@GetMapping("/admin")
	public String loadAdmin(Model model)
	{
		ReservationListDTO reservationListDTO = service.getReservations();
		model.addAttribute("reservationListDTO" , reservationListDTO);
		
		CarDTOList carListDTO = service.getCars();
		model.addAttribute("carListDTO", carListDTO);
		
		return "admin.html";
	}
		
}