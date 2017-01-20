package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Placeholder;

@Repository
public interface PlaceholderRepository extends JpaRepository<Placeholder, Integer> {

	@Query("select p from Placeholder p where p.contest.id = ?1")
	Collection<Placeholder> findAllByContest(int contestId);
	
}
