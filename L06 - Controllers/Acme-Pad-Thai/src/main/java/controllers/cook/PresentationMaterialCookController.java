

package controllers.cook;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.PresentationMaterialService;
import services.MasterClassService;
import controllers.AbstractController;
import domain.PresentationMaterial;
import domain.MasterClass;


@Controller
@RequestMapping("/presentationMaterial/cook")
public class PresentationMaterialCookController extends AbstractController {
	
	// Services ---------------------------------------------------------------
	
	@Autowired
	private PresentationMaterialService presentationMaterialService;
	
	@Autowired
	private MasterClassService masterClassService;
	
	// Constructors -----------------------------------------------------------
	
	public PresentationMaterialCookController() {
		super();
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int masterClassId) {
		
		ModelAndView result;
		MasterClass masterClass = masterClassService.findOne(masterClassId);
		PresentationMaterial presentationMaterial = presentationMaterialService.create(masterClass);
		
		result = createEditModelAndView(presentationMaterial);
		result.addObject("presentationMaterial", presentationMaterial);
		
		return result;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int presentationMaterialId) {
		ModelAndView result;
		PresentationMaterial presentationMaterial;

		presentationMaterial = presentationMaterialService.findOneToEdit(presentationMaterialId);
		Assert.notNull(presentationMaterial);
		result = createEditModelAndView(presentationMaterial);

		return result;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid PresentationMaterial presentationMaterial, BindingResult binding) {
		
		ModelAndView result;
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(presentationMaterial);
		} else {
				try {
					presentationMaterialService.save(presentationMaterial);
					result = new ModelAndView("redirect:/masterClass/cook/edit.do?masterClassId="+presentationMaterial.getMasterClass().getId());					
				} catch (Throwable oops) {
					result = createEditModelAndView(presentationMaterial, "presentationMaterial.commit.error");		
				}
		}
			
		return result;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(PresentationMaterial presentationMaterial, BindingResult binding) {
		ModelAndView result;

		try {			
			presentationMaterialService.delete(presentationMaterial);
			result = new ModelAndView("redirect:/masterClass/cook/edit.do?masterClassId="+presentationMaterial.getMasterClass().getId());						
		} catch (Throwable oops) {
			result = createEditModelAndView(presentationMaterial, "presentationMaterial.commit.error");
		}

		return result;
	}
	
	protected ModelAndView createEditModelAndView(PresentationMaterial presentationMaterial) {
		ModelAndView result;

		result = createEditModelAndView(presentationMaterial, null);
		result.addObject("requestURI", "presentationMaterial/cook/edit.do");
		result.addObject("cancelURI", "masterClass/cook/edit.do?masterClassId="+presentationMaterial.getMasterClass().getId());
		
		return result;
	}	
	
	
	
	protected ModelAndView createEditModelAndView(PresentationMaterial presentationMaterial, String message) {
		ModelAndView result;
		
		result = new ModelAndView("learningMaterial/edit");
		result.addObject("learningMaterial", presentationMaterial);
		result.addObject("errorMessage", message);
		result.addObject("requestURI", "presentationMaterial/cook/edit.do");
		result.addObject("cancelURI", "learningMaterial/actor/list.do?masterClassId="+presentationMaterial.getMasterClass().getId());

		return result;
	}
 	
 
}