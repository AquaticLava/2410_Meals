package sql;

import application.Ingredient;

import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    
	public static void printData(ResultSet rs) throws SQLException {
		
		System.out.println("Id |      Name      | Calores");
		System.out.println("____________________________________");
		
		while(rs.next()) {
			
			int id = rs.getInt("Id");
			String name = rs.getString("Name");
			String calories = rs.getString("Calories");

			System.out.printf("%-2d | %-14s | %-14s%n",
					id, name, calories);
		}
	}

}
