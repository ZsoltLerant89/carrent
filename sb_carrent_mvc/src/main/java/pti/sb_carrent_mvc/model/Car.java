package pti.sb_carrent_mvc.model;

import org.springframework.util.Base64Utils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "cars")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "carid")
	private int carId;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "active" )
	private boolean active;
	
	@Column(name = "reservationamount")
	private int reservationAmount;

	@Lob
	@Column(name = "img")
	private byte[] img;

	public Car() {
		super();
	}


	public Car(	String type,
				boolean active,
				int reservationAmount
				) 
	{
		super();
		this.carId = 0;
		this.type = type;
		this.active = active;
		this.reservationAmount = reservationAmount;
		this.img = null;
	}


	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getReservationAmount() {
		return reservationAmount;
	}

	public void setReservationAmount(int reservationAmount) {
		this.reservationAmount = reservationAmount;
	}
	
	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

}
