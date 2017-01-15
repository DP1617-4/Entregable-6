package controllers.cook;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CookService;
import controllers.AbstractController;
import domain.Cook;

@Controller
@RequestMapping("/cook/cook")
public class CookCookController extends AbstractController {

	// Services ---------------------------------------------------------------
	
	@Autowired
	CookService cookService;

	// Constructors -----------------------------------------------------------

	public CookCookController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Cook cook;

		cook = cookService.findByPrincipal();		
		result = createEditModelAndView(cook);
		
		result.addObject("cook", cook);

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Cook cook, BindingResult binding) {
		ModelAndView result;
		if(binding.hasErrors()){
			result = createEditModelAndView(cook);
		}
		else{
			try{
				cook = cookService.save(cook);
				result = new ModelAndView(
						"redirect:/cook/display.do?cookId="
								+ cook.getId());
			} catch (Throwable oops){
				result = createEditModelAndView(cook, "cook.commit.error");
			}
		}
		return result;
	}
	

	
	protected ModelAndView createEditModelAndView(Cook cook) {
		ModelAndView result;

		result = createEditModelAndView(cook, null);

		return result;
	}
	protected ModelAndView createEditModelAndView(Cook cook, String message) {
		ModelAndView result;
		
		String requestURI = "cook/cook/edit.do";

		result = new ModelAndView("cook/edit");
		result.addObject("cook", cook);
		result.addObject("errorMessage", message);
		result.addObject("requestURI", requestURI);
		
		return result;
	}

}
