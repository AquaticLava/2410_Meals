package EditData;

import application.Main;
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
import sql.SQLRecipes;

import java.io.IOException;
import java.sql.Statement;

/**
 * Controller class for the add meal page, this will allow users to submit new
 * meals and take them back to the main menu.
 * 
 * @author Malcolm Bailey
 *
 */
public class AddRecipeController {
	private Parent root;
	private Stage stage;
	private Scene scene;

	@FXML
	private TextField recipeNameField;
	@FXML
	private TextArea recipeInstructionField;
	@FXML
	private TextField cookTimeField;
	@FXML
	private TextField prepTimeField;
	@FXML
	private TextArea recipeDescriptionField;
	@FXML
	private TextField costCategoryField;

	/**
	 * Adds the new recipe to our database
	 * 
	 * @param event: The event from the add recipe button being clicked.
	 * @throws IOException
	 */
	public void submitRecipe(ActionEvent event) throws IOException {

		if (validInput(recipeNameField) && validInput(recipeInstructionField) && validInput(cookTimeField)
				&& validInput(prepTimeField) && validInput(recipeDescriptionField) && validInput(costCategoryField)) {
			// TODO change gui to use dropdowns
			Recipe recipe = new Recipe(-1, recipeNameField.getText(), recipeInstructionField.getText(),
					cookTimeField.getText(), prepTimeField.getText(), recipeDescriptionField.getText(),
					costCategoryField.getText());

			try (SQLConnection sqlConnection = new SQLConnection()) {
				Statement s = sqlConnection.getSqlStatement();
				s.execute(SQLRecipes.insertDataIntoTable(recipe));
			} catch (Exception e) {
				e.printStackTrace();
			}

			root = FXMLLoader.load(getClass().getResource("EditData.fxml"));
			scene = new Scene(root);
			scene.getStylesheets().add(Main.css);
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		}

	}

	// Checks to see if any input is empty to prevent null values.
	private boolean validInput(TextArea t) {

		if (t.getText() == "") {
			t.setText("ERROR: EACH FIELD NEEDS TO HAVE A VALUE SET");
			return false;
		}

		return true;
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