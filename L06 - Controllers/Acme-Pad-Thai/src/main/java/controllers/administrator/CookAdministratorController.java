package controllers.administrator;

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
@RequestMapping("/cook/administrator")
public class CookAdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private CookService cookService;

	// Constructors -----------------------------------------------------------

	public CookAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Cook cook;

		cook = cookService.create();
		result = createEditModelAndView(cook);

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid Cook cook, BindingResult binding) {
		
		ModelAndView result;
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(cook);
		} else {
				try {
					cookService.save(cook);
					result = new ModelAndView("redirect:/welcome/index.do");					
				} catch (Throwable oops) {
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
		
		result = new ModelAndView("cook/edit");
		result.addObject("cook", cook);
		result.addObject("errorMessage", message);
		result.addObject("requestURI", "cook/administrator/edit.do");
		result.addObject("cancelURI", "welcome/index.do");

		return result;
	}

}
