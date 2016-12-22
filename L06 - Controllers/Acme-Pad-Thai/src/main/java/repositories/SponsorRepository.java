package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Sponsor;

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Integer> {
	
	@Query("select min(s.campaigns.size), avg(s.campaigns.size), max(s.campaigns.size) from Sponsor s")
	Double[] calculateMinAvgMaxFromCampaignsOfSponsors();
	
	@Query("select count(c) from Campaign c where CURRENT_DATE between c.startDate and c.endDate group by c.sponsor")
	List<Long> calculateMinAvgMaxFromCampaignsOfSponsorsByDate();
	
	@Query("select s.companyName from Sponsor s group by s.companyName order by s.campaigns.size DESC")
	Collection<String> findCompaniesNameOfSponsors();
	
	@Query("select s.companyName from Sponsor s left join s.bills b group by s.companyName order by sum(b.cost) DESC")
	Collection<String> findCompaniesNameOfSponsorsByBills();
	
	@Query("select s from Sponsor s where (select count(c) from Campaign c where c.sponsor = s and datediff(current_date, c.endDate) < 90) = 0 and s.campaigns.size > 0")
	Collection<Sponsor> findInnactiveSponsorInThreeMonths();
	
	@Query("select distinct s.companyName from Sponsor s join s.bills b where b.paymentDate is not null and b.cost < (select avg(b.cost) from Bill b where b.paymentDate is not null) group by s.companyName")
	Collection<String> findCompaniesNameWhichSpentLessInCampaignsThanAvg();
	
	@Query("select distinct b.sponsor.companyName from Bill b where b.cost > (select max(b.cost)*0.9 from Bill b where b.paymentDate is not null)")
	Collection<String> findCompaniesNameSpent90Percent();
	
	@Query("select s from Sponsor s where s.userAccount.id = ?1")
	Sponsor findByPrincipal(int userAccountId);
}
