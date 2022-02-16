package application;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Ingredient {

	private int Id;
	private String ingredientName;
	private String nutritionInfo;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public String getNutritionInfo() {
		return nutritionInfo;
	}

	public void setNutritionInfo(String nutritionInfo) {
		this.nutritionInfo = nutritionInfo;
	}
}
