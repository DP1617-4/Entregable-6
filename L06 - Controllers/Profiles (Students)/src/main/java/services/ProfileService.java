/* ProfileService.java
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ProfileRepository;
import domain.Customer;
import domain.Profile;

@Service
@Transactional
public class ProfileService {

	// Managed repository -------------------------------------------------

	@Autowired
	private ProfileRepository profileRepository;
	
	// Supporting services ------------------------------------------------
	
	@Autowired
	private CustomerService customerService;
		
	// Constructors -------------------------------------------------------
	
	public ProfileService() {
		super();
	}

	// Simple CRUD methods ------------------------------------------------
	
	public Profile findOne(int ProfileId) {
		Profile result;
		
		result = profileRepository.findOne(ProfileId);
		
		return result;
	}
	
	public Profile findByPrincipal() {
		Profile result;
		
		Customer customer;
		
		customer = customerService.findByPrincipal();
		result = customer.getProfile();
		
		return result;
	}

	public Collection<Profile> findAll() {
		Collection<Profile> result;
		
		result = profileRepository.findAll();
		
		return result;
	}

	public void save(Profile Profile) {
		profileRepository.save(Profile);		
	}
			
	// Other business methods ---------------------------------------------
	
	public void addLiker(int profileId) {
		Profile profile;
		Customer customer;
		
		profile = profileRepository.findOne(profileId);		
		Assert.notNull(profile);
		customer = customerService.findByPrincipal();
		customer.getLikes().add(profile);
		profile.getLikers().add(customer);
		
		profileRepository.save(profile);
		customerService.save(customer);
	}
	
	public void removeLiker(int profileId) {
		Profile profile;
		Customer customer;
		
		profile = profileRepository.findOne(profileId);		
		Assert.notNull(profile);
		customer = customerService.findByPrincipal();
		customer.getLikes().remove(profile);
		profile.getLikers().remove(customer);
		
		profileRepository.save(profile);
		customerService.save(customer);		
	}

}
