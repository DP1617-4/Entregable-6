

package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.RecipeService;
import controllers.AbstractController;
import domain.Recipe;


@Controller
@RequestMapping("/recipe")
public class RecipeController extends AbstractController {
	
	// Services ---------------------------------------------------------------

	@Autowired
	private RecipeService recipeService;	
	
	// Constructors -----------------------------------------------------------
	
	public RecipeController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		
		ModelAndView result;
		Collection<Recipe> recipes;

		recipes = recipeService.findAllNotDeleted();
		
		result = new ModelAndView("announcement/list");
		result.addObject("requestURI", "recipe/user/list.do");
		result.addObject("recipes", recipes);
		
		return result;
	}

	
}