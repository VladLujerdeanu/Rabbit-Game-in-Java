package model.game;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class Language {

    private ArrayList<Observer> observers = new ArrayList<>();
    private String lang = "EN";
    public Locale locale;
    public ResourceBundle rb;

    public String getState() {
        return lang;
    }

    public void setState(String state) {
        this.lang = state;
        this.locale = new Locale(lang);
        this.rb = ResourceBundle.getBundle("resources.ui", locale);
        notifyAllObservers();
    }

    public void attachObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for(Observer o: observers) {
            o.update();
        }
    }
}
