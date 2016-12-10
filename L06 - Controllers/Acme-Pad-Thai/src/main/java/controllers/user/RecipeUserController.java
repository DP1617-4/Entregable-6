

package controllers.user;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.IngredientService;
import services.RecipeService;
import services.SocialUserService;
import services.UserService;
import controllers.AbstractController;
import controllers.RecipeController;
import domain.Ingredient;
import domain.Quantity;
import domain.Recipe;
import domain.Step;
import domain.User;
import forms.AddIngredient;
import forms.AddPicture;
import forms.FilterString;


@Controller
@RequestMapping("/recipe/user")
public class RecipeUserController extends AbstractController {
	
	// Services ---------------------------------------------------------------

	@Autowired
	private RecipeService recipeService;	
	@Autowired
	private UserService userService;	
	@Autowired
	private SocialUserService socialUserService;
	@Autowired
	private IngredientService ingredientService;
	
	// Constructors -----------------------------------------------------------
	
	public RecipeUserController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	
	@RequestMapping(value = "/listOwn", method = RequestMethod.GET)
	public ModelAndView own() {
		
		ModelAndView result;
		Collection<Recipe> recipes;
		FilterString filter = new FilterString();
		
		User u = userService.findByPrincipal();

		recipes = recipeService.findAllByUser(u);
		
		result = new ModelAndView("recipe/list");
		result.addObject("requestURI", "recipe/user/listOwn.do");
		result.addObject("recipes", recipes);
 		result.addObject("filterString", filter);
		
		return result;
	}

	
	@RequestMapping(value = "/addPicture", method = RequestMethod.POST, params = "addImage")
	public ModelAndView addPicture(@Valid AddPicture addPicture, BindingResult binding) {
		
		ModelAndView result;
		Recipe recipe = recipeService.findOne(addPicture.getId());
		String picture= addPicture.getPicture();
		
		if (binding.hasErrors()) {
			result = new ModelAndView("redirect:/recipe/display.do?recipeId="+recipe.getId());
		} else {

				try {
					recipe.getPictures().add(picture);
					Collection<Quantity> quantities = recipe.getQuantities();
					Collection<Step> steps = recipe.getSteps();
					addPicture = new AddPicture();
					AddIngredient addIngredient = new AddIngredient();
					Collection<Ingredient> ingredientlist = ingredientService.findAllNotDeleted();
					
					result = new ModelAndView("redirect:/recipe/display.do?recipeId="+recipe.getId());
					result.addObject("recipe", recipe);
					result.addObject("ingredients", ingredientlist );
					result.addObject("quantities", quantities );
					result.addObject("steps", steps );
					result.addObject("addIngredient", addIngredient);
					result.addObject("addPicture", addPicture);
					
					
					
				} catch (Throwable oops) {
					result = new ModelAndView("redirect:/recipe/display.do?recipeId="+recipe.getId());			
			}
		}
			
		return result;
	}
	
}