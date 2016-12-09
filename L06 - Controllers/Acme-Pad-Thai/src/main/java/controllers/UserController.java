package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.UserAccount;
import services.UserService;

import controllers.AbstractController;
import domain.Comment;
import domain.Folder;
import domain.Ingredient;
import domain.MasterClass;
import domain.Recipe;
import domain.Score;
import domain.SocialIdentity;
import domain.SocialUser;
import domain.User;
import forms.FilterString;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {
	
	// Services ---------------------------------------------------------------
	
			@Autowired
			private UserService userService;
	
	// Constructors -----------------------------------------------------------
	
			public UserController() {
				super();
			}

			// Listing ----------------------------------------------------------------
			
			@RequestMapping(value = "/list", method = RequestMethod.GET)
			public ModelAndView list() {
				
				ModelAndView result;
		 		Collection<User> users;
		 		FilterString filter = new FilterString();
		 
		 		users = userService.findAll();
		 		
				result = new ModelAndView("user/list");
				result.addObject("requestURI", "user/list.do");
		 		result.addObject("users", users);
		 		result.addObject("filterString", filter);
		 		
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
			
			@RequestMapping(value = "/filter", method = RequestMethod.POST, params = "filter")
			public ModelAndView filter(@Valid FilterString filterString, BindingResult binding) {
				
				ModelAndView result;
				
				if (binding.hasErrors()) {
					result = new ModelAndView("redirect:list.do");
				} else {

				
					result = new ModelAndView("redirect:filter.do?filter="+filterString.getFilter());
					
				}
					
				return result;
			}
			
			
			@RequestMapping(value = "/filter", method = RequestMethod.GET)
			public ModelAndView listFilter(@RequestParam String filter) {
				ModelAndView result;
				
		 		FilterString filter2 = new FilterString();
				Collection<User> users;
				
				try {
					users = userService.findAllFiltered(filter);	
					result = new ModelAndView("recipe/list");
					result.addObject("requestURI", "recipe/filter.do?filter="+filter);
					result.addObject("users", users);
					result.addObject("filterString", filter2);
					
				} catch (Throwable oops) {
					result = new ModelAndView("redirect:list.do");			
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
