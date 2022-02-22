package MealBrowser;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Ingredient;
import application.Meal;
import application.StringKeyValuePair;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sql.SQLConnection;
import sql.SQLMeals;
import sql.SQLRecipes;

/**
 * Controller class for the meal browser, controls buttons and loads meal/recipe info.
 * @author Malcolm, Collin, and Eric
 *
 */
public class MealBrowserController implements Initializable{

	@FXML
	private TextArea recipeDescriptionField;
	@FXML
	private Image mealImage;
	@FXML
	private ComboBox<StringKeyValuePair<Meal>> mealsDropdown;
	private Parent root;
	private Stage stage;
	private Scene scene;

	//here is where recipe instructions go.
	@FXML
	private TextArea recipeInstructionField;
	@FXML
	private ListView<Ingredient> ingredientsList;
	@FXML
	private Slider ingredientsSlider;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		recipeDescriptionField recipeInstructionField
		ObservableList<StringKeyValuePair<Meal>> observableList = FXCollections.observableArrayList();
		//observableList.add(new Meal(1,"test","photo",9));
		mealsDropdown.setItems(observableList);

		//==========================================================================
		SQLConnection c = null;
		ResultSet rsMeals;
		int i = 0;
		Meal[] meals = new Meal[5];
		//Pull Meals from database
		try {
			c = new SQLConnection();
			rsMeals = c.getSqlStatement().executeQuery(SQLMeals.ALLDATAFROMTABLE);
			while (rsMeals.next()){
				meals[i] = new Meal(i+1, rsMeals.getString("Name"), rsMeals.getString("Photo"), i+1);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//==========================================================================

//		Meal meal = new Meal(1,"test","photo",9);
		for (Meal meal : meals) {
			observableList.add(new StringKeyValuePair<>(meal, meal.getName()));
		}

		SQLConnection finalC = c;
		mealsDropdown.getSelectionModel().selectedItemProperty().addListener
				((observableValue1, integerSingleSelectionModel, t11) -> {
					Meal m = mealsDropdown.getSelectionModel().getSelectedItem().getValue();
					try {
						ResultSet rs = finalC.getSqlStatement().executeQuery(SQLRecipes.pullRecipeByID(m.getId()));
						while (rs.next()){
							recipeDescriptionField.setText(rs.getString("RecipeDescription"));
							recipeInstructionField.setText(rs.getString("RecipeInstructions"));
						}

					} catch (SQLException e) {
						e.printStackTrace();
					}

				});
	}



	public void switchToMainMenu(ActionEvent event) throws IOException {
		
		root = FXMLLoader.load(getClass().getResource("/MainMenu/MainMenu.fxml"));
		scene = new Scene(root);
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	/* ******
	Pull GUI into class //TODO
	Add Items to those GUI elements (use SQL Commands here - Meal / Recipe)
		-Populate Instructions
	 */

}
