package application;

/**
 * An ingredient that contains a name, id and nutritional information.
 *
 * @author Collin Williams, Malcolm Bailey
 */
public class Ingredient {

	private int Id;
	private float calories;
	private String name, carbs, fiber, protein, fat, sugar, servingSize;

	/**
	 * This overloaded constructor will take in a String from the csv file
	 * with the data seperated by a comma and split the data initializing it
	 * in the proper variables.
	 *
	 * String must be formatting in the following order, seperated by column.
	 *
	 * name, calories, carbs, fiber, protein, fat, sugar, servingSize.
	 *
	 * @param csvString String in specified csv format
	 */
	public Ingredient (String csvString){
		String[] data = csvString.split(",");

		name = data[0];
		calories = Float.parseFloat(data[1]);
		carbs = data[2];
		fiber = data[3];
		protein = data[4];
		fat = data[5];
		sugar = data[6];
		servingSize = data[7];

	}

	public Ingredient(int Id,
					  String name, float calories, String carbs,
					  String fiber, String protein, String fat,
					  String sugar, String servingSize) {
		this.Id = Id;
		this.name = name;
		this.calories = calories;
		this.carbs = carbs;
		this.fiber = fiber;
		this.protein = protein;
		this.fat = fat;
		this.sugar = sugar;
		this.servingSize = servingSize;
	}

	public int getId() {
		return Id;
	}

	public String getName() {
		return name;
	}

	public float getCalories() {
		return calories;
	}

	public String getCarbs() {
		return carbs;
	}

	public String getFiber() {
		return fiber;
	}

	public String getProtein() {
		return protein;
	}

	public String getFat() {
		return fat;
	}

	public String getSugar() {
		return sugar;
	}

	public String getServingSize() {
		return servingSize;
	}

	@Override
	public String toString() {
		return "Ingredient{" +
				"Id = " + Id +
				", name = " + name +
				", calories = " + calories +
				", carbs = " + carbs +
				", fiber = " + fiber +
				", protein = " + protein +
				", fat = " + fat +
				", sugar = " + sugar +
				", servingSize = " + servingSize +
				'}';
	}


}
