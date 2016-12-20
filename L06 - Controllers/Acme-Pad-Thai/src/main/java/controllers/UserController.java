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
import services.SocialUserService;
import services.UserService;
import domain.Actor;
import domain.SocialUser;
import domain.User;
import forms.FilterString;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private UserService userService;
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private SocialUserService socialUserService;

	// Constructors -----------------------------------------------------------

	public UserController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<User> users;
		Collection<SocialUser> followed;
		FilterString filter = new FilterString();

		users = userService.findAll();

		result = new ModelAndView("user/list");
		result.addObject("requestURI", "user/list.do");
		result.addObject("users", users);
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
	
	@RequestMapping(value = "/follow", method = RequestMethod.GET)
	public ModelAndView follow(@RequestParam int userId){
		
		ModelAndView result;
		User followed;
		followed = userService.findOne(userId);
		socialUserService.follow(followed);
		
		result = new ModelAndView("redirect:list.do");
		
		
		return result;
	}
	
	@RequestMapping(value = "/unfollow", method = RequestMethod.GET)
	public ModelAndView unfollow(@RequestParam int userId){
		
		ModelAndView result;
		User unfollowed;
		unfollowed = userService.findOne(userId);
		socialUserService.unfollow(unfollowed);
		
		result = new ModelAndView("redirect:list.do");
		
		
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		User user;

		user = userService.create();
		result = createEditModelAndView(user);

		return result;
	}

	@RequestMapping(value = "/filter", method = RequestMethod.POST, params = "filterButton")
	public ModelAndView filter(@Valid FilterString filterString,
			BindingResult binding) {

		ModelAndView result;
		Collection<User> users;
		String filter = filterString.getFilter();
		if (binding.hasErrors()) {
			result = new ModelAndView("redirect:list.do");
		} else {

			try {
				users = userService.findAllFiltered(filter);
				result = new ModelAndView("user/list");
				result.addObject("requestURI", "user/list.do");
				result.addObject("users", users);
				result.addObject("filterString", filterString);

			} catch (Throwable oops) {
				result = new ModelAndView("redirect:list.do");
			}
		}

		return result;
	}

	
	

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam(required = false, defaultValue = "0") int userId) {

		
		ModelAndView result;
		User user;
		
		if(userId==0){
			
			user= userService.findByPrincipal();
		}
		else{
			
			user = userService.findOne(userId);
		}


		result = new ModelAndView("user/display");
		result.addObject("user", user);

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid User user, BindingResult binding) {
		ModelAndView result;
		if(binding.hasErrors()){
			result = createEditModelAndView(user);
		}
		else{
			try{
				user = userService.save(user);
				result = new ModelAndView(
						"redirect:/user/display.do?userId="
								+ user.getId());
			} catch (Throwable oops){
				result = createEditModelAndView(user, "user.commit.error");
			}
		}
		return result;
	}
	
	protected ModelAndView createEditModelAndView(User user) {
		ModelAndView result;

		result = createEditModelAndView(user, null);

		return result;
	}
	protected ModelAndView createEditModelAndView(User user, String message) {
		ModelAndView result;

		String requestURI = "user/edit.do";
		
		result = new ModelAndView("user/edit");
		result.addObject("user", user);
		result.addObject("message", message);
		result.addObject("requestURI", requestURI);
		
		return result;
	}

}
