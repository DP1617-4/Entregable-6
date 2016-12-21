package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.LoginService;
import security.UserAccount;
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
		Object access = SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		
		result = new ModelAndView("contest/list");
		result.addObject("requestURI", "contest/list.do");
		result.addObject("contests", contests);
		if (access != "anonymousUser") {
			
			UserAccount userAccount = LoginService.getPrincipal();
			for(Authority a: userAccount.getAuthorities()){
				if(a.getAuthority().equals(Authority.USER)){
					
					AddRecipe addRecipe = new AddRecipe();
					Collection<Recipe> pRecipes = recipeService
							.findAllNotQualifiedPrincipal();
					Collection<Recipe> recipes = new ArrayList<Recipe>();
					for (Recipe r : pRecipes) {
						boolean checker = true;
						if(r.getScore() >= 5){
							for (Score s : r.getScores()) {
								if (s.getLikes() == false) {
									checker = false;
									break;
								} 
								
							}
							if(checker)
								recipes.add(r);
						}
					}
					result.addObject("addRecipe", addRecipe);
					result.addObject("recipes", recipes);
					
				}
			}

		}
		return result;
	}

}