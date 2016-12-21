package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Value extends DomainEntity {
	
	//Constructor
	
	public Value(){
		super();
	}
	
	//Attributes
	
	private double valuen;

	@Min(0)
	@NotNull
	public double getValuen() {
		return valuen;
	}

	public void setValuen(double valuen) {
		this.valuen = valuen;
	}
	
	//Relationships
	
	private Ingredient ingredient;
	private Property property;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}
	
	
	
	

}
