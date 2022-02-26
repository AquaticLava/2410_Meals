package EditData;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

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
	
	
	public void loadRecipe(int recipeId) {
		
		this.recipeId = recipeId;
		try (SQLConnection sqlConnection = new SQLConnection()){
			
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void submitEditedRecipe(ActionEvent event) throws IOException{
		
		String recipeName = recipeNameField.getText();
		String recipeInstructions =recipeInstructionField.getText();
		String cookTime =cookTimeField.getText();
		String prepTime = prepTimeField.getText();
		String recipeDescription = recipeDescriptionField.getText();
		String costCategory = costCategoryField.getText();
		
		try (SQLConnection sqlConnection = new SQLConnection()){
			Statement s = sqlConnection.getSqlStatement();
//			System.out.println(SQLRecipes.updateRow(recipeId, recipeName, recipeInstructions, cookTime,
//					prepTime, recipeDescription, costCategory));
			s.execute(SQLRecipes.updateRow(recipeId, recipeName, recipeInstructions, cookTime,
					 prepTime, recipeDescription, costCategory));
		
		
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
