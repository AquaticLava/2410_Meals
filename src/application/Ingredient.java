package application;

/**
 * An ingredient that contains a name, id and nutritional value.
 *
 * @author Collin Williams, Malcolm Bailey
 */
public class Ingredient {

	private int id;
	private String name;
	private double calories, carbs, fiber, protein, fat, sugar, servingSize;

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
					calories = Double.parseDouble(data[i]);
					break;
				case 2:		//carbs
					carbs = Double.parseDouble(data[i]);
					break;
				case 3:		//fiber
					fiber = Double.parseDouble(data[i]);
					break;
				case 4:		//protein
					protein = Double.parseDouble(data[i]);
					break;
				case 5:		//fat
					fat = Double.parseDouble(data[i]);
					break;
				case 6:		//sugar
					sugar = Double.parseDouble(data[i]);
					break;
				case 7:		//serving size
					servingSize = Double.parseDouble(data[i]);
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

	public double getCalories() {
		return calories;
	}

	public void setCalories(double calories) {
		this.calories = calories;
	}

	public double getCarbs() {
		return carbs;
	}

	public void setCarbs(double carbs) {
		this.carbs = carbs;
	}

	public double getFiber() {
		return fiber;
	}

	public void setFiber(double fiber) {
		this.fiber = fiber;
	}

	public double getProtein() {
		return protein;
	}

	public void setProtein(double protein) {
		this.protein = protein;
	}

	public double getFat() {
		return fat;
	}

	public void setFat(double fat) {
		this.fat = fat;
	}

	public double getSugar() {
		return sugar;
	}

	public void setSugar(double sugar) {
		this.sugar = sugar;
	}

	public double getServingSize() {
		return servingSize;
	}

	public void setServingSize(double servingSize) {
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
