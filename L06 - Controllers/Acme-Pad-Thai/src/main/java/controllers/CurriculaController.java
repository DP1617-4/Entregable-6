package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Curricula;
import domain.Recipe;

import services.CategoryService;
import services.CurriculaService;
import services.IngredientService;
import services.NutritionistService;
import services.QuantityService;
import services.RecipeService;
import services.UserService;

@Controller
@RequestMapping("/curricula/nutritionist")
public class CurriculaController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private CurriculaService curriculaService;
	@Autowired
	private NutritionistService nutritionistService;

	// Constructors -----------------------------------------------------------

	public CurriculaController() {
		super();
	}
	
	protected ModelAndView createEditModelAndView(Curricula curricula) {
		ModelAndView result;

		result = createEditModelAndView(curricula, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Curricula curricula, String message) {
		ModelAndView result;

		result = new ModelAndView("curricula/edit");
		result.addObject("curricula", curricula);
		result.addObject("message", message);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		Curricula curricula = curriculaService.create();

		result = createEditModelAndView(curricula);
		result.addObject("curricula", curricula);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int curriculaId) {
		ModelAndView result;
		Curricula curricula;

		curricula = curriculaService.findOne(curriculaId);
		result = createEditModelAndView(curricula);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Curricula curricula, BindingResult binding) {

		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(curricula);
		} else {

			try {
				curricula = curriculaService.save(curricula);
				result = new ModelAndView(
						"redirect:/curricula/display.do?curriculaId="
								+ curricula.getId());

			} catch (Throwable oops) {
				result = createEditModelAndView(curricula, "curricula.commit.error");
				result.addObject("curricula", curricula);
			}
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Curricula curricula, BindingResult binding) {
		ModelAndView result;

		try {
			curriculaService.delete(curricula);
			result = new ModelAndView("redirect:/nutritionist/display.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(curricula, "curricula.commit.error");
		}

		return result;
	}

}
