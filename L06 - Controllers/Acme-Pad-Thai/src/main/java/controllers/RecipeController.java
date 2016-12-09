

package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.IngredientService;
import services.RecipeService;
import controllers.AbstractController;
import domain.Ingredient;
import domain.Recipe;
import forms.FilterString;


@Controller
@RequestMapping("/recipe")
public class RecipeController extends AbstractController {
	
	// Services ---------------------------------------------------------------

	@Autowired
	private RecipeService recipeService;	
	@Autowired
	private IngredientService ingredientService;
	
	// Constructors -----------------------------------------------------------
	
	public RecipeController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
 	public ModelAndView list() {
 		
 		ModelAndView result;
 		Collection<Recipe> recipes;
 		FilterString filter = new FilterString();
 
 		recipes = recipeService.findAllNotDeleted();
 		
		result = new ModelAndView("recipe/list");
		result.addObject("requestURI", "recipe/list.do");
 		result.addObject("recipes", recipes);
 		result.addObject("filterString", filter);
 		
 		return result;
 	}
	
	@RequestMapping(value = "/listQualified", method = RequestMethod.GET)
 	public ModelAndView listQualified(@RequestParam int contestId) {
 		
 		ModelAndView result;
 		Collection<Recipe> recipes;
 		FilterString filter = new FilterString();
 
 		recipes = recipeService.findAllQualified(contestId);
 		
		result = new ModelAndView("recipe/list");
		result.addObject("requestURI", "recipe/listQualified.do");
 		result.addObject("recipes", recipes);
 		result.addObject("filterString", filter);
 		
 		return result;
 	}
	
	@RequestMapping(value = "/listWinners", method = RequestMethod.GET)
 	public ModelAndView listWinners(@RequestParam int contestId) {
 		
 		ModelAndView result;
 		Collection<Recipe> recipes;
 		FilterString filter = new FilterString();
 
 		recipes = recipeService.findAllWinners(contestId);
 		
		result = new ModelAndView("recipe/list");
		result.addObject("requestURI", "recipe/listWinners.do");
 		result.addObject("recipes", recipes);
 		result.addObject("filterString", filter);
 		
 		return result;
 	}
	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int recipeId) {
		
		ModelAndView result;
		Recipe recipe;
		Collection<Ingredient> ingredientlist = ingredientService.findAllNotDeleted();

		recipe = recipeService.findOne(recipeId);
		
		result = new ModelAndView("recipe/display.do");
		result.addObject("recipe", recipe);
		result.addObject("ingredients", ingredientlist );
		
		return result;
	}
	
	@RequestMapping(value = "/filter", method = RequestMethod.POST, params = "filterButton")
	public ModelAndView filter(@Valid FilterString filterString, BindingResult binding) {
		
		ModelAndView result;
		Collection<Recipe> recipes;
		String filter= filterString.getFilter();
		if (binding.hasErrors()) {
			result = new ModelAndView("redirect:list.do");
		} else {

				try {
					recipes = recipeService.findAllFiltered(filter);	
					result = new ModelAndView("recipe/list");
					result.addObject("requestURI", "recipe/list.do");
					result.addObject("recipes", recipes);
					result.addObject("filterString", filterString);
					
				} catch (Throwable oops) {
					result = new ModelAndView("redirect:list.do");			
			}
		}
			
		return result;
	}
	
	
	
 	
 
}