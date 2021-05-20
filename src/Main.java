import controller.GameController;
import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import view.GameView;

import java.net.URL;
import java.util.ResourceBundle;

public class Main extends Application implements Initializable {
    private ResourceBundle langBundle;

    @Override
    public void start(Stage stage) {
        GameView view = new GameView(langBundle);
        GameController controller = new GameController(view, stage);

        stage.setTitle("Rabbit");
        stage.setScene(view.activeScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        langBundle = resourceBundle;
    }
}
