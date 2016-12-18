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
import domain.Ingredient;
import domain.Property;
import domain.Value;
import forms.AddIngredient;
import forms.AddPicture;


@Controller
@RequestMapping("/ingredient")
public class IngredientController extends AbstractController {
	
	// Services ---------------------------------------------------------------

	@Autowired
	private IngredientService ingredientService;
	@Autowired
	private PropertyService propertyService;
	
	// Constructors -----------------------------------------------------------
	
	public IngredientController() {
		super();
	}

	// Listing ----------------------------------------------------------------
		
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