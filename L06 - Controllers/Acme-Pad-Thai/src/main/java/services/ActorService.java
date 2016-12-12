package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.MasterClass;

@Service
@Transactional
public class ActorService {

	
	//Constructor
	public ActorService(){
		super();
	}
	
	//Managed Repository
	
	@Autowired
	private ActorRepository actorRepository;

	
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
}
