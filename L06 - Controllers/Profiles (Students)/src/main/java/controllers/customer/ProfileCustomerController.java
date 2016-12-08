/* ProfileCustomerController.java
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers.customer;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.ProfileService;

import controllers.AbstractController;
import domain.Customer;
import domain.Profile;

@Controller
@RequestMapping("/profile/customer")
public class ProfileCustomerController extends AbstractController {


	//Constructor
	
	public ProfileCustomerController(){
		super();
	}
	
	//Services
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private CustomerService customerService;
	
	
	@RequestMapping(value = "/list", method= RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Profile> profiles;
		Collection<Profile> likes;
		Customer principal;
		
		profiles = profileService.findAll();
		principal = customerService.findByPrincipal();
		likes = principal.getLikes();
		
		
		result = new ModelAndView("profile/list");
		result.addObject("profiles", profiles);
		result.addObject("likes", likes);
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(){
		ModelAndView result;
		Profile profile;
		
		profile = profileService.findByPrincipal();
		
		result = new ModelAndView("profile/edit");
		result.addObject("profile", profile);
		
		return result;
		
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Profile profile, BindingResult binding){
		ModelAndView result;
		
		
		if(binding.hasErrors()){
			result = createEditModelAndView(profile, null);
		}else{
			try{
				profileService.save(profile);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops){
					result = createEditModelAndView(profile, "profile.commit.error");
			}
		}
		return result;
		
		
	}
	
	@RequestMapping(value = "/dislike", method = RequestMethod.GET)
	public ModelAndView dislike(@RequestParam int profileId){
		ModelAndView result;
		
		
		profileService.removeLiker(profileId);
		result = new ModelAndView("redirect:list.do");


		
		return result;
	}
	
	
	@RequestMapping(value = "/like", method = RequestMethod.GET)
	public ModelAndView like(@RequestParam int profileId){
		ModelAndView result;
		
		
		profileService.addLiker(profileId);
		result = new ModelAndView("redirect:list.do");
		


		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Profile profile, String message) {
		ModelAndView result;
		
		
		result = new ModelAndView("profile/edit");
		result.addObject("profile", profile);
		result.addObject("message", message);

		return result;
	}
	
	
}