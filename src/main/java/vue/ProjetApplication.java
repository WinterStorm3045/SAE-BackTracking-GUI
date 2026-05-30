package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class ProjetApplication extends Application {
    @Override
    public void start(Stage stage) {
        HBoxRoot root = new HBoxRoot();
        Scene scene = new Scene(root, 725, 430);

        File cssFile = new File("css/style.css");
        scene.getStylesheets().add(cssFile.toURI().toString());

        stage.setScene(scene);
        stage.setTitle("Sudoku Java-FX");
        stage.setResizable(false);
        stage.show();
    }
    public static void main(String[] args) { launch(args); }
}