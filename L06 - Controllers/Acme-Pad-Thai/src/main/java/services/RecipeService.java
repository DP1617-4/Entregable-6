package services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Comment;
import domain.Contest;
import domain.Quantity;
import domain.Recipe;
import domain.Score;
import domain.SocialUser;
import domain.Step;
import domain.User;

import repositories.RecipeRepository;
import security.LoginService;
import security.UserAccount;



@Service
@Transactional
public class RecipeService {
	
	//managed repository-------------------
	@Autowired
	private RecipeRepository recipeRepository;
	
	//supporting services-------------------
	@Autowired
	private StepService stepService;
	@Autowired
	private QuantityService quantityService;
	@Autowired
	private UserService userService;
	@Autowired
	private SocialUserService socialUserService;
	
	//Basic CRUD methods-------------------
	
	public Recipe create(){
		
		Recipe created;
		Date moment = new Date(System.currentTimeMillis()-100);
		User principal = userService.findByPrincipal();
		created = new Recipe();
		created.setAuthored(moment);
		created.setDeleted(false);
		created.setUser(principal);
		String ticker = this.createTicker();
		created.setTicker(ticker);
		created.setUpdated(moment);
		
		return created;
	}
	
	public Recipe findOne(int recipeId){
		
		Recipe retrieved;
		retrieved = recipeRepository.findOne(recipeId);
		return retrieved;
	}
	
	public Collection<Recipe> findAllByUser(User u){
		
		return recipeRepository.findAllByUserId(u.getId());
	}
	
	public Collection<Recipe> findAllByUserFollowed(SocialUser socialUser){
		
		User principal = userService.findByPrincipal(); 
		Collection<Recipe> result = new ArrayList<Recipe>();
		Collection<User> followed = socialUserService.findAllFollowed(principal);
		
		for(User u : followed){
			
			result.addAll(this.findAllByUser(u));
		}
		
		return result;
	}

	public Recipe save(Recipe recipe){
		
		Recipe saved, toSave;
		toSave = recipe;
		Date moment = new Date(System.currentTimeMillis()-100);
		toSave.setUpdated(moment);
		saved = recipeRepository.save(recipe);
		return saved;
		
	}
	
	public void delete(Recipe recipe){
		
		recipeRepository.delete(recipe);
		
	}
	
	public Collection<Recipe> findAll(){
		
		return recipeRepository.findAll();
	}
	
	public Collection<Recipe> findAllFiltered(String filter){
				
		Collection<Recipe> result = new ArrayList<Recipe>();
		Collection<Recipe> all = this.findAllNotDeleted();
						
		for(Recipe r:all){	
			if(r.getTicker().contains(filter)||r.getTitle().contains(filter)||r.getSummary().contains(filter)){
				result.add(r);
			}
		}
		return result;
	}
	
	public Collection<Recipe> findAllQualified(int contestId){
		
		return recipeRepository.findAllQualified(contestId);
	}
	
	public Collection<Recipe> findAllWinners(int contestId){
		
		return recipeRepository.findAllWinners(contestId);
	}
	
	//Auxiliary methods
	public char randomLetter(){
		char result;
		String alphabet= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random random = new Random();
        result = alphabet.charAt(random.nextInt(52));
		return result;
	}
	
//	public Collection<Step> copyStepts(Recipe recipe){
//		Collection <Step> stepsToCopy = recipe.getSteps();
//		Collection <Step> result;
//		for(Step s : stepsToCopy){
//			
//			Step stepCreated = stepService.create();
//			stepCreated.setDescription(s.getDescription());
//			stepCreated.setHints(s.getHints());
//			stepCreated.setPictures(s.getPictures());
//			stepCreated.setStepNumber(s.getStepNumber());
//			stepCreated.setRecipe(s.getRecipe());
//		}
//	}
	
	//Our other bussiness methods
	
	public String createTicker(){
		
		String result;
		String datePattern = "yyMMdd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
		String moment = simpleDateFormat.format(new Date());
		String code = "";
		code += "-" + this.randomLetter() + this.randomLetter() + this.randomLetter() + this.randomLetter();
		result= moment + code;
		return result;
	}
	
