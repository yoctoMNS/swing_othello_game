package org.event;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.engine.GameManager;
import org.entity.Cursor;
import org.stage.Stage;

public class KeyboardListener implements KeyListener {
    private GameManager game;
    private Stage stage;
    private Cursor cursor;

    public KeyboardListener( GameManager game ) {
        this.game = game;
        this.stage = game.getStage();
        this.cursor = game.getCursor();
    }

    @Override
    public void keyPressed( KeyEvent e ) {
        cursor.update( e );
        stage.update( e,
                      cursor.getX() / Stage.TEXTURE_WIDTH,
                      cursor.getY() / Stage.TEXTURE_HEIGHT,
                      game.getTurn() );
        if ( e.getKeyCode() == KeyEvent.VK_P ) {
            game.turnChange();
        }
    }

    @Override
    public void keyReleased( KeyEvent e ) {
    }

    @Override
    public void keyTyped( KeyEvent e ) {
    }
}
