package sql;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Holds methods containing strings with SQL commands for
 * manipulating the <code>Recipes</code> table.
 *
 * For more information database structure documentation is located at:
 * <a href="http://www.ericrytting.com/DatabaseDocs/">Docs</a>
 *
 * @author Eric R
 * @author Malcolm
 */
public class SQLRecipes {
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
				+ "Name varchar(255), "
				+ "Instructions varchar(5000), "
				+ "CookTime int, "
				+ "PrepTime int, "
				+ "RecipeDescription varchar(1000), "
				+ "CostCategory int)";
    }

    /**
     * Creates a string containing SQL commands to drop
     * the <code>Recipes</code> table from the database.
     *
     * @return the string containing the SQL commands to drop the
     * <code>Recipes</code> table.
     */
    public static String dropTable() {
    	
    	return "DROP TABLE Recipes";
    }

    /**
     * Creates a string containing SQL commands to pull all
     * the information from the <code>Recipes</code> table.
     *
     * @return the string containing the SQL commands to pull all
     * data from the <code>Recipes</code> table.
     */
    public static String allDataFromTable() {
    	
    	return "SELECT * FROM Recipes";
    }

    /**
     * Creates a string containing SQL commands to put insert new data
     * into the <code>Recipes</code> table.
     *
     * @return the string containing the SQL commands to insert new
     * data into the <code>Recipes</code> table.
     */
    public static String insertDataIntoTable() {
    	
    	return "INSERT INTO Recipes "
    			+ "(Name, Instructions, CookTime, PrepTime, RecipeDescription, CostCategory) "
    			+ "VALUES "
    			+ "('Spaghetti Bolognese', '1. Cook Spaghetti 2. Add Bolognese', 30, 15, 'It is spaghetti"
    			+ " with a red meat sauce', 1), "
    			+ "('Sushi', '1. prepare meat 2. slice avocado 3. wrap in seaweed', 0, 20, 'Sushi rolls', 3), "
    			+ "('Grilled Cheese', '1. put cheese between two slices of bread 2. cook on buttered pan', "
    			+ "10, 5, 'A grilled cheese sandwich', 1), "
    			+ "('Tonkotsu Ramen', '1. Bring chicken broth to boil 2. add egg, noodles, meat, and spices', "
    			+ "20, 5, 'delicious, spicy ramen', 2), "
    			+ "('Carne Asada Burrito', '1.Cook beans 2. cube meats and cook ingredients"
    			+ " 3. wrap in tortilla', 10, 10, 'A classic mexican burrito', 2)";
    }
    
	public static void printData(ResultSet rs) throws SQLException {
		
		System.out.println("Id   | Recipe Name | Instructions | Cook Time | Prep Time | Description | Cost Category");
		System.out.println("_______________________________________________________________________________________");
		
		while(rs.next()) {
			
			int id = rs.getInt("Id");
			String name = rs.getString("Name");
			String instructions = rs.getString("Instructions");
			int cookTime = rs.getInt("CookTime");
			int prepTime = rs.getInt("PrepTime");
			String description = rs.getString("RecipeDescription");
			int costCategory = rs.getInt("CostCategory");
			
			System.out.printf("%d | %-10s | %s | %d | %d | %s | %d%n",
					id, name, instructions, cookTime, prepTime, description, costCategory);
		}
	}
}

