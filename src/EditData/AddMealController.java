package EditData;

import application.Ingredient;
import application.Meal;
import application.Recipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sql.SQLConnection;
import sql.SQLMeals;

import java.io.IOException;
import java.sql.Statement;

/**
 * Controller class for the add meal page, this will allow users to submit new meals and take them back to the main menu.
 * @author Malcolm
 *
 */
public class AddMealController {
	
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	@FXML
	private TextArea mealNameField;
	@FXML
	private TextArea mealPhotoField;
	@FXML
	private TableView<Recipe> mealRecipeTable;
	@FXML
	private TableColumn<Recipe,Integer> mealRecipeIdColumn;
	
	public void submitMeal(ActionEvent event) throws IOException {
		
		Recipe selectedRecipe = mealRecipeTable.getSelectionModel().getSelectedItem();
		//todo: input validation, change gui to use dropdowns
		Meal meal = new Meal(-1,mealNameField.getText(),
				mealPhotoField.getText(),
				selectedRecipe.getId()
		);



		try (SQLConnection sqlConnection = new SQLConnection()){
			Statement s = sqlConnection.getSqlStatement();
//			s.execute(SQLMeals.insertDataIntoTable(Meal));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		root = FXMLLoader.load(getClass().getResource("EditData.fxml"));
		scene = new Scene(root);
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
}
