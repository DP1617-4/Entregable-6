package controllers.nutritionist;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.IngredientService;
import services.PropertyService;
import services.RecipeService;
import controllers.AbstractController;
import domain.Ingredient;
import domain.Property;
import domain.Recipe;
import forms.FilterString;


@Controller
@RequestMapping("/ingredient/nutritionist")
public class IngredientNutritionistController extends AbstractController {
	
	// Services ---------------------------------------------------------------

	@Autowired
	private RecipeService recipeService;	
	@Autowired
	private IngredientService ingredientService;
	@Autowired
	private PropertyService propertyService;
	
	// Constructors -----------------------------------------------------------
	
	public IngredientNutritionistController() {
		super();
	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
 	public ModelAndView list() {
 		
 		ModelAndView result;
 		Collection<Recipe> recipes;
 		FilterString filter = new FilterString();
 
 		recipes = recipeService.findAllNotDeleted();
 		Collection<Ingredient> ingredients = ingredientService.findAllNotDeleted();
 		Collection<Property> properties = propertyService.findAllNotDeleted();
 		
		result = new ModelAndView("recipe/list");
		result.addObject("requestURI", "recipe/list.do");
 		result.addObject("recipes", recipes);
 		result.addObject("filterString", filter);
 		result.addObject("properties", properties);
 		result.addObject("ingredients", ingredients);
 		
 		return result;
 	}
}
