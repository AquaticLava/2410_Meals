package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sql.*;

/**
 * 
 * @author Malcolm
 *
 */

public class Main extends Application {
	// Add a properties file that can be read from for SQL queries.
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		//launch(args);
		Database db = new Database();
		Statement s = db.getStatement();
		
		try {
			s.execute(SQLMeals.createTable());
			s.execute(SQLMeals.insertDataIntoTable());
			ResultSet rsMeals = s.executeQuery(SQLMeals.allDataFromTable());
			SQLMeals.printData(rsMeals);
			
			s.execute(SQLRecipes.createTable());
			s.execute(SQLRecipes.insertDataIntoTable());
			ResultSet rsRecipes = s.executeQuery(SQLRecipes.allDataFromTable());
			SQLRecipes.printData(rsRecipes);
			
			s.execute(SQLIngredients.createTable());
			s.execute(SQLIngredients.insertDataIntoTable());
			ResultSet rsIngredients = s.executeQuery(SQLIngredients.allDataFromTable());
			SQLIngredients.printData(rsIngredients);
			
			s.execute(SQLRecipesIngredients.createTable());
			s.execute(SQLRecipesIngredients.insertDataIntoTable());
			ResultSet rsRecipesIngredients = s.executeQuery(SQLRecipesIngredients.allDataFromTable());
			SQLRecipesIngredients.printData(rsRecipesIngredients);
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
