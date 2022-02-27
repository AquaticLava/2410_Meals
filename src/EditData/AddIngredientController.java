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
 * Controller class for the add meal page, this will allow users to submit new
 * meals and take them back to the main menu.
 * 
 * @author Malcolm Bailey
 *
 */
public class AddIngredientController {

	private Parent root;
	private Stage stage;
	private Scene scene;

	// FXML handles to make the respective fields accessible to the controller.
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

	/**
	 * Creates a new ingredient and stores it in the database.
	 * 
	 * @param event : the event when the add ingredient button is pressed
	 * @throws IOException
	 */
	public void submitIngredient(ActionEvent event) throws IOException {

		// Check valid input
		if (validInput(ingredientNameField) && validInput(ingredientCaloriesField) && validInput(ingredientCarbsField)
				&& validInput(ingredientFiberField) && validInput(ingredientProteinField)
				&& validInput(ingredientFatField) && validInput(ingredientSugarField)
				&& validInput(ingredientServingSizeField)) {
			// TODO change gui to use dropdowns
			Ingredient ingredient = new Ingredient(-1, ingredientNameField.getText(),
					Float.parseFloat(ingredientCaloriesField.getText()), ingredientCarbsField.getText(),
					ingredientFiberField.getText(), ingredientProteinField.getText(), ingredientFatField.getText(),
					ingredientSugarField.getText(), ingredientServingSizeField.getText());

			try (SQLConnection sqlConnection = new SQLConnection()) {
				Statement s = sqlConnection.getSqlStatement();
				s.executeUpdate(SQLIngredients.insertDataIntoTable(ingredient));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("EditData.fxml"));
			root = loader.load();
			scene = new Scene(root);
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		}

	}

	// Checks to see if any input is empty to prevent null values.
	private boolean validInput(TextField t) {

		if (t.getText() == "") {
			t.setText("ERROR: EACH FIELD NEEDS TO HAVE A VALUE SET");
			return false;
		}

		return true;
	}

}
