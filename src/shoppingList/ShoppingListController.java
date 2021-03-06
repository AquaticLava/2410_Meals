package shoppingList;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.stage.Stage;
/**
 * Prototype class that will allow the user to create a shopping list in the future.
 * @author Malcolm
 *
 */
public class ShoppingListController {

	private Parent root;
	private Stage stage;
	private Scene scene;

	@FXML
	private Accordion shoppingListMeals;
	
	public void switchToMainMenu(ActionEvent event) throws IOException {
		
		root = FXMLLoader.load(getClass().getResource("/MealBrowser/MealBrowser.fxml"));
		scene = new Scene(root);
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
}
