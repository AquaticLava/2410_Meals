package sql;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Holds methods containing strings with SQL commands for
 * manipulating the <code>Ingredients</code> table.
 *
 * For more information database structure documentation is located at:
 * <a href="http://www.ericrytting.com/DatabaseDocs/">Docs</a>
 *
 * @author Eric R
 * @author Malcolm
 */
public class SQLMeals {
    /**
     * Creates a string containing SQL commands to create
     * the <code>Ingredients</code> table.
     * <p>
     * Creates the table with the columns:
     * ID, MealName, Photo, RecipeID.
     * <p>
     *
     * @return a string containing the SQL commands to create the
     * <code>Ingredients</code> table.
     */
	public static String createTable() {
    	
		return "CREATE TABLE Meals ("
				+ "Id int not null primary key "
				+ "GENERATED ALWAYS AS IDENTITY "
				+ "(START WITH 1, INCREMENT BY 1),"
				+ "Name varchar(255), "
				+ "Photo varchar(255)"
				+ "RecipeId int )";
    }

    /**
     * Creates a string containing SQL commands to drop
     * the <code>Ingredients</code> table from the database.
     *
     * @return the string containing the SQL commands to drop the
     * <code>Ingredients</code> table.
     */
    public static String dropTable() {

		return "DROP TABLE Meals";
    }

    /**
     * Creates a string containing SQL commands to pull all
     * the information from the <code>Ingredients</code> table.
     *
     * @return the string containing the SQL commands to pull all
     * data from the <code>Ingredients</code> table.
     */
    public static String allDataFromTable() {

		return "SELECT * FROM Meals";
    }

    /**
     * Creates a string containing SQL commands to put insert new data
     * into the <code>Ingredients</code> table.
     *
     * @return the string containing the SQL commands to insert new
     * data into the <code>Ingredients</code> table.
     */
    public static String insertDataIntoTable() {
    	
		return "INSERT INTO Meals "
				+ "(Name, Photo, RecipeId) VALUES "
				+ "('Spaghetti Bolognese', 'Pic001.png', '1'), "
				+ "('Sushi', 'Pic002.png', '2'), "
				+ "('Grilled Cheese', 'Pic003.png', '3'), "
				+ "('Tonkotsu Ramen', 'Pic004.png', '4'), "
				+ "('Carne Asada Burrito', 'Pic005.png', '5')";
    }
    
	static void printData(ResultSet rs) throws SQLException {
		
		System.out.println("Id   | Meal Name | Photo | Recipe Id");
		System.out.println("____________________________________");
		
		while(rs.next()) {
			
			int id = rs.getInt("Id");
			String name = rs.getString("Name");
			String photo = rs.getString("photoName");
			int recipeId = rs.getInt("RecipeId");
			
		}
	}
}
