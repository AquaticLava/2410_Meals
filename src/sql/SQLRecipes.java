package sql;

import application.Recipe;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Holds methods containing strings with SQL commands for
 * manipulating the <code>Recipes</code> table.
 *
 * For more information database structure documentation is located at:
 * <a href="http://www.ericrytting.com/DatabaseDocs/">Docs</a>
 *
 * @author Eric Rytting
 * @author Malcolm Bailey
 * @author Collin Williams
 */
public class SQLRecipes {

	public static String returnSpecificColumns (String[] columns){
		StringBuilder sb = new StringBuilder();

		return sb.toString();
	}

	/* ****************************************************************
		Make method that takes in array of columns and returns sql query that returns only those columns.
		Return the command //TODO
	 */


    /**
     * Creates a string containing SQL commands to create
     * the <code>Recipes</code> table.
     * <p>
     * Creates the table with the columns:
     * ID, RecipeName, RecipeInstructions, CookTime,
     * PrepTime, RecipeDescription, and CostCategory.
     * <p>
     *
     * @return a string containing the SQL commands to create the
     * <code>Recipes</code> table.
     */
	public static String createTable() {
    	
		return "CREATE TABLE Recipes ("
				+ "Id int not null primary key "
				+ "GENERATED ALWAYS AS IDENTITY "
				+ "(START WITH 1, INCREMENT BY 1), "
				+ "RecipeName varchar(255), "
				+ "RecipeInstructions varchar(5000), "
				+ "CookTime varchar(4), "
				+ "PrepTime varchar(4), "
				+ "RecipeDescription varchar(1000), "
				+ "CostCategory varchar(10))";
    }

    /**
     * Creates a string containing SQL commands to drop
     * the <code>Recipes</code> table from the database.
     *
     * The string containing the SQL commands to drop the
     * <code>Recipes</code> table.
     */
    public static String DROPTABLE = "DROP TABLE Recipes";

    /**
     * Creates a string containing SQL commands to pull all
     * the information from the <code>Recipes</code> table.
     *
     * @return the string containing the SQL commands to pull all
     * data from the <code>Recipes</code> table.
     */
    public static String allDataFromTable(String sortMethod) {
    	
    	return "SELECT * FROM Recipes ORDER BY " + sortMethod;
    }

    /**
     * Creates a string containing SQL commands to put insert new data
     * into the <code>Recipes</code> table.
     *
     * @return the string containing the SQL commands to insert new
     * data into the <code>Recipes</code> table.
     */
    public static String insertDataIntoTable(Recipe[] recipes) {
		StringBuilder sb = new StringBuilder();

		sb.append("INSERT INTO Recipes (RecipeName, RecipeInstructions, CookTime, " +
				"PrepTime, RecipeDescription, CostCategory)  VALUES ");


		for (Recipe recipe: recipes){
			sb.append("('").append(recipe.getRecipeName()).append("', '")
					.append(recipe.getRecipeInstructions()).append("', '")
					.append(recipe.getCookTime()).append("', '")
					.append(recipe.getPrepTime()).append("', '")
					.append(recipe.getRecipeDescription()).append("', '")
					.append(recipe.getCostCategory()).append("'),");
		}
		sb.replace(sb.length() - 1,sb.length(),"");
//		System.out.println(sb);
		return sb.toString();
    }

	public static String pullRecipeByID (int ID){
		return "SELECT RecipeDescription, RecipeInstructions from Recipes WHERE ID = " + ID;
	}
	
	public static String pullRecipeRecordByID (int ID){
		return "SELECT * from Recipes WHERE ID = " + ID;
	}


	public static String insertDataIntoTable(Recipe recipe) {
		Recipe[] r = {recipe};
		return insertDataIntoTable(r);
	}

	/**
	 * Returns a string to query for a limited number of rows.
	 *
	 *
	 * @param numberOfRows Number of rows to query for.
	 * @return the string for a query of rows between startID and endID, inclusive.
	 */
	public static String partialDataFromTable(int numberOfRows){
		return partialDataFromTable(numberOfRows,"ID");
	}

	/**
	 * Returns a string to query for a limited number of rows with sorting.
	 *
	 * The sort method is a SQL command to add to the query for sorting.
	 * ORDER BY is included in this method.
	 *
	 * @see <a href='https://www.w3schools.com/sql/sql_orderby.asp'>sortMethod Refrence</a>
	 * @param numberOfRows Number of rows for the query to return.
	 * @param sortMethod SQL command for sorting, ORDER BY is not needed.
	 * @return a string to query for the rows based off of id with sorting.
	 */
	public static String partialDataFromTable(int numberOfRows, String sortMethod){
		return "SELECT * from Recipes " +
				"ORDER BY " + sortMethod +
				" FETCH FIRST " + numberOfRows + " ROWS ONLY";
	}

	public static String deleteRow(int id){
		return "DELETE FROM Recipes WHERE ID = " + id;
	}
	
	/**
	 * 
	 * @param id
	 * @param recipeName
	 * @param recipeInstructions
	 * @param cookTime
	 * @param prepTime
	 * @param recipeDescription
	 * @param costCategory
	 * @return
	 */
	public static String updateRow(int id, String recipeName, String recipeInstructions, String cookTime, 
			String prepTime, String recipeDescription, String costCategory){
		
		return "UPDATE Recipes "
				+ "SET RecipeName = '" + recipeName
				+ "', RecipeInstructions = '" + recipeInstructions
				+ "', CookTime = '" + cookTime
				+ "', PrepTime = '" + prepTime
				+ "', RecipeDescription = '" + recipeDescription
				+ "', CostCategory = '" + costCategory
				+ "' WHERE Id = " + id;
	}
    
	public static void printData(ResultSet rs) throws SQLException {
		
		System.out.println("Id |     Recipe Name     |                              Instructions                              | Cook Time | Prep Time |               Description                | Cost Category");
		System.out.println("___________________________________________________________________________________________________________________________________________________________________________");
		
		while(rs.next()) {

			int id = rs.getInt("Id");
			String name = rs.getString("RecipeName");
			String instructions = rs.getString("RecipeInstructions");
			String cookTime = rs.getString("CookTime");
			String prepTime = rs.getString("PrepTime");
			String description = rs.getString("RecipeDescription");
			String costCategory = rs.getString("CostCategory");
			
			System.out.printf("%-3d| %-19s | %-70s | %-9s | %-9s | %-40s | %-10s%n",
					id, name, instructions, cookTime, prepTime, description, costCategory);
		}
	}
}

