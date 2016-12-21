

package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.LoginService;
import security.UserAccount;
import services.BannerService;
import services.CategoryService;
import services.IngredientService;
import services.RecipeService;
import services.ScoreService;
import services.SocialUserService;
import services.UserService;
import controllers.AbstractController;
import domain.Banner;
import domain.Category;
import domain.Ingredient;
import domain.Quantity;
import domain.Recipe;
import domain.SocialUser;
import domain.Step;
import domain.User;
import forms.AddIngredient;
import forms.AddPicture;
import forms.FilterString;


@Controller
@RequestMapping("/recipe")
public class RecipeController extends AbstractController {
	
	// Services ---------------------------------------------------------------

	@Autowired
	private RecipeService recipeService;	
	@Autowired
	private IngredientService ingredientService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private BannerService bannerService;
	@Autowired
	private ScoreService scoreService;
	@Autowired
	private SocialUserService socialUserService;
	// Constructors -----------------------------------------------------------
	
	public RecipeController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	
	@Autowired
	private UserService userService;
	
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
	
	@RequestMapping(value = "/listUser", method = RequestMethod.GET)
	public ModelAndView own(@RequestParam int userId) {

		ModelAndView result;
		Collection<Recipe> recipes;
		FilterString filter = new FilterString();

		User u = userService.findOne(userId);

		recipes = recipeService.findAllByUser(u);

		result = new ModelAndView("recipe/list");
		result.addObject("requestURI", "recipe/user/listOwn.do");
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
		
		SocialUser principal;
	
		recipe = recipeService.findOne(recipeId);
		boolean scored = false;
		Object principalContext = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(principalContext!="anonymousUser"){
			UserAccount userAccount = LoginService.getPrincipal();
			for(Authority a: userAccount.getAuthorities()){
				if(a.getAuthority().equals(Authority.USER)||a.getAuthority().equals(Authority.NUTRITIONIST)){
					
					principal = socialUserService.findByPrincipal();
					
						if(scoreService.findScoreExistant(principal.getId(), recipeId)!=null){
							
							scored = true;
						}
					}
			}
		}
		
		
		
		
		Collection<Quantity> quantities = recipe.getQuantities();
		Collection<Step> steps = recipe.getSteps();
		AddPicture addPicture = new AddPicture();
		AddIngredient addIngredient = new AddIngredient();
		Collection<Category> categories = recipe.getCategories();
		Collection<Category> categoryList = categoryService.findAllNotDeleted();
		Banner banner = bannerService.findRandomStarBanner();
		
		result = new ModelAndView("recipe/display");
		result.addObject("recipe", recipe);
		result.addObject("ingredients", ingredientlist );
		result.addObject("quantities", quantities );
		result.addObject("steps", steps );
		result.addObject("addIngredient", addIngredient);
		result.addObject("addPicture", addPicture);
		result.addObject("categories", categories);
		result.addObject("categoryList", categoryList);
		result.addObject("banner", banner);
		result.addObject("scored", scored);
		
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