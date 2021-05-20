package model.game;

import model.gameobjects.Carrot;
import model.gameobjects.GameObject;
import model.gameobjects.Rabbit;
import model.gameobjects.Trap;

import java.util.ArrayList;

public class GameModel {
    private final int rows;
    private final int cols;
    public GameObject[][] board;
    public ArrayList<Trap> traps;
    public ArrayList<Carrot> carrots;
    public Rabbit rabbit;
    public ArrayList<Astar.Node>optimalSolution;
    public boolean end = false;


    public GameModel(int rows, int cols, int trapNo, int carrotNo) {
        this.rows = rows;
        this.cols = cols;

        Astar astar = Astar.getInstance();

        optimalSolution = null;
        while(optimalSolution == null) {
            board = new GameObject[rows][cols];
            traps = new ArrayList<>();
            carrots = new ArrayList<>();

            generateLevel(trapNo, carrotNo);
            optimalSolution = Astar.pathFind(board, rabbit.getX(), rabbit.getY(), carrots.get(0).getX(), carrots.get(0).getY());
        }
    }

    public void generateLevel(int trapNo, int carrotNo) {
        for (int i = 0; i < trapNo; i++) {
            int trapX;
            int trapY;
            do {
                trapX = (int) (Math.random() * rows);
                trapY = (int) (Math.random() * cols);
            } while (board[trapX][trapY] != null);

            Trap temp = new Trap(trapX, trapY);
            board[trapX][trapY] = temp;
            traps.add(temp);
        }

        for (int i = 0; i < carrotNo; i++) {
            int carrotY;
            do {
                carrotY = (int) (Math.random() * rows);
            } while (board[rows - 1][carrotY] != null);

            Carrot temp = new Carrot(rows-1, carrotY);
            board[rows-1][carrotY] = temp;
            carrots.add(temp);
        }

        int rabbitY;
        do {
            rabbitY = (int) (Math.random() * rows);
        } while (board[0][rabbitY] != null);

        rabbit = new Rabbit(0, rabbitY);
        board[0][rabbitY] = rabbit;
    }

    public boolean moveRabbit(int x, int y) {
        return rabbit.move(x, y);
    }

    public void printBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] instanceof Trap) {
                    System.out.print("[T]");
                } else {
                    if (board[i][j] instanceof Carrot) {
                        System.out.print("[C]");
                    } else {
                        if (board[i][j] instanceof Rabbit) {
                            System.out.print("[R]");
                        } else {
                            System.out.print("[ ]");
                        }
                    }
                }
            }
            System.out.println();
        }
    }

    public void checkWin(int x, int y) {
        if(!end && carrots.get(0).getX() == x && carrots.get(0).getY() == y) {
            end = true;
            System.out.println("WIN BOYYYYYYYYYYYYYYYYYYYYYYYY!");
        }
    }

    public void checkLoss(int x, int y) {
        for(Trap t:traps) {
            if(t.getX() == x && t.getY() == y) {
                end = true;
                System.out.println("LOSER BOYYYYYYYYYYYYYYYY!");
            }
        }
    }
}
