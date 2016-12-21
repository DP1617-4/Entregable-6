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
import services.SocialUserService;
import services.NutritionistService;
import controllers.AbstractController;
import domain.Actor;
import domain.Nutritionist;
import domain.SocialUser;
import domain.Nutritionist;
import forms.FilterString;

@Controller
@RequestMapping("/nutritionist/socialuser")
public class NutritionistSocialUserController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private SocialUserService socialUserService;
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private NutritionistService nutritionistService;

	// Constructors -----------------------------------------------------------

	public NutritionistSocialUserController() {
		super();
	}
	
	@RequestMapping(value = "/follow", method = RequestMethod.GET)
	public ModelAndView followNutritionists(@RequestParam int nutritionistId){
		
		ModelAndView result;
		Nutritionist followed;
		followed = nutritionistService.findOne(nutritionistId);
		socialUserService.follow(followed);
		
		result = new ModelAndView("redirect:/nutritionist/list.do");
		
		
		return result;
	}
	
	@RequestMapping(value = "/unfollow", method = RequestMethod.GET)
	public ModelAndView unfollowNutritionists(@RequestParam int nutritionistId){
		
		ModelAndView result;
		Nutritionist unfollowed;
		unfollowed = nutritionistService.findOne(nutritionistId);
		socialUserService.unfollow(unfollowed);
		
		result = new ModelAndView("redirect:/nutritionist/list.do");
		
		
		return result;
	}
	
}
