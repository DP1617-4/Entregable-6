package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.StepService;
import domain.Step;
import forms.AddPicture;

@Controller
@RequestMapping("/step")
public class StepController extends AbstractController {
	
	// Services ---------------------------------------------------------------

//	@Autowired
//	private RecipeService recipeService;	
	@Autowired
	private StepService stepService;
	
	// Constructors -----------------------------------------------------------
	
	public StepController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int stepId) {
		
		ModelAndView result;
		Step step;
		
		step = stepService.findOne(stepId);
		Collection<String> pictures = step.getPictures();
		AddPicture addPicture = new AddPicture();
		
		result = new ModelAndView("step/edit");
		result.addObject("step", step);
		result.addObject("pictures", pictures);
		result.addObject("addPicture", addPicture);
		
		
		return result;
	}
	
}