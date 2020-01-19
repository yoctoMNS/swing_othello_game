package org.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import org.engine.GameManager;
import org.entity.Cursor;
import org.stage.Stage;

public class GameState extends State {
    private Stage stage;
    private Cursor cursor;

    public GameState( GameManager game ) {
        super( game );
        stage = game.getStage();
        cursor = game.getCursor();
    }

    @Override
    public void render( Graphics g ) {
        game.getStage().render( g );
        game.getCursor().render( g );
        game.renderInfo( g );
    }

    @Override
    public void update( KeyEvent e ) {
        cursor.update( e );
        stage.update( e,
                      cursor.getX() / Stage.TEXTURE_WIDTH,
                      cursor.getY() / Stage.TEXTURE_HEIGHT,
                      game.getTurn() );
    }
}
