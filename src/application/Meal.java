package application;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class represent a meal with a name, id and recipe id.
 * @author Malcolm
 *
 */

public class Meal {

	private int Id;
	private String name;
	private String photoName;
	private int recipeId;

	public Meal(int Id, String name, String photoName, int recipeId) {
		this.Id = Id;
		this.name = name;
		this.photoName = photoName;
		this.recipeId = recipeId;
	}
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}
}
