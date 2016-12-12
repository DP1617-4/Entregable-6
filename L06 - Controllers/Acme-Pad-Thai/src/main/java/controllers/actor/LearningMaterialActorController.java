

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
import controllers.AbstractController;
import domain.Actor;
import domain.LearningMaterial;


@Controller
@RequestMapping("/learningMaterial/actor")
public class LearningMaterialActorController extends AbstractController {
	
	// Services ---------------------------------------------------------------

	@Autowired
	private LearningMaterialService learningMaterialService;
	
	@Autowired
	private ActorService actorService;
	
	// Constructors -----------------------------------------------------------
	
	public LearningMaterialActorController() {
		super();
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
 	public ModelAndView list(@RequestParam int masterClassId) {
 		
 		ModelAndView result;
 		Collection<LearningMaterial> learningMaterials;
 		Actor actor = actorService.findByPrincipal();
 
 		learningMaterials = learningMaterialService.findAllByMasterClass(masterClassId);
 		
		result = new ModelAndView("learningMaterial/list");
		result.addObject("requestURI", "learningMaterial/list.do");
 		result.addObject("learningMaterials", learningMaterials);
 		result.addObject("actor", actor);
 		
 		return result;
 	}

 
}