package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CreditCardService;
import services.SponsorService;
import domain.Sponsor;

@Controller
@RequestMapping("/sponsor")
public class SponsorController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private SponsorService sponsorService;
	
	@Autowired
	private CreditCardService creditCardService;
	
	// Constructors -----------------------------------------------------------
	public SponsorController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam(required = false, defaultValue = "0") int sponsorId) {

		ModelAndView result;
		Sponsor sponsor;
		
		if(sponsorId==0){
			
			sponsor= sponsorService.findByPrincipal();
		}
		else{
			
			sponsor = sponsorService.findOne(sponsorId);
		}


		result = new ModelAndView("sponsor/display");
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
				if (creditCardService.expirationDate(sponsor.getCreditCard())){
					sponsorService.save(sponsor);
					if (sponsor.getId() == 0) {
						result = new ModelAndView("redirect:../security/login.do");
					} else {
						result = new ModelAndView("redirect: display.do");
					}
				}
				else
					result = createEditModelAndView(sponsor, "sponsor.credit.card.wrong");
				
				
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
		return result;
	}

	protected ModelAndView createEditModelAndView(Sponsor sponsor,
			String message) {
		ModelAndView result;
		
		String requestURI = "sponsor/edit.do";
		
		result = new ModelAndView("sponsor/edit");
		result.addObject("sponsor", sponsor);
		result.addObject("errorMessage", message);
		result.addObject("requestURI", requestURI);
		result.addObject("cancelURI", "welcome/index.do");
		return result;
	}
}
