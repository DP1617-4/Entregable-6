package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.SponsorService;
import domain.Sponsor;

@Controller
@RequestMapping("/sponsor")
public class SponsorController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private SponsorService sponsorService;
	
	// Constructors -----------------------------------------------------------
	public SponsorController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		
		Sponsor sponsor = sponsorService.findByPrincipal();

		result = new ModelAndView("sponsor/display");
		result.addObject("requestURI", "sponsor/display.do");
		result.addObject("sponsor", sponsor);

		return result;
	}
	
	// Creation ---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		Sponsor sponsor = sponsorService.create();
		
		result = createEditModelAndView(sponsor);
		
		return result;
	}

	// Edition ----------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Sponsor sponsor;

		sponsor = sponsorService.findByPrincipal();
		Assert.notNull(sponsor);
		result = createEditModelAndView(sponsor);

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit (@Valid Sponsor sponsor, BindingResult binding) {
		ModelAndView result;
		
		if(binding.hasErrors()){
			result = createEditModelAndView(sponsor);
		} else {
			try {
				sponsorService.save(sponsor);
				if (sponsor.getId() == 0) {
					result = new ModelAndView("redirect:../security/login.do");
				} else {
					result = new ModelAndView("redirect: display.do");
				}
				
			} catch (Throwable oops){
				result = createEditModelAndView(sponsor, "sponsor.commit.error");
			}
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------
	protected ModelAndView createEditModelAndView(Sponsor sponsor) {
		ModelAndView result;
		result = createEditModelAndView(sponsor, null);
		result.addObject("requestURI", "security/login.do");
		result.addObject("cancelURI", "welcome/index.do");
		return result;
	}

	protected ModelAndView createEditModelAndView(Sponsor sponsor,
			String message) {
		ModelAndView result;
		result = new ModelAndView("sponsor/edit");
		result.addObject("sponsor", sponsor);
		result.addObject("message", message);
		result.addObject("requestURI", "security/login.do");
		result.addObject("cancelURI", "welcome/index.do");
		return result;
	}
}
