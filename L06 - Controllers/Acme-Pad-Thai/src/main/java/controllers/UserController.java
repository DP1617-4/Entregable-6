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

import security.LoginService;
import security.UserAccount;
import services.SocialUserService;
import services.UserService;

import controllers.AbstractController;
import domain.Comment;
import domain.Folder;
import domain.Ingredient;
import domain.MasterClass;
import domain.Quantity;
import domain.Recipe;
import domain.Score;
import domain.SocialIdentity;
import domain.SocialUser;
import domain.Step;
import domain.User;
import forms.AddIngredient;
import forms.AddPicture;
import forms.FilterString;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private UserService userService;
	
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

			User principal = userService.findByPrincipal();
			followed = principal.getFollowed();
			result.addObject("followed", followed);
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

	protected ModelAndView createEditModelAndView(User user) {
		ModelAndView result;

		result = createEditModelAndView(user, null);

		return result;
	}
	protected ModelAndView createEditModelAndView(User user, String message) {
		ModelAndView result;
		UserAccount userAccount;
		Collection<Folder> folders;
		Collection<SocialIdentity> socialIdentities;
		Collection<MasterClass> enroled;
		Collection<Recipe> recipes;
		Collection<SocialUser> followed;
		Collection<SocialUser> followers;
		Collection<Score> scores;
		Collection<Comment> comments;

		if (user.getUserAccount() == null) {
			userAccount = null;
		} else {
			userAccount = user.getUserAccount();
		}

		if (user.getFolders() == null) {
			folders = null;
		} else {
			folders = user.getFolders();
		}

		if (user.getSocialIdentities() == null) {
			socialIdentities = null;
		} else {
			socialIdentities = user.getSocialIdentities();
		}

		if (user.getEnroled() == null) {
			enroled = null;
		} else {
			enroled = user.getEnroled();
		}

		if (user.getRecipes() == null) {
			recipes = null;
		} else {
			recipes = user.getRecipes();
		}

		if (user.getFollowed() == null) {
			followed = null;
		} else {
			followed = user.getFollowed();
		}

		if (user.getFollowers() == null) {
			followers = null;
		} else {
			followers = user.getFollowers();
		}

		if (user.getComments() == null) {
			comments = null;
		} else {
			comments = user.getComments();
		}

		if (user.getScores() == null) {
			scores = null;
		} else {
			scores = user.getScores();
		}

		result = new ModelAndView("user/edit");
		result.addObject("userAccount", userAccount);
		result.addObject("folders", folders);
		result.addObject("socialIdentities", socialIdentities);
		result.addObject("enroled", enroled);
		result.addObject("recipes", recipes);
		result.addObject("followed", followed);
		result.addObject("followers", followers);
		result.addObject("comments", comments);
		result.addObject("scores", scores);

		return result;
	}

}
