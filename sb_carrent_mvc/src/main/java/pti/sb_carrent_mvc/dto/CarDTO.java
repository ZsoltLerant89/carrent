package pti.sb_carrent_mvc.dto;

public class CarDTO {
	
	private int carid;
	private String type;
	private int fee;
	private boolean active;
	
	public CarDTO(int carid, String type, int fee, boolean active) {
		super();
		this.carid = carid;
		this.type = type;
		this.fee = fee;
		this.active = active;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String isActiveString()
	{
		String result = "";
		
		if(this.isActive() == true)
		{
			result = "active";
		}
		else
		{
			result ="inactive";
		}
		
		return result;
	}

	@Override
	public String toString() {
		return "CarDTO [carid=" + carid + ", type=" + type + ", fee=" + fee + ", active=" + active + "]";
	}


	

	
	

}
