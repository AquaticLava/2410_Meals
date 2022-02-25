package EditData;

import application.Ingredient;
import application.Meal;
import application.Recipe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sql.SQLConnection;
import sql.SQLIngredients;
import sql.SQLMeals;
import sql.SQLRecipes;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

/**
 * Controller class for the data editing tab, controls buttons and manipulates
 * data records.
 * 
 * @author Malcolm and Eric
 *
 */
public class EditDataController implements Initializable {
	String toggleMethod = "ID";

	@FXML
	private ComboBox<String> rowSelectionRecipe;
	@FXML
	private ComboBox<String> rowSelectionIngredient;
	@FXML
	private ComboBox<String> rowSelectionMeal;

	@FXML
	private ToggleGroup sortRecipes;
	@FXML
	private ToggleGroup sortIngredients;
	@FXML
	private ToggleGroup sortMeals;

	private Parent root;
	private Stage stage;
	private Scene scene;
	private int numOfRows = 5;

	@FXML
	private TableView<Recipe> recipeTableView;
	@FXML
	private TableColumn<Recipe, Integer> recipeIdColumn;
	@FXML
	private TableColumn<Recipe, String> recipeNameColumn;
	@FXML
	private TableColumn<Recipe, String> recipeInstructionsColumn;
	@FXML
	private TableColumn<Recipe, Integer> recipeCookTimeColumn;
	@FXML
	private TableColumn<Recipe, Integer> recipePrepTimeColumn;
	@FXML
	private TableColumn<Recipe, String> recipeDescriptionColumn;
	@FXML
	private TableColumn<Recipe, Integer> recipeCostCategoryColumn;
	@FXML
	private TableColumn<Recipe, String> recipeIngredientsColumn;

	@FXML
	private TableView<Meal> mealTableView;
	@FXML
	private TableColumn<Meal, Integer> mealIdColumn;
	@FXML
	private TableColumn<Meal, String> mealNameColumn;
	@FXML
	private TableColumn<Meal, String> mealPhotoColumn;
	@FXML
	private TableColumn<Meal, Integer> mealRecipeColumn;

	@FXML
	private TableView<Ingredient> ingredientTableView;
	@FXML
	private TableColumn<Ingredient, Integer> ingredientIdColumn;
	@FXML
	private TableColumn<Ingredient, String> ingredientNameColumn;
	@FXML
	private TableColumn<Ingredient, String> ingredientCaloriesColumn;
	@FXML
	private TableColumn<Ingredient, String> ingredientCarbColumn;
	@FXML
	private TableColumn<Ingredient, String> ingredientFiberColumn;
	@FXML
	private TableColumn<Ingredient, String> ingredientProteinColumn;
	@FXML
	private TableColumn<Ingredient, String> ingredientFatColumn;
	@FXML
	private TableColumn<Ingredient, String> ingredientSugarColumn;
	@FXML
	private TableColumn<Ingredient, String> ingredientServingSizeColumn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Recipe table initialize
		recipeIdColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
		recipeNameColumn.setCellValueFactory(new PropertyValueFactory<>("recipeName"));
		recipeInstructionsColumn.setCellValueFactory(new PropertyValueFactory<>("recipeInstructions"));
		recipeCookTimeColumn.setCellValueFactory(new PropertyValueFactory<>("cookTime"));
		recipePrepTimeColumn.setCellValueFactory(new PropertyValueFactory<>("prepTime"));
		recipeDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("recipeDescription"));
		recipeCostCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("costCategory"));
		// TODO Needs to be connected to the database using an id recipeIngredients...
//		recipeIngredientsColumn.setCellValueFactory(new PropertyValueFactory<>("recipeIngredients"));

		recipeTableView.getItems().setAll(parseRecipeList("ID"));

		// meal table initialize
		mealIdColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
		mealNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		mealPhotoColumn.setCellValueFactory(new PropertyValueFactory<>("photoName"));
		mealRecipeColumn.setCellValueFactory(new PropertyValueFactory<>("recipeId"));

		mealTableView.getItems().setAll(parseMealList("ID"));

		// ingredient table initialize
		ingredientIdColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
		ingredientNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		ingredientCaloriesColumn.setCellValueFactory(new PropertyValueFactory<>("calories"));
		ingredientCarbColumn.setCellValueFactory(new PropertyValueFactory<>("carbs"));
		ingredientFiberColumn.setCellValueFactory(new PropertyValueFactory<>("fiber"));
		ingredientProteinColumn.setCellValueFactory(new PropertyValueFactory<>("protein"));
		ingredientFatColumn.setCellValueFactory(new PropertyValueFactory<>("fat"));
		ingredientSugarColumn.setCellValueFactory(new PropertyValueFactory<>("sugar"));
		ingredientServingSizeColumn.setCellValueFactory(new PropertyValueFactory<>("servingSize"));

		ingredientTableView.getItems().setAll(parseIngredientList("ID"));

		// Recipe sort buttons
		sortRecipes.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
			RadioButton rbRecipe = (RadioButton) sortRecipes.getSelectedToggle();

			if (rbRecipe != null) {
				switch (rbRecipe.getText()) {
				case "Sort: Name" -> toggleMethod = "RecipeName";
				case "Sort: cost" -> toggleMethod = "CostCategory, RecipeName";
				case "Sort: Cook & prep" -> toggleMethod = "CookTime, PrepTime, RecipeName";
				default -> toggleMethod = "ID";
				}
			}
			recipeTableView.getItems().setAll(parseRecipeList(toggleMethod));
		});

		String[] ints = { "5", "10", "25", "50", "all" };
		ObservableList<String> integers = FXCollections.observableList(Arrays.asList(ints));
		
		rowSelectionRecipe.setItems(integers);
		// Recipe parse based on sort button and number of rows
		rowSelectionRecipe.getSelectionModel().selectedItemProperty()
				.addListener((observableValue1, integerSingleSelectionModel, t11) -> {
					String intAsString = rowSelectionRecipe.getSelectionModel().getSelectedItem();
					numOfRows = intAsString.equals("all") ? -1 : Integer.parseInt(intAsString);
					recipeTableView.getItems().setAll(parseRecipeList(toggleMethod));
				});

		// Ingredient sort buttons
		sortIngredients.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
			RadioButton rbIngredient = (RadioButton) sortIngredients.getSelectedToggle();

			if (rbIngredient != null) {
				switch (rbIngredient.getText()) {
				case "Sort: Name" -> toggleMethod = "Name";
				default -> toggleMethod = "ID";
				}
			}
			ingredientTableView.getItems().setAll(parseIngredientList(toggleMethod));
		});
		
		// Ingredient sort based on sort button and number of rows
		rowSelectionIngredient.setItems(integers);
		rowSelectionIngredient.getSelectionModel().selectedItemProperty()
				.addListener((observableValue1, integerSingleSelectionModel, t11) -> {
					String intAsString = rowSelectionIngredient.getSelectionModel().getSelectedItem();
					numOfRows = intAsString.equals("all") ? -1 : Integer.parseInt(intAsString);
					ingredientTableView.getItems().setAll(parseIngredientList(toggleMethod));
				});
		
		// Meal sort buttons
		sortMeals.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
			RadioButton rbMeal = (RadioButton) sortMeals.getSelectedToggle();

			if (rbMeal != null) {
				switch (rbMeal.getText()) {
				case "Sort: Name" -> toggleMethod = "Name";
				default -> toggleMethod = "ID";
				}
			}
			mealTableView.getItems().setAll(parseMealList(toggleMethod));
		});
		
		// Meal sort based on sort button and number of rows
		rowSelectionMeal.setItems(integers);
		rowSelectionMeal.getSelectionModel().selectedItemProperty()
				.addListener((observableValue1, integerSingleSelectionModel, t11) -> {
					String intAsString = rowSelectionMeal.getSelectionModel().getSelectedItem();
					numOfRows = intAsString.equals("all") ? -1 : Integer.parseInt(intAsString);
					mealTableView.getItems().setAll(parseMealList(toggleMethod));
				});
	}

	/**
	 * Parse method for filling recipe table on the view table page
	 * @param sortMethod
	 * @return
	 */
	
	private List<Recipe> parseRecipeList(String sortMethod) {
		List<Recipe> r = new LinkedList<>();
		try (SQLConnection sqlConnection = new SQLConnection()) {
			Statement s = sqlConnection.getSqlStatement();

			ResultSet recipeRS = (numOfRows == -1) ? s.executeQuery(SQLRecipes.allDataFromTable(sortMethod))
					: s.executeQuery(SQLRecipes.partialDataFromTable(numOfRows, sortMethod));

			while (recipeRS.next()) {
				r.add(new Recipe(Integer.parseInt(recipeRS.getString("ID")), recipeRS.getString("RecipeName"),
						recipeRS.getString("RecipeInstructions"), recipeRS.getString("CookTime"),
						recipeRS.getString("PrepTime"), recipeRS.getString("RecipeDescription"),
						recipeRS.getString("CostCategory")));
			}
			recipeRS.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	/**
	 * Parse method for filling meal table on the view table page
	 * @param sortMethod
	 * @return
	 */
	private List<Meal> parseMealList(String sortMethod) {
		// Here is where we will populate the meal table with default 10 rows for each
		// table
		// TODO Add functional sort buttons like recipe and ingredients
		List<Meal> r = new LinkedList<>();
		try (SQLConnection sqlConnection = new SQLConnection()) {
			Statement s = sqlConnection.getSqlStatement();

			ResultSet mealRS = (numOfRows == -1) ?
					s.executeQuery(SQLMeals.allDataFromTable(sortMethod)) :
					s.executeQuery(SQLMeals.partialDataFromTable
							(numOfRows,sortMethod));

			while (mealRS.next()) {
				r.add(new Meal(Integer.parseInt(mealRS.getString("Id")), mealRS.getString("Name"),
						mealRS.getString("Photo"), Integer.parseInt(mealRS.getString("RecipeId"))));
			}

			mealRS.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return r;
	}

	/**
	 * Parse method for filling ingredient table on the view table page
	 * @param sortMethod
	 * @return
	 */
	private List<Ingredient> parseIngredientList(String sortMethod) {
		// Here is where we will populate the ingredient table with default 10 rows for
		// each table
		// TODO parse and construct recipe datamodel list by looping your ResultSet rs
		// and return the list
//    	List<Ingredient> i = new LinkedList<Ingredient>();
//    	i.add(new Ingredient(-1, "Chicken Broth", "100", "20g", "0g", "5g", "3g", "0g", "6 oz"));
//
//    	return i;

		List<Ingredient> r = new LinkedList<>();
		try (SQLConnection sqlConnection = new SQLConnection()) {
			Statement s = sqlConnection.getSqlStatement();

			ResultSet ingredientRS = (numOfRows == -1) ? s.executeQuery(SQLIngredients.allDataFromTable(sortMethod))
					: s.executeQuery(SQLIngredients.partialDataFromTable(numOfRows, sortMethod));

			while (ingredientRS.next()) {
				r.add(new Ingredient(Integer.parseInt(ingredientRS.getString("Id")), ingredientRS.getString("Name"),
						ingredientRS.getString("Calories"), ingredientRS.getString("Carbs"),
						ingredientRS.getString("Fiber"), ingredientRS.getString("Protein"),
						ingredientRS.getString("Fat"), ingredientRS.getString("Sugar"),
						ingredientRS.getString("ServingSize")));
			}
			ingredientRS.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return r;
	}

	public void switchToMainMenu(ActionEvent event) throws IOException {

		root = FXMLLoader.load(getClass().getResource("/MainMenu/MainMenu.fxml"));
		scene = new Scene(root);
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	public void switchToAddMeal(ActionEvent event) throws IOException {

		root = FXMLLoader.load(getClass().getResource("AddMeal.fxml"));
		scene = new Scene(root);
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	public void switchToAddIngredient(ActionEvent event) throws IOException {

		root = FXMLLoader.load(getClass().getResource("AddIngredient.fxml"));
		scene = new Scene(root);
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	public void switchToAddRecipe(ActionEvent event) throws IOException {

		root = FXMLLoader.load(getClass().getResource("AddRecipe.fxml"));
		scene = new Scene(root);
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	public void deleteRecipe() {
		Recipe selectedItem = recipeTableView.getSelectionModel().getSelectedItem();
		recipeTableView.getItems().remove(selectedItem);
		try (SQLConnection sqlConnection = new SQLConnection()) {
			Statement s = sqlConnection.getSqlStatement();
			s.execute(SQLRecipes.deleteRow(selectedItem.getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteMeal() {
		// TODO need to add call to remove from database
//		Meal selectedItem = (Meal)recipeTableView.getSelectionModel().getSelectedItem();
//		mealTableView.getItems().remove(selectedItem);
	}
}
