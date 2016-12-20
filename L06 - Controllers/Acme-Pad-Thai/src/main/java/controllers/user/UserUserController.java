package controllers.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.SocialUserService;
import services.UserService;
import controllers.AbstractController;
import domain.User;

@Controller
@RequestMapping("/user/user")
public class UserUserController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private UserService userService;
	
	@Autowired
	private SocialUserService socialUserService;

	// Constructors -----------------------------------------------------------

	public UserUserController() {
		super();
	}

	// Listing ----------------------------------------------------------------

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
		
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		User user;

		user = userService.findByPrincipal();		
		result = createEditModelAndView(user);
		
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
		
		String requestURI = "user/user/edit.do";

		result = new ModelAndView("user/edit");
		result.addObject("user", user);
		result.addObject("message", message);
		result.addObject("requestURI", requestURI);
		
		return result;
	}

}

