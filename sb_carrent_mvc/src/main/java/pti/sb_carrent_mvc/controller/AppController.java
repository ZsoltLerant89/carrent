package pti.sb_carrent_mvc.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pti.sb_carrent_mvc.dto.AdminDTO;
import pti.sb_carrent_mvc.dto.CarDTO;
import pti.sb_carrent_mvc.dto.CarDTOList;
import pti.sb_carrent_mvc.dto.ReservationDTO;
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
		
		AdminDTO adminDTO = service.getAdminDTO();
		model.addAttribute("adminDTO", adminDTO);

		
		return "admin.html";
	}
	
	@PostMapping("/car/activate")
	public String activOrDeactivCar(Model model,
									@RequestParam("carid") int carId
									)
	{
		
		service.activateOrDeactivateCar(carId);
		
		AdminDTO adminDTO = service.getAdminDTO();
		model.addAttribute("adminDTO", adminDTO);
		
		return "admin.html";
	}
	
	@PostMapping("/car/edit")
	public String editCarPage(Model model,
						 @RequestParam("carid") int carId
						 )
	{
		CarDTO carDTO = service.getCarDTO(carId);
		model.addAttribute("carDTO", carDTO);
		
		return "editcar.html";
	}
	
	@GetMapping("/car/edited")
	public String editCar(	Model model,
							@RequestParam("carid") int carId,
							@RequestParam("type") String type,
							@RequestParam("active") String active,
							@RequestParam("reservationamount") int reservationAmount				
							)
	{
		
		CarDTO carDTO = service.updateCar(carId,type,active,reservationAmount);
		model.addAttribute("carDTO", carDTO);
		
		model.addAttribute("text", "Successfully edited the car!");
		
		return "editcar.html";
	}
	
	@PostMapping("/car/delete")
	public String DeleteCar(Model model,
							@RequestParam("carid") int carId)
	{
		service.deleteCar(carId);
		
		AdminDTO adminDTO = service.getAdminDTO();
		model.addAttribute("adminDTO", adminDTO);
		
		model.addAttribute("text", "Successfully deleted the car!");
		
		return "admin.html";
	}
	
	@PostMapping("/admin/addnewcarpage")
	public String loadAddNewCarPage()
	{
		return "addnewcar.html";
	}
	
	@GetMapping("/addnewcarpage/addnewcar")
	public String addNewCar(Model model,
							@RequestParam("type") String type,
							@RequestParam("active") String active,
							@RequestParam("reservationamount") int reservationAmount				
							)
	{
		
		CarDTO carDTO = service.addNewCar(type,active,reservationAmount);
		model.addAttribute("carDTO", carDTO);
		
		return "addnewcar.html";
	}
		
}
