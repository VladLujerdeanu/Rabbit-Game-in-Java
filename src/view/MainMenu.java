package view;

import controller.GameController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.game.MainMenuObserver;

import java.awt.event.ActionEvent;

public class MainMenu {

    public Scene scene;
    public Button startButton;
    public Button exitButton;
    public ComboBox<String> levelSelect;
    public ComboBox<String> langSelect;

    private GameController controller;

    public MainMenu() {
        Pane root = new Pane();

        langSelect = new ComboBox<>();
        langSelect.getItems().addAll("EN", "RO", "DE");
        langSelect.getSelectionModel().selectFirst();
        langSelect.setPrefSize(64, 25);
        langSelect.setLayoutX(562);
        langSelect.setLayoutY(15);
        langSelect.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if(controller != null) {
                controller.lang.setState(newValue);
            }
        });

        startButton = new Button("Start Game");
        startButton.setPrefSize(118, 42);
        startButton.setLayoutX(197);
        startButton.setLayoutY(197);
        startButton.setOnAction(event -> startButtonAction());

        exitButton = new Button("Exit");
        exitButton.setPrefSize(118, 42);
        exitButton.setLayoutX(325);
        exitButton.setLayoutY(197);
        exitButton.setOnAction(event -> exitGameAction());

        levelSelect = new ComboBox<>();
        levelSelect.setPromptText("Select Level");
        levelSelect.getItems().addAll("4 x 4", "5 x 5", "6 x 6", "7 x 7", "8 x 8", "9 x 9", "10 x 10");
        levelSelect.setPrefSize(246, 42);
        levelSelect.setLayoutX(197);
        levelSelect.setLayoutY(138);

        root.getChildren().addAll(langSelect, levelSelect, startButton, exitButton);

        scene = new Scene(root, 640, 375, Color.WHITESMOKE);
    }

    public void comboAction(ActionEvent event) {
        System.out.println(langSelect.getValue());
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public void startButtonAction() {
        controller.startGame(levelSelect.getSelectionModel().getSelectedItem());
    }

    public void exitGameAction() {
        controller.exitGame();
    }
}
