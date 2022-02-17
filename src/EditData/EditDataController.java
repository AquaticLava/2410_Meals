package EditData;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import application.Ingredient;
import application.Meal;
import application.Recipe;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

/**
 * Controller class for the data editing tab, controls buttons and manipulates data records.
 * @author Malcolm
 *
 */
public class EditDataController implements Initializable{


	@FXML
	private ToggleGroup sortRecipes;
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	@FXML
	private TableView ingredientTableView;

	@FXML
	private TableView recipeTableView;
	@FXML
	private TableColumn recipeIdColumn;
	@FXML
	private TableColumn recipeNameColumn;
	@FXML
	private TableColumn recipeInstructionsColumn;
	@FXML
	private TableColumn recipeCookTimeColumn;
	@FXML
	private TableColumn recipePrepTimeColumn;
	@FXML
	private TableColumn recipeDescriptionColumn;
	@FXML
	private TableColumn recipeCostCategoryColumn;
	
	@FXML
	private TableView mealTableView;
	@FXML
	private TableColumn mealIdColumn;
	@FXML
	private TableColumn mealNameColumn;
	@FXML
	private TableColumn mealPhotoColumn;
	@FXML
	private TableColumn mealRecipeColumn;
	
	@Override
    public void initialize(URL location, ResourceBundle resources) {
		
		//Recipe table initialize
		recipeIdColumn.setCellValueFactory(new PropertyValueFactory<Recipe,Integer>("Id"));
		recipeNameColumn.setCellValueFactory(new PropertyValueFactory<Recipe,String>("recipeName"));
		recipeInstructionsColumn.setCellValueFactory(new PropertyValueFactory<Recipe,String>("recipeInstructions"));
		recipeCookTimeColumn.setCellValueFactory(new PropertyValueFactory<Recipe,Integer>("cookTime"));
		recipePrepTimeColumn.setCellValueFactory(new PropertyValueFactory<Recipe,Integer>("prepTime"));
		recipeDescriptionColumn.setCellValueFactory(new PropertyValueFactory<Recipe,String>("recipeDescription"));
		recipeCostCategoryColumn.setCellValueFactory(new PropertyValueFactory<Recipe,Integer>("costCategory"));
		
		recipeTableView.getItems().setAll(parseRecipeList("ID"));
		
		//meal table initialize
		mealIdColumn.setCellValueFactory(new PropertyValueFactory<Meal,Integer>("Id"));
		mealNameColumn.setCellValueFactory(new PropertyValueFactory<Meal,String>("name"));
		mealPhotoColumn.setCellValueFactory(new PropertyValueFactory<Meal,String>("photoName"));
		mealRecipeColumn.setCellValueFactory(new PropertyValueFactory<Meal,Integer>("recipeId"));
		
		mealTableView.getItems().setAll(parseMealList());

		sortRecipes.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
			String toggleMethod = "ID";
			RadioButton rb = (RadioButton)sortRecipes.getSelectedToggle();

			if (rb != null) {
				switch (rb.getText()){
					case "Sort: Name":
						toggleMethod = "RecipeName";
						break;
					case "Sort: cost":
						toggleMethod = "CostCategory, RecipeName";
						break;
					case "Sort: Cook & prep":
						toggleMethod = "CookTime, PrepTime, RecipeName";
						break;
					default:
						toggleMethod = "ID";
				}
			}

			recipeTableView.getItems().setAll(parseRecipeList(toggleMethod));
		});
    }
	
    private List<Recipe> parseRecipeList(String sortMethod){
    	List<Recipe> r = new LinkedList<>();
		try (SQLConnection sqlConnection = new SQLConnection()) {
			Statement s = sqlConnection.getSqlStatement();
			ResultSet recipeRS = s.executeQuery(
					SQLRecipes.partialDataFromTable(1,10,sortMethod));
			while (recipeRS.next()){
				r.add(new Recipe(
						Integer.parseInt(recipeRS.getString("ID")),
						recipeRS.getString("RecipeName"),
						recipeRS.getString("RecipeInstructions"),
						Integer.parseInt(recipeRS.getString("CookTime")),
						Integer.parseInt(recipeRS.getString("PrepTime")),
						recipeRS.getString("RecipeDescription"),
						Integer.parseInt(recipeRS.getString("CostCategory"))
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
    	List<Meal> m = new LinkedList<Meal>();
    	m.add(new Meal(1, "Ramen", "Meal_001.png", 1));
    	
    	return m;
    }
	
//    private List<Ingredient> parseIngredientList(){
//    	//Here is where we will populate the ingredient table with default 10 rows for each table
//        //TODO parse and construct recipe datamodel list by looping your ResultSet rs
//        // and return the list 
//    	List<Ingredient> i = new LinkedList<Ingredient>();
//    	i.add(new Ingredient(1, "Ramen", "1. Put Noodles in Bowl \n 2. Add hot water, eggs, and beef",
//    	           	        20, 15, "It\'s a bowl of delicious ramen", 1));
//    	
//    	return i;
//    }
//	
	
	
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
	
	public void deleteRecipe(ActionEvent event) throws IOException {
		//TODO need to add call to remove from database
		Recipe selectedItem = (Recipe)recipeTableView.getSelectionModel().getSelectedItem();
		recipeTableView.getItems().remove(selectedItem);
	}
	
	public void deleteMeal(ActionEvent event) throws IOException {
		//TODO need to add call to remove from database
		Meal selectedItem = (Meal)recipeTableView.getSelectionModel().getSelectedItem();
		mealTableView.getItems().remove(selectedItem);
	}
	
}
