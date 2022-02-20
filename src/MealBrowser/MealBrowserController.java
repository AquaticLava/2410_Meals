package MealBrowser;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Ingredient;
import application.Meal;
import application.Recipe;
import application.StringKeyValuePair;
import javafx.animation.KeyValue;
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
import sql.SQLRecipes;

/**
 * Controller class for the meal browser, controls buttons and loads meal/recipe info.
 * @author Malcolm
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
		ObservableList<StringKeyValuePair<Meal>> observableList = FXCollections.observableArrayList();
		//observableList.add(new Meal(1,"test","photo",9));
		mealsDropdown.setItems(observableList);
		Meal meal = new Meal(1,"test","photo",9);
		observableList.add(new StringKeyValuePair<>(meal, meal.getName()));
		mealsDropdown.getSelectionModel().selectedItemProperty().addListener
				((observableValue1, integerSingleSelectionModel, t11) -> {
					Recipe recipe = new Recipe(4,"Recipe","Cookme","7","56","Dish","$$");
					recipeDescriptionField.setText(recipe.getRecipeDescription());
					recipeInstructionField.setText(recipe.getRecipeInstructions());
				});
	}



	public void switchToMainMenu(ActionEvent event) throws IOException {
		
		root = FXMLLoader.load(getClass().getResource("/MainMenu/MainMenu.fxml"));
		scene = new Scene(root);
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	
}
