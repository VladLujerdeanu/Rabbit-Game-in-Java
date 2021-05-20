package view;

import controller.GameController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.gameobjects.Carrot;
import model.gameobjects.Rabbit;
import model.gameobjects.Trap;

public class Level {

    public Scene scene;
    public Button[][] buttons;
    public Button showSolutionButton;
    private final GameController controller;

    public Level(int levelSize, GameController controller) {
        this.controller = controller;

        Pane root = new Pane();

        showSolutionButton = new Button("Show Solution");
        showSolutionButton.setText(controller.lang.rb.getString("solutionKey"));
        showSolutionButton.setPrefSize(100, 38);
        showSolutionButton.setLayoutX(280);
        showSolutionButton.setLayoutY(30);
        showSolutionButton.setOnAction(event -> showSolution());

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(1));
        grid.setHgap(1);
        grid.setVgap(1);
        grid.setLayoutX(325 - (25 * levelSize));
        grid.setLayoutY(575 - (50 * levelSize));

        buttons = new Button[levelSize][levelSize];

        int traps = 0;
        for (int i = 0; i < levelSize; i++) {
            for (int j = 0; j < levelSize; j++) {
                if(controller.model.board[i][j] instanceof Rabbit) {
                    buttons[i][j] = new Button();
                    buttons[i][j].setGraphic(controller.view.rabbitIcon);
                } else
                if(controller.model.board[i][j] instanceof Trap) {
                    buttons[i][j] = new Button();
                    buttons[i][j].setGraphic(controller.view.trapIcons.get(traps++));
                } else
                if(controller.model.board[i][j] instanceof Carrot) {
                    buttons[i][j] = new Button();
                    buttons[i][j].setGraphic(controller.view.carrotIcon);
                } else {
                    buttons[i][j] = new Button();
                }

                buttons[i][j].setPrefSize(50, 50);
                buttons[i][j].setMinSize(50,50);
                buttons[i][j].setMaxSize(50,50);

                int finalI = i;
                int finalJ = j;
                buttons[i][j].setOnAction(event -> controller.moveRabbit(finalI, finalJ));
            }
        }

        for (int r = 0; r < levelSize; r++) {
            for (int c = 0; c < levelSize; c++) {
                grid.add(buttons[r][c], c, r);
            }
        }

        root.getChildren().addAll(showSolutionButton, grid);

        scene = new Scene(root, 650, 600, Color.WHITESMOKE);
    }

    private void showSolution() {
        controller.showSolution();
    }
}
