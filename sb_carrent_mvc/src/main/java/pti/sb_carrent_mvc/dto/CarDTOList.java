package pti.sb_carrent_mvc.dto;

import java.util.ArrayList;
import java.util.List;

public class CarDTOList {

	private List<CarDTO> carList;
	
	
	
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


	@Override
	public String toString() {
		return "CarDTOList [carList=" + carList + "]";
	}

	
	
	
}
