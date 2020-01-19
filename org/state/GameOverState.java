package org.state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import org.engine.GameManager;
import org.graphics.Assets;
import org.stage.Stage;

public class GameOverState extends State {
    public GameOverState( GameManager game ) {
        super( game );
    }

    @Override
    public void render( Graphics g ) {
        int winner = game.getWinner();

        g.setColor( Color.black );
        g.fillRect( 0, 0, game.getDisplay().getWidth(), game.getDisplay().getHeight() );

        g.drawImage( Assets.replayButton,
                     ( game.getDisplay().getWidth() / 2 ) - ( Assets.BUTTON_WIDTH / 2 ),
                     ( game.getDisplay().getHeight() / 2 ) - ( Assets.BUTTON_HEIGHT / 2 ) * 3,
                     null );
        g.drawImage( Assets.exitButton,
                     ( game.getDisplay().getWidth() / 2 ) - ( Assets.BUTTON_WIDTH / 2 ),
                     ( game.getDisplay().getHeight() / 2 ) + ( Assets.BUTTON_HEIGHT / 2 ),
                     null );

        String winnerStr = ( ( winner == Stage.BLACK ) ? "黒" : "白" ) + "の勝利！";
        g.setFont( game.getScoreFont() );
        g.setColor( Color.white );
        g.drawString( winnerStr,
                      game.getDisplay().getWidth() / 2 - 80,
                      game.getDisplay().getHeight() / 2 );

    }

    @Override
    public void update( KeyEvent e ) {
    }
}
