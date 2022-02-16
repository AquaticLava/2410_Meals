package application;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Recipe {

	private int Id;
	private String recipeName;
	private String recipeInstructions;
	private int cookTime;
	private int prepTime;
	private String recipeDescription;
	private int costCategory;
	
	public Recipe(int Id, String recipeName, String recipeInstructions, int cookTime,
	 int prepTime, String recipeDescription, int costCategory) {
		this.Id = Id;
		this.recipeName = recipeName;
		this.recipeInstructions = recipeInstructions;
		this.cookTime = cookTime;
		this.prepTime = prepTime;
		this.recipeDescription = recipeDescription;
		this.costCategory = costCategory;
	}
	
	public int getId() {
		return Id;
	}
	
	public String getRecipeName() {
		return recipeName;
	}
	
	public String getRecipeInstructions() {
		return recipeInstructions;
	}
	
	public int getCookTime() {
		return cookTime;
	}
	
	public int getPrepTime() {
		return prepTime;
	}
	
	public String getRecipeDescription() {
		return recipeDescription;
	}
	
	public int getCostCategory() {
		return costCategory;
	}
}
