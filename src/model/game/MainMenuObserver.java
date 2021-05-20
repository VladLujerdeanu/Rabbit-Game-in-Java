package model.game;

import view.MainMenu;

public class MainMenuObserver extends Observer{

    private MainMenu menu;

    public MainMenuObserver(Language lang, MainMenu menu) {
        this.menu = menu;
        this.subject = lang;
        this.subject.attachObserver(this);
    }

    @Override
    public void update() {
        menu.startButton.setText(subject.rb.getString("startKey"));
        menu.exitButton.setText(subject.rb.getString("exitKey"));
        menu.levelSelect.setPromptText(subject.rb.getString("levelKey"));

    }
}
