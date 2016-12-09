package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CreditCardService;
import domain.CreditCard;
import domain.Sponsor;

@Controller
@RequestMapping("/sponsor")
public class CreditCardController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private CreditCardService creditCardService;

	// Constructors -----------------------------------------------------------
	public CreditCardController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	// Creation ---------------------------------------------------------------
//	@RequestMapping(value = "/create", method = RequestMethod.GET)
//	public ModelAndView create() {
//		ModelAndView result;
//		CreditCard creditCard;
//		Sponsor sponsor = creditCard.getSponsor(); // Que sponsor le meto?
//		creditCard = creditCardService.create(sponsor);
//		Assert.notNull(creditCard);
//		result = createEditModelAndView(creditCard);
//		result = new ModelAndView("redirect:/creditcard/create.do");
//		return result;
//	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid CreditCard creditCard, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(creditCard);
		} else {
			try {
				creditCardService.save(creditCard);
				result = new ModelAndView("redirect:../security/login.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(creditCard,
						"creditCard.registration.error");
			}
		}
		return result;
	}

	// Edition ----------------------------------------------------------------

	// Ancillary methods ------------------------------------------------------
	protected ModelAndView createEditModelAndView(CreditCard creditCard) {
		ModelAndView result;
		result = createEditModelAndView(creditCard, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(CreditCard creditCard,
			String message) {
		ModelAndView result;
		result = new ModelAndView("creditCard/edit");
		result.addObject("creditCard", creditCard);
		result.addObject("message", message);
		return result;
	}
}
