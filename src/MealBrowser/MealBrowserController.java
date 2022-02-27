package MealBrowser;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;

import application.Ingredient;
import application.Meal;
import application.StringKeyValuePair;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Callback;
import sql.SQLConnection;
import sql.SQLIngredients;
import sql.SQLMeals;
import sql.SQLRecipes;
import sql.SQLRecipesIngredients;

/**
 * Controller class for the meal browser, controls buttons and loads meal/recipe info.
 * @author Malcolm Bailey, Eric Rytting, Collin Williams
 *
 */
public class MealBrowserController implements Initializable{

	@FXML
	private TextArea recipeDescriptionField;
	@FXML
	private Image mealImage;
	@FXML
	private ComboBox<StringKeyValuePair<Meal>> mealsDropdown;
	@FXML
	private BorderPane mealBrowserPane;
	
	private Parent root;
	private Stage stage;
	private Scene scene;

	//here is where recipe instructions go.
	@FXML
	private TextArea recipeInstructionField;
	@FXML
	private ListView<String> ingredientNameList;

	/**
	 * Loads page and fills value for combo box containing all meals.
	 */
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

		for (Meal meal : meals) {
			observableList.add(new StringKeyValuePair<>(meal, meal.getName()));
		}
		
		//Add listener to the drop down to load new meal info whenever a meal is selected.
		mealsDropdown.getSelectionModel().selectedItemProperty().addListener
				((observableValue1, integerSingleSelectionModel, t11) -> {
					Meal m = mealsDropdown.getSelectionModel().getSelectedItem().getValue();
					
					//TODO refactor the following block into a method that's called by the listener.
					try {
						SQLConnection finalC = new SQLConnection();
						ResultSet rs = finalC.getSqlStatement().executeQuery(SQLRecipes.pullRecipeByID(m.getRecipeId()));
						while (rs.next()){
							recipeDescriptionField.setText(rs.getString("RecipeDescription"));
							recipeInstructionField.setText(rs.getString("RecipeInstructions"));
							
							
							rs = finalC.getSqlStatement().executeQuery(SQLRecipesIngredients.pullIngredientsByRecipeID(m.getRecipeId()));
							LinkedList<Integer> ingredientIdList = new LinkedList<>();
							while(rs.next()) {
								ingredientIdList.add(rs.getInt("IngredientId"));
							}
							
							LinkedList<String> ingredientNames = new LinkedList<>();
							
							for(Integer id : ingredientIdList) {
								
								rs = finalC.getSqlStatement().executeQuery(SQLIngredients.getIngredientById(id));
								rs.next();
								ingredientNames.add(rs.getString("Name"));

							}

							
							ingredientNameList.setItems(FXCollections.observableList(ingredientNames));
						}
						rs.close();

					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					String photo = m.getPhotoName();
					String imagePath = System.getProperty("user.dir") + "\\src\\application\\imgs\\" + photo;
					Image img = new Image(imagePath, 600, 600, false, true);
			        BackgroundImage bImg = new BackgroundImage(img,
			                                                   BackgroundRepeat.NO_REPEAT,
			                                                   BackgroundRepeat.NO_REPEAT,
			                                                   BackgroundPosition.DEFAULT,
			                                                   BackgroundSize.DEFAULT);
			        Background bGround = new Background(bImg);
			        mealBrowserPane.setBackground(bGround);
				});
	}

	
	/**
	 * Changes the scene back to the main menu.
	 * @param event
	 * @throws IOException
	 */
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
