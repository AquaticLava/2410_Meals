package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import EditData.CSVInput;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sql.*;

/**
 * Main method to launch the recipe book application.
 * 
 * @author Collin Williams, Malcolm Bailey
 */

public class Main extends Application{

	public static String css = Main.class.getResource("application.css").toExternalForm();
	
	 //Add a properties file that can be read from for SQL queries.
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/MainMenu/MainMenu.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(Main.css);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Main Menu");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method reads in the ingredients and recipes csv files and syncs them with
	 * the java database.
	 */
	private static void csvToDB() {
		//Read in and save the ingredients into an array list of ingredients.
		CSVInput csvIngredients = new CSVInput("src/application/resources/ingredients.csv", "ingredients");
		ArrayList<String> dataLines = csvIngredients.getDataList();
		Ingredient[] ingredients = new Ingredient[dataLines.size()];
		for (int i = 0; i < dataLines.size(); i++){
			ingredients[i] = new Ingredient(dataLines.get(i));
		}

//		Read in and save the recipes into an array list of recipes.
		CSVInput csvRecipes = new CSVInput("src/application/resources/recipes.csv", "recipes");
		ArrayList<String> dataLineRecipes = csvRecipes.getDataList();
		String[] lines = dataLineRecipes.get(0).split("@");
		Recipe[] recipes = new Recipe[lines.length];
//		System.out.println("Printing out recipes!");
		for (int i = 0; i < lines.length; i++){
			recipes[i] = new Recipe(lines[i]);
		}


		try (Connection c = DriverManager.getConnection("jdbc:derby:MealDatabase;create=true");
			 Statement s = c.createStatement()){
//			System.out.println("Recipes");

			s.execute(SQLRecipes.createTable());
			s.execute(SQLRecipes.insertDataIntoTable(recipes));
			ResultSet rsRecipes = s.executeQuery(SQLRecipes.allDataFromTable("ID"));
//			SQLRecipes.printData(rsRecipes);

			//Puts five meals into the database and prints out the results.
//			System.out.println("Put Testing Meals into the database");

			s.execute(SQLMeals.createTable());
			s.execute(SQLMeals.insertFirstTestMeals());
			ResultSet rsMeals = s.executeQuery(SQLMeals.ALLDATAFROMTABLE);
//			SQLMeals.printData(rsMeals);
//			System.out.println();

//			System.out.println("Ingredients");

			s.execute(SQLIngredients.createTable());
			s.execute(SQLIngredients.insertDataIntoTable(ingredients));
			ResultSet rsIngredients = s.executeQuery(SQLIngredients.allDataFromTable("Id"));
//			SQLIngredients.printData(rsIngredients);
//			System.out.println();

//			System.out.println("Recipes/Ingredients");

			s.execute(SQLRecipesIngredients.createTable());
			s.execute(SQLRecipesIngredients.insertDataIntoTable());
//			ResultSet rsRecipesIngredients = s.executeQuery(SQLRecipesIngredients.allDataFromTable());
//			SQLRecipesIngredients.printData(rsRecipesIngredients);
//			System.out.println();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private static void DBDropTables(){
		try (Connection c = DriverManager.getConnection("jdbc:derby:MealDatabase;create=true");
			 Statement s = c.createStatement()) {
			s.execute(SQLRecipes.DROPTABLE);
			s.execute(SQLMeals.DROPTABLE);
			s.execute(SQLIngredients.dropTable());
			s.execute(SQLRecipesIngredients.dropTable());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

//		DBDropTables();
//		csvToDB();
		launch(args);

	}
}
