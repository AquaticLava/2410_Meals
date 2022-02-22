package sql;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Holds methods containing strings with SQL commands for
 * manipulating the <code>RecipesIngredients</code> table.
 *
 * For more information database structure documentation is located at:
 * <a href="http://www.ericrytting.com/DatabaseDocs/">Docs</a>
 *
 * @author Eric R
 */
public class SQLRecipesIngredients {
    /**
     * Creates a string containing SQL commands to create
     * the <code>RecipesIngredients</code> table.
     * <p>
     * Creates the table with the columns:
     * RecipeID and IngredientID.
     * <p>
     *
     * @return a string containing the SQL commands to create the
     * <code>RecipesIngredients</code> table.
     */
	public static String createTable() {

    	return "CREATE TABLE RecipesIngredients ("
    			+ "RecipeId int, "
    			+ "IngredientId int)";
    }

    /**
     * Creates a string containing SQL commands to drop
     * the <code>RecipesIngredients</code> table from the database.
     *
     * The string containing the SQL commands to drop the
     * <code>RecipesIngredients</code> table.
     */
    public static String DROPTABLE = "DROP TABLE RecipesIngredients";

    /**
     * Creates a string containing SQL commands to pull all
     * the information from the <code>RecipesIngredients</code> table.
     *
     * The string containing the SQL commands to pull all
     * data from the <code>RecipesIngredients</code> table.
     */
    public static String ALLDATAFROMTABLE = "SELECT * FROM RecipesIngredients";

    /**
     * Creates a string containing SQL commands to put insert new data
     * into the <code>RecipesIngredients</code> table.
     *
     * @return the string containing the SQL commands to insert new
     * data into the <code>RecipesIngredients</code> table.
     */
    public static String insertDataIntoTable() {

    	return "INSERT INTO RecipesIngredients "
    			+ "(RecipeId, IngredientId) "
    			+ "VALUES "
    			+ "(1, 1), "
    			+ "(1, 2), "
    			+ "(1, 3), "
    			+ "(2, 6), "
    			+ "(2, 7), "
    			+ "(3, 8), "
    			+ "(3, 9), "
    			+ "(4, 1), "
    			+ "(4, 3), "
    			+ "(4, 5), "
    			+ "(5, 3), "
    			+ "(5, 4), "
    			+ "(5, 7), "
    			+ "(5, 9), "
    			+ "(5, 10)";
    }
    
	public static void printData(ResultSet rs) throws SQLException {
		
		System.out.println("Recipe Id | Ingredient Id");
		System.out.println("_________________________");
		
		while(rs.next()) {
			
			int recipeId = rs.getInt("RecipeId");
			int ingredientId = rs.getInt("IngredientId");
			
			System.out.printf("%-9d | %-12d%n",
					recipeId, ingredientId);
		}
	}
}
