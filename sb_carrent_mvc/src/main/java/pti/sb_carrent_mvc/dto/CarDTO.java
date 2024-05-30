package pti.sb_carrent_mvc.dto;

import java.util.Base64;

import org.springframework.util.Base64Utils;

public class CarDTO {
	
	private int carid;
	private String type;
	private int fee;
	private boolean active;
	private byte[] img;
	
	public CarDTO(	int carid,
					String type,
					int fee,
					boolean active,
					byte[] img
					) 
	{
		super();
		this.carid = carid;
		this.type = type;
		this.fee = fee;
		this.active = active;
		this.img = img;
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
	
	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}


	public String getBase64String()
	{
		String base64String = Base64.getEncoder().encodeToString(img);
		
		return base64String;
	}

	
}
