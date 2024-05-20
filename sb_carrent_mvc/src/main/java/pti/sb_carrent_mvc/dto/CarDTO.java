package pti.sb_carrent_mvc.dto;

public class CarDTO {
	
	private int carid;
	private String type;
	private int fee;
	
	
	public CarDTO(int carid, String type, int fee) {
		super();
		this.carid = carid;
		this.type = type;
		this.fee = fee;
	}


	public int getCarid() {
		return carid;
	}

	public void setCarid(int carid) {
		this.carid = carid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}


	@Override
	public String toString() {
		return "CarDTO [carid=" + carid + ", type=" + type + ", fee=" + fee + "]";
	}

	
	

}
