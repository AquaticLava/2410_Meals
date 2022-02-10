package sql;

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
    public String createDatabase() {
    	
		return "CREATE TABLE Recipes ("
				+ "Id int not null primary key "
				+ "GENERATED ALWAYS AS IDENTITY "
				+ "(START WITH 1, INCREMENT BY 1),"
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
    public String dropDatabase() {
    	
    	return "DROP TABLE Recipes";
    }

    /**
     * Creates a string containing SQL commands to pull all
     * the information from the <code>Recipes</code> table.
     *
     * @return the string containing the SQL commands to pull all
     * data from the <code>Recipes</code> table.
     */
    public String allDataFromDatabase() {
    	
    	return "SELECT * FROM Recipes";
    }

    /**
     * Creates a string containing SQL commands to put insert new data
     * into the <code>Recipes</code> table.
     *
     * @return the string containing the SQL commands to insert new
     * data into the <code>Recipes</code> table.
     */
    public String insertDataIntoDatabase() {
        return null;
        //todo Create method!
    }
}
