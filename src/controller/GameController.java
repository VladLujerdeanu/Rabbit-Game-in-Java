package controller;

import javafx.fxml.Initializable;
import javafx.stage.Stage;
import model.game.*;
import view.GameView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameController {
    public GameModel model;
    public GameView view;
    public Stage mainStage;
    private int size;
    public ResourceBundle langBundle;
    public Language lang;

    public GameController(GameView v, Stage main) {
        lang = new Language();
        lang.setState("EN");

        this.view = v;
        this.mainStage = main;

        view.setController(this);
        view.mainMenu.setController(this);

        new MainMenuObserver(lang, view.mainMenu);
    }

    public void startGame(String selectedLevel) {
        switch (selectedLevel) {
            case "4 x 4" -> {
                size = 4;
            }
            case "5 x 5" -> {
                size = 5;
            }
            case "6 x 6" -> {
                size = 6;
            }
            case "7 x 7" -> {
                size = 7;
            }
            case "8 x 8" -> {
                size = 8;
            }
            case "9 x 9" -> {
                size = 9;
            }
            case "10 x 10" -> {
                size = 10;
            }
            default -> size = 4;
        }

        this.model = new GameModel(size, size, (size*size)/2, 1);

        this.view.setupLevel(size);
        new LevelObserver(lang, view.level);
        mainStage.setScene(this.view.level.scene);
    }

    public void exitGame() {
        System.exit(0);
    }

    public void showSolution() {
        ArrayList<Astar.Node> sol = model.optimalSolution;
        for (Astar.Node n : sol) {
            view.level.buttons[n.x][n.y].setStyle("-fx-background-color: #6699ff;");
        }
    }

    public void moveRabbit(int x, int y) {
        if (!model.end) {
            int oldX = model.rabbit.getX();
            int oldY = model.rabbit.getY();
            if (model.moveRabbit(x, y)) {
                view.level.buttons[x][y].setGraphic(view.rabbitIcon);
                view.level.buttons[oldX][oldY].setText("");
                model.checkWin(x, y);
                model.checkLoss(x, y);
            }
        }
        if (model.end) {
            disableButtons();
        }
    }

    private void disableButtons() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                view.level.buttons[i][j].setDisable(true);
            }
        }
    }
}
