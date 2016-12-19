package services;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.SocialUserRepository;
import security.LoginService;
import security.UserAccount;
import domain.Recipe;
import domain.Score;
import domain.SocialUser;
import domain.User;

@Service
@Transactional
public class SocialUserService {

	//managed repository-------------------
			@Autowired
			private SocialUserRepository socialUserRepository;
			
			@Autowired
			private ScoreService scoreService;
			
			//Basic CRUD methods-------------------
			
			public SocialUser findOne(int socialUserId){
				
				SocialUser retrieved;
				retrieved = socialUserRepository.findOne(socialUserId);
				return retrieved;
			}

			public SocialUser save(SocialUser user){
				
				SocialUser saved;
				saved = socialUserRepository.save(user);
				
				return saved;
				
			}
			
			public void delete(SocialUser user){
				
				socialUserRepository.delete(user);
				
			}
			
			public Collection<User> findAllFollowed(SocialUser u){
				
				
				return socialUserRepository.findAllUserFollowed(u.getId());
			}
			
			public SocialUser findByUserAccount(UserAccount userAccount){
				SocialUser result;
				result = socialUserRepository.findOneByUserAccountId(userAccount.getId());
				
				return result;
			}
			
			public SocialUser findByPrincipal(){
				SocialUser result;
				UserAccount userAccount;
				
				userAccount = LoginService.getPrincipal();
				result = findByUserAccount(userAccount);
				return result;
			}
			
			public Collection<SocialUser> findAll(){
				return socialUserRepository.findAll();
			}
 			
			//Auxiliary methods

			//Our other bussiness methods
			
			public void follow(SocialUser followed){

				SocialUser socialUser = findByPrincipal();
				socialUser.getFollowed().add(followed);
				save(socialUser);
			}
			
			public void unfollow(SocialUser unfollowed){

				SocialUser socialUser = findByPrincipal();
				socialUser.getFollowed().remove(unfollowed);
				save(socialUser);
			}
			
//			public void comment(Comment comment){
//				SocialUser socialUser;
//				socialUser = findByPrincipal();
//				Collection<Comment> comments = socialUser.getComments();
//				comments.add(comment);
//				socialUser.setComments(comments);
//			}
			
			public Score like(Recipe recipe){
				
				Score score = scoreService.create(findByPrincipal(), recipe);
				score.setLikes(true);
				scoreService.save(score);
				
				
				return score;
			}
			
			public Score dislike(Recipe recipe){
				
				Score score = scoreService.create(findByPrincipal(), recipe);
				score.setLikes(false);
				scoreService.save(score);
				
				
				return score;
			}
}
