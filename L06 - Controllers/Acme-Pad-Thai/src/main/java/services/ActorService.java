package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Administrator;
import domain.Cook;
import domain.MasterClass;
import domain.Nutritionist;
import domain.Sponsor;
import domain.User;
import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class ActorService {
	
	//Managed Repository
	
	@Autowired
	private ActorRepository actorRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private NutritionistService nutritionistService;
	
	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private SponsorService sponsorService;
	
	@Autowired
	private CookService cookService;

	
	//Constructor
	public ActorService(){
		super();
	}
	
	//Auxiliary Services
	
	
	
	//CRUD
	
	public Actor findByUserAccount(UserAccount userAccount){
		Actor result;
		result = actorRepository.findByUserAccount(userAccount.getId());
		
		return result;
	}
	
	public Actor findByPrincipal(){
		Actor result;
		UserAccount userAccount;
		
		userAccount = LoginService.getPrincipal();
		result = findByUserAccount(userAccount);
		return result;
	}
	
	public Collection<Actor> findAll(){
		Collection<Actor> result;
				
		result = actorRepository.findAll();
		return result;
	}
	//Business Methods
	
	//Maybe in MasterClassService makes more sense, but it is mapped by "Actor" so...
	public void register (MasterClass masterClass){
		Actor actor;
		actor = findByPrincipal();
		actor.getEnroled().add(masterClass);
		actorRepository.save(actor);
	}
	
	public String findNamePrincipal(){
		
		String result="John Doe";
		
		Actor actor = this.findByPrincipal();
		result = actor.getName()+" "+actor.getSurname();
		
		return result;
	}
	
	public Actor save(Actor actor){
		Assert.notNull(actor);
		Actor result;
		if(actor instanceof User){
			result = userService.save((User) actor);
		}else{
			if(actor instanceof Nutritionist){
				result = nutritionistService.save((Nutritionist) actor);
			}else{

				if(actor instanceof Administrator){
					result = administratorService.save((Administrator) actor);
				}else{

					if(actor instanceof Sponsor){
						result = sponsorService.save((Sponsor) actor);
					}else{
						result = cookService.save((Cook) actor);
						
					}
				}
			}
		}
		return result;
	}
	
	public Collection<Actor> findAllByMasterClassId(int masterClassId){
		Collection<Actor> actors = actorRepository.findAllByMasterClassId(masterClassId);
		return actors;
	}
}
