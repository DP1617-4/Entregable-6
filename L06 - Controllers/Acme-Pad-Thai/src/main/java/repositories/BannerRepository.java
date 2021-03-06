package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Banner;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer> {

	@Query("select ban from Sponsor s join s.campaigns c join c.banners ban where s.id = ?1 and ban.timesShownMonth > 0")
	Collection<Banner> numberOfBanners(int id);

	@Query("select b from Banner b where (b.campaign.starred = true) and (CURRENT_TIMESTAMP between b.campaign.startDate and b.campaign.endDate) and ((b.timesShownMonth + b.timesShown) < b.maxNumber) order by rand()")
	Collection<Banner> findRandomStarBanner();
	
	@Query("select b from Banner b where (CURRENT_TIMESTAMP between b.campaign.startDate and b.campaign.endDate) and ((b.timesShownMonth + b.timesShown) < b.maxNumber) order by rand()")
	Collection<Banner> findRandomBanner();

	@Query("select b from Banner b where b.campaign.id = ?1")
	Collection<Banner> findAllByCampaign(int id);
}
