package pti.sb_carrent_mvc.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pti.sb_carrent_mvc.dto.CarDTOList;
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
	
	@GetMapping("/search")
	public String search(Model model,
						@RequestParam("beginofreservation") LocalDate beginOfReservation,
						@RequestParam("endofreservation") LocalDate endOfReservation
						)
	{
		
		CarDTOList carDTOList = service.getCarDTOList(beginOfReservation, endOfReservation);
		model.addAttribute("carDTOList", carDTOList);
		
		
		return "index.html";
	}
	
}
