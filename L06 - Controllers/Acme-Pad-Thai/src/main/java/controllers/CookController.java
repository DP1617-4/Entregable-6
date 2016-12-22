package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.CookService;
import domain.Cook;

@Controller
@RequestMapping("/cook")
public class CookController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private CookService cookService;

	// Constructors -----------------------------------------------------------

	public CookController() {
		super();
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam(required = false, defaultValue = "0") int cookId) {

		
		ModelAndView result;
		Cook cook;
		
		if(cookId==0){
			
			cook= cookService.findByPrincipal();
		}
		else{
			
			cook = cookService.findOne(cookId);
		}


		result = new ModelAndView("cook/display");
		result.addObject("cook", cook);

		return result;
	}
	
}

