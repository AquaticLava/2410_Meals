package EditData;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sql.SQLConnection;
import sql.SQLIngredients;
import sql.SQLMeals;
import sql.SQLRecipes;

public class EditMealController {

	private Parent root;
	private Stage stage;
	private Scene scene;

	private int currentMealId;
	@FXML
	TextArea mealNameField;
	@FXML
	TextField mealPhotoField;
	@FXML
	TableView<Recipe> mealRecipeTable;
	@FXML
	TableColumn<Recipe, Integer> mealRecipeIdColumn;
	@FXML
	TableColumn<Recipe, String> mealRecipeNameColumn;

	/**
	 * Updates the meal in the SQL database to the new values.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void submitEditedMeal(ActionEvent event) throws IOException {

		try (SQLConnection c = new SQLConnection()) {

			Statement s = c.getSqlStatement();

			String mealName = mealNameField.getText();
			String mealPhoto = mealPhotoField.getText();
			Recipe mealRecipe = mealRecipeTable.getSelectionModel().getSelectedItem();
			String recipeId = Integer.toString(mealRecipe.getId());

			s.executeUpdate(SQLMeals.updateRow(currentMealId, mealName, mealPhoto, recipeId));
			s.close();

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		root = FXMLLoader.load(getClass().getResource("EditData.fxml"));
		scene = new Scene(root);
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Fills in the fields based on the select meal to be edited.
	 * @param mealId
	 */
	void loadCurrentMeal(int mealId) {

		this.currentMealId = mealId;
		try (SQLConnection c = new SQLConnection()) {
			initialize();
			Statement s = c.getSqlStatement();
			ResultSet rs = s.executeQuery(SQLMeals.getMealById(mealId));
			rs.next();
			mealNameField.setText(rs.getString("Name"));
			mealPhotoField.setText(rs.getString("Photo"));
			Integer recipeId = rs.getInt("RecipeId");
			mealRecipeTable.getSelectionModel().select(recipeId);
			rs.close();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Populates the recipe table on load.
	 */
	private void initialize() {

		mealRecipeIdColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
		mealRecipeNameColumn.setCellValueFactory(new PropertyValueFactory<>("recipeName"));

		mealRecipeTable.getItems().setAll(parseRecipeList("ID"));
	}
		
	/**
	 * Fetches the recipes from the database and passes them as a list
	 * @param sortMethod determines the field to sort the recipes by.
	 * @return list of sorted recipes
	 */
	private List<Recipe> parseRecipeList(String sortMethod) {
		List<Recipe> r = new LinkedList<>();
		try (SQLConnection sqlConnection = new SQLConnection()) {
			Statement s = sqlConnection.getSqlStatement();

			ResultSet rs = s.executeQuery(SQLRecipes.allDataFromTable(sortMethod));

			while (rs.next()) {
				r.add(new Recipe(Integer.parseInt(rs.getString("ID")), rs.getString("RecipeName"),
						rs.getString("RecipeInstructions"), rs.getString("CookTime"), rs.getString("PrepTime"),
						rs.getString("RecipeDescription"), rs.getString("CostCategory")));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
}
