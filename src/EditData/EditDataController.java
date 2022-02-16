package EditData;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import application.Recipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * Controller class for the data editing tab, controls buttons and manipulates data records.
 * @author Malcolm
 *
 */
public class EditDataController implements Initializable{
	
	
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
	
	@Override
    public void initialize(URL location, ResourceBundle resources) {
		
		recipeIdColumn.setCellValueFactory(new PropertyValueFactory<Recipe,Integer>("Id"));
		recipeNameColumn.setCellValueFactory(new PropertyValueFactory<Recipe,String>("recipeName"));
		recipeInstructionsColumn.setCellValueFactory(new PropertyValueFactory<Recipe,String>("recipeInstructions"));
		recipeCookTimeColumn.setCellValueFactory(new PropertyValueFactory<Recipe,Integer>("cookTime"));
		recipePrepTimeColumn.setCellValueFactory(new PropertyValueFactory<Recipe,Integer>("prepTime"));
		recipeDescriptionColumn.setCellValueFactory(new PropertyValueFactory<Recipe,String>("recipeDescription"));
		recipeCostCategoryColumn.setCellValueFactory(new PropertyValueFactory<Recipe,Integer>("costCategory"));
		
		recipeTableView.getItems().setAll(parseRecipeList());
    }
	
    private List<Recipe> parseRecipeList(){
        //TODO parse and construct recipe datamodel list by looping your ResultSet rs
        // and return the list  
    	List<Recipe> r = new LinkedList<Recipe>();
    	r.add(new Recipe(1, "Ramen", "1. Put Noodles in Bowl \n 2. Add hot water, eggs, and beef",
    	           	        20, 15, "It\'s a bowl of delicious ramen", 1));
    	
    	return r;
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
	
}
