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
				+ "calories double,"
				+ "carbs double,"
				+ "fiber double,"
				+ "protein double,"
				+ "fat double,"
				+ "sugar double,"
				+ "servingSize double)";
	}

    /**
     * Creates a string containing SQL commands to drop
     * the <code>Ingredients</code> table from the database.
     *
     * @return the string containing the SQL commands to drop the
     * <code>Ingredients</code> table.
     */
    public static String dropTable(){
    	
        return "DROP TABLE Ingredients";

    }

    /**
     * Creates a string containing SQL commands to pull all
     * the information from the <code>Ingredients</code> table.
     *
     * @return the string containing the SQL commands to pull all
     * data from the <code>Ingredients</code> table.
     */
    public static String allDataFromTable(){

    	return "SELECT * FROM Ingredients";
    }

    /**
     * Creates a string containing SQL commands to put insert new data
     * into the <code>Ingredients</code> table.
     *
     * @return the string containing the SQL commands to insert new
     * data into the <code>Ingredients</code> table.
     */
    public static String insertDataIntoTable(){

    	return "INSERT INTO Ingredients "
				+ "(Name, NutritionInfo) VALUES "
				+ "('Spaghetti', 'Healthy'), "
				+ "('Tomato Sauce', 'Healthy'), "
				+ "('Ground Beef', 'Healthy'), "
				+ "('Tortillas', 'Unhealthy'), "
				+ "('Eggs', 'Nutrition'), "
				+ "('White Rice', 'Unhealthy'), "
				+ "('Avocado', 'Healthy'), "
				+ "('Bread', 'Unhealthy'), "
				+ "('Cheese', 'Unhealthy'), "
				+ "('Black Beans', 'Healthy')";
    }
    
	public static void printData(ResultSet rs) throws SQLException {
		
		System.out.println("Id |      Name      | Nutrition Info");
		System.out.println("____________________________________");
		
		while(rs.next()) {
			
			int id = rs.getInt("Id");
			String name = rs.getString("Name");
			String nutrition = rs.getString("NutritionInfo");
			
			System.out.printf("%-2d | %-14s | %-14s%n",
					id, name, nutrition);
		}
	}

}
