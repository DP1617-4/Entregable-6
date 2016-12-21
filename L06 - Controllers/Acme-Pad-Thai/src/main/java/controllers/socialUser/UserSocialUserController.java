package controllers.socialUser;

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
import services.RecipeService;
import services.SocialUserService;
import services.UserService;
import controllers.AbstractController;
import domain.Actor;
import domain.Nutritionist;
import domain.SocialUser;
import domain.User;
import forms.FilterString;

@Controller
@RequestMapping("/user/socialuser")
public class UserSocialUserController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private SocialUserService socialUserService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ActorService actorService;

	// Constructors -----------------------------------------------------------

	public UserSocialUserController() {
		super();
	}

	@RequestMapping(value = "/follow", method = RequestMethod.GET)
	public ModelAndView followUsers(@RequestParam int userId){
		
		ModelAndView result;
		User followed;
		followed = userService.findOne(userId);
		socialUserService.follow(followed);
		
		result = new ModelAndView("redirect:/user/list.do");
		
		
		return result;
	}
	
	@RequestMapping(value = "/unfollow", method = RequestMethod.GET)
	public ModelAndView unfollowUsers(@RequestParam int userId){
		
		ModelAndView result;
		User unfollowed;
		unfollowed = userService.findOne(userId);
		socialUserService.unfollow(unfollowed);
		
		result = new ModelAndView("redirect:/user/list.do");
		
		
		return result;
	}

}