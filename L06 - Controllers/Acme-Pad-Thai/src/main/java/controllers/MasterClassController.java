

package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.LearningMaterialService;
import services.MasterClassService;
import controllers.AbstractController;
import domain.LearningMaterial;
import domain.MasterClass;


@Controller
@RequestMapping("/masterClass")
public class MasterClassController extends AbstractController {
	
	// Services ---------------------------------------------------------------

	@Autowired
	private MasterClassService masterClassService;
	
	@Autowired
	private LearningMaterialService learningMaterialService;
	
	// Constructors -----------------------------------------------------------
	
	public MasterClassController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
 	public ModelAndView list() {
 		
 		ModelAndView result;
 		Collection<MasterClass> masterClasses;
 
 		masterClasses = masterClassService.findAllNotDeleted();
 		
		result = new ModelAndView("masterClass/list");
		result.addObject("requestURI", "masterClass/list.do");
 		result.addObject("masterClasses", masterClasses);
 		
 		return result;
 	}
	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int masterClassId) {
		
		ModelAndView result;
		MasterClass masterClass;
		masterClass = masterClassService.findOne(masterClassId);
		Collection<LearningMaterial> learningMaterials = learningMaterialService.findAllByMasterClass(masterClass.getId());
		
		result = new ModelAndView("masterClass/edit");
		result.addObject("requestURI", "masterClass/display.do");
		result.addObject("masterClass", masterClass);
		result.addObject("learningMaterials", learningMaterials );
		result.addObject("display", true);
		result.addObject("cancelURI", "masterClass/list.do");
		
		return result;
	}
 	
 
}