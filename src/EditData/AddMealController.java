package EditData;

import java.io.IOException;

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
import javafx.stage.Stage;

/**
 * Controller class for the add meal page, this will allow users to submit new meals and take them back to the main menu.
 * @author Malcolm
 *
 */
public class AddMealController {
	
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	@FXML
	private TextArea mealNameField;
	private TextArea mealPhotoField;
	private TableView mealRecipeTable;
	private TableColumn mealRecipeIdColumn;
	
	public void submitMeal(ActionEvent event) throws IOException {
		
		//TODO submit the info to the database
		
		root = FXMLLoader.load(getClass().getResource("EditData.fxml"));
		scene = new Scene(root);
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
}
