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
	private enum costCategory {
		$(1),
		$$(2),
		$$$(3),
		$$$$(4);
		private int costInt;
		private costCategory(int num) {
			this.costInt = num;
		}
	}
	
}
