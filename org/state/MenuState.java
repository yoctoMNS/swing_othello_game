package org.state;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;

import org.engine.GameManager;
import org.graphics.Assets;

public class MenuState extends State {
    public MenuState( GameManager game ) {
        super( game );
    }

    @Override
    public void render( Graphics g ) {
        g.setColor( Color.black );
        g.fillRect( 0, 0, game.getDisplay().getWidth(), game.getDisplay().getHeight() );
        g.drawImage( Assets.backButton,
                     ( game.getDisplay().getWidth() / 2 ) - ( Assets.BUTTON_WIDTH / 2 ),
                     ( game.getDisplay().getHeight() / 2 ) - ( Assets.BUTTON_HEIGHT / 2 ) * 3,
                     null );
        g.drawImage( Assets.exitButton,
                     ( game.getDisplay().getWidth() / 2 ) - ( Assets.BUTTON_WIDTH / 2 ),
                     ( game.getDisplay().getHeight() / 2 ) + ( Assets.BUTTON_HEIGHT / 2 ),
                     null );
    }

    @Override
    public void update( KeyEvent e ) {
    }
}
