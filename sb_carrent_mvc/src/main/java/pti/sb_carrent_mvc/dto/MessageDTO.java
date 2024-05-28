package pti.sb_carrent_mvc.dto;

public class MessageDTO {

	private boolean successfully;

	public MessageDTO(boolean successfully) {
		super();
		this.successfully = successfully;
	}

	public boolean isSuccessfully() {
		return successfully;
	}

	public void setSuccessfully(boolean successfully) {
		this.successfully = successfully;
	}
	
	
	
	
}
