package controllers.user;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryService;
import services.IngredientService;
import services.QuantityService;
import services.RecipeService;
import services.UserService;
import controllers.AbstractController;
import domain.Category;
import domain.Ingredient;
import domain.Quantity;
import domain.Recipe;
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
	private CategoryService categoryService;
	@Autowired
	private IngredientService ingredientService;
	@Autowired
	private QuantityService quantityService;

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
	public ModelAndView addPicture(@Valid AddPicture addPicture,
			BindingResult binding) {

		ModelAndView result;
		Recipe recipe = recipeService.findOne(addPicture.getId());
		String picture = addPicture.getPicture();

		if (binding.hasErrors()) {
			result = new ModelAndView("redirect:/recipe/display.do?recipeId="
					+ recipe.getId());
		} else {

			try {
				recipe.getPictures().add(picture);
				recipe = recipeService.save(recipe);

				result = new ModelAndView(
						"redirect:/recipe/display.do?recipeId="
								+ recipe.getId());

			} catch (Throwable oops) {
				result = new ModelAndView(
						"redirect:/recipe/display.do?recipeId="
								+ recipe.getId());
			}
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(Recipe recipe) {
		ModelAndView result;

		result = createEditModelAndView(recipe, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Recipe recipe, String message) {
		ModelAndView result;

		result = new ModelAndView("recipe/edit");
		result.addObject("recipe", recipe);
		result.addObject("message", message);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		Recipe recipe = recipeService.create();

		result = createEditModelAndView(recipe);
		result.addObject("recipe", recipe);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int recipeId) {
		ModelAndView result;
		Recipe recipe;

		recipe = recipeService.findOne(recipeId);
		if(recipe.getContest().equals(null)){
			result = createEditModelAndView(recipe);
		}
		else{
			
			result = new ModelAndView("redirect:/recipe/list.do");
			result.addObject("message", "recipe.error.access");
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid Recipe recipe, BindingResult binding) {

		ModelAndView result;
		recipe.getCategories();
		if (binding.hasErrors()) {
			result = createEditModelAndView(recipe);
		} else {

			try {
				recipe = recipeService.save(recipe);
				result = new ModelAndView(
						"redirect:/recipe/display.do?recipeId="
								+ recipe.getId());

			} catch (Throwable oops) {
				result = createEditModelAndView(recipe, "recipe.commit.error");
				result.addObject("recipe", recipe);
			}
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Recipe recipe, BindingResult binding) {
		ModelAndView result;

		try {
			recipeService.delete2(recipe);
			result = new ModelAndView("redirect:/recipe/list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(recipe, "recipe.commit.error");
		}

		return result;
	}
	
	@RequestMapping(value = "/addCategory", method = RequestMethod.POST, params = "addCategory")
	public ModelAndView addCategory(@Valid AddIngredient addIngredient,
			BindingResult binding) {

		ModelAndView result;
		Recipe recipe = recipeService.findOne(addIngredient.getRecipeId());
		Category category = categoryService.findOne(addIngredient.getIngredientId());

		if (binding.hasErrors()) {
			result = new ModelAndView("redirect:/recipe/display.do?recipeId="
					+ recipe.getId());
		} else {

			try {
				if(!(recipe.getCategories().contains(category))){
					
					recipe.getCategories().add(category);
					category.getRecipes().add(recipe);
					categoryService.save(category);
					recipeService.save(recipe);
				}


				result = new ModelAndView(
						"redirect:/recipe/display.do?recipeId="
								+ recipe.getId());

			} catch (Throwable oops) {
				result = new ModelAndView(
						"redirect:/recipe/display.do?recipeId="
								+ recipe.getId());
			}
		}

		return result;
	}
	
	@RequestMapping(value = "/removeCategory", method = RequestMethod.POST, params = "removeCategory")
	public ModelAndView removeCategory(@Valid AddIngredient addIngredient,
			BindingResult binding) {

		ModelAndView result;
		Recipe recipe = recipeService.findOne(addIngredient.getRecipeId());
		Category category = categoryService.findOne(addIngredient.getIngredientId());

		if (binding.hasErrors()) {
			result = new ModelAndView("redirect:/recipe/display.do?recipeId="
					+ recipe.getId());
		} else {

			try {
				if(recipe.getCategories().contains(category)){
					
					recipe.getCategories().remove(category);
					category.getRecipes().remove(recipe);
					categoryService.save(category);
					recipeService.save(recipe);
				}


				result = new ModelAndView(
						"redirect:/recipe/display.do?recipeId="
								+ recipe.getId());

			} catch (Throwable oops) {
				result = new ModelAndView(
						"redirect:/recipe/display.do?recipeId="
								+ recipe.getId());
			}
		}

		return result;
	}
	
	@RequestMapping(value = "/addIngredient", method = RequestMethod.POST, params = "addIngredient")
	public ModelAndView addIngredient(@Valid AddIngredient addIngredient,
			BindingResult binding) {

		ModelAndView result;
		Recipe recipe = recipeService.findOne(addIngredient.getRecipeId());
		Ingredient ingredient = ingredientService.findOne(addIngredient.getIngredientId());

		if (binding.hasErrors()) {
			result = new ModelAndView("redirect:/recipe/display.do?recipeId="
					+ recipe.getId());
		} else {

			try {
				
				Quantity quantity = quantityService.create();
				quantity.setIngredient(ingredient);
				quantity.setRecipe(recipe);

				result = createQuantityModelAndView(quantity);

			} catch (Throwable oops) {
				result = new ModelAndView(
						"redirect:/recipe/display.do?recipeId="
								+ recipe.getId());
			}
		}

		return result;
	}
	
	protected ModelAndView createQuantityModelAndView(Quantity quantity) {
		ModelAndView result;

		result = createQuantityModelAndView(quantity, null);

		return result;
	}

	protected ModelAndView createQuantityModelAndView(Quantity quantity, String message) {
		ModelAndView result;

		result = new ModelAndView("recipe/addingredients");
		result.addObject("quantity", quantity);
		result.addObject("message", message);

		return result;
	}
	
	@RequestMapping(value = "/addingredients", method = RequestMethod.POST, params = "save")
	public ModelAndView editQuantity(@Valid Quantity quantity, BindingResult binding) {

		ModelAndView result;
		if (binding.hasErrors()) {
			result = createQuantityModelAndView(quantity);
		} else {

			try {
				quantity = quantityService.save(quantity);
				result = new ModelAndView(
						"redirect:/recipe/display.do?recipeId="
								+ quantity.getRecipe().getId());

			} catch (Throwable oops) {
				result = createQuantityModelAndView(quantity, "recipe.commit.error");
				result.addObject("quantity", quantity);
			}
		}

		return result;
	}

	@RequestMapping(value = "/removeIngredient", method = RequestMethod.GET)
	public ModelAndView removeIngredient(@RequestParam int quantityId) {

		ModelAndView result;
		Quantity quantity = quantityService.findOne(quantityId);
		Recipe recipe = quantity.getRecipe();
		quantityService.delete(quantity);
		result = new ModelAndView("redirect:/recipe/display.do?recipeId="
								+ recipe.getId());
		return result;
	}
	
	@RequestMapping(value = "/editQuantity", method = RequestMethod.GET)
	public ModelAndView editQuantity(@RequestParam int quantityId) {

		ModelAndView result;
		Quantity quantity = quantityService.findOne(quantityId);
		result = createQuantityModelAndView(quantity);
		return result;
	}

}