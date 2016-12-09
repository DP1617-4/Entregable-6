package controllers.user;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.UserService;

import controllers.AbstractController;
import domain.User;
import forms.FilterString;

@Controller
@RequestMapping("/user")
public class ListUsersController extends AbstractController {
	
	// Services ---------------------------------------------------------------
	
		@Autowired
		private UserService userService;	

		// Constructors -----------------------------------------------------------
		
		public ListUsersController() {
			super();
		}

		// Listing ----------------------------------------------------------------
		
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView list() {
			
			ModelAndView result;
			Collection<User> users;

			users = userService.findAll();
			
			result = new ModelAndView("user/list");
			result.addObject("requestURI", "user/list.do");
			result.addObject("users", users);
			
			return result;
		}
		
		@RequestMapping(value = "/filter", method = RequestMethod.POST, params = "filter")
		public ModelAndView save(@Valid FilterString filterString) {
			ModelAndView result;
			
			Collection<User> users;
			
			try {
				users = userService.findAllFiltered(filterString.getFilter());	
				result = new ModelAndView("user/list");
				result.addObject("requestURI", "user/filter.do");
				result.addObject("users", users);
				
			} catch (Throwable oops) {
				result = new ModelAndView("redirect:list.do");			
			}
			

			return result;
		}

}
