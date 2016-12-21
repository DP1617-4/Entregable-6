/* AdministratorController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.SystemConfiguration;
import services.SystemConfigurationService;

@Controller
@RequestMapping("/systemConfiguration/administrator")
public class SystemConfigurationAdministratorController extends AbstractController {
	
	@Autowired
	SystemConfigurationService systemConfigurationService;

	// Constructors -----------------------------------------------------------
	
	public SystemConfigurationAdministratorController() {
		super();
	}
		
	// Action-1 ---------------------------------------------------------------		

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		SystemConfiguration systemConfiguration;

		systemConfiguration = systemConfigurationService.findMain();
		result = createEditModelAndView(systemConfiguration);

		return result;
	}
	
	@RequestMapping("/setFee")
	public ModelAndView setFee(@RequestParam(required=true) Double fee) {
		ModelAndView result;
		SystemConfiguration systemConfiguration = systemConfigurationService.findMain();
		systemConfiguration.setFee(fee);
		systemConfigurationService.save(systemConfiguration);
		
		result = new ModelAndView("redirect:edit.do");
		return result;
	}
	
	@RequestMapping("/addKeyword")
	public ModelAndView addKeyword(@RequestParam(required=true) String keyword) {
		ModelAndView result;
		SystemConfiguration systemConfiguration = systemConfigurationService.findMain();
		Collection<String> words = systemConfiguration.getKeywords();
		words.add(keyword);
		systemConfiguration.setKeywords(words);
		systemConfigurationService.save(systemConfiguration);

		result = new ModelAndView("redirect:edit.do");
		return result;
	}
	
	@RequestMapping("/deleteWord")
	public ModelAndView deleteWord(@RequestParam(required=true) String word) {
		ModelAndView result;
		SystemConfiguration systemConfiguration = systemConfigurationService.findMain();
		Collection<String> words = systemConfiguration.getKeywords();
		words.remove(word);
		systemConfiguration.setKeywords(words);
		systemConfigurationService.save(systemConfiguration);

		result = new ModelAndView("redirect:edit.do");
		return result;
	}
	
	protected ModelAndView createEditModelAndView(SystemConfiguration systemConfiguration) {
		ModelAndView result;

		result = createEditModelAndView(systemConfiguration, null);
		result.addObject("requestURI", "systemConfiguration/administrator/edit.do");
		result.addObject("cancelURI", "welcome/index.do");
		
		return result;
	}	
	
	protected ModelAndView createEditModelAndView(SystemConfiguration systemConfiguration, String message) {
		ModelAndView result;
		
		result = new ModelAndView("systemConfiguration/edit");
		result.addObject("systemConfiguration", systemConfiguration);
		result.addObject("message", message);
		result.addObject("requestURI", "systemConfiguration/administrator/edit.do");
		result.addObject("cancelURI", "welcome/index.do");

		return result;
	}
	
}