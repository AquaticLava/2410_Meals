package EditData;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

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

/**
 * Controller class for the add meal page, this will allow users to submit new meals and take them back to the main menu.
 * @author Malcolm
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
	
	
	public void submitRecipe(ActionEvent event) throws IOException {

		root = FXMLLoader.load(getClass().getResource("EditData.fxml"));
		scene = new Scene(root);
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
		//todo: input validation, change gui to use dropdowns
		Recipe recipe = new Recipe(0,recipeNameField.getText(),
				recipeInstructionField.getText(),
				cookTimeField.getText(),
				prepTimeField.getText(),
				recipeDescriptionField.getText(),
				costCategoryField.getText()
		);

		try (SQLConnection sqlConnection = new SQLConnection()){
			Statement s = sqlConnection.getSqlStatement();
			s.execute(SQLRecipes.insertDataIntoTable(recipe));
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}