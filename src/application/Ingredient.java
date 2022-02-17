package application;

/**
 * An ingredient that contains a name, id and nutritional value.
 *
 * @author Collin Williams, Malcolm Bailey
 */
public class Ingredient {

	private int id;
	private String name;
	private String calories, carbs, fiber, protein, fat, sugar, servingSize;

	/**
	 * This overloaded constructor will take in a String from the csv file
	 * with the data seperated by a comma and split the data initializing it
	 * in the proper variables.
	 * @param csvString
	 */
	public Ingredient (String csvString){
		String[] data = csvString.split(",");
		for (int i = 0; i < data.length; i++){
			switch(i){
				case 0:		//name
					name = data[i];
					break;
				case 1:		//calories
					calories = data[i];
					break;
				case 2:		//carbs
					carbs = data[i];
					break;
				case 3:		//fiber
					fiber = data[i];
					break;
				case 4:		//protein
					protein = data[i];
					break;
				case 5:		//fat
					fat = data[i];
					break;
				case 6:		//sugar
					sugar = data[i];
					break;
				case 7:		//serving size
					servingSize = data[i];
					break;
			}
		}
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCalories() {
		return calories;
	}

	public void setCalories(String calories) {
		this.calories = calories;
	}

	public String getCarbs() {
		return carbs;
	}

	public void setCarbs(String carbs) {
		this.carbs = carbs;
	}

	public String getFiber() {
		return fiber;
	}

	public void setFiber(String fiber) {
		this.fiber = fiber;
	}

	public String getProtein() {
		return protein;
	}

	public void setProtein(String protein) {
		this.protein = protein;
	}

	public String getFat() {
		return fat;
	}

	public void setFat(String fat) {
		this.fat = fat;
	}

	public String getSugar() {
		return sugar;
	}

	public void setSugar(String sugar) {
		this.sugar = sugar;
	}

	public String getServingSize() {
		return servingSize;
	}

	public void setServingSize(String servingSize) {
		this.servingSize = servingSize;
	}

	@Override
	public String toString() {
		return "Ingredient{" +
				"Id = " + id +
				", ingredientName = " + name +
				", calories = " + calories +
				", carbs = " + carbs +
				", fiber = " + fiber +
				", protein = " + protein +
				", fat = " + fat +
				", sugar = " + sugar +
				", servingSize = " + servingSize +
				'}';
	}

	/**
	 * This is a testing methods for the class Ingredients.
	 *
	 * @param args
	 */
//	public static void main (String [] args){
//		CSVInput csv = new CSVInput("src/application/resources/ingredients.csv");
//		csv.readFileData();
//		ArrayList<String> data = csv.getDataList();
//		Ingredient ingredient = new Ingredient(data.get(45));
//		System.out.println(ingredient.toString());
//
//	}

}
