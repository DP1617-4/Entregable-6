

package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RecipeService;
import services.SocialUserService;
import services.UserService;

import controllers.AbstractController;


@Controller
@RequestMapping("/announcement/customer")
public class RecipeUserController extends AbstractController {
	
	// Services ---------------------------------------------------------------

	@Autowired
	private RecipeService announcementService;	
	@Autowired
	private UserService customerService;	
	@Autowired
	private SocialUserService socialUserService;
	
	// Constructors -----------------------------------------------------------
	
	public RecipeUserController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Announcement> announcements;
		Collection<Announcement> registeredAnnouncements;

		announcements = announcementService.findAllActive();
		registeredAnnouncements = announcementService.findRegistered();
		
		result = new ModelAndView("announcement/list");
		result.addObject("requestURI", "announcement/customer/list.do");
		result.addObject("announcements", announcements);
		result.addObject("registeredAnnouncements", registeredAnnouncements);
		
		return result;
	}

	
}