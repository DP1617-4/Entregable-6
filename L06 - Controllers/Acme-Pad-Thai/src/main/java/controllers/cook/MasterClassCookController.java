

package controllers.cook;

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

import services.ActorService;
import services.LearningMaterialService;
import services.MasterClassService;
import controllers.AbstractController;
import domain.Actor;
import domain.LearningMaterial;
import domain.MasterClass;


@Controller
@RequestMapping("/masterClass/cook")
public class MasterClassCookController extends AbstractController {
	
	// Services ---------------------------------------------------------------

	@Autowired
	private MasterClassService masterClassService;
	
	@Autowired
	private LearningMaterialService learningMaterialService;
	
	@Autowired
	private ActorService actorService;
	
	// Constructors -----------------------------------------------------------
	
	public MasterClassCookController() {
		super();
	}
	
	@RequestMapping(value = "/listOwn", method = RequestMethod.GET)
 	public ModelAndView listOwn() {
 		
 		ModelAndView result;
 		Collection<MasterClass> masterClasses;
 		Actor actor = actorService.findByPrincipal();
 
 		masterClasses = masterClassService.findImpartedByPrincipal();
 		
		result = new ModelAndView("masterClass/list");
		result.addObject("requestURI", "masterClass/list.do");
 		result.addObject("masterClasses", masterClasses);
 		result.addObject("actor", actor);
 		
 		return result;
 	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		
		ModelAndView result;
		MasterClass masterClass = masterClassService.create();
		
		result = createEditModelAndView(masterClass);
		result.addObject("masterClass", masterClass);
		
		return result;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int masterClassId) {
		ModelAndView result;
		MasterClass masterClass;

		masterClass = masterClassService.findOneToEdit(masterClassId);
		Assert.notNull(masterClass);
		result = createEditModelAndView(masterClass);
		
 		Collection<LearningMaterial> learningMaterials = learningMaterialService.findAllByMasterClass(masterClassId);
 		result.addObject("learningMaterials", learningMaterials );

		return result;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid MasterClass masterClass, BindingResult binding) {
		
		ModelAndView result;
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(masterClass);
		} else {
				try {
					masterClassService.save(masterClass);
					result = new ModelAndView("redirect:/masterClass/cook/listOwn.do");					
				} catch (Throwable oops) {
					result = createEditModelAndView(masterClass, "masterClass.commit.error");

			 		Collection<LearningMaterial> learningMaterials = learningMaterialService.findAllByMasterClass(masterClass.getId());
			 		result.addObject("learningMaterials", learningMaterials );

				}
		}
			
		return result;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(MasterClass masterClass, BindingResult binding) {
		ModelAndView result;

		try {			
			masterClassService.delete(masterClass);
			result = new ModelAndView("redirect:/masterClass/cook/listOwn.do");						
		} catch (Throwable oops) {
			result = createEditModelAndView(masterClass, "masterClass.commit.error");
	 		Collection<LearningMaterial> learningMaterials = learningMaterialService.findAllByMasterClass(masterClass.getId());
	 		result.addObject("learningMaterials", learningMaterials );

		}

		return result;
	}
	
	protected ModelAndView createEditModelAndView(MasterClass masterClass) {
		ModelAndView result;

		result = createEditModelAndView(masterClass, null);
		
		return result;
	}	
	
	
	
	protected ModelAndView createEditModelAndView(MasterClass masterClass, String message) {
		ModelAndView result;
		
		result = new ModelAndView("masterClass/edit");
		result.addObject("masterClass", masterClass);
		result.addObject("errorMessage", message);
		result.addObject("requestURI", "masterClass/cook/edit.do");
		result.addObject("cancelURI", "masterClass/cook/listOwn.do");

		return result;
	}
 	
 
}