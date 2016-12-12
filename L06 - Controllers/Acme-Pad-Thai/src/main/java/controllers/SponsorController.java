package controllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.UserAccount;
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
				// Creamos un codificador de hash para la password.
				Md5PasswordEncoder encoder = new Md5PasswordEncoder();
				// Convertimos la pass del usuario a hash.
				String pass = encoder.encodePassword(sponsor.getUserAccount()
						.getPassword(), null);
				// Creamos una nueva cuenta y le pasamos los parametros.
				UserAccount user = new UserAccount();
				user.setId(sponsor.getUserAccount().getId());
				user.setVersion(sponsor.getUserAccount().getVersion());
				user.setUsername(sponsor.getUserAccount().getUsername());
				user.setPassword(pass);
				// Asignamos autoridad al sponsor
				Collection<Authority> authorities = new ArrayList<Authority>();
				Authority a = new Authority();
				a.setAuthority(Authority.SPONSOR);
				authorities.add(a);
				user.setAuthorities(authorities);
				// Sobreescribimos la cuenta de usuario
				sponsor.setUserAccount(user);
				Sponsor saved = sponsorService.save(sponsor);
				// Si no tiene tarjeta asignada aun.
				if (saved.getCreditCard() == null) {
					CreditCard credit = creditCardService.create(saved);
					result = new ModelAndView("creditcard/edit");
					result.addObject("creditCard", credit);
				}
				result = new ModelAndView("redirect:../creditcard/edit.do");

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
}
