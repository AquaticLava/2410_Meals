package application;

import java.sql.Array;
import java.util.ArrayList;

/**
 * Class file that represents a recipe with a name, a set of instructions,
 * cook time, prep time, a recipe description, a cost category, and a list of ingredients.
 *
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
	private ArrayList<String> amountIngredient = new ArrayList<>();
	private ArrayList<String> ingredients = new ArrayList<>();;

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
		if (parts.length == 7){
			String[] listIngred = parts[6].split(",");
			for (int i = 0; i < listIngred.length; i++){
				if (i % 2 == 0){
					//Adds the amount of the ingredient to the array list amount Ingredient
					amountIngredient.add(listIngred[i]);
				} else {
					//Adds the actual ingredient to the array list of ingredients
					ingredients.add(listIngred[i]);
				}
			}
		}
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < amountIngredient.size(); i++){
			sb.append(amountIngredient.get(i) + " ");
			sb.append(ingredients.get(i) + "\n");
		}

		return "Recipe{" +
				"Id=" + Id +
				", recipeName='" + recipeName + '\'' +
				", recipeInstructions='" + recipeInstructions + '\'' +
				", cookTime=" + cookTime +
				", prepTime=" + prepTime +
				", recipeDescription='" + recipeDescription + '\'' +
				", costCategory=" + costCategory +
				", ingredients:\n" + sb.toString() +
				'}';
	}
}
