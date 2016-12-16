package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import domain.Comment;

@Controller
@RequestMapping("/comment")
public class CommentController extends AbstractController {
	
	// Services ---------------------------------------------------------------

	@Autowired
	private CommentService commentService;
//	@Autowired
//	private RecipeService recipeService;
	
	// Constructors -----------------------------------------------------------
	
	public CommentController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
 	public ModelAndView list(@RequestParam int recipeId) {
 		
 		ModelAndView result;
 		Collection<Comment> comments;
 
 		comments = commentService.findAllByRecipeId(recipeId);
 		
 		
		result = new ModelAndView("comment/list");
		result.addObject("requestURI", "comment/list.do");
 		result.addObject("comments", comments);
 		result.addObject("recipeId", recipeId);
 		
 		return result;
 	}
	
}