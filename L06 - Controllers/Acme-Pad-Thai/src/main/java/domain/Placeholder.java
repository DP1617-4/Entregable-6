package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Placeholder extends DomainEntity {

	//Constructor
	
	public Placeholder(){
		super();
	}
	
	//Attributes
	
	private String unica;
	private String normal;
	private Double quantity;
	private Integer numero;
	private Collection<String> cadenas;
	private Date startMoment;
	
	@Column(unique = true)
	@NotBlank
	public String getUnica() {
		return unica;
	}
	public void setUnica(String unica) {
		this.unica = unica;
	}
	
	@NotBlank
	public String getNormal() {
		return normal;
	}
	public void setNormal(String normal) {
		this.normal = normal;
	}
	@Min(0)
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	@Range(min=0, max=99)
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	@NotEmpty
	@NotNull
	@ElementCollection
	public Collection<String> getCadenas() {
		return cadenas;
	}
	public void setCadenas(Collection<String> cadenas) {
		this.cadenas = cadenas;
	}
	
	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	public Date getStartMoment() {
		return startMoment;
	}
	public void setStartMoment(Date startMoment) {
		this.startMoment = startMoment;
	}
	
	//Relationships
	
	private Collection<User> winners;
	private Contest contest;
	private Recipe recipe;

	
	@NotNull
	@Valid
	@OneToMany(mappedBy = "placeholder")
	public Collection<User> getWinners() {
		return winners;
	}
	public void setWinners(Collection<User> winners) {
		this.winners = winners;
	}
	@ManyToOne()
	@Valid
	public Contest getContest() {
		return contest;
	}
	public void setContest(Contest contest) {
		this.contest = contest;
	}
	
	@OneToOne()
	public Recipe getRecipe() {
		return recipe;
	}
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	
	
	
	
	
	
	
}
