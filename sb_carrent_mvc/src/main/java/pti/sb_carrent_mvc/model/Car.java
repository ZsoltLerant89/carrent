package pti.sb_carrent_mvc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	
	public Car(String type, boolean active, int reservationAmount) {
		super();
		this.carId = 0;
		this.type = type;
		this.active = active;
		this.reservationAmount = reservationAmount;
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
	
	
	
	
	
	
	
	
}
