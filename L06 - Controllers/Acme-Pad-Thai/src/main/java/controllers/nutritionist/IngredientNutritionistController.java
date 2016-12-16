package controllers.nutritionist;

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
import services.PropertyService;
import services.RecipeService;
import services.ValueService;
import controllers.AbstractController;
import domain.Ingredient;
import domain.Property;
import domain.Recipe;
import domain.Value;
import forms.AddIngredient;
import forms.AddPicture;
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
	@Autowired
	private ValueService valueService;
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
 		
		result = new ModelAndView("ingredient/list");
		result.addObject("requestURI", "recipe/list.do");
 		result.addObject("recipes", recipes);
 		result.addObject("filterString", filter);
 		result.addObject("properties", properties);
 		result.addObject("ingredients", ingredients);
 		
 		return result;
 	}
	
	protected ModelAndView createEditModelAndView(Ingredient ingredient) {
		ModelAndView result;

		result = createEditModelAndView(ingredient, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Ingredient ingredient, String message) {
		ModelAndView result;

		result = new ModelAndView("ingredient/edit");
		result.addObject("ingredient", ingredient);
		result.addObject("message", message);

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int ingredientId) {
		ModelAndView result;
		Ingredient ingredient;

		ingredient = ingredientService.findOne(ingredientId);
			result = createEditModelAndView(ingredient);
			
		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		Ingredient ingredient = ingredientService.create();

		result = createEditModelAndView(ingredient);
		result.addObject("ingredient", ingredient);

		return result;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid Ingredient ingredient, BindingResult binding) {

		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(ingredient);
		} else {

			try {
				ingredient = ingredientService.save(ingredient);
				result = new ModelAndView(
						"redirect:/ingredient/display.do?ingredientId="
								+ ingredient.getId());

			} catch (Throwable oops) {
				result = createEditModelAndView(ingredient, "ingredient.commit.error");
				result.addObject("ingredient", ingredient);
			}
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Ingredient ingredient, BindingResult binding) {
		ModelAndView result;

		try {
			ingredientService.delete2(ingredient);
			
			result = new ModelAndView("redirect:/ingredient/nutritionist/list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(ingredient, "ingredient.commit.error");
		}

		return result;
	}
	
	@RequestMapping(value = "/addPicture", method = RequestMethod.POST, params = "addImage")
	public ModelAndView addPicture(@Valid AddPicture addPicture,
			BindingResult binding) {

		ModelAndView result;
		Ingredient ingredient = ingredientService.findOne(addPicture.getId());
		String picture = addPicture.getPicture();

		if (binding.hasErrors()) {
			result = new ModelAndView("redirect:/ingredient/display.do?ingredientId="
					+ ingredient.getId());
		} else {

			try {
				ingredient.getPictures().add(picture);
				ingredient = ingredientService.save(ingredient);

				result = new ModelAndView(
						"redirect:/ingredient/display.do?ingredientId="
								+ ingredient.getId());

			} catch (Throwable oops) {
				result = new ModelAndView(
						"redirect:/ingredient/display.do?ingredientId="
								+ ingredient.getId());
			}
		}

		return result;
	}
	
	@RequestMapping(value = "/addProperty", method = RequestMethod.POST, params = "addProperty")
	public ModelAndView addProperty(@Valid AddIngredient addIngredient,
			BindingResult binding) {

		ModelAndView result;
		Ingredient ingredient = ingredientService.findOne(addIngredient.getRecipeId());
		Property property = propertyService.findOne(addIngredient.getIngredientId());

		if (binding.hasErrors()) {
			result = new ModelAndView("redirect:/ingredient/display.do?ingredientId="
					+ ingredient.getId());
		} else {

			try {
				
				Value value = valueService.create();
				value.setIngredient(ingredient);
				value.setProperty(property);

				result = createValueModelAndView(value);

			} catch (Throwable oops) {
				result = new ModelAndView(
						"redirect:/ingredient/display.do?ingredientId="
								+ ingredient.getId());
			}
		}

		return result;
	}
	
	protected ModelAndView createValueModelAndView(Value value) {
		ModelAndView result;

		result = createValueModelAndView(value, null);

		return result;
	}

	protected ModelAndView createValueModelAndView(Value value, String message) {
		ModelAndView result;

		result = new ModelAndView("ingredient/addproperty");
		result.addObject("value", value);
		result.addObject("message", message);

		return result;
	}
	
	@RequestMapping(value = "/addproperty", method = RequestMethod.POST, params = "save")
	public ModelAndView editValue(@Valid Value value, BindingResult binding) {

		ModelAndView result;
		if (binding.hasErrors()) {
			result = createValueModelAndView(value);
		} else {

			try {
				value = valueService.save(value);
				result = new ModelAndView(
						"redirect:/ingredient/display.do?ingredientId="
								+ value.getIngredient().getId());

			} catch (Throwable oops) {
				result = createValueModelAndView(value, "ingredient.commit.error");
				result.addObject("value", value);
			}
		}

		return result;
	}
	
	@RequestMapping(value = "/removeProperty", method = RequestMethod.GET)
	public ModelAndView removeProperty(@RequestParam int valueId) {

		ModelAndView result;
		Value value = valueService.findOne(valueId);
		Ingredient ingredient = value.getIngredient();
		valueService.delete(value);
		result = new ModelAndView("redirect:/ingredient/display.do?ingredientId="
								+ ingredient.getId());
		return result;
	}
	
	@RequestMapping(value = "/editValue", method = RequestMethod.GET)
	public ModelAndView editValue(@RequestParam int valueId) {

		ModelAndView result;
		Value value = valueService.findOne(valueId);
		result = createValueModelAndView(value);
		return result;
	}
	
	@RequestMapping(value = "/deleteProperty", method = RequestMethod.GET)
	public ModelAndView deleteProperty(@RequestParam int propertyId) {

		ModelAndView result;
		Property property = propertyService.findOne(propertyId);
		propertyService.delete2(property);
		result = new ModelAndView(
				"redirect:list.do");
		return result;
	}
	
	@RequestMapping(value = "/createProperty", method = RequestMethod.POST, params = "createProperty")
	public ModelAndView editValue(@Valid FilterString filterString, BindingResult binding) {

		String name = filterString.getFilter();
		
		ModelAndView result;
		if (binding.hasErrors()) {
			result = new ModelAndView("redirect:list.do");
		} else {

			try {
				Property property = propertyService.create();
				property.setName(name);
				propertyService.save(property);
				result = new ModelAndView(
						"redirect:list.do");

			} catch (Throwable oops) {
				result = new ModelAndView(
						"redirect:list.do");
			}
		}

		return result;
	}
}
