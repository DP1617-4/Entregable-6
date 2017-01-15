package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CampaignService;
import controllers.AbstractController;
import domain.Campaign;

@Controller
@RequestMapping("/campaign/administrator")
public class CampaignAdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private CampaignService campaignService;

	// Constructors -----------------------------------------------------------
	public CampaignAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Campaign> campaigns;

		campaigns = campaignService.findAllNotDeleted();

		result = new ModelAndView("campaign/list");
		result.addObject("requestURI", "campaign/administrator/list.do");
		result.addObject("campaigns", campaigns);

		return result;
	}

	// Other methods ----------------------------------------------------------
	@RequestMapping(value = "/star", method = RequestMethod.GET)
	public ModelAndView payBill(@RequestParam int campaignId) {
		ModelAndView result;
		Campaign campaign;

		campaign = campaignService.findOne(campaignId);
		Assert.notNull(campaign);
		campaignService.modifyStarred(campaign);
		campaign = campaignService.saveAdmin(campaign);

		result = new ModelAndView("redirect: list.do");

		return result;
	}

}
