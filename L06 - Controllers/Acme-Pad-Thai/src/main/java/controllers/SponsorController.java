package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

	// Creation ---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		
		Sponsor sponsor = sponsorService.create();
		result = createEditModelAndView(sponsor);
		
		return result;
	}

	// Edition ----------------------------------------------------------------
//	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
//	public ModelAndView edit(@Valid Sponsor sponsor,
//			BindingResult binding) {
//		ModelAndView result;
//		boolean bindingError;
//		
//		if (binding.hasFieldErrors("creditCard")) {
//			result = createEditModelAndView(sponsor);
//		} else {
//			try {
//				sponsorService.save(sponsor);
//				// Si no tiene tarjeta asignada aun.
////				if (sponsor.getCreditCard() == null) {
////					CreditCard credit = creditCardService.create(saved);
////					result = createEditModelAndView(credit);
////					result.addObject("creditCard", credit);
////					result = new ModelAndView("redirect:sponsor/creditCard/edit.do");			
////				}
//				result = new ModelAndView("redirect:creditCard/edit.do");
//			} catch (Throwable Oops) {
//				result = createEditModelAndView(sponsor, "sponsor.registrarion.error");
//				result.addObject("sponsor", sponsor);
//			}
//		}
//		return result;
//	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit (@Valid Sponsor sponsor, BindingResult binding) {
		ModelAndView result;
		boolean bindingError;
		
		if (binding.hasFieldErrors("creditCard")) {
			bindingError = binding.getErrorCount() > 1;
		}else{
			bindingError = binding.getErrorCount() > 0;
		}
		
		if(bindingError){
			result = createEditModelAndView(sponsor);
		} else {
			try {
				sponsorService.save(sponsor);
				result = new ModelAndView("redirect:../security/login.do");
				result.addObject("messageStatus", "sponsor.commit.ok");
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
		result = new ModelAndView("sponsor/edit");
		result.addObject("sponsor", sponsor);
		result.addObject("message", message);
		return result;
	}
}