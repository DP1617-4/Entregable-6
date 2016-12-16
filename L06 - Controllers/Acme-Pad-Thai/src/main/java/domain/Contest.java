package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
	@NotNull
	public boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
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
}
