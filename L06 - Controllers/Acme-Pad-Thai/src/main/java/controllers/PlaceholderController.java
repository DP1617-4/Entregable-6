

package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.PlaceholderService;
import controllers.AbstractController;
import domain.Placeholder;
import forms.FilterString;


@Controller
@RequestMapping("/placeholder/actor")
public class PlaceholderController extends AbstractController {
	
	// Services ---------------------------------------------------------------

	@Autowired
	private PlaceholderService placeholderService;	
	public PlaceholderController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
 	public ModelAndView list(@RequestParam(required = false, defaultValue = "0") int contestId) {
 		
 		ModelAndView result;
 		Collection<Placeholder> placeholders;
 		FilterString filter = new FilterString();
 		
 		if (contestId != 0){
 			placeholders = placeholderService.findAllByContest(contestId);
 		}
 		else{
 	 		placeholders = placeholderService.findAll();
 		}
		result = new ModelAndView("placeholder/list");
		result.addObject("requestURI", "placeholder/listWinners.do");
 		result.addObject("placeholders", placeholders);
 		result.addObject("filterString", filter);
 		
 		return result;
 	}
	

	protected ModelAndView createEditModelAndView(Placeholder placeholder) {
		ModelAndView result;

		result = createEditModelAndView(placeholder, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Placeholder placeholder, String message) {
		ModelAndView result;

		result = new ModelAndView("placeholder/edit");
		result.addObject("placeholder", placeholder);
		result.addObject("errorMessage", message);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		Placeholder placeholder = placeholderService.create();

		result = createEditModelAndView(placeholder);
		result.addObject("placeholder", placeholder);

		return result;
	}

	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int placeholderId) {
		ModelAndView result;
		Placeholder placeholder;

		//TODO Comprueba si el contest del placeholder es nulo, servirá de algo... chaaan
		try{
		
			placeholder = placeholderService.findOne(placeholderId);
			Assert.isNull(placeholder.getContest());
			result = createEditModelAndView(placeholder);
			
		}catch(Exception oops){

			
			result = new ModelAndView("redirect:/placeholder/list.do");
		
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid Placeholder placeholder, BindingResult binding) {

		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(placeholder);
		} else {
			try {
				placeholder = placeholderService.save(placeholder);
				result = list(0);

			} catch (Throwable oops) {
				result = createEditModelAndView(placeholder, "placeholder.commit.error");
				result.addObject("placeholder", placeholder);
			}
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Placeholder placeholder, BindingResult binding) {
		ModelAndView result;

		try {
			placeholderService.delete(placeholder);
			result = list(0);
		} catch (Throwable oops) {
			result = createEditModelAndView(placeholder, "placeholder.commit.error");
		}

		return result;
	}
	
	
	
	
	
 	
 
}