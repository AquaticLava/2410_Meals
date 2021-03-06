package EditData;

import application.Ingredient;
import application.Main;
import application.Meal;
import application.Recipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import sql.SQLMeals;
import sql.SQLRecipes;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller class for the add meal page, this will allow users to submit new
 * meals and take them back to the main menu.
 * 
 * @author Malcolm Bailey
 *
 */
public class AddMealController implements Initializable {

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
	private TableColumn<Recipe, Integer> mealRecipeIdColumn;
	@FXML
	private TableColumn<Recipe, String> mealRecipeNameColumn;

	/**
	 * Adds the new meal to our database
	 * 
	 * @param event: The event from the add meal button being clicked.
	 * @throws IOException
	 */
	public void submitMeal(ActionEvent event) throws IOException {

		Recipe selectedRecipe = mealRecipeTable.getSelectionModel().getSelectedItem();

		if (validInput(mealNameField) && validInput(mealPhotoField)) {
			// TODO change gui to use dropdowns
			Meal meal = new Meal(-1, mealNameField.getText(), mealPhotoField.getText(), selectedRecipe.getId());

			try (SQLConnection sqlConnection = new SQLConnection()) {
				Statement s = sqlConnection.getSqlStatement();
				s.execute(SQLMeals.insertDataIntoTable(meal));
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

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		mealRecipeIdColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
		mealRecipeNameColumn.setCellValueFactory(new PropertyValueFactory<>("recipeName"));

		mealRecipeTable.getItems().setAll(parseRecipeList("ID"));
	}

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

	// Checks to see if any input is empty to prevent null values.
	private boolean validInput(TextArea t) {

		if (t.getText() == "") {
			t.setText("ERROR: EACH FIELD NEEDS TO HAVE A VALUE SET");
			return false;
		}

		return true;
	}
}