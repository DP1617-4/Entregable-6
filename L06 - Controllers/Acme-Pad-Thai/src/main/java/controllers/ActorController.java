package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import domain.Actor;
import domain.Administrator;
import domain.Nutritionist;
import domain.Sponsor;
import domain.User;

@Controller
@RequestMapping("/actor")
public class ActorController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private ActorService actorService;
	
	// Constructors -----------------------------------------------------------
	public ActorController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam(required = false, defaultValue = "0") int userAccountId) {

		ModelAndView result;
		Actor actor;
		
		if(userAccountId==0){
			
			actor= actorService.findByPrincipal();
		}
		else{
			actor = actorService.findByUserAccount(userAccountId);
			
		}
		
		if(actor instanceof User){
			result = new ModelAndView("redirect:/user/display.do");
			result.addObject("userId", actor.getId());
		}
		else if(actor instanceof Nutritionist){
			result = new ModelAndView("redirect:/nutritionist/display.do");
			result.addObject("nutritionistId", actor.getId());
		}
		else if(actor instanceof Administrator){
			result = new ModelAndView("redirect:/administrator/display.do");
			result.addObject("administratorId", actor.getId());
		}
		else if(actor instanceof Sponsor){
			result = new ModelAndView("redirect:/sponsor/display.do");
			result.addObject("sponsorId", actor.getId());
		}else{
			result = new ModelAndView("redirect:/cook/display.do");
			result.addObject("cookId", actor.getId());
		}

		return result;
	}
}