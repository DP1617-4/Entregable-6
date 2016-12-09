

package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.IngredientService;
import services.MasterClassService;
import controllers.AbstractController;
import domain.Ingredient;
import domain.MasterClass;


@Controller
@RequestMapping("/masterClass/user")
public class MasterClassUserController extends AbstractController {
	
	// Services ---------------------------------------------------------------

	@Autowired
	private MasterClassService masterClassService;
	
	@Autowired
	private IngredientService ingredientService;
	
	// Constructors -----------------------------------------------------------
	
	public MasterClassUserController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
 	public ModelAndView list() {
 		
 		ModelAndView result;
 		Collection<MasterClass> masterClasss;
 
 		masterClasss = masterClassService.findAllNotDeleted();
 		
		result = new ModelAndView("masterClass/list");
		result.addObject("requestURI", "masterClass/list.do");
 		result.addObject("masterClasss", masterClasss);
 		
 		return result;
 	}
	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int masterClassId) {
		
		ModelAndView result;
		MasterClass masterClass;
		Collection<Ingredient> ingredientlist = ingredientService.findAllNotDeleted();

		masterClass = masterClassService.findOne(masterClassId);
		
		result = new ModelAndView("masterClass/display");
		result.addObject("requestURI", "masterClass/display.do");
		result.addObject("masterClass", masterClass);
		result.addObject("ingredients", ingredientlist );
		
		return result;
	}
 	
 
}