package application;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Represents a recipe.
 *
 * @author Collin, Malcolm
 */
public class Recipe {

	private int Id;
	private String recipeName;
	private String recipeInstructions;
	private String cookTime;
	private String prepTime;
	private String recipeDescription;
	private String costCategory;

	/**
	 * Overloaded constructor that takes in a line from a csv file
	 * and initializes the values to the attributes above.
	 *
	 * @param csvString One whole line from a csv file.
	 */
	public Recipe (String csvString){
		String[] parts = csvString.split("#");
		this.recipeName = parts[0];
		this.recipeInstructions = parts[1];
		this.cookTime = parts[2];
		this.prepTime = parts[3];
		this.recipeDescription = parts[4];
		this.costCategory = parts[5];
	}

	public Recipe(int Id, String recipeName, String recipeInstructions, String cookTime,
	 String prepTime, String recipeDescription, String costCategory) {
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
	
	public String getCookTime() {
		return cookTime;
	}

	public String getPrepTime() {
		return prepTime;
	}
	
	public String getRecipeDescription() {
		return recipeDescription;
	}
	
	public String getCostCategory() {
		return costCategory;
	}
	
	public void setId(int Id) {
		this.Id = Id;
	}
	
	public void setRecipeName(String name) {
		this.recipeName = name;
	}
	
	public void setRecipeInstructions(String instructions) {
		this.recipeInstructions = instructions;
	}
	
	public void setCookTime(String cookTime) {
		this.cookTime = cookTime;
	}
	
	public void setPrepTime(String prepTime) {
		this.prepTime = prepTime;
	}
	
	public void setRecipeDescription(String description) {
		this.recipeDescription = description;
	}
	
	public void setCostCategory(String costCategory) {
		
		this.costCategory = costCategory;
	}

	@Override
	public String toString() {
		return "Recipe{" +
				"Id=" + Id +
				", recipeName='" + recipeName + '\'' +
				", recipeInstructions='" + recipeInstructions + '\'' +
				", cookTime=" + cookTime +
				", prepTime=" + prepTime +
				", recipeDescription='" + recipeDescription + '\'' +
				", costCategory=" + costCategory +
				'}';
	}
}
