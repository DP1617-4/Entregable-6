package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.MasterClassService;
import controllers.AbstractController;


@Controller
@RequestMapping("/masterClass/administrator")
public class MasterClassAdministratorController extends AbstractController {
	
	// Services ---------------------------------------------------------------

	@Autowired
	private MasterClassService masterClassService;
	
	// Constructors -----------------------------------------------------------
	
	public MasterClassAdministratorController() {
		super();
	}

	@RequestMapping(value = "/promote", method = RequestMethod.GET)
	public ModelAndView promote(@RequestParam int masterClassId) {
		
		ModelAndView result;
		masterClassService.promoteDemote(masterClassId);

		result = new ModelAndView("redirect:../../masterClass/actor/list.do");	
		
		return result;
	}
	
	@RequestMapping(value = "/demote", method = RequestMethod.GET)
	public ModelAndView demote(@RequestParam int masterClassId) {
		
		ModelAndView result;
		masterClassService.promoteDemote(masterClassId);

		result = new ModelAndView("redirect:../../masterClass/actor/list.do");	
		
		return result;
	}
 	
 
}