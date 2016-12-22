

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
import services.VideoMaterialService;
import services.MasterClassService;
import controllers.AbstractController;
import domain.VideoMaterial;
import domain.MasterClass;


@Controller
@RequestMapping("/videoMaterial/cook")
public class VideoMaterialCookController extends AbstractController {
	
	// Services ---------------------------------------------------------------
	
	@Autowired
	private VideoMaterialService videoMaterialService;
	
	@Autowired
	private MasterClassService masterClassService;
	
	// Constructors -----------------------------------------------------------
	
	public VideoMaterialCookController() {
		super();
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int masterClassId) {
		
		ModelAndView result;
		MasterClass masterClass = masterClassService.findOne(masterClassId);
		VideoMaterial videoMaterial = videoMaterialService.create(masterClass);
		
		result = createEditModelAndView(videoMaterial);
		result.addObject("videoMaterial", videoMaterial);
		
		return result;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int videoMaterialId) {
		ModelAndView result;
		VideoMaterial videoMaterial;

		videoMaterial = videoMaterialService.findOneToEdit(videoMaterialId);
		Assert.notNull(videoMaterial);
		result = createEditModelAndView(videoMaterial);

		return result;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid VideoMaterial videoMaterial, BindingResult binding) {
		
		ModelAndView result;
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(videoMaterial);
		} else {
				try {
					videoMaterialService.save(videoMaterial);
					result = new ModelAndView("redirect:/masterClass/cook/edit.do?masterClassId="+videoMaterial.getMasterClass().getId());					
				} catch (Throwable oops) {
					result = createEditModelAndView(videoMaterial, "videoMaterial.commit.error");		
				}
		}
			
		return result;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(VideoMaterial videoMaterial, BindingResult binding) {
		ModelAndView result;

		try {			
			videoMaterialService.delete(videoMaterial);
			result = new ModelAndView("redirect:/masterClass/cook/edit.do?masterClassId="+videoMaterial.getMasterClass().getId());						
		} catch (Throwable oops) {
			result = createEditModelAndView(videoMaterial, "videoMaterial.commit.error");
		}

		return result;
	}
	
	protected ModelAndView createEditModelAndView(VideoMaterial videoMaterial) {
		ModelAndView result;

		result = createEditModelAndView(videoMaterial, null);
		result.addObject("requestURI", "videoMaterial/cook/edit.do");
		result.addObject("cancelURI", "masterClass/cook/edit.do?masterClassId="+videoMaterial.getMasterClass().getId());
		
		return result;
	}	
	
	
	
	protected ModelAndView createEditModelAndView(VideoMaterial videoMaterial, String message) {
		ModelAndView result;
		
		result = new ModelAndView("learningMaterial/edit");
		result.addObject("learningMaterial", videoMaterial);
		result.addObject("errorMessage", message);
		result.addObject("requestURI", "videoMaterial/cook/edit.do");
		result.addObject("cancelURI", "learningMaterial/actor/list.do?masterClassId="+videoMaterial.getMasterClass().getId());

		return result;
	}
 	
 
}