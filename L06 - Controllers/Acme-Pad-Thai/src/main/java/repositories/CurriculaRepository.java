package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Curriculum;

@Repository
public interface CurriculaRepository extends JpaRepository<Curriculum, Integer> {

}
