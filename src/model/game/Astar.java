package model.game;

import model.gameobjects.GameObject;
import model.gameobjects.Trap;

import java.util.*;

public class Astar {

    private static final Astar astar = new Astar();

    public static Astar getInstance() {
        return astar;
    }

    public static ArrayList<Node> pathFind(GameObject[][] board, int startX, int startY, int finishX, int finishY) {
        Comparator<Node> comparator = (o1, o2) -> (int) (o1.g - o2.g);

        Queue<Node> openSet = new PriorityQueue<>(10, comparator);

        Node[][] grid = new Node[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(!(board[i][j] instanceof Trap)) {
                    grid[i][j] = new Node(i, j);
                }
            }
        }

        openSet.add(grid[startX][startY]);

        Map<Node, Node> cameFrom = new HashMap<>();

        grid[startX][startY].g = 0;
        grid[startX][startY].f = grid[startX][startY].h(grid[finishX][finishY], board);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current == grid[finishX][finishY]) {
                return reconstructPath(cameFrom, current);
            }

            for (int i : new int[]{-1, 1}) {
                for (int j : new int[]{-1, 1}) {
                    if (current.x + i >= 0 && current.x + i < board.length && current.y + j >= 0 && current.y + j < board[0].length && grid[current.x + i][current.y + j] != null) {
                        Node neighbor = grid[current.x + i][current.y + j];
                        double tentativeGScore = current.g + current.getDistance(neighbor);
                        if (tentativeGScore < neighbor.g) {
                            cameFrom.put(neighbor, current);
                            neighbor.g = tentativeGScore;
                            neighbor.f = neighbor.g + neighbor.h(grid[finishX][finishY], board);
                            if (!openSet.contains(neighbor)) {
                                openSet.add(neighbor);
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private static ArrayList<Node> reconstructPath(Map<Node, Node> cameFrom, Node current) {
        ArrayList<Node> path = new ArrayList<>();

        path.add(current);

        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            path.add(current);
        }
        return path;
    }

    public static class Node {
        double f = 1.0 / 0;
        double g = 1.0 / 0;

        public int x;
        public int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getDistance(Node n) {
            return Math.abs(this.x - n.x) + Math.abs(this.y - n.y);
        }

        private double h(Node n, GameObject[][] board) {
            if(board[n.x][n.y] instanceof Trap) {
                return 1000;
            }
            return Math.abs(this.x - n.x) + Math.abs(this.y - n.y);
        }
    }
}
