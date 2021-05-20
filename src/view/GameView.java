package view;

import controller.GameController;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameView {
    public Scene activeScene;
    public Level level = null;
    public MainMenu mainMenu = null;
    public ImageView rabbitIcon;
    public ArrayList<ImageView> trapIcons;
    public ImageView carrotIcon;

    public void setController(GameController controller) {
        this.controller = controller;
    }

    private GameController controller;
    public ResourceBundle langBundle;

    public GameView(ResourceBundle langBundle) {
        this.langBundle = langBundle;
        mainMenu = setupMainMenu();
        this.activeScene = mainMenu.scene;

        trapIcons = new ArrayList<>();

        Image rabbitImg = new Image("view/images/rabbit.png");
        rabbitIcon = new ImageView(rabbitImg);
        rabbitIcon.setFitHeight(40);
        rabbitIcon.setPreserveRatio(true);

        Image carrotImg = new Image("view/images/carrot.png");
        carrotIcon = new ImageView(carrotImg);
        carrotIcon.setFitHeight(40);
        carrotIcon.setPreserveRatio(true);
    }

    public MainMenu setupMainMenu() {
        return new MainMenu();
    }

    public Level setupLevel(int levelSize) {
        Image trapImg = new Image("view/images/trap.png");

        for (int i = 0; i < (levelSize * levelSize) / 2; i++) {
            ImageView temp = new ImageView(trapImg);
            temp.setFitHeight(40);
            temp.setPreserveRatio(true);
            trapIcons.add(temp);
        }

        this.level = new Level(levelSize, controller);
        return level;
    }
}
