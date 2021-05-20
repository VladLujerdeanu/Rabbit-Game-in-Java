package model.game;

public abstract class Observer {
    public Language subject;
    public abstract void update();
}
