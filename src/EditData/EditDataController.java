package EditData;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Controller class for the data editing tab, controls buttons and manipulates data records.
 * @author Malcolm
 *
 */
public class EditDataController {
	
	@FXML
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	public void switchToMainMenu(ActionEvent event) throws IOException {
		
		root = FXMLLoader.load(getClass().getResource("/MainMenu/MainMenu.fxml"));
		scene = new Scene(root);
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToAddMeal(ActionEvent event) throws IOException {
		
		root = FXMLLoader.load(getClass().getResource("AddMeal.fxml"));
		scene = new Scene(root);
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToAddIngredient(ActionEvent event) throws IOException {
		
		root = FXMLLoader.load(getClass().getResource("AddIngredient.fxml"));
		scene = new Scene(root);
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToAddRecipe(ActionEvent event) throws IOException {
		
		root = FXMLLoader.load(getClass().getResource("AddRecipe.fxml"));
		scene = new Scene(root);
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
}
