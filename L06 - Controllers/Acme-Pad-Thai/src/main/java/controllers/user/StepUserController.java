package controllers.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;

import services.RecipeService;
import services.StepService;
import domain.Recipe;
import domain.Step;

@Controller
@RequestMapping("/step/user")
public class StepUserController extends AbstractController {
	
	// Services ---------------------------------------------------------------

	@Autowired
	private RecipeService recipeService;	
	@Autowired
	private StepService stepService;
	
	// Constructors -----------------------------------------------------------
	
	public StepUserController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	
	protected ModelAndView createEditModelAndView(Step step) {
		ModelAndView result;

		result = createEditModelAndView(step, null);
		
		return result;
	}	
	
	
	
	protected ModelAndView createEditModelAndView(Step step, String message) {
		ModelAndView result;
		
		result = new ModelAndView("step/edit");
		result.addObject("step", step);
		result.addObject("errorMessage", message);

		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int recipeId) {
		
		ModelAndView result;
		Recipe recipe = recipeService.findOne(recipeId);
		Step step = stepService.create(recipe);
		
		result = createEditModelAndView(step);
		result.addObject("step", step);
		
		return result;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int stepId) {
		ModelAndView result;
		Step step;

		step = stepService.findOne(stepId);		
		Assert.notNull(step);
		result = createEditModelAndView(step);
		result.addObject("requestURI", "step/user/edit.do");

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid Step step, BindingResult binding) {
		
		ModelAndView result;
		
		if (binding.hasErrors()){
			result = createEditModelAndView(step); 
			} else {
				try {
					step = stepService.save(step);
					result = new ModelAndView("redirect:/recipe/display.do?recipeId="+step.getRecipe().getId());
				} catch (Throwable oops) {
					result = createEditModelAndView(step, "step.commit.error");			
			}
		}
			
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Step step, BindingResult binding) {
		ModelAndView result;
		try {			
			stepService.delete(step);
			result = new ModelAndView("redirect:/recipe/display.do?recipeId="+step.getRecipe().getId());						
		} catch (Throwable oops) {
			result = createEditModelAndView(step, "step.commit.error");
		}

		return result;
	}
	
}