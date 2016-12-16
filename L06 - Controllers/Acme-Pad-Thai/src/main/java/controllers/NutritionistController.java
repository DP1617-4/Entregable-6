package controllers;

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
import services.UserService;
import domain.Nutritionist;
import domain.User;

@Controller
@RequestMapping("/nutritionist")
public class NutritionistController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private NutritionistService nutritionistService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SocialUserService socialUserService;

	// Constructors -----------------------------------------------------------

	public NutritionistController() {
		super();
	}
	
	@RequestMapping(value = "/follow", method = RequestMethod.GET)
	public ModelAndView follow(@RequestParam int userId){
		
		ModelAndView result;
		User followed;
		followed = userService.findOne(userId);
		socialUserService.follow(followed);
		
		result = new ModelAndView("redirect:list.do");
		
		
		return result;
	}
	
	@RequestMapping(value = "/unfollow", method = RequestMethod.GET)
	public ModelAndView unfollow(@RequestParam int userId){
		
		ModelAndView result;
		User unfollowed;
		unfollowed = userService.findOne(userId);
		socialUserService.unfollow(unfollowed);
		
		result = new ModelAndView("redirect:list.do");
		
		
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Nutritionist nutritionist;

		nutritionist = nutritionistService.create();
		result = createEditModelAndView(nutritionist);

		return result;
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam(required = false, defaultValue = "0") int nutritionistId) {

		
		ModelAndView result;
		Nutritionist nutritionist;
		
		if(nutritionistId==0){
			
			nutritionist= nutritionistService.findByPrincipal();
		}
		else{
			
			nutritionist = nutritionistService.findOne(nutritionistId);
		}


		result = new ModelAndView("nutritionist/display");
		result.addObject("nutritionist", nutritionist);

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

		result = new ModelAndView("nutritionist/edit");
		result.addObject("nutritionist", nutritionist);
		result.addObject("message", message);
		
		return result;
	}

}

