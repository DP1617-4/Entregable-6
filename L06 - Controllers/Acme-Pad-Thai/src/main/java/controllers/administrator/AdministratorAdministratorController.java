package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.BillService;
import services.ContestService;
import services.CookService;
import services.MasterClassService;
import services.RecipeService;
import services.SponsorService;
import services.UserService;
import controllers.AbstractController;
import domain.Contest;
import domain.Cook;
import domain.Sponsor;
import domain.User;

@Controller
@RequestMapping("/administrator/administrator")
public class AdministratorAdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private MasterClassService masterClassService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RecipeService recipeService;
	
	@Autowired
	ContestService contestService;
	
	@Autowired
	SponsorService sponsorService;
	
	@Autowired
	BillService billService;
	
	@Autowired
	CookService cookService;

	// Constructors -----------------------------------------------------------

	public AdministratorAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		ModelAndView result;
		Double[] recipesPerUser = userService.selectMinAvgMaxRecipesInUsers();
		Double[] recipesQualifiedForConstest = contestService.getMinAvgMaxRecipesQualifiedForContest();
		User userWithMostRecipes = userService.selectUserWithMostRecipes();
		Contest contestWithMostQualifiedRecipes= contestService.getContestWithMoreRecipesQualified();
		Double[] stepsPerRecipe = recipeService.getAvgStdStepsPerRecipe();
		Double[] ingredientsPerRecipe = recipeService.getAvgStdIngredientsPerRecipe();
		Collection<User> usersByPopularity = userService.selectAllUsersDescendingNumberOfFollowers();
		Collection<User> usersByRecipeLikes = userService.findAllUsersByRecipeLikesAndDislikes();
		Double[] campaignsPerSponsor = sponsorService.calculateMinAvgMaxFromCampaignsOfSponsors();
		Double[] activeCampaignsPerSponsor = sponsorService.calculateMinAvgMaxFromCampaignsOfSponsorsByDate();
		Collection<String> companiesByOrganisedCampaigns = sponsorService.findCompaniesNameOfSponsors();
		Collection<String> companiesByMonthlyBills = sponsorService.findCompaniesNameOfSponsorsByBills();
		Double[] unpaidMonthlyBills = billService.calculateAvgDevPaidAndUnpaidBills();
		Collection<Sponsor> sponsorNotManagedCampaign3Months = sponsorService.findInnactiveSponsorInThreeMonths();
		Collection<String> companiesSpentLessThanAverage = sponsorService.findCompaniesNameWhichSpentLessInCampaignsThanAvg();
		Collection<String> companiesSpentOver90Percent = sponsorService.findCompaniesNameSpent90Percent();
		Double[] masterClassesPerCook = cookService.calculateMinMaxAvgDevFromMasterClassesOfCooks();
		Double learningMaterialsPerMasterClass = masterClassService.calculateAvgLearningMaterialsPerMasterClass();
		Long propotedMasterClasses = masterClassService.countNumberPromotedMasterClasses();
		Collection<Cook> cooksByMasterClasses = masterClassService.findCooksOrderByPromotedMasterClasses();
		Double[] promotedDemotedMasterClassesPerCook = masterClassService.calculateAvgPromotedAndDemotedMasterClassesPerCook();
		
		result = new ModelAndView("administrator/dashboard");
		result.addObject("recipesPerUser",recipesPerUser);
		result.addObject("userWithMostRecipes",userWithMostRecipes);
		result.addObject("contestWithMostQualifiedRecipes",contestWithMostQualifiedRecipes);
		result.addObject("stepsPerRecipe",stepsPerRecipe);
		result.addObject("ingredientsPerRecipe",ingredientsPerRecipe);
		result.addObject("recipesQualifiedForConstest",recipesQualifiedForConstest);
		result.addObject("usersByPopularity",usersByPopularity);	
		result.addObject("usersByRecipeLikes",usersByRecipeLikes);	
		result.addObject("campaignsPerSponsor",campaignsPerSponsor);	
		result.addObject("activeCampaignsPerSponsor",activeCampaignsPerSponsor);	
		result.addObject("companiesByOrganisedCampaigns",companiesByOrganisedCampaigns);	
		result.addObject("companiesByMonthlyBills",companiesByMonthlyBills);	
		result.addObject("unpaidMonthlyBills",unpaidMonthlyBills);	
		result.addObject("sponsorNotManagedCampaign3Months",sponsorNotManagedCampaign3Months);	
		result.addObject("companiesSpentLessThanAverage",companiesSpentLessThanAverage);
		result.addObject("companiesSpentOver90Percent",companiesSpentOver90Percent);
		result.addObject("masterClassesPerCook",masterClassesPerCook);
		result.addObject("learningMaterialsPerMasterClass",learningMaterialsPerMasterClass);
		result.addObject("propotedMasterClasses",propotedMasterClasses);
		result.addObject("cooksByMasterClasses",cooksByMasterClasses);
		result.addObject("promotedDemotedMasterClassesPerCook",promotedDemotedMasterClassesPerCook);
		result.addObject("requestURI","administrator/administrator/dashborad.do");
		return result;
	}

}
