package org.event;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.engine.GameManager;
import org.entity.Cursor;
import org.stage.Stage;

public class KeyboardListener implements KeyListener {
    private GameManager game;

    public KeyboardListener( GameManager game ) {
        this.game = game;
    }

    @Override
    public void keyPressed( KeyEvent e ) {
        switch ( e.getKeyCode() ) {
        case KeyEvent.VK_P:
            game.turnChange();
            break;

        case KeyEvent.VK_M:
            game.changeCurrentState();
            break;
        }

        game.getState().update( e );
    }

    @Override
    public void keyReleased( KeyEvent e ) {
    }

    @Override
    public void keyTyped( KeyEvent e ) {
    }
}
