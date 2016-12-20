package services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CurriculumRepository;
import security.LoginService;
import security.UserAccount;
import domain.Curriculum;
import domain.Endorser;
import domain.Nutritionist;

@Service
@Transactional
public class CurriculumService {

	
	//managed repository-------------------
	@Autowired
	private CurriculumRepository curriculumRepository;
	
	//supporting services-------------------
	@Autowired
	private NutritionistService nutritionistService;
	
	//Basic CRUD methods-------------------
	
	public Curriculum create(){
		
		Curriculum created;
		created = new Curriculum();
		created.setDeleted(false);
		created.setNutritionist(nutritionistService.findByPrincipal());	
		created.setEndorsers(new ArrayList<Endorser>());
		return created;
	}
	
	public Curriculum findOne(int curriculumId){
		
		Curriculum retrieved;
		retrieved = curriculumRepository.findOne(curriculumId);
		Assert.isTrue(checkPrincipal(retrieved));
		return retrieved;
	}

	public Curriculum save(Curriculum curriculum){
		
		Assert.notNull(curriculum);
		Curriculum saved = curriculumRepository.save(curriculum);
		nutritionistService.findByPrincipal().setCurriculum(saved);
		Assert.isTrue(checkPrincipal(curriculum));
		
		
		return saved;
		
	}
	
	public void delete(Curriculum curriculum){
		
		Assert.isTrue(checkPrincipal(curriculum));
		curriculumRepository.delete(curriculum);
		
	}
	
	public Curriculum findByPrincipal(){
		
		Nutritionist nutritionist;
		nutritionist = nutritionistService.findByPrincipal();
		Curriculum curriculum;
		curriculum = nutritionist.getCurriculum();
		
		return curriculum;
	}
	
	//Auxiliary methods
	
	public Boolean checkPrincipal(Curriculum c){
		
//		Nutritionist nutritionist = nutritionistService.findByPrincipal();
//		Assert.isTrue(c.equals(nutritionist.getCurricula()));
		
//		Boolean result = false;
//		UserAccount recipeUser = recipe.getUser().getUserAccount();
//		UserAccount principal = LoginService.getPrincipal();
//		if(recipeUser.equals(principal)){
//			
//			result = true;
//		}
//		
//		return result;
		
		Boolean result = false;
		UserAccount nutritionistUser = c.getNutritionist().getUserAccount();
		UserAccount principal = LoginService.getPrincipal();
		if(nutritionistUser.equals(principal)){
			result = true;
		}
		return result;
	}

	//Our other bussiness methods
	
	
}
