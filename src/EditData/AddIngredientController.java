package EditData;

import java.io.IOException;
import java.sql.Statement;

import application.Ingredient;
import application.Recipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sql.SQLConnection;
import sql.SQLIngredients;
import sql.SQLRecipes;

/**
 * Controller class for the add meal page, this will allow users to submit new meals and take them back to the main menu.
 * @author Malcolm
 *
 */
public class AddIngredientController {
	
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	@FXML
	private TextField ingredientNameField;
	@FXML
	private TextField ingredientCaloriesField;
	@FXML
	private TextField ingredientCarbsField;
	@FXML
	private TextField ingredientFiberField;
	@FXML
	private TextField ingredientProteinField;
	@FXML
	private TextField ingredientFatField;
	@FXML
	private TextField ingredientSugarField;
	@FXML
	private TextField ingredientServingSizeField;
	
	public void submitIngredient(ActionEvent event) throws IOException {
		
		//todo: input validation, change gui to use dropdowns
		Ingredient ingredient = new Ingredient(0,ingredientNameField.getText(),
				ingredientCaloriesField.getText(),
				ingredientCarbsField.getText(),
				ingredientFiberField.getText(),
				ingredientProteinField.getText(),
				ingredientFatField.getText(),
				ingredientSugarField.getText(),
				ingredientServingSizeField.getText()
		);

		try (SQLConnection sqlConnection = new SQLConnection()){
			Statement s = sqlConnection.getSqlStatement();
			//s.execute(SQLIngredients.insertDataIntoTable(ingredient));
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
