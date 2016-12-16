package controllers.socialUser;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import services.RecipeService;
import controllers.AbstractController;
import domain.Comment;
import domain.Recipe;

@Controller
@RequestMapping("/comment/socialUser")
public class CommentSocialUserController extends AbstractController {
	
	// Services ---------------------------------------------------------------

	@Autowired
	private CommentService commentService;
	@Autowired
	private RecipeService recipeService;
	
	// Constructors -----------------------------------------------------------
	
	public CommentSocialUserController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	
	protected ModelAndView createEditModelAndView(Comment comment) {
		ModelAndView result;

		result = createEditModelAndView(comment, null);
		
		return result;
	}	
	
	
	
	protected ModelAndView createEditModelAndView(Comment comment, String message) {
		ModelAndView result;
		
		result = new ModelAndView("comment/edit");
		result.addObject("comment", comment);
		result.addObject("message", message);

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int recipeId) {
		
		ModelAndView result;
		Recipe recipe = recipeService.findOne(recipeId);
		try{
		
		Comment comment = commentService.create(recipe);
		
		result = createEditModelAndView(comment);
		result.addObject("comment", comment);
		result.addObject("recipeId", recipe.getId());
		} catch(Throwable oops) {
			result = new ModelAndView("redirect:/comment/list.do?recipeId="+ recipe.getId());
		}
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid Comment comment, BindingResult binding) {
		
		ModelAndView result;
		
		if (binding.hasErrors()){
			result = createEditModelAndView(comment); 
			} else {
				try {
					comment = commentService.save(comment);
					result = new ModelAndView("redirect:/recipe/display.do?recipeId="+comment.getRecipe().getId());
				} catch (Throwable oops) {
					result = createEditModelAndView(comment, "comment.commit.error");			
			}
		}
			
		return result;
	}
}