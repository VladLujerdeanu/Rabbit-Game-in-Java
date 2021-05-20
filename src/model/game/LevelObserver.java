package model.game;

import view.Level;

public class LevelObserver extends Observer{

    private Level level;

    public LevelObserver(Language lang, Level level) {
        this.subject = lang;
        this.level = level;
        this.subject.attachObserver(this);
    }

    @Override
    public void update() {
        level.showSolutionButton.setText(subject.rb.getString("solutionKey"));
    }
}
