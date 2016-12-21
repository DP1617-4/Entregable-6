package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryService;
import controllers.AbstractController;
import domain.Category;

@Controller
@RequestMapping("/category/administrator")
public class CategoryAdministratorController extends AbstractController {
	
	// Services ---------------------------------------------------------------

//	@Autowired
//	private RecipeService recipeService;	
	@Autowired
	private CategoryService categoryService;
	
	// Constructors -----------------------------------------------------------
	
	public CategoryAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	
	protected ModelAndView createEditModelAndView(Category category) {
		ModelAndView result;

		result = createEditModelAndView(category, null);
		
		return result;
	}	
	
	
	
	protected ModelAndView createEditModelAndView(Category category, String message) {
		ModelAndView result;
		
		result = new ModelAndView("category/edit");
		result.addObject("category", category);
		result.addObject("errorMessage", message);

		return result;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
 	public ModelAndView list() {
 		
 		ModelAndView result;
 		Collection<Category> categories;
 
 		categories = categoryService.findAllNotDeleted();

		result = new ModelAndView("category/list");
		result.addObject("requestURI", "category/administrator/list.do");
 		result.addObject("categories", categories);
 		
 		return result;
 	}
	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int categoryId) {
		
		ModelAndView result;
		Category category;

		category = categoryService.findOne(categoryId);
		
		result = new ModelAndView("category/display");
		result.addObject("category", category);
		
		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		Collection<Category> categories = categoryService.findAllNotDeleted();
		Category newCategory = categoryService.create(null);
		
		result = createEditModelAndView(newCategory);
		result.addObject("categories", categories);

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int categoryId) {
		ModelAndView result;
		Category category;

		try{
		
			category = categoryService.findOne(categoryId);
			result = createEditModelAndView(category);
			
		}catch(Exception oops){

			
			result = new ModelAndView("redirect:/category/administrator/list.do");
		
		}

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid Category category, BindingResult binding) {
		
		ModelAndView result;
		
		if (binding.hasErrors()){
			result = createEditModelAndView(category); 
			} else {
				try {
					category = categoryService.save(category);
					result = new ModelAndView("redirect:/category/administrator/list.do");
				} catch (Throwable oops) {
					result = createEditModelAndView(category, "category.commit.error");			
			}
		}
			
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Category category, BindingResult binding) {
		ModelAndView result;

		try {
			categoryService.delete(category);
			result = new ModelAndView("redirect:/category/administrator/list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(category, "category.commit.error");
		}

		return result;
	}
	
}