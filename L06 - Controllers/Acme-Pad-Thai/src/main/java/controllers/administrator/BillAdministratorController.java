package controllers.administrator;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.BillService;
import controllers.AbstractController;
import domain.Bill;

@Controller
@RequestMapping("/bill/administrator")
public class BillAdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private BillService billService;

	// Constructors -----------------------------------------------------------
	public BillAdministratorController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Bill> bills;
		String requestURI = "bill/administrator/list.do";

		bills = billService.findAll();

		result = new ModelAndView("bill/list");
		result.addObject("requestURI", requestURI);
		result.addObject("bills", bills);

		return result;
	}

	// Other methods ----------------------------------------------------------
	@RequestMapping(value = "/compute", method = RequestMethod.GET)
	public ModelAndView computeBills() {
		ModelAndView result;

		billService.computeProcedureMonthlyBills();

		result = new ModelAndView("redirect:list.do");

		return result;
	}

	@RequestMapping(value = "/mail", method = RequestMethod.GET)
	public ModelAndView generateEmails() {
		ModelAndView result;

		billService.sendMessageSponsors();

		result = new ModelAndView("redirect:/welcome/index.do");

		return result;
	}

}