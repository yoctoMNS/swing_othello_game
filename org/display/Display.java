package org.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import org.engine.GameManager;
import org.event.KeyboardListener;

public class Display {
    private GameManager game;
    private JFrame frame;
    private Canvas canvas;
    private String title;
    private int width;
    private int height;

    public Display( GameManager game, String title, int width, int height ) {
        this.game = game;
        this.title = title;
        this.width = width;
        this.height = height;

        createWindow();
    }

    private void createWindow() {
        frame = new JFrame( title );
        frame.setSize( width, height );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setLocationRelativeTo( null );
        frame.setResizable( false );
        frame.setVisible( true );

        canvas = new Canvas();
        canvas.setPreferredSize( new Dimension( width, height ) );
        canvas.setMaximumSize( new Dimension( width, height ) );
        canvas.setMinimumSize( new Dimension( width, height ) );

        frame.add( canvas );
        frame.addKeyListener( new KeyboardListener( game ) );
        frame.pack();
    }

    public JFrame getFrame() {
        return frame;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
