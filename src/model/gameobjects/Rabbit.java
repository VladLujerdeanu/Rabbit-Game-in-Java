package model.gameobjects;

public class Rabbit extends GameObject {

    public Rabbit(int x, int y){
        super(x,y);
    }

    public boolean move(int x, int y) {
        if((x == super.x + 1 || x == super.x - 1) && (y == super.y + 1 || y == super.y - 1)){
            super.x = x;
            super.y = y;
            return true;
        }
        return false;
    }
}
