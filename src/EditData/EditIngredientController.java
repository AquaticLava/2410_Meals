package EditData;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sql.SQLConnection;
import sql.SQLIngredients;
/**
 * Controller class that enables the loading of fields for a selected ingredient
 * and the consequent saving of those fields with updated values.
 * @author Malcolm Bailey
 *
 */
public class EditIngredientController {

	private Parent root;
	private Stage stage;
	private Scene scene;

	private int currentIngredientId;
	@FXML
	TextField ingredientNameField;
	@FXML
	TextField ingredientCaloriesField;
	@FXML
	TextField ingredientCarbsField;
	@FXML
	TextField ingredientFiberField;
	@FXML
	TextField ingredientProteinField;
	@FXML
	TextField ingredientFatField;
	@FXML
	TextField ingredientSugarField;
	@FXML
	TextField ingredientServingSizeField;

	@FXML
	void submitEditedIngredient(ActionEvent event) throws IOException {

		try (SQLConnection c = new SQLConnection()) {

			Statement s = c.getSqlStatement();

			String name = ingredientNameField.getText();
			String calories = ingredientCaloriesField.getText();
			String carbs = ingredientCarbsField.getText();
			String fiber = ingredientFiberField.getText();
			String protein = ingredientProteinField.getText();
			String fat = ingredientFatField.getText();
			String sugar = ingredientSugarField.getText();
			String servingSize = ingredientServingSizeField.getText();

			s.executeUpdate(SQLIngredients.updateRow(currentIngredientId, name, calories, carbs, fiber, protein, fat,
					sugar, servingSize));
			s.close();

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		root = FXMLLoader.load(getClass().getResource("EditData.fxml"));
		scene = new Scene(root);
		scene.getStylesheets().add(Main.css);
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	void loadCurrentIngredient(int ingredientId) {

		this.currentIngredientId = ingredientId;
		try (SQLConnection c = new SQLConnection()) {

			Statement s = c.getSqlStatement();
			ResultSet rs = s.executeQuery(SQLIngredients.getIngredientById(ingredientId));
			rs.next();
			ingredientNameField.setText(rs.getString("Name"));
			ingredientCaloriesField.setText(rs.getString("Calories"));
			ingredientCarbsField.setText(rs.getString("Carbs"));
			ingredientFiberField.setText(rs.getString("Fiber"));
			ingredientProteinField.setText(rs.getString("Protein"));
			ingredientFatField.setText(rs.getString("Fat"));
			ingredientSugarField.setText(rs.getString("Sugar"));
			ingredientServingSizeField.setText(rs.getString("ServingSize"));
			rs.close();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
