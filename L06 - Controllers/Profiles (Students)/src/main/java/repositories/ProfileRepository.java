/* ProfileRepository.java
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

	@Query("select p from Profile p where p.customer.id = ?1")
	Collection<Profile> findByCustomerId(int customerId);
	
}
