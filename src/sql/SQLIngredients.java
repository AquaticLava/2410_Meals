package sql;

import application.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Holds methods containing strings with SQL commands for
 * manipulating the <code>Ingredients</code> table.
 *
 * For more information database structure documentation is located at:
 * <a href="http://www.ericrytting.com/DatabaseDocs/">Docs</a>
 *
 * @author Eric Rytting
 * @author Malcolm Bailey
 * @author Collin Williams
 */
public class SQLIngredients {
    /**
     * Creates a string containing SQL commands to create
     * the <code>Ingredients</code> table.
     *
     * Creates the table with the columns:
     * ID, Ingredients, NutritionInfo.
     *
     * Note: NutritionInfo may change into multiple columns later.
     *
     * @return a string containing the SQL commands to create the
     * <code>Ingredients</code> table.
     */
//	public static String createTable(){
//
//		return "CREATE TABLE Ingredients ("
//				+ "Id int not null primary key "
//				+ "GENERATED ALWAYS AS IDENTITY "
//				+ "(START WITH 1, INCREMENT BY 1), "
//				+ "Name varchar (255), "
//				+ "NutritionInfo varchar(1028) )";
//    }

	public static String createTable(){
		return "CREATE TABLE Ingredients ("
				+ "Id int not null primary key "
				+ "GENERATED ALWAYS AS IDENTITY "
				+ "(START WITH 1, INCREMENT BY 1), "
				+ "Name varchar (255), "
				+ "Calories varchar (255),"
				+ "Carbs varchar (255),"
				+ "Fiber varchar (255),"
				+ "Protein varchar (255),"
				+ "Fat varchar (255),"
				+ "Sugar varchar (255),"
				+ "ServingSize varchar (255))";
	}

    /**
     * Creates a string containing SQL commands to drop
     * the <code>Ingredients</code> table from the database.
     *
     * The string containing the SQL commands to drop the
     * <code>Ingredients</code> table.
     */
    public static String DROPTABLE = "DROP TABLE Ingredients";

    /**
     * Creates a string containing SQL commands to pull all
     * the information from the <code>Ingredients</code> table.
     *
     * The string containing the SQL commands to pull all
     * data from the <code>Ingredients</code> table.
     */
    public static String ALLDATAFROMTABLE = "SELECT * FROM Ingredients";

    /**
     * Creates a string containing SQL commands to put insert new data
     * into the <code>Ingredients</code> table.
     *
     * @return the string containing the SQL commands to insert new
     * data into the <code>Ingredients</code> table.
     */
    public static String insertDataIntoTable(Ingredient[] ingredients){
		StringBuilder sb = new StringBuilder();

		sb.append("INSERT INTO Ingredients (Name, Calories, " +
				"Carbs, Fiber, Protein, Fat, Sugar, ServingSize) VALUES ");


		for (Ingredient el: ingredients){
			sb.append("('").append(el.getName()).append("', '")
					.append(el.getCalories()).append("', '")
					.append(el.getCarbs()).append("', '")
					.append(el.getFiber()).append("', '")
					.append(el.getProtein()).append("', '")
					.append(el.getFat()).append("', '")
					.append(el.getSugar()).append("', '")
					.append(el.getServingSize()).append("'),");

		}
		sb.replace(sb.length() - 1,sb.length(),"");
		System.out.println(sb);
		return sb.toString();
    }

	public static String insertDataIntoTable(Ingredient ingredients){
		Ingredient[] i = {ingredients};
		return insertDataIntoTable(i);
	}

	public static String deleteRow(int id) {
		return "DELETE FROM Ingredients WHERE ID = " + id;
	}

	public static String pullIngredientsByRecipeID (int ID){
		return "SELECT Ingredients.Name FROM Ingredients JOIN RecipesIngredients ON Ingredients.Id = RecipesIngredients.IngredientId" +
				" JOIN Recipes ON Recipes.Id = RecipesIngredients.RecipeId WHERE Recipes.Id = " + ID;
	}

	public static void printData(ResultSet rs) throws SQLException {
		
		System.out.println("Id |      Name      | Calores");
		System.out.println("________________________________");
		
		while(rs.next()) {
			
			int id = rs.getInt("Id");
//			String name = rs.getString("Name");
			String calories = rs.getString(3);

			System.out.printf("%-2d |  | %-14s%n",
					id, calories);
		}
	}

	/**
	 * Generate SQL string for updating a specific ingredient based on its Id.
	 * 
	 * @param id
	 * @param name
	 * @param calories
	 * @param carbs
	 * @param fiber
	 * @param protein
	 * @param fat
	 * @param sugar
	 * @param servingSize
	 * @return : returns a string that can be executed as a SQL query to update
	 */
	public static String updateRow(int id, String name, String calories, String carbs, String fiber,
			String protein, String fat, String sugar, String servingSize){
		
		return "UPDATE Ingredients "
				+ "SET Name = '" + name
				+ "', Calories = '" + calories
				+ "', Carbs = '" + carbs
				+ "', Fiber = '" + fiber
				+ "', Protein = '" + protein
				+ "', Fat = '" + fat
				+ "', Sugar = '" + sugar
				+ "', ServingSize = '" + servingSize
				+ "' WHERE Id = " + id;
	}
	

	
    public static String allDataFromTable(String sortMethod) {
		return "SELECT * FROM Ingredients ORDER BY " + sortMethod;
    }
    
    public static String getIngredientById(int Id) {
		return "SELECT * FROM Ingredients WHERE Id = " + Id;
    }

	public static String partialDataFromTable(int numberOfRows, String sortMethod) {
		return "SELECT * from Ingredients " +
				"ORDER BY " + sortMethod +
				" FETCH FIRST " + numberOfRows + " ROWS ONLY";
	}

	public static String dropTable() {
		return "DROP TABLE Ingredients";
	}


}
