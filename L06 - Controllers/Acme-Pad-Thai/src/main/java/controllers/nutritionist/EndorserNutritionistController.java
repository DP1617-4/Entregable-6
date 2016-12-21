package controllers.nutritionist;

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

import domain.Actor;
import domain.Endorser;
import domain.Endorser;
import domain.SocialUser;
import domain.User;
import forms.FilterString;

import services.EndorserService;
import services.IngredientService;
import services.PropertyService;
import services.ValueService;

@Controller
@RequestMapping("/endorser/nutritionist")
public class EndorserNutritionistController {
	
	// Services ---------------------------------------------------------------

		@Autowired
		private EndorserService endorserService;	
		@Autowired
		private IngredientService ingredientService;
		@Autowired
		private PropertyService propertyService;
		@Autowired
		private ValueService valueService;
		// Constructors -----------------------------------------------------------
		
		public EndorserNutritionistController() {
			super();
		}
		
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView list() {

			ModelAndView result;
			Collection<Endorser> endorsers;
			String requestURI = "endorser/nutritionist/list.do";

			endorsers = endorserService.findAll();

			result = new ModelAndView("endorser/list");
			result.addObject("requestURI", requestURI);
			result.addObject("endorsers", endorsers);

			return result;
		}

		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create() {
			ModelAndView result;
			Endorser endorser;

			endorser = endorserService.create();
			result = createEditModelAndView(endorser);

			return result;
		}
		
		protected ModelAndView createEditModelAndView(Endorser endorser) {
			ModelAndView result;

			result = createEditModelAndView(endorser, null);

			return result;
		}

		protected ModelAndView createEditModelAndView(Endorser endorser, String message) {
			ModelAndView result;

			result = new ModelAndView("endorser/edit");
			result.addObject("endorser", endorser);
			result.addObject("message", message);

			return result;
		}
		
		@RequestMapping(value = "/edit", method = RequestMethod.GET)
		public ModelAndView edit(@RequestParam int endorserId) {
			ModelAndView result;
			Endorser endorser;

			endorser = endorserService.findOne(endorserId);
			result = createEditModelAndView(endorser);

			return result;
		}

		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid Endorser endorser, BindingResult binding) {

			ModelAndView result;
			if (binding.hasErrors()) {
				result = createEditModelAndView(endorser);
			} else {

				try {
					endorser = endorserService.save(endorser);
					result = new ModelAndView(
							"redirect:/endorser/nutritionist/list.do");

				} catch (Throwable oops) {
					result = createEditModelAndView(endorser, "endorser.commit.error");
					result.addObject("endorser", endorser);
				}
			}

			return result;
		}

		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
		public ModelAndView delete(Endorser endorser, BindingResult binding) {
			ModelAndView result;

			try {
				endorserService.delete(endorser);
				result = new ModelAndView("redirect:/nutritionist/display.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(endorser, "endorser.commit.error");
			}

			return result;
		}

}
