package controllers.sponsor;

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
import controllers.AbstractController;
import domain.CreditCard;
import domain.Sponsor;

@Controller
@RequestMapping("/creditCard/sponsor")
public class CreditCardSponsorController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private CreditCardService creditCardService;

	@Autowired
	private SponsorService sponsorService;

	// Constructors -----------------------------------------------------------
	public CreditCardSponsorController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {
		ModelAndView result;
		
		Sponsor sponsor = sponsorService.findByPrincipal();
		CreditCard credit = creditCardService.findCreditCardByPrincipal();

		result = new ModelAndView("creditCard/list");
		result.addObject("creditCard", credit);
		result.addObject("sponsor", sponsor);

		return result;
	}

	// Creation ---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		CreditCard credit;
		Sponsor sponsor = sponsorService.findByPrincipal();

		credit = creditCardService.create(sponsor);
		Assert.notNull(credit);

		result = createEditModelAndView(credit);
		return result;
	}

	// Edition ----------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int creditId) {
		ModelAndView result;
		CreditCard credit;

		credit = creditCardService.findOneToEdit(creditId);
		Assert.notNull(creditId);
		result = createEditModelAndView(credit);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid CreditCard c, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(c);
		} else {
			try {
				creditCardService.save(c);
				result = new ModelAndView("redirect:display.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(c,
						"creditCard.registration.error");
			}
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------
	protected ModelAndView createEditModelAndView(CreditCard c) {
		ModelAndView result;
		result = createEditModelAndView(c, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(CreditCard c, String message) {
		ModelAndView result;
		result = new ModelAndView("creditcard/edit");
		result.addObject("creditCard", c);
		result.addObject("errorMessage", message);
		return result;
	}
}
