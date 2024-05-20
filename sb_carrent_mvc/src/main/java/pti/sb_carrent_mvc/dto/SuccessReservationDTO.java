package pti.sb_carrent_mvc.dto;

import java.time.LocalDate;

public class SuccessReservationDTO {
	
	private String name;
	private String email;
	private String address;
	private String tel;
	private int reservationId;
	private String type;
	private LocalDate beginOfReservation;
	private LocalDate endOfReservation;
	private long priceOfReservation;
	
	
	public SuccessReservationDTO(String name,
								 String email, 
								 String address, 
								 String tel, 
								 String type,
								 LocalDate beginOfReservation, 
								 LocalDate endOfReservation,
								 long priceOfReservation
								 ) 
	{
		super();
		this.name = name;
		this.email = email;
		this.address = address;
		this.tel = tel;
		this.reservationId = 0;
		this.type = type;
		this.beginOfReservation = beginOfReservation;
		this.endOfReservation = endOfReservation;
		this.priceOfReservation = priceOfReservation;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public long getPriceOfReservation() {
		return priceOfReservation;
	}

	public void setPriceOfReservation(long priceOfReservation) {
		this.priceOfReservation = priceOfReservation;
	}


	@Override
	public String toString() {
		return "SuccessReservationDTO [name=" + name + ", email=" + email + ", address=" + address + ", tel=" + tel
				+ ", reservationId=" + reservationId + ", type=" + type + ", beginOfReservation=" + beginOfReservation
				+ ", endOfReservation=" + endOfReservation + ", priceOfReservation=" + priceOfReservation + "]";
	}



}
