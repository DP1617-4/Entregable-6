package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ContestService;
import services.CookService;
import services.RecipeService;
import services.UserService;
import controllers.AbstractController;
import domain.Contest;
import domain.User;

@Controller
@RequestMapping("/administrator/administrator")
public class AdministratorAdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private CookService administratorService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RecipeService recipeService;
	
	@Autowired
	ContestService contestService;

	// Constructors -----------------------------------------------------------

	public AdministratorAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		ModelAndView result;
		Collection<Integer> recipesPerUser = userService.selectMinAvgMaxRecipesInUsers();
		Collection<Double> recipesQualifiedForConstest = contestService.getMinAvgMaxRecipesQualifiedForContest();
		User userWithMostRecipes = userService.selectUserWithMostRecipes();
		Contest contestWithMostQualifiedRecipes= contestService.getContestWithMoreRecipesQualified();
		Collection<Double> stepsPerRecipe = recipeService.getAvgStdStepsPerRecipe();
		Collection<Double> ingredientsPerRecipe = recipeService.getAvgStdIngredientsPerRecipe();
		Collection<User> usersByPopularity = userService.selectAllUsersDescendingNumberOfFollowers();
		
		result = new ModelAndView("administrator/dashboard");
		result.addObject("recipesPerUser",recipesPerUser);
		result.addObject("userWithMostRecipes",userWithMostRecipes);
		result.addObject("contestWithMostQualifiedRecipes",contestWithMostQualifiedRecipes);
		result.addObject("stepsPerRecipe",stepsPerRecipe);
		result.addObject("ingredientsPerRecipe",ingredientsPerRecipe);
		result.addObject("recipesQualifiedForConstest",recipesQualifiedForConstest);
		result.addObject("usersByPopularity",usersByPopularity);
		result.addObject("requestURI","administrator/administrator/dashborad.do");		
		return result;
	}

}
