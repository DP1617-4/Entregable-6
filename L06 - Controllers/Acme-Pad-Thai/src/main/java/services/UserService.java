package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.UserRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Comment;
import domain.Folder;
import domain.MasterClass;
import domain.Recipe;
import domain.Score;
import domain.SocialIdentity;
import domain.SocialUser;
import domain.User;

@Service
@Transactional
public class UserService {

	
	//managed repository-------------------
		@Autowired
		private UserRepository userRepository;
		
		
		
		//Basic CRUD methods-------------------
		
		public User create(){
			User created;
			created = new User();
			created.setRecipes( new ArrayList<Recipe>());
			created.setComments(new ArrayList<Comment>());
			created.setEnroled(new ArrayList<MasterClass>());
			created.setFolders(new ArrayList<Folder>());
			created.setFollowed(new ArrayList<SocialUser>());
			created.setFollowers(new ArrayList<SocialUser>());
		    created.setScores(new ArrayList<Score>());
		    created.setSocialIdentities(new ArrayList<SocialIdentity>());
			
			UserAccount userAccount = new UserAccount();
			Authority authority = new Authority();
			authority.setAuthority(Authority.USER);
			Collection<Authority> authorities = new ArrayList<Authority>();
			authorities.add(authority);
			userAccount.setAuthorities(authorities);
			
			created.setUserAccount(userAccount);
			
			return created;
		}
		
		public User findOne(int userId){
			
			User retrieved;
			retrieved = userRepository.findOne(userId);
			return retrieved;
		}

		public User save(User user){
			
			User saved;
			if(user.getUserAccount().getId()==0){
				// Creamos un codificador de hash para la password.
				Md5PasswordEncoder encoder = new Md5PasswordEncoder();
				// Convertimos la pass del usuario a hash.
				String pass = encoder.encodePassword(user.getUserAccount()
						.getPassword(), null);
				// Creamos una nueva cuenta y le pasamos los parametros.
				user.getUserAccount().setPassword(pass);
			}
			saved = userRepository.save(user);
			
			return saved;
			
		}
		
		public void delete(User user){
			
			userRepository.delete(user);
			
		}
		
		
		public User findByPrincipal(){
			
			User user = userRepository.findOneByUserAccountId(LoginService.getPrincipal().getId());
			return user;

		}

		public User findByUserAccountId(int userAccountId){
			
			User user = userRepository.findOneByUserAccountId(userAccountId);
			return user;

		}
		
		public Collection<User> findAll(){
			
			return userRepository.findAll();
		}
		
		public Collection<User> findAllFiltered(String filter){
			
			Collection<User> result = new ArrayList<User>();
			Collection<User> all = this.findAll();
			
			
			for(User u:all){
				
				if(u.getName().contains(filter)||u.getSurname().contains(filter)||u.getEmail().contains(filter)||u.getEnroled().contains(filter)
						||u.getPhone().contains(filter)){
					
					result.add(u);
				}
			}
			return result;
		}
		
		//Auxiliary methods

		//Our other bussiness methods
		
		public Double[] selectMinAvgMaxRecipesInUsers(){
			
			return userRepository.selectMinAvgMaxRecipesInUsers();
		}
		
		public User selectUserWithMostRecipes(){
			
			return userRepository.selectUserWithMostRecipes();
		}
		
		public Collection<User> selectAllUsersDescendingNumberOfFollowers(){
			
			return userRepository.selectAllUsersDescendingNumberOfFollowers();
		}
		
		public Collection<User> findAllUsersByRecipeLikesAndDislikes(){
			
			return userRepository.findAllUsersByRecipeLikesAndDislikes();
		}

		
}
