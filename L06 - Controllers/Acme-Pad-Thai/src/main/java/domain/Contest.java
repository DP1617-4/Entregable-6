package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Contest extends DomainEntity {
	
	//Constructor
	
	public Contest(){
		super();
	}
	
	
	// Attributes
	
	private String title;
	private Date openingTime;
	private Date closingTime;
	private boolean deleted;
	private boolean closed;
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	public Date getOpeningTime() {
		return openingTime;
	}
	public void setOpeningTime(Date openingTime) {
		this.openingTime = openingTime;
	}
	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	public Date getClosingTime() {
		return closingTime;
	}
	public void setClosingTime(Date closingTime) {
		this.closingTime = closingTime;
	}
	
	
	public boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	@Transient
	public boolean getClosed() {
		return closed;
	}
	public void setClosed(boolean closed) {
		this.closed = closed;
	}
	

	
	//Relationships
	
	


	private Collection<Recipe> qualified;
	private Collection<Recipe> winners;

	@Valid
	@OneToMany(mappedBy = "contest", cascade = CascadeType.ALL)
	public Collection<Recipe> getQualified() {
		return qualified;
	}
	public void setQualified(Collection<Recipe> qualified) {
		this.qualified = qualified;
	}
	
	@Valid
	@OneToMany(mappedBy = "wonContest")
	public Collection<Recipe> getWinners() {
		return winners;
	}
	public void setWinners(Collection<Recipe> winners) {
		this.winners = winners;
	}
	
	
	
	@PostLoad
	protected void initClosed(){
		boolean aux = false;
		Date cTime = new Date();
		if(cTime.after(closingTime))
			aux = true;
		this.closed = aux;
	}
}
