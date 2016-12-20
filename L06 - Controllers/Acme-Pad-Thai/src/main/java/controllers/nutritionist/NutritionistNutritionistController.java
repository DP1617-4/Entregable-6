package controllers.nutritionist;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.NutritionistService;
import services.SocialUserService;
import controllers.AbstractController;
import domain.Nutritionist;

@Controller
@RequestMapping("/nutritionist/nutritionist")
public class NutritionistNutritionistController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private NutritionistService nutritionistService;
	
	@Autowired
	private SocialUserService socialUserService;

	// Constructors -----------------------------------------------------------

	public NutritionistNutritionistController() {
		super();
	}
	
	@RequestMapping(value = "/follow", method = RequestMethod.GET)
	public ModelAndView follow(@RequestParam int nutritionistId){
		
		ModelAndView result;
		Nutritionist followed;
		followed = nutritionistService.findOne(nutritionistId);
		socialUserService.follow(followed);
		
		result = new ModelAndView("redirect:list.do");
		
		
		return result;
	}
	
	@RequestMapping(value = "/unfollow", method = RequestMethod.GET)
	public ModelAndView unfollow(@RequestParam int nutritionistId){
		
		ModelAndView result;
		Nutritionist unfollowed;
		unfollowed = nutritionistService.findOne(nutritionistId);
		socialUserService.unfollow(unfollowed);
		
		result = new ModelAndView("redirect:list.do");
		
		
		return result;
	}
		
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Nutritionist nutritionist;

		nutritionist = nutritionistService.findByPrincipal();		
		result = createEditModelAndView(nutritionist);
		
		result.addObject("nutritionist", nutritionist);

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Nutritionist nutritionist, BindingResult binding) {
		ModelAndView result;
		if(binding.hasErrors()){
			result = createEditModelAndView(nutritionist);
		}
		else{
			try{
				nutritionist = nutritionistService.save(nutritionist);
				result = new ModelAndView(
						"redirect:/nutritionist/display.do?nutritionistId="
								+ nutritionist.getId());
			} catch (Throwable oops){
				result = createEditModelAndView(nutritionist, "nutritionist.commit.error");
			}
		}
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Nutritionist nutritionist) {
		ModelAndView result;

		result = createEditModelAndView(nutritionist, null);

		return result;
	}
	protected ModelAndView createEditModelAndView(Nutritionist nutritionist, String message) {
		ModelAndView result;
		
		String requestURI = "nutritionist/nutritionist/edit.do";

		result = new ModelAndView("nutritionist/edit");
		result.addObject("nutritionist", nutritionist);
		result.addObject("message", message);
		result.addObject("requestURI", requestURI);
		
		return result;
	}

}
