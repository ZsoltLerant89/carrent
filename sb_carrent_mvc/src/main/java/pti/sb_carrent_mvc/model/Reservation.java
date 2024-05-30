package pti.sb_carrent_mvc.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservations")
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservationid")
	private int reservationId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "tel")
	private String tel;
	
	@Column(name = "carid")
	private int carId;
	
	@Column(name = "beginofreservation")
	private LocalDate beginOfReservation;
	
	@Column(name = "endofreservation")
	private LocalDate endOfReservation;

	
	public Reservation() {
		super();
	}


	public Reservation(	String name,
						String email, 
						String tel, 
						int carId, 
						LocalDate beginOfReservation,
						LocalDate endOfReservation
						) 
	{
		super();
		this.reservationId = 0;
		this.name = name;
		this.email = email;
		this.tel = tel;
		this.carId = carId;
		this.beginOfReservation = beginOfReservation;
		this.endOfReservation = endOfReservation;
	}


	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}


	public LocalDate getBeginOfReservation() {
		return beginOfReservation;
	}


	public void setBeginOfReservation(LocalDate beginOfReservation) {
		this.beginOfReservation = beginOfReservation;
	}


	public LocalDate getEndOfReservation() {
		return endOfReservation;
	}

	public void setEndOfReservation(LocalDate endOfReservation) {
		this.endOfReservation = endOfReservation;
	}

}
