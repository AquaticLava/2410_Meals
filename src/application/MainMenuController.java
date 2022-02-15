package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenuController {
	
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	public void switchToMealBrowser(ActionEvent event) throws IOException {
		
		root = FXMLLoader.load(getClass().getResource("MealBrowser.fxml"));
		scene = new Scene(root);
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToAddMeals(ActionEvent event) throws IOException {
		
		root = FXMLLoader.load(getClass().getResource("AddMeal.fxml"));
		scene = new Scene(root);
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToEditData(ActionEvent event) throws IOException {
		
		root = FXMLLoader.load(getClass().getResource("EditData.fxml"));
		scene = new Scene(root);
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
}
