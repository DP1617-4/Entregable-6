package controllers.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ContestService;
import services.RecipeService;
import controllers.AbstractController;
import domain.Contest;
import domain.Recipe;
import forms.AddRecipe;

@Controller
@RequestMapping("/contest/user")
public class ContestUserController extends AbstractController {
	
	// Services ---------------------------------------------------------------

	@Autowired
	private RecipeService recipeService;	
	@Autowired
	private ContestService contestService;
	
	// Constructors -----------------------------------------------------------
	
	public ContestUserController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	
	protected ModelAndView createEditModelAndView(Contest contest) {
		ModelAndView result;

		result = createEditModelAndView(contest, null);
		
		return result;
	}	
	
	
	
	protected ModelAndView createEditModelAndView(Contest contest, String message) {
		ModelAndView result;
		
		result = new ModelAndView("contest/qualify");
		result.addObject("contest", contest);
		result.addObject("message", message);

		return result;
	}
	
	@RequestMapping(value = "/qualify", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int contestId) {
		
		ModelAndView result;
		Contest contest = contestService.findOne(contestId);
		
		result = createEditModelAndView(contest);
		result.addObject("contest", contest);
		
		return result;
	}
	
	
	@RequestMapping(value = "/qualify", method = RequestMethod.POST, params = "save")
	public ModelAndView addRecipe(@Valid AddRecipe addRecipe,
			BindingResult binding) {

		ModelAndView result;
		Contest contest= contestService.findOne(addRecipe.getContestId());
		Recipe recipeToQualify = recipeService.findOne(addRecipe.getRecipeId());

		if (binding.hasErrors()) {
			result = new ModelAndView("redirect:/contest/list.do");
		} else {

			try {
				
				recipeToQualify = recipeService.createCopyForContest(recipeToQualify);
				contestService.setQualified(contest, recipeToQualify);
				result = new ModelAndView(
						"redirect:/contest/list.do");

			} catch (Throwable oops) {
				result = new ModelAndView(
						"redirect:/contest/list.do");
			}
		}

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid Contest contest, BindingResult binding) {
		
		ModelAndView result;
		
		if (binding.hasErrors()){
			result = createEditModelAndView(contest); 
			} else {
				try {
					contest = contestService.save(contest);
					result = new ModelAndView("redirect:/contest/list.do");
				} catch (Throwable oops) {
					result = createEditModelAndView(contest, "contest.commit.error");			
			}
		}
			
		return result;
	}
	
}