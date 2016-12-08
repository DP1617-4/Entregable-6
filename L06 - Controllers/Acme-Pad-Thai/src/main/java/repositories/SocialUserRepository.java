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
	
	@Query("select u from SocialUser su, User u where su.id=14 and u member of su.followed")
	Collection<User> findAllUserFollowed(int userId);
	
}
