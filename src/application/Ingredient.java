package application;

/**
 * An ingredient that contains a name, id and nutritional information.
 *
 * @author Collin Williams, Malcolm Bailey
 */
public class Ingredient {

	private int id;
	private String name, calories, carbs, fiber, protein, fat, sugar, servingSize;

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
		for (int i = 0; i < data.length; i++){
			switch (i) {
				case 0 ->        //name
						name = data[i];
				case 1 ->        //calories
						calories = data[i];
				case 2 ->        //carbs
						carbs = data[i];
				case 3 ->        //fiber
						fiber = data[i];
				case 4 ->        //protein
						protein = data[i];
				case 5 ->        //fat
						fat = data[i];
				case 6 ->        //sugar
						sugar = data[i];
				case 7 ->        //serving size
						servingSize = data[i];
			}
		}
	}

	public Ingredient(int id,
					  String name, String calories, String carbs,
					  String fiber, String protein, String fat,
					  String sugar, String servingSize) {
		this.id = id;
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
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCalories() {
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
				"Id = " + id +
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

//	/**
//	 * This is a testing methods for the class Ingredients.
//	 *
//	 * @param args
//	 */
//	public static void main (String [] args){
//		CSVInput csv = new CSVInput("src/application/resources/ingredients.csv");
//		csv.readFileData();
//		ArrayList<String> data = csv.getDataList();
//		Ingredient ingredient = new Ingredient(data.get(45));
//		System.out.println(ingredient.toString());
//
//	}

}
