package EditData;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sql.SQLConnection;
import sql.SQLRecipes;

/**
 * Controller for the edit recipe fxml window. Enables the loading of a selected recipe's fields
 * and then the editing of them to the database.
 * @author Malcolm Bailey
 *
 */
public class EditRecipeController {

	private Parent root;
	private Stage stage;
	private Scene scene;

	int recipeId;
	@FXML
	TextField recipeNameField;
	@FXML
	TextArea recipeInstructionField;
	@FXML
	TextField cookTimeField;
	@FXML
	TextField prepTimeField;
	@FXML
	TextArea recipeDescriptionField;
	@FXML
	TextField costCategoryField;

	/**
	 * Updates recipe in database.
	 * 
	 * @param event : event from edit recipe button being pressed.
	 * @throws IOException
	 */
	@FXML
	void submitEditedRecipe(ActionEvent event) throws IOException {

		String recipeName = recipeNameField.getText();
		String recipeInstructions = recipeInstructionField.getText();
		String cookTime = cookTimeField.getText();
		String prepTime = prepTimeField.getText();
		String recipeDescription = recipeDescriptionField.getText();
		String costCategory = costCategoryField.getText();

		try (SQLConnection sqlConnection = new SQLConnection()) {

			Statement s = sqlConnection.getSqlStatement();
			s.execute(SQLRecipes.updateRow(recipeId, recipeName, recipeInstructions, cookTime, prepTime,
					recipeDescription, costCategory));
			s.close();
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

	/**
	 * Populates the fields based on the recipe selected to be edited.
	 * @param recipeId
	 */
	void loadCurrentRecipe(int recipeId) {

		this.recipeId = recipeId;
		try (SQLConnection sqlConnection = new SQLConnection()) {

			Statement s = sqlConnection.getSqlStatement();
			ResultSet rs = s.executeQuery(SQLRecipes.pullRecipeRecordByID(recipeId));
			rs.next();
			recipeNameField.setText(rs.getString("RecipeName"));
			recipeInstructionField.setText(rs.getString("RecipeInstructions"));
			cookTimeField.setText(rs.getString("CookTime"));
			prepTimeField.setText(rs.getString("PrepTime"));
			recipeDescriptionField.setText(rs.getString("RecipeDescription"));
			costCategoryField.setText(rs.getString("CostCategory"));
			rs.close();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
