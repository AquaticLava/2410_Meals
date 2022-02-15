module Recipe_Book {
	requires javafx.controls;
	requires java.sql;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;

	opens application to javafx.graphics, javafx.fxml;
	opens MainMenu to javafx.graphics, javafx.fxml;
	opens MealBrowser to javafx.graphics, javafx.fxml;
	opens EditData to javafx.graphics, javafx.fxml;
}
