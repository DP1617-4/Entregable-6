package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CreditCardService;
import services.SponsorService;
import domain.CreditCard;
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

	// Creation ---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Sponsor sponsor = sponsorService.create();
		result = createEditModelAndView(sponsor);
		return result;
	}

	// Edition ----------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView continueSubmit(@Valid Sponsor sponsor,
			BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(sponsor);
		} else {
			try {
				Sponsor saved = sponsorService.save(sponsor);
				// Si no tiene tarjeta asignada aun.
				if (saved.getCreditCard() == null) {
					CreditCard credit = creditCardService.create(saved);
					result = createEditModelAndView(credit);
					result.addObject("creditCard", credit);
				}
				result = new ModelAndView("redirect:/creditCard/edit.do");
			} catch (Throwable Oops) {
				result = createEditModelAndView(sponsor, "sponsor.registrarion.error");
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
	
	protected ModelAndView createEditModelAndView(CreditCard credit) {
		ModelAndView result;
		result = createEditModelAndView(credit, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(CreditCard credit,
			String message) {
		ModelAndView result;
		result = new ModelAndView("creditCard/edit");
		result.addObject("creditCard", credit);
		result.addObject("message", message);
		return result;
	}
}
