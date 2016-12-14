package controllers.sponsor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BillService;
import services.SponsorService;
import controllers.AbstractController;
import domain.Bill;
import domain.Sponsor;

@Controller
@RequestMapping("/bill/sponsor")
public class BillSponsorController extends AbstractController {
	
	// Services ---------------------------------------------------------------
	@Autowired
	private BillService billService;
	
	@Autowired
	private SponsorService sponsorService;
	
	// Constructors -----------------------------------------------------------
	public BillSponsorController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
 	public ModelAndView list() {
 		ModelAndView result;
 		Collection<Bill> bills;
 		Sponsor sponsor = sponsorService.findByPrincipal();
 
 		bills = billService.findAllByPrincipal();
 		
		result = new ModelAndView("bill/list");
		result.addObject("requestURI", "bill/list.do");
 		result.addObject("bills", bills);
 		result.addObject("sponsor", sponsor);
 		
 		return result;
 	}
	
	// Creation ---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Sponsor sponsor = sponsorService.findByPrincipal();
		Bill bill = billService.create(sponsor);
		
		result = createEditModelAndView(bill);
		result.addObject("bill", bill);
		
		return result;
	}
	
	// Edition ----------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int billId) {
		ModelAndView result;
		Bill bill;

		bill = billService.findOneToEdit(billId);
		Assert.notNull(bill);
		result = createEditModelAndView(bill);

		return result;
	}
	
	
	// Ancillary methods ------------------------------------------------------
	protected ModelAndView createEditModelAndView(Bill b) {
		ModelAndView result;

		result = createEditModelAndView(b, null);
		result.addObject("requestURI", "bill/sponsor/edit.do");
		result.addObject("cancelURI", "bill/sponsor/list.do");
		
		return result;
	}	
	
	
	
	protected ModelAndView createEditModelAndView(Bill b, String message) {
		ModelAndView result;
		
		result = new ModelAndView("bill/edit");
		result.addObject("bill", b);
		result.addObject("message", message);
		result.addObject("requestURI", "bill/sponsor/edit.do");
		result.addObject("cancelURI", "bill/sponsor/list.do");

		return result;
	}
}
