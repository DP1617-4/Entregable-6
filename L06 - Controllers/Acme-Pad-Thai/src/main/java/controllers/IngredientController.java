package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.IngredientService;
import services.PropertyService;
import services.RecipeService;
import domain.Ingredient;
import domain.Property;
import domain.Recipe;
import domain.Value;
import forms.AddIngredient;
import forms.AddPicture;
import forms.FilterString;


@Controller
@RequestMapping("/ingredient")
public class IngredientController extends AbstractController {
	
	// Services ---------------------------------------------------------------

	@Autowired
	private RecipeService recipeService;	
	@Autowired
	private IngredientService ingredientService;
	@Autowired
	private PropertyService propertyService;
	
	// Constructors -----------------------------------------------------------
	
	public IngredientController() {
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
	

	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int ingredientId) {
		
		ModelAndView result;
		Ingredient ingredient;
		Collection<Property> properties = propertyService.findAllNotDeleted();

		ingredient = ingredientService.findOne(ingredientId);
		Collection<Value> values = ingredient.getValues();
		AddPicture addPicture = new AddPicture();
		AddIngredient addIngredient = new AddIngredient();
		
		result = new ModelAndView("ingredient/display");
		result.addObject("ingredient", ingredient);
		result.addObject("properties", properties );
		result.addObject("values", values );
		result.addObject("addIngredient", addIngredient);
		result.addObject("addPicture", addPicture);
		
		
		return result;
	}
}