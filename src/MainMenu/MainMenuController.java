package MainMenu;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Controller class for the main menu, controls buttons to navigate to other pages.
 * @author Malcolm
 *
 */
public class MainMenuController {
	
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	public void switchToMealBrowser(ActionEvent event) throws IOException {
		
		root = FXMLLoader.load(getClass().getResource("/MealBrowser/MealBrowser.fxml"));
		scene = new Scene(root);
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToEditData(ActionEvent event) throws IOException {
		
		root = FXMLLoader.load(getClass().getResource("/EditData/EditData.fxml"));
		scene = new Scene(root);
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
}
