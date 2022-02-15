package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sql.*;

/**
 * 
 * @author Malcolm
 *
 */

public class Main extends Application{
	
	 //Add a properties file that can be read from for SQL queries.
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
//	
	public static void main(String[] args) {
		
		launch(args);
		
//		try {Connection c = 
//			DriverManager.getConnection("jdbc:derby:MealDatabase;create=true");
//			Statement s = c.createStatement();
//			
//			System.out.println("Meals");
//			s.execute(SQLMeals.dropTable());
//			s.execute(SQLMeals.createTable());
//			s.execute(SQLMeals.insertDataIntoTable());
//			ResultSet rsMeals = s.executeQuery(SQLMeals.allDataFromTable());
//			SQLMeals.printData(rsMeals);
//			System.out.println();
//			
//			System.out.println("Recipes");
//			s.execute(SQLRecipes.dropTable());
//			s.execute(SQLRecipes.createTable());
//			s.execute(SQLRecipes.insertDataIntoTable());
//			ResultSet rsRecipes = s.executeQuery(SQLRecipes.allDataFromTable());
//			SQLRecipes.printData(rsRecipes);
//			System.out.println();
//			
//			System.out.println("Ingredients");
//			s.execute(SQLIngredients.dropTable());
//			s.execute(SQLIngredients.createTable());
//			s.execute(SQLIngredients.insertDataIntoTable());
//			ResultSet rsIngredients = s.executeQuery(SQLIngredients.allDataFromTable());
//			SQLIngredients.printData(rsIngredients);
//			System.out.println();
//			
//			System.out.println("Recipes/Ingredients");
//			s.execute(SQLRecipesIngredients.dropTable());
//			s.execute(SQLRecipesIngredients.createTable());
//			s.execute(SQLRecipesIngredients.insertDataIntoTable());
//			ResultSet rsRecipesIngredients = s.executeQuery(SQLRecipesIngredients.allDataFromTable());
//			SQLRecipesIngredients.printData(rsRecipesIngredients);
//			System.out.println();
//			
//			
//			
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
