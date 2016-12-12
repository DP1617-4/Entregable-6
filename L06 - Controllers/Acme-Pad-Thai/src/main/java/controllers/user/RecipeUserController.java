

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
import services.RecipeService;
import services.UserService;
import controllers.AbstractController;
import domain.Category;
import domain.Recipe;
import domain.User;
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
					recipe = recipeService.save(recipe);
					
					result = new ModelAndView("redirect:/recipe/display.do?recipeId="+recipe.getId());		
					
				} catch (Throwable oops) {
					result = new ModelAndView("redirect:/recipe/display.do?recipeId="+recipe.getId());			
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
		Collection<Category> categories = categoryService.findAllNotDeleted();
		
		result = new ModelAndView("recipe/edit");
		result.addObject("recipe", recipe);
		result.addObject("message", message);
		result.addObject("categoryList", categories);

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
		Assert.notNull(recipe);
		result = createEditModelAndView(recipe);

		return result;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid Recipe recipe, BindingResult binding) {
		
		ModelAndView result;
		
		if (binding.hasErrors()){
			result = new ModelAndView("redirect:/recipe/user/edit.do?recipeId="+recipe.getId());
		} else {

				try {
					recipe = recipeService.save(recipe);
					result = new ModelAndView("redirect:/recipe/display.do?recipeId="+recipe.getId());
					
					
					
				} catch (Throwable oops) {
					result = createEditModelAndView(recipe, "recipe.commit.error");			
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
	
}