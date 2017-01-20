package forms;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;


public class AddPicture {
	
	public AddPicture(){
		super();
	}

	private String picture;
	private int id;
	
	@URL
	@NotBlank
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public int getId(){
		
		return id;
	}
	
	public void setId(int id){
		
		this.id=id;
	}
	

}