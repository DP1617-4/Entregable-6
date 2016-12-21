package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ContestService;
import services.RecipeService;
import domain.Contest;
import domain.Recipe;
import domain.Score;
import forms.AddRecipe;

@Controller
@RequestMapping("/contest")
public class ContestController extends AbstractController {
	
	// Services ---------------------------------------------------------------

	@Autowired
	private ContestService contestService;
	@Autowired
	private RecipeService recipeService;
	
	// Constructors -----------------------------------------------------------
	
	public ContestController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
 	public ModelAndView list() {
 		
 		ModelAndView result;
 		Collection<Contest> contests;
 
 		contests = contestService.findAllNotDeleted();
 		AddRecipe addRecipe = new AddRecipe();
 		Collection<Recipe> recipes = recipeService.findAllNotQualifiedPrincipal();
 		int puntuacion = 0;
 		for(Recipe r : recipes){
 			for(Score s : r.getScores()){
 				if(puntuacion < 5){
	 				if(s.getLikes()==true){
	 					puntuacion++;
	 				} else {
	 					recipes.remove(r);
	 				}
 				} else {
 					break;
 				}
 			}
 		}
 		
		result = new ModelAndView("contest/list");
		result.addObject("requestURI", "contest/list.do");
 		result.addObject("contests", contests);
 		result.addObject("addRecipe", addRecipe);
 		result.addObject("recipes", recipes);
 		
 		return result;
 	}
	
}