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
import sql.SQLRecipes;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

/**
 * Controller class for the data editing tab, controls buttons and manipulates data records.
 * @author Malcolm and Eric
 *
 */
public class EditDataController implements Initializable{
	String toggleMethod = "ID";

	@FXML
	private ComboBox<String> rowSelectionRecipe;

	@FXML
	private ToggleGroup sortRecipes;
	private Parent root;
	private Stage stage;
	private Scene scene;
	private int numOfRows = 5;
	
	@FXML
	private TableView<Recipe> recipeTableView;
	@FXML
	private TableColumn<Recipe,Integer> recipeIdColumn;
	@FXML
	private TableColumn<Recipe,String> recipeNameColumn;
	@FXML
	private TableColumn<Recipe,String> recipeInstructionsColumn;
	@FXML
	private TableColumn<Recipe,Integer> recipeCookTimeColumn;
	@FXML
	private TableColumn<Recipe,Integer> recipePrepTimeColumn;
	@FXML
	private TableColumn<Recipe,String> recipeDescriptionColumn;
	@FXML
	private TableColumn<Recipe,Integer> recipeCostCategoryColumn;
	
	@FXML
	private TableView<Meal> mealTableView;
	@FXML
	private TableColumn<Meal,Integer> mealIdColumn;
	@FXML
	private TableColumn<Meal,String> mealNameColumn;
	@FXML
	private TableColumn<Meal,String> mealPhotoColumn;
	@FXML
	private TableColumn<Meal,Integer> mealRecipeColumn;
	
	@FXML
	private TableView<Ingredient> ingredientTableView;
	@FXML
	private TableColumn<Ingredient,Integer> ingredientIdColumn;
	@FXML
	private TableColumn<Ingredient,String> ingredientNameColumn;
	@FXML
	private TableColumn<Ingredient,String> ingredientCaloriesColumn;
	@FXML
	private TableColumn<Ingredient,String> ingredientCarbColumn;
	@FXML
	private TableColumn<Ingredient,String> ingredientFiberColumn;
	@FXML
	private TableColumn<Ingredient,String> ingredientProteinColumn;
	@FXML
	private TableColumn<Ingredient,String> ingredientFatColumn;
	@FXML
	private TableColumn<Ingredient,String> ingredientSugarColumn;
	@FXML
	private TableColumn<Ingredient,String> ingredientServingSizeColumn;
	
	@Override
    public void initialize(URL location, ResourceBundle resources) {
		
		//Recipe table initialize
		recipeIdColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
		recipeNameColumn.setCellValueFactory(new PropertyValueFactory<>("recipeName"));
		recipeInstructionsColumn.setCellValueFactory(new PropertyValueFactory<>("recipeInstructions"));
		recipeCookTimeColumn.setCellValueFactory(new PropertyValueFactory<>("cookTime"));
		recipePrepTimeColumn.setCellValueFactory(new PropertyValueFactory<>("prepTime"));
		recipeDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("recipeDescription"));
		recipeCostCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("costCategory"));
		
		recipeTableView.getItems().setAll(parseRecipeList("ID"));
		
		//meal table initialize
		mealIdColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
		mealNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		mealPhotoColumn.setCellValueFactory(new PropertyValueFactory<>("photoName"));
		mealRecipeColumn.setCellValueFactory(new PropertyValueFactory<>("recipeId"));
		
		mealTableView.getItems().setAll(parseMealList());
		
		//ingredient table initialize
		ingredientIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		ingredientNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		ingredientCaloriesColumn.setCellValueFactory(new PropertyValueFactory<>("calories"));
		ingredientCarbColumn.setCellValueFactory(new PropertyValueFactory<>("carbs"));
		ingredientFiberColumn.setCellValueFactory(new PropertyValueFactory<>("fiber"));
		ingredientProteinColumn.setCellValueFactory(new PropertyValueFactory<>("protein"));
		ingredientFatColumn.setCellValueFactory(new PropertyValueFactory<>("fat"));
		ingredientSugarColumn.setCellValueFactory(new PropertyValueFactory<>("sugar"));
		ingredientServingSizeColumn.setCellValueFactory(new PropertyValueFactory<>("servingSize"));

		ingredientTableView.getItems().setAll(parseIngredientList());

		
		sortRecipes.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
			RadioButton rb = (RadioButton)sortRecipes.getSelectedToggle();

			if (rb != null) {
				switch (rb.getText()) {
					case "Sort: Name" -> toggleMethod = "RecipeName";
					case "Sort: cost" -> toggleMethod = "CostCategory, RecipeName";
					case "Sort: Cook & prep" -> toggleMethod = "CookTime, PrepTime, RecipeName";
					default -> toggleMethod = "ID";
				}
			}
			recipeTableView.getItems().setAll(parseRecipeList(toggleMethod));
		});

		String[] ints = {
				"5","10","25","50","all"
		};
		ObservableList<String> integers = FXCollections.observableList(Arrays.asList(ints));

		rowSelectionRecipe.setItems(integers);
		rowSelectionRecipe.getSelectionModel().selectedItemProperty().addListener
				((observableValue1, integerSingleSelectionModel, t11) -> {
					String intAsString = rowSelectionRecipe.getSelectionModel().getSelectedItem();
					numOfRows = intAsString.equals("all") ?
							-1 : Integer.parseInt(intAsString);
					recipeTableView.getItems().setAll(parseRecipeList(toggleMethod));
				});
    }
	
    private List<Recipe> parseRecipeList(String sortMethod){
    	List<Recipe> r = new LinkedList<>();
		try (SQLConnection sqlConnection = new SQLConnection()) {
			Statement s = sqlConnection.getSqlStatement();

			ResultSet recipeRS = (numOfRows == -1) ?
					s.executeQuery(SQLRecipes.allDataFromTable(sortMethod)) :
					s.executeQuery(SQLRecipes.partialDataFromTable
							(numOfRows,sortMethod));

			while (recipeRS.next()){
				r.add(new Recipe(
						Integer.parseInt(recipeRS.getString("ID")),
						recipeRS.getString("RecipeName"),
						recipeRS.getString("RecipeInstructions"),
						recipeRS.getString("CookTime"),
						recipeRS.getString("PrepTime"),
						recipeRS.getString("RecipeDescription"),
						recipeRS.getString("CostCategory")
				));
			}
			recipeRS.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
    }
    
    private List<Meal> parseMealList(){
    	//Here is where we will populate the meal table with default 10 rows for each table
        //TODO parse and construct recipe datamodel list by looping your ResultSet rs
        // and return the list 
    	List<Meal> m = new LinkedList<>();
    	m.add(new Meal(1, "Ramen", "Meal_001.png", 1));
    	
    	return m;
    }
	
    private List<Ingredient> parseIngredientList(){
    	//Here is where we will populate the ingredient table with default 10 rows for each table
        //TODO parse and construct recipe datamodel list by looping your ResultSet rs
        // and return the list 
    	List<Ingredient> i = new LinkedList<Ingredient>();
    	i.add(new Ingredient(1, "Chicken Broth", "100", "20g", "0g", "5g", "3g", "0g", "6 oz"));
    	
    	return i;
    }
	
	
	
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
	
	public void deleteRecipe() {
		Recipe selectedItem = recipeTableView.getSelectionModel().getSelectedItem();
		recipeTableView.getItems().remove(selectedItem);
		try (SQLConnection sqlConnection = new SQLConnection()){
			Statement s = sqlConnection.getSqlStatement();
			s.execute(SQLRecipes.deleteRow(selectedItem.getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteMeal() {
		//TODO need to add call to remove from database
//		Meal selectedItem = (Meal)recipeTableView.getSelectionModel().getSelectedItem();
//		mealTableView.getItems().remove(selectedItem);
	}
}
