package services;


import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.NutritionistRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Comment;
import domain.Folder;
import domain.MasterClass;
import domain.Nutritionist;
import domain.Score;
import domain.SocialIdentity;
import domain.SocialUser;

@Service
@Transactional
public class NutritionistService {

	
	//managed repository-------------------
			@Autowired
			private NutritionistRepository nutritionistRepository;
			
			
			
			//Basic CRUD methods-------------------
			
			public Nutritionist create(){
				
				Nutritionist created = new Nutritionist();
				created.setComments(new ArrayList<Comment>());
				created.setEnroled(new ArrayList<MasterClass>());
				created.setFolders(new ArrayList<Folder>());
				created.setFollowed(new ArrayList<SocialUser>());
				created.setFollowers(new ArrayList<SocialUser>());
			    created.setScores(new ArrayList<Score>());
			    created.setSocialIdentities(new ArrayList<SocialIdentity>());
				
				UserAccount userAccount = new UserAccount();
				Authority authority = new Authority();
				authority.setAuthority(Authority.NUTRITIONIST);
				Collection<Authority> authorities = new ArrayList<Authority>();
				authorities.add(authority);
				userAccount.setAuthorities(authorities);
				
				created.setUserAccount(userAccount);
				return created;
			}
			
			public Nutritionist findOne(int nutritionistId){
				
				Nutritionist retrieved;
				retrieved = nutritionistRepository.findOne(nutritionistId);
				return retrieved;
			}
			
			public Nutritionist findByPrincipal(){
				
			    UserAccount userAccount = LoginService.getPrincipal();
			    Nutritionist nutritionist;
				nutritionist = nutritionistRepository.findOneByUserAccountId(userAccount.getId());
				return nutritionist;
			}

			public Nutritionist save(Nutritionist nutritionist){
				
				Nutritionist saved;
				if(nutritionist.getUserAccount().getId()==0){
					// Creamos un codificador de hash para la password.
					Md5PasswordEncoder encoder = new Md5PasswordEncoder();
					
					
					// Convertimos la pass del usuario a hash.
					String pass = encoder.encodePassword(nutritionist.getUserAccount()
							.getPassword(), null);
					// Creamos una nueva cuenta y le pasamos los parametros.
					UserAccount userAccount = nutritionist.getUserAccount();
					nutritionist.setUserAccount(userAccount);
					nutritionist.getUserAccount().setPassword(pass);
				}
		
				saved = nutritionistRepository.save(nutritionist);
				
				return saved;
				
				
			}
			
			public void delete(Nutritionist nutritionist){
				
				nutritionistRepository.delete(nutritionist);
				
			}
			
			public Collection<Nutritionist> findAll(){
				
				return nutritionistRepository.findAll();
			}

			public Collection<Nutritionist> findAllFiltered(String filter){
			
			Collection<Nutritionist> result = new ArrayList<Nutritionist>();
			Collection<Nutritionist> all = this.findAll();
			
			
			for(Nutritionist u:all){
				
				if(u.getName().contains(filter)||u.getSurname().contains(filter)||u.getEmail().contains(filter)||u.getEnroled().contains(filter)
						||u.getPhone().contains(filter)){
					
					result.add(u);
				}
			}
			return result;
		}
			
			//Auxiliary methods

			//Our other bussiness methods
			
			
			
}
