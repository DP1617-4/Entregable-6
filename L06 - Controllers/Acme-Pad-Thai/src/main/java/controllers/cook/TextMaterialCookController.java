

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
import services.TextMaterialService;
import services.MasterClassService;
import controllers.AbstractController;
import domain.TextMaterial;
import domain.MasterClass;


@Controller
@RequestMapping("/textMaterial/cook")
public class TextMaterialCookController extends AbstractController {
	
	// Services ---------------------------------------------------------------
	
	@Autowired
	private TextMaterialService textMaterialService;
	
	@Autowired
	private MasterClassService masterClassService;
	
	// Constructors -----------------------------------------------------------
	
	public TextMaterialCookController() {
		super();
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int masterClassId) {
		
		ModelAndView result;
		MasterClass masterClass = masterClassService.findOne(masterClassId);
		TextMaterial textMaterial = textMaterialService.create(masterClass);
		
		result = createEditModelAndView(textMaterial);
		result.addObject("textMaterial", textMaterial);
		
		return result;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int textMaterialId) {
		ModelAndView result;
		TextMaterial textMaterial;

		textMaterial = textMaterialService.findOneToEdit(textMaterialId);
		Assert.notNull(textMaterial);
		result = createEditModelAndView(textMaterial);

		return result;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid TextMaterial textMaterial, BindingResult binding) {
		
		ModelAndView result;
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(textMaterial, binding.getFieldError(arg0));
		} else {
				try {
					textMaterialService.save(textMaterial);
					result = new ModelAndView("redirect:/masterClass/cook/edit.do?masterClassId="+textMaterial.getMasterClass().getId());					
				} catch (Throwable oops) {
					result = createEditModelAndView(textMaterial, "textMaterial.commit.error");		
				}
		}
			
		return result;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(TextMaterial textMaterial, BindingResult binding) {
		ModelAndView result;

		try {			
			textMaterialService.delete(textMaterial);
			result = new ModelAndView("redirect:/masterClass/cook/edit.do?masterClassId="+textMaterial.getMasterClass().getId());						
		} catch (Throwable oops) {
			result = createEditModelAndView(textMaterial, "textMaterial.commit.error");
		}

		return result;
	}
	
	protected ModelAndView createEditModelAndView(TextMaterial textMaterial) {
		ModelAndView result;

		result = createEditModelAndView(textMaterial, null);
		
		return result;
	}	
	
	
	
	protected ModelAndView createEditModelAndView(TextMaterial textMaterial, String message) {
		ModelAndView result;
		
		result = new ModelAndView("learningMaterial/edit");
		result.addObject("learningMaterial", textMaterial);
		result.addObject("errorMessage", message);
		result.addObject("requestURI", "textMaterial/cook/edit.do");
		result.addObject("cancelURI", "learningMaterial/actor/list.do?masterClassId="+textMaterial.getMasterClass().getId());

		return result;
	}
 	
 
}