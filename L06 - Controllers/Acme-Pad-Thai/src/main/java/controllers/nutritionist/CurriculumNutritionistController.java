package controllers.nutritionist;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;

import domain.Curriculum;
import domain.Nutritionist;
import domain.Recipe;

import services.CategoryService;
import services.CurriculumService;
import services.IngredientService;
import services.NutritionistService;
import services.QuantityService;
import services.RecipeService;
import services.UserService;

@Controller
@RequestMapping("/curriculum/nutritionist")
public class CurriculumNutritionistController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private CurriculumService curriculumService;
	@Autowired
	private NutritionistService nutritionistService;

	// Constructors -----------------------------------------------------------

	public CurriculumNutritionistController() {
		super();
	}
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam(required = false, defaultValue = "0") int curriculumId) {

		
		ModelAndView result;
		Curriculum curriculum;
		
			curriculum = curriculumService.findByPrincipal();

		result = new ModelAndView("curriculum/display");
		result.addObject("curriculum", curriculum);

		return result;
	}
	
	protected ModelAndView createEditModelAndView(Curriculum curriculum) {
		ModelAndView result;

		result = createEditModelAndView(curriculum, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Curriculum curriculum, String message) {
		ModelAndView result;

		result = new ModelAndView("curriculum/edit");
		result.addObject("curriculum", curriculum);
		result.addObject("message", message);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		Curriculum curriculum = curriculumService.create();

		result = createEditModelAndView(curriculum);
		result.addObject("curriculum", curriculum);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Curriculum curriculum;

		curriculum = curriculumService.findByPrincipal();
		result = createEditModelAndView(curriculum);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Curriculum curriculum, BindingResult binding) {

		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(curriculum);
		} else {

			try {
				curriculum = curriculumService.save(curriculum);
				result = new ModelAndView(
						"redirect:/curriculum/nutritionist/display.do?curriculumId="
								+ curriculum.getId());

			} catch (Throwable oops) {
				result = createEditModelAndView(curriculum, "curriculum.commit.error");
				result.addObject("curriculum", curriculum);
			}
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Curriculum curriculum, BindingResult binding) {
		ModelAndView result;

		try {
			curriculumService.delete(curriculum);
			result = new ModelAndView("redirect:/nutritionist/display.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(curriculum, "curriculum.commit.error");
		}

		return result;
	}

}
