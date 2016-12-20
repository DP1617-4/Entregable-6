/* WelcomeController.java
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Actor;
import domain.Banner;
import domain.MasterClass;

import services.ActorService;
import services.BannerService;
import services.MasterClassService;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

	// Services

	@Autowired
	private BannerService bannerService;

	@Autowired
	private MasterClassService masterClassService;

	@Autowired
	private ActorService actorService;

	// Constructors -----------------------------------------------------------

	public WelcomeController() {
		super();
	}

	// Index ------------------------------------------------------------------

	@RequestMapping(value = "/index")
	public ModelAndView index(
			@RequestParam(required = false, defaultValue = "John Doe") String name) {
		ModelAndView result;
		SimpleDateFormat formatter;
		String moment;
		Banner banner;
		Collection<MasterClass> masterClasses;

		banner = bannerService.findRandomStarBanner();
		masterClasses = masterClassService.findPromoted();
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		result = new ModelAndView("welcome/index");
		if(principal!="anonymousUser"){
			
	 		Actor actor = actorService.findByPrincipal();
	 		name = actor.getName()+" "+actor.getSurname();
	 		result.addObject("actor", actor);
		}

		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		moment = formatter.format(new Date());

		result.addObject("name", name);
		result.addObject("moment", moment);
		result.addObject("banner", banner);
		result.addObject("masterClasses", masterClasses);

		return result;
	}
}