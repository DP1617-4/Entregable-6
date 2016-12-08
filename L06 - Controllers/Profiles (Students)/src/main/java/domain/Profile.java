/* Profile.java
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

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Profile extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Profile() {
		super();
	}

	// Attributes -------------------------------------------------------------
	
	private String name;
	private String email;
	private String picture;

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank
	@Email
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@NotBlank
	@URL
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	// Relationships ----------------------------------------------------------

	private Customer customer;
	private Collection<Customer> likers;

	@NotNull
	@Valid
	@OneToOne(mappedBy="profile", optional=false)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@NotNull
	@Valid
	@ManyToMany(mappedBy="likes")
	public Collection<Customer> getLikers() {
		return likers;
	}

	public void setLikers(Collection<Customer> likers) {
		this.likers = likers;
	}

}
