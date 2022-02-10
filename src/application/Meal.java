package application;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Meal {

	private int Id;
	private String name;
	private String photoName;
	private int recipeId;
	
	static String createTable() {
		
		return "CREATE TABLE Meals ("
				+ "Id int not null primary key "
				+ "GENERATED ALWAYS AS IDENTITY "
				+ "(START WITH 1, INCREMENT BY 1),"
				+ "Name varchar(255), "
				+ "Photo varchar(255)"
				+ "RecipeId int )";
	}
	
	static String insertData() {
		
		return "INSERT INTO Meals "
				+ "(Name, Photo, RecipeId) VALUES "
				+ "('Spaghetti Bolognese', 'Pic001.png', '1'), "
				+ "('Sushi', 'Pic002.png', '2'), "
				+ "('Grilled Cheese', 'Pic003.png', '3'), "
				+ "('Tonkotsu Ramen', 'Pic004.png', '4'), "
				+ "('Carne Asada Burrito', 'Pic005.png', '5')";
	}
	
	static String allData() {
		
		return "SELECT * FROM Meals";
	}
	
	static String dropTable() {
		
		return "DROP TABLE Meals";
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
