package controllers.socialUser;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RecipeService;
import services.SocialUserService;
import controllers.AbstractController;
import domain.Recipe;
import forms.FilterString;

@Controller
@RequestMapping("/recipe/socialuser")
public class RecipeSocialUserController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private SocialUserService socialUserService;

	// Constructors -----------------------------------------------------------

	public RecipeSocialUserController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/listFollowed", method = RequestMethod.GET)
	public ModelAndView listFollowed() {

		ModelAndView result;
		Collection<Recipe> recipes = recipeService.findAllByUserFollowed();
		FilterString filter = new FilterString();
		result = new ModelAndView("recipe/list");
		result.addObject("requestURI", "recipe/list.do");
 		result.addObject("recipes", recipes);
 		result.addObject("filterString", filter);
		return result;
	}
	
	@RequestMapping(value = "/like", method = RequestMethod.GET)
	public ModelAndView like(@RequestParam int recipeId) {

		ModelAndView result;
		Recipe recipe;
		recipe = recipeService.findOne(recipeId);
		socialUserService.like(recipe);
		recipe = recipeService.save(recipe);
		
		result = new ModelAndView("redirect:/recipe/display.do?recipeId=" + recipe.getId());
		
		return result;
	}
	
	@RequestMapping(value = "/dislike", method = RequestMethod.GET)
	public ModelAndView dislike(@RequestParam int recipeId) {

		ModelAndView result;
		
		Recipe recipe;
		recipe = recipeService.findOne(recipeId);
		socialUserService.dislike(recipe);
		recipe = recipeService.save(recipe);
		
		result = new ModelAndView("redirect:/recipe/display.do?recipeId=" + recipe.getId());
		
		return result;
	}
	

}
