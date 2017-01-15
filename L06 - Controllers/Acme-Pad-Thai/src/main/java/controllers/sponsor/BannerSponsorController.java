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

import services.BannerService;
import services.CampaignService;
import controllers.AbstractController;
import domain.Banner;
import domain.Campaign;

@Controller
@RequestMapping("/banner/sponsor")
public class BannerSponsorController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private BannerService bannerService;

	@Autowired
	private CampaignService campaignService;

	// Constructors -----------------------------------------------------------
	public BannerSponsorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	// Creation ---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int campaignId) {
		ModelAndView result;
		Campaign c = campaignService.findOne(campaignId);
		Banner b = bannerService.create(c);

		result = createEditModelAndView(b);
		result.addObject("banner", b);

		return result;
	}

	// Edition ----------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int bannerId) {
		ModelAndView result;
		Banner b;

		b = bannerService.findOneToEdit(bannerId);
		Assert.notNull(b);
		result = createEditModelAndView(b);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid Banner b, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(b);
		} else {
			try {
				bannerService.save(b);
				result = new ModelAndView(
						"redirect:/campaign/sponsor/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(b, "banner.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Banner b, BindingResult binding) {
		ModelAndView result;

		try {
			bannerService.delete(b);
			result = new ModelAndView(
					"redirect:/campaign/sponsor/edit.do?campaignId="
							+ b.getCampaign().getId());
		} catch (Throwable oops) {
			result = createEditModelAndView(b, "banner.commit.error");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------
	protected ModelAndView createEditModelAndView(Banner b) {
		ModelAndView result;

		result = createEditModelAndView(b, null);
		result.addObject("requestURI", "banner/sponsor/edit.do");
		result.addObject("cancelURI", "campaign/sponsor/edit.do?campaignId="
				+ b.getCampaign().getId());

		return result;
	}

	protected ModelAndView createEditModelAndView(Banner b, String message) {
		ModelAndView result;

		result = new ModelAndView("banner/edit");
		result.addObject("banner", b);
		result.addObject("errorMessage", message);
		result.addObject("requestURI", "banner/sponsor/edit.do");
		result.addObject("cancelURI", "banner/sponsor/list.do?campaignId="
				+ b.getCampaign().getId());

		return result;
	}
}
