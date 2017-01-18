

package controllers.actor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.LearningMaterialService;
import services.MasterClassService;
import controllers.AbstractController;
import domain.Actor;
import domain.LearningMaterial;
import domain.MasterClass;


@Controller
@RequestMapping("/masterClass/actor")
public class MasterClassActorController extends AbstractController {
	
	// Services ---------------------------------------------------------------

	@Autowired
	private MasterClassService masterClassService;
	
	@Autowired
	private LearningMaterialService learningMaterialService;
	
	@Autowired
	private ActorService actorService;
	
	// Constructors -----------------------------------------------------------
	
	public MasterClassActorController() {
		super();
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
 	public ModelAndView list() {
 		
 		ModelAndView result;
 		Collection<MasterClass> masterClasses;
 		Actor actor = actorService.findByPrincipal();
 
 		masterClasses = masterClassService.findAllNotDeleted();
 		
		result = new ModelAndView("masterClass/list");
		result.addObject("requestURI", "masterClass/actor/list.do");
 		result.addObject("masterClasses", masterClasses);
 		result.addObject("actor", actor);
 		
 		return result;
 	}
	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int masterClassId) {
		
		ModelAndView result;
		MasterClass masterClass;
		
		masterClass = masterClassService.findOne(masterClassId);
 		Actor actor = actorService.findByPrincipal();
		
		result = new ModelAndView("masterClass/edit");
		result.addObject("requestURI", "masterClass/actor/display.do");
		result.addObject("cancelURI", "masterClass/actor/list.do");
		result.addObject("masterClass", masterClass);
		result.addObject("display", true);
 		result.addObject("actor", actor);

 		if(actor.getEnroled().contains(masterClass) || masterClass.getCook().equals(actor)){
 			Collection<LearningMaterial> learningMaterials = learningMaterialService.findAllByMasterClass(masterClassId);
 			result.addObject("learningMaterials", learningMaterials );
 		}
		
		return result;
	}
	
	@RequestMapping(value = "/enrol", method = RequestMethod.GET)
	public ModelAndView enrol(@RequestParam int masterClassId) {
		
		ModelAndView result;		
 		Collection<MasterClass> masterClasses;
 		Actor actor = actorService.findByPrincipal();
 
 		masterClasses = masterClassService.findAllNotDeleted();
 		
		result = new ModelAndView("masterClass/list");
		result.addObject("requestURI", "masterClass/actor/enrol.do");
 		result.addObject("masterClasses", masterClasses);
 		result.addObject("actor", actor);
		try{
			masterClassService.enrol(masterClassId);
		}catch(IllegalArgumentException e){
			result.addObject("errorMessage", "masterClass.enrol.error");
		}
		
		return result;
	}
 	
 
}