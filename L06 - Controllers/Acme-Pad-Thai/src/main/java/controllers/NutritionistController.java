package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.NutritionistService;
import domain.Actor;
import domain.Nutritionist;
import domain.SocialUser;
import forms.FilterString;

@Controller
@RequestMapping("/nutritionist")
public class NutritionistController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private NutritionistService nutritionistService;
	
	@Autowired
	private ActorService actorService;

	// Constructors -----------------------------------------------------------

	public NutritionistController() {
		super();
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listNutritionists() {

		ModelAndView result;
		Collection<Nutritionist> nutritionists;
		Collection<SocialUser> followed;
		FilterString filter = new FilterString();

		nutritionists = nutritionistService.findAll();

		result = new ModelAndView("nutritionist/list");
		result.addObject("requestURI", "/nutritionist/list.do");
		result.addObject("nutritionists", nutritionists);
		result.addObject("filterString", filter);
		Object access = SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		if (access != "anonymousUser") {

			Actor principal = actorService.findByPrincipal();
			if(principal instanceof SocialUser){
				followed = ((SocialUser) principal).getFollowed();
				result.addObject("followed", followed);
			}
		}

		return result;
	}


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Nutritionist nutritionist;

		nutritionist = nutritionistService.create();
		result = createEditModelAndView(nutritionist);

		return result;
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam(required = false, defaultValue = "0") int nutritionistId) {
		
		ModelAndView result;
		Nutritionist nutritionist;
		
		if(nutritionistId==0){
			
			nutritionist= nutritionistService.findByPrincipal();
		}
		else{
			
			nutritionist = nutritionistService.findOne(nutritionistId);
		}


		result = new ModelAndView("nutritionist/display");
		result.addObject("nutritionist", nutritionist);

		return result;
	}
		
//	@RequestMapping(value = "/edit", method = RequestMethod.GET)
//	public ModelAndView edit() {
//		ModelAndView result;
//		Nutritionist nutritionist;
//
//		nutritionist = nutritionistService.findByPrincipal();		
//		result = createEditModelAndView(nutritionist);
//		
//		result.addObject("nutritionist", nutritionist);
//
//		return result;
//	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Nutritionist nutritionist, BindingResult binding) {
		ModelAndView result;
		if(binding.hasErrors()){
			result = createEditModelAndView(nutritionist);
		}
		else{
			try{
				nutritionist = nutritionistService.save(nutritionist);
				result = new ModelAndView(
						"redirect:/nutritionist/display.do?nutritionistId="
								+ nutritionist.getId());
			} catch (Throwable oops){
				result = createEditModelAndView(nutritionist, "nutritionist.commit.error");
			}
		}
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Nutritionist nutritionist) {
		ModelAndView result;

		result = createEditModelAndView(nutritionist, null);

		return result;
	}
	protected ModelAndView createEditModelAndView(Nutritionist nutritionist, String message) {
		ModelAndView result;

		String requestURI = "nutritionist/edit.do";
		
		result = new ModelAndView("nutritionist/edit");
		result.addObject("nutritionist", nutritionist);
		result.addObject("errorMessage", message);
		result.addObject("requestURI", requestURI);
		
		return result;
	}
	
	@RequestMapping(value = "/filter", method = RequestMethod.POST, params = "filterButton")
	public ModelAndView filterNutritionists(@Valid FilterString filterString,
			BindingResult binding) {

		ModelAndView result;
		Collection<Nutritionist> nutritionists;
		String filter = filterString.getFilter();
		if (binding.hasErrors()) {
			result = new ModelAndView("redirect:list.do");
		} else {

			try {
				nutritionists = nutritionistService.findAllFiltered(filter);
				result = new ModelAndView("nutritionist/list");
				result.addObject("requestURI", "nutritionist/list.do");
				result.addObject("nutritionists", nutritionists);
				result.addObject("filterString", filterString);

			} catch (Throwable oops) {
				result = new ModelAndView("redirect:list.do");
			}
		}

		return result;
	}

	
}

