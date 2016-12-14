package forms;

public class AddRecipe {

	public AddRecipe(){
		super();
	}

	private int recipeId;
	private int contestId;
	
	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}
	
	public int getContestId(){
		
		return contestId;
	}
	
	public void setCpmtestId(int contestId){
		
		this.contestId=contestId;
	}
	
}
