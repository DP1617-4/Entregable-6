package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Campaign;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer> {
	
	@Query("select c from Campaign c where c.sponsor.id = ?1 and c.deleted = FALSE")
	Collection<Campaign> findAllBySponsorId(int sponsorId);
	
}
