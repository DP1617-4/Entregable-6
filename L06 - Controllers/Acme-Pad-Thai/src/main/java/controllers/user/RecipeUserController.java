

package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RecipeService;
import services.SocialUserService;
import services.UserService;

import controllers.AbstractController;
import domain.Recipe;
import domain.User;


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
	
	// Constructors -----------------------------------------------------------
	
	public RecipeUserController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	
	@RequestMapping(value = "/own", method = RequestMethod.GET)
	public ModelAndView own() {
		
		ModelAndView result;
		Collection<Recipe> recipes;
		
		User u = userService.findByPrincipal();

		recipes = recipeService.findAllByUser(u);
		
		result = new ModelAndView("announcement/list");
		result.addObject("requestURI", "recipe/user/list.do");
		result.addObject("recipes", recipes);
		
		return result;
	}

	
}