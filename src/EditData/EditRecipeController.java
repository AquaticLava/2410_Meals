package EditData;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sql.SQLConnection;
import sql.SQLRecipes;

public class EditRecipeController {

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
	
	
	public void submitEditedRecipe() {
		
		String recipeName = recipeNameField.getText();
		String recipeInstructions =recipeInstructionField.getText();
		String cookTime =cookTimeField.getText();
		String prepTime = prepTimeField.getText();
		String recipeDescription = recipeDescriptionField.getText();
		String costCategory = costCategoryField.getText();
		
		try (SQLConnection sqlConnection = new SQLConnection()){
			Statement s = sqlConnection.getSqlStatement();
			s.executeQuery(SQLRecipes.updateRow(recipeId, recipeName, recipeInstructions, cookTime, 
					 prepTime, recipeDescription, costCategory));
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
