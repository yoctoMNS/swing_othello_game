package org.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import org.stage.Stage;

public class Cursor extends Entity {
    public Cursor( int x, int y, int width, int height ) {
        super( x, y, width, height );
    }

    @Override
    public void render( Graphics g ) {
        g.setColor( Color.red );
        g.drawRect( x, y, width, height );
    }

    @Override
    public void update( KeyEvent e ) {
        int dx = x;
        int dy = y;

        switch ( e.getKeyCode() ) {
        case KeyEvent.VK_W:
            dy -= Stage.TEXTURE_WIDTH;
            break;

        case KeyEvent.VK_A:
            dx -= Stage.TEXTURE_WIDTH;
            break;

        case KeyEvent.VK_S:
            dy += Stage.TEXTURE_WIDTH;
            break;

        case KeyEvent.VK_D:
            dx += Stage.TEXTURE_WIDTH;
            break;
        }

        if ( dx < 0 ) {
            dx = Stage.TEXTURE_WIDTH * 7;
        } else if ( dx > Stage.TEXTURE_WIDTH * 7 ) {
            dx = 0;
        }

        if ( dy < 0 ) {
            dy = Stage.TEXTURE_HEIGHT * 7;
        } else if ( dy > Stage.TEXTURE_WIDTH * 7 ) {
            dy = 0;
        }

        x = dx;
        y = dy;
    }
}
