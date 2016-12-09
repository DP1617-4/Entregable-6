package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ContestService;
import domain.Contest;
import forms.FilterString;

@Controller
@RequestMapping("/contest")
public class ContestController extends AbstractController {
	
	// Services ---------------------------------------------------------------

	@Autowired
	private ContestService contestService;	
	
	// Constructors -----------------------------------------------------------
	
	public ContestController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
 	public ModelAndView list() {
 		
 		ModelAndView result;
 		Collection<Contest> contests;
 
 		contests = contestService.findAllNotDeleted();
 		
		result = new ModelAndView("contest/list");
		result.addObject("requestURI", "contest/list.do");
 		result.addObject("contests", contests);
 		
 		return result;
 	}
	
}