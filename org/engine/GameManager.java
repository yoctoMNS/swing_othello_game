package org.engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import org.display.Display;
import org.entity.Cursor;
import org.graphics.Assets;
import org.stage.Stage;

public class GameManager implements Runnable {
    private boolean running;
    private Thread gameThread;
    private Display display;
    private Stage stage;
    private BufferStrategy bs;
    private Graphics g;
    private Cursor cursor;
    private int turn;
    private Font scoreFont;

    private void init() {
        turn = Stage.BLACK;
        Assets.init();
        scoreFont = new Font( "Score Font", Font.BOLD, 30 );
        cursor = new Cursor( 0, 0, Stage.TEXTURE_WIDTH, Stage.TEXTURE_HEIGHT );
        stage = new Stage( this );
        display = new Display( this, "オセロ", Stage.TEXTURE_WIDTH * Stage.WIDTH, Stage.TEXTURE_HEIGHT * Stage.HEIGHT + 30 );
    }

    public synchronized void start() {
        if ( running ) {
            return;
        }

        running = true;
        gameThread = new Thread( this );
        gameThread.setName( "Game Thread" );
        gameThread.start();
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if ( bs == null ) {
            display.getCanvas().createBufferStrategy( 3 );
            return;
        }

        g = bs.getDrawGraphics();
        g.clearRect( 0, 0, Stage.TEXTURE_WIDTH * Stage.WIDTH, Stage.TEXTURE_HEIGHT * Stage.HEIGHT + 30 );
        
        stage.render( g );
        cursor.render( g );
        renderInfo( g );

        bs.show();
        g.dispose();
    }
    
    private void renderInfo( Graphics g ) {
        String blackScore = "黒 : " + String.valueOf( stage.getBlackScore() ) + "個";
        String whiteScore = "白 : " + String.valueOf( stage.getWhiteScore() ) + "個";
        String turnStr = ( ( turn == Stage.BLACK ) ? "黒" : "白" ) + "のターン";

        g.setFont( scoreFont );
        g.setColor( Color.black );
        g.drawString( blackScore, 0, display.getHeight() );
        g.drawString( whiteScore, 150, display.getHeight() );
        g.drawString( turnStr, 300, display.getHeight() );
    }

    public synchronized void stop() {
        if ( !running ) {
            return;
        }

        try {
            gameThread.join();
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        init();

        while ( running ) {
            render();
        }
    }

    public void isGameEnd() {
        if ( stage.isFullPiece() ) {
            running = false;
        }
    }

    public Stage getStage() {
        return stage;
    }

    public Cursor getCursor() {
        return cursor;
    }

    public boolean isRunning() {
        return running;
    }

    public int getTurn() {
        return turn;
    }

    public void turnChange() {
        turn = ( ( turn == Stage.BLACK ) ? Stage.WHITE : Stage.BLACK );
    }
}