	public Collection<Double> getAvgStdStepsPerRecipe(){
		
		Collection<Double> result = new ArrayList<Double>();
		Double[] aux = recipeRepository.getAvgStdStepsPerRecipe();
		for(int i=0;i<aux.length;i++){
			
			result.add(aux[i]);
		}
		return result;
	}
	
	public Collection<Double> getAvgStdIngredientsPerRecipe(){
		
		Collection<Double> result = new ArrayList<Double>();
		Double[] aux = recipeRepository.getAvgStdIngredientsPerRecipe();
		for(int i=0;i<aux.length;i++){
			
			result.add(aux[i]);
		}
		return result;
	}
	
	public Collection<User> getUsersByAvgOfLikesAndDislikesOfRecipe(){
		
		return getUsersByAvgOfLikesAndDislikesOfRecipe();
	}
	
	
	//Este metodo hay que testearlo much�simo. Es bastante grande y parte core del funcionamiento de la aplicaci�n.
	public Recipe createCopyForContest(Recipe recipe){
		
		String ticker = this.createTicker();
		Recipe copy = new Recipe();
		
		copy.setUser(recipe.getUser());
		copy.setAuthored(recipe.getAuthored());
		copy.setCategories(recipe.getCategories());
		copy.setHints(recipe.getHints());
		copy.setPictures(recipe.getPictures());
		copy.setQuantities(new ArrayList<Quantity>());
		copy.setSummary(recipe.getSummary());
		copy.setSteps(new ArrayList<Step>());
		copy.setTicker(ticker);
		copy.setTitle(recipe.getTitle());
		copy.setComments(new ArrayList<Comment>());
		copy.setDeleted(recipe.getDeleted());
		copy.setScores(new ArrayList<Score>());
		
		Recipe copySavedNoSteps = this.save(copy);
		
		Collection<Step> stepsForCopy = new ArrayList<Step>();
		for(Step s : recipe.getSteps()){
			
			Step newStep = stepService.createCopy(s);
			newStep.setRecipe(copySavedNoSteps);
			Step savedNewStep = stepService.save(newStep);
			stepsForCopy.add(savedNewStep);
		}
		
		Collection<Quantity> quantitiesForCopy = new ArrayList<Quantity>();
		for(Quantity q : recipe.getQuantities()){
			
			Quantity newQuantity = quantityService.createCopy(q);
			newQuantity.setRecipe(copySavedNoSteps);
			Quantity savedNewQuantity = quantityService.save(newQuantity);
			quantitiesForCopy.add(savedNewQuantity);
		}
		
		copySavedNoSteps.setSteps(stepsForCopy);
		copySavedNoSteps.setQuantities(quantitiesForCopy);
		Recipe copySavedSteps = this.save(copySavedNoSteps);
		
		return copySavedSteps;
	}
	
	public Recipe delete2(Recipe recipe){
		Assert.isTrue(this.checkPrincipal(recipe));
		recipe.setDeleted(true);
		Recipe saved = this.save(recipe);
		return saved;
	}
	
	public Recipe restore(Recipe recipe){
		
		recipe.setDeleted(false);
		Recipe saved = this.save(recipe);
		return saved;
	}
	
	public Boolean checkPrincipal(Recipe recipe){
		
		Boolean result = false;
		UserAccount recipeUser = recipe.getUser().getUserAccount();
		UserAccount principal = LoginService.getPrincipal();
		if(recipeUser.equals(principal)){
			
			result = true;
		}
		
		return result;
		
	}
	
	public Collection<Recipe> findAllNotDeleted(){
		
		Collection<Recipe> notDeleted = recipeRepository.findAllGrouped();
		
		
		return notDeleted;
	}
	
	public Recipe qualifyForContest(Contest contest, Recipe recipe){
		
		Recipe copy = this.createCopyForContest(recipe);
		copy.setContest(contest);
		Recipe saved = this.save(copy);
		return saved;
	}
	
	public Recipe winContest(Contest contest, Recipe recipe){
		
		recipe.setWonContest(contest);
		Recipe saved = this.save(recipe);
		return saved;
	}
	
	
}
