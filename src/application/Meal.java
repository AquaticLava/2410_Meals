package application;

/**
 * Meal class represents a meal with a name, id, photo file and recipe id.
 * @author Malcolm Bailey
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

	public String getName() {
		return name;
	}

	public String getPhotoName() {
		return photoName;
	}

	public int getRecipeId() {
		return recipeId;
	}
}
