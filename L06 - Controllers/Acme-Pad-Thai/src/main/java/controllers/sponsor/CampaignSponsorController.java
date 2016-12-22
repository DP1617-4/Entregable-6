package controllers.sponsor;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BannerService;
import services.CampaignService;
import services.SponsorService;
import controllers.AbstractController;
import domain.Banner;
import domain.Campaign;
import domain.Sponsor;

@Controller
@RequestMapping("/campaign/sponsor")
public class CampaignSponsorController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private CampaignService campaignService;

	@Autowired
	private SponsorService sponsorService;

	@Autowired
	private BannerService bannerService;

	// Constructors -----------------------------------------------------------
	public CampaignSponsorController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Campaign> campaigns;
		Sponsor sponsor = sponsorService.findByPrincipal();

		campaigns = campaignService.findCampaignsByPrincipal();

		result = new ModelAndView("campaign/list");
		result.addObject("requestURI", "campaign/sponsor/list.do");
		result.addObject("campaigns", campaigns);
		result.addObject("sponsor", sponsor);

		return result;
	}

	// Creation ---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Campaign campaign = campaignService.create();

		result = createEditModelAndView(campaign);
		result.addObject("campaign", campaign);

		return result;
	}

	// Edition ----------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int campaignId) {
		ModelAndView result;
		Campaign campaign;

		campaign = campaignService.findOne(campaignId);
		Assert.notNull(campaign);
		result = createEditModelAndView(campaign);

		Collection<Banner> banners = bannerService
				.findAllByCampaign(campaignId);
		result.addObject("banners", banners);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid Campaign campaign, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(campaign);
		} else {
			try {
				campaignService.save2(campaign);
				result = new ModelAndView("redirect:/campaign/sponsor/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(campaign,
						"campaign.commit.error");

				Collection<Banner> banners = bannerService
						.findAllByCampaign(campaign.getId());
				result.addObject("banners", banners);
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Campaign campaign, BindingResult binding) {
		ModelAndView result;

		try {
			if (campaignService.activeCampaign(campaign) == false
					&& campaignService.campaignPassed(campaign) == false) {
				campaignService.delete2(campaign);
				result = new ModelAndView("redirect:/campaign/sponsor/list.do");
			}
			result = new ModelAndView("redirect:/campaign/sponsor/list.do");

		} catch (Throwable oops) {
			result = createEditModelAndView(campaign, "campaign.commit.error");

			Collection<Banner> banners = bannerService
					.findAllByCampaign(campaign.getId());
			result.addObject("banners", banners);
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------
	protected ModelAndView createEditModelAndView(Campaign campaign) {
		ModelAndView result;

		result = createEditModelAndView(campaign, null);
		result.addObject("requestURI", "campaign/sponsor/edit.do");
		result.addObject("cancelURI", "campaign/sponsor/list.do");

		return result;
	}

	protected ModelAndView createEditModelAndView(Campaign campaign,
			String message) {
		ModelAndView result;

		result = new ModelAndView("campaign/edit");
		result.addObject("campaign", campaign);
		result.addObject("errorMessage", message);
		result.addObject("requestURI", "campaign/sponsor/edit.do");
		result.addObject("cancelURI", "campaign/sponsor/list.do");

		return result;
	}
}
