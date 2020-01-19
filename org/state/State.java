package org.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import org.engine.GameManager;

public abstract class State {
    private static State currentState = null;
    protected GameManager game;
    protected int cursor;

    public State( GameManager game ) {
        this.game = game;
        cursor = 0;
    }

    public void setState( State state) {
        currentState = state;
    }

    public State getState() {
        return currentState;
    }

    public abstract void render( Graphics g );

    public abstract void update( KeyEvent e );

    public GameManager getGameManager() {
        return game;
    }
}
