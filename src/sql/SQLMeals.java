package sql;

import application.Meal;
import application.Recipe;

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
 * @author Collin W.
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
				+ "(START WITH 1, INCREMENT BY 1), "
				+ "Name varchar(255), "
				+ "Photo varchar(255), "
				+ "RecipeId int )";
    }

    /**
     * Creates a string containing SQL commands to drop
     * the <code>Ingredients</code> table from the database.
     *
     * The string containing the SQL commands to drop the
     * <code>Ingredients</code> table.
     */
    public static String DROPTABLE =  "DROP TABLE Meals";

    /**
     * Creates a string containing SQL commands to pull all
     * the information from the <code>Ingredients</code> table.
     *
     * The string containing the SQL commands to pull all
     * data from the <code>Ingredients</code> table.
     */
    public static String ALLDATAFROMTABLE = "SELECT * FROM Meals";
    
    /**
     * Creates a string containing SQL commands to pull all
     * the information from the <code>Recipes</code> table.
     *
     * @return the string containing the SQL commands to pull all
     * data from the <code>Recipes</code> table.
     */
    public static String allDataFromTable(String sortMethod) {
    	
    	return "SELECT * FROM Meals ORDER BY " + sortMethod;
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
		return "SELECT * from Meals " +
				"ORDER BY " + sortMethod +
				" FETCH FIRST " + numberOfRows + " ROWS ONLY";
	}
    /**
     * Creates a string containing SQL commands to put insert new data
     * into the <code>Ingredients</code> table.
     *
     * @return the string containing the SQL commands to insert new
     * data into the <code>Ingredients</code> table.
     */
    public static String insertDataIntoTable(Meal[] meals) {

		StringBuilder sb = new StringBuilder();

		sb.append("INSERT INTO Meals (Name, Photo, RecipeId) VALUES ");


		for (Meal meal: meals){
			sb.append("('").append(meal.getName()).append("', '")
					.append(meal.getPhotoName()).append("', ")
					.append(meal.getRecipeId()).append("),");
		}
		sb.replace(sb.length() - 1,sb.length(),"");
//		System.out.println(sb);
		return sb.toString();
    }

	public static String insertDataIntoTable(Meal meal) {
		Meal[] m = {meal};
		return insertDataIntoTable(m);
	}
	
/**
 * 
 * @param id
 * @param mealName
 * @param photoName
 * @param recipeId
 * @return
 */
	public static String updateRow(int id, String mealName, String photoName, String recipeId){
		
		return "UPDATE Meals "
				+ "SET Name = '" + mealName
				+ "', Photo = '" + photoName
				+ "', RecipeId = " + recipeId
				+ " WHERE Id = " + id;
	}

	public static String insertFirstTestMeals (){
		return "INSERT INTO Meals "
				+ "(Name, Photo, RecipeId) VALUES "
				+ "('Pork and Peanut Dragon Noodles', 'PorkAndPeanutDragonNoodles.jpg', 1), "
				+ "('Ground Turkey Skillet with Veggies', 'GroundTurkey.png', 2), "
				+ "('Spinach Stuffed Chicken Breast', 'SpinachStuffedChicken.png', 3), "
				+ "('Instant Pot Baked Ziti', 'InstantPotBakedZiti.png', 4), "
				+ "('Air Fryer Chicken Sandwich', 'AirFryerChickenSandwich.png', 5)";
	}

	public static String deleteRow(int id) {
		return "DELETE FROM Meals WHERE ID = " + id;
	}
    
	public static void printData(ResultSet rs) throws SQLException {
		
		System.out.println(" Id |      Meal Name      |   Photo    | Recipe Id");
		System.out.println("__________________________________________________");
		
		while(rs.next()) {
			
			int id = rs.getInt("Id");
			String name = rs.getString("Name");
			String photo = rs.getString("Photo");
			int recipeId = rs.getInt("RecipeId");
			
			System.out.printf("%4d| %-19s | %-9s | %d%n",
					id, name, photo, recipeId);
			
		}
	}

	public static String getMealById(int mealId) {
		
		return "SELECT * FROM Meals WHERE Id = " + mealId;
	}



}
