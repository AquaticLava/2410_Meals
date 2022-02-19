package MealBrowser;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * Controller class for the meal browser, controls buttons and loads meal/recipe info.
 * @author Malcolm
 *
 */
public class MealBrowserController {
	
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	@FXML
	private TextArea recipeDescriptionAndInstructionField;
	private ListView ingredientsList;
	private Slider ingredientsSlider;
	
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
