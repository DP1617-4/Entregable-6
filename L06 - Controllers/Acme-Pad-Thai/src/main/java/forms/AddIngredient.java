package forms;


public class AddIngredient {
	
	public AddIngredient(){
		super();
	}

	private int ingredientId;
	private int recipeId;
	
	public int getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(int ingredientId) {
		this.ingredientId = ingredientId;
	}
	
	public int getRecipeId(){
		return recipeId;
	}
	
	public void setRecipeId(int recipeId){
		
		this.recipeId=recipeId;
	}
	

}