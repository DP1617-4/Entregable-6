package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.SocialUser;
import domain.User;

@Repository
public interface SocialUserRepository extends JpaRepository<SocialUser, Integer> {
	

	@Query("select u from SocialUser u where u.userAccount.id = ?1")
	SocialUser findOneByUserAccountId(int userAccountId);
	
	@Query("select uf from SocialUser u where u.id=?1 join u.followed uf where uf.class= domain.User")
	Collection<User> findAllUserFollowed(int userId);
	
}
