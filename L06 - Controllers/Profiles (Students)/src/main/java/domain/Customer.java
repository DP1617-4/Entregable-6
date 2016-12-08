/* Customer.java
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Customer extends Actor {

	// Constructors -----------------------------------------------------------

	public Customer() {
		super();
	}

	// Attributes -------------------------------------------------------------

	// Relationships ----------------------------------------------------------

	private Profile profile;
	private Collection<Profile> likes;

	@NotNull
	@Valid
	@OneToOne(optional=false)
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@NotNull
	@Valid
	@ManyToMany()
	public Collection<Profile> getLikes() {
		return likes;
	}

	public void setLikes(Collection<Profile> likes) {
		this.likes = likes;
	}	
		
}
