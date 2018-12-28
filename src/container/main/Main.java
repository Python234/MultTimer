package container.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override public void start(Stage stage) throws Exception {
//        Image icon = new Image(getClass().getResource("/resources/icons/icon.png").toExternalForm());
        Parent parent = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene scene = new Scene(parent);
        scene.getStylesheets().add(getClass().getResource("/container/css/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("MultiTimer Clock");
        stage.setResizable(false);
//        stage.getIcons().add(icon);
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
}
