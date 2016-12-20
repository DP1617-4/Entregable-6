package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ContestService;
import controllers.AbstractController;
import domain.Contest;

@Controller
@RequestMapping("/contest/administrator")
public class ContestAdministratorController extends AbstractController {
	
	// Services ---------------------------------------------------------------

//	@Autowired
//	private RecipeService recipeService;	
	@Autowired
	private ContestService contestService;
	
	// Constructors -----------------------------------------------------------
	
	public ContestAdministratorController() {
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
		
		result = new ModelAndView("contest/administrator/edit");
		result.addObject("contest", contest);
		result.addObject("message", message);

		return result;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
 	public ModelAndView list() {
 		
 		ModelAndView result;

		result = new ModelAndView("contest/administrator/list");
		result.addObject("requestURI", "contest/administrator/list.do");
 		
 		return result;
 	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		Contest contest = contestService.create();
		
		result = createEditModelAndView(contest);

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int contestId) {
		ModelAndView result;
		Contest contest;

		try{
		
			contest = contestService.findOne(contestId);
			result = createEditModelAndView(contest);
			
		}catch(Exception oops){

			
			result = new ModelAndView("redirect:/contest/list.do");
		
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
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Contest contest, BindingResult binding) {
		ModelAndView result;

		try {
			contestService.delete2(contest);
			result = new ModelAndView("redirect:/contest/list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(contest, "contest.commit.error");
		}

		return result;
	}
	
}