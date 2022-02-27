module Recipe_Book {
	requires javafx.controls;
	requires java.sql;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;

	requires org.kordamp.bootstrapfx.core;

	opens application to javafx.graphics, javafx.fxml, javafx.base;
	opens MainMenu to javafx.graphics, javafx.fxml, javafx.base;
	opens MealBrowser to javafx.graphics, javafx.fxml, javafx.base;
	opens EditData to javafx.graphics, javafx.fxml, javafx.base;
}
