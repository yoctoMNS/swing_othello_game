package org.graphics;

import java.awt.image.BufferedImage;

import org.graphics.SpritSheet;

public class Assets {
    public static final int TIP_WIDTH = 8;
    public static final int TIP_HEIGHT = 8;
    public static final int BUTTON_WIDTH = 250;
    public static final int BUTTON_HEIGHT = 150;

    public static BufferedImage black;
    public static BufferedImage white;
    public static BufferedImage none;
    public static BufferedImage backButton;
    public static BufferedImage exitButton;
    public static BufferedImage replayButton;

    public static void init() {
        SpritSheet tipSheet = new SpritSheet( ImageLoader.loadImage( "/res/images/texture.png" ) );
        SpritSheet buttonSheet = new SpritSheet( ImageLoader.loadImage( "/res/images/button.png" ) );

        black = tipSheet.crop( TIP_HEIGHT, 0, TIP_WIDTH, TIP_HEIGHT );
        white = tipSheet.crop( TIP_HEIGHT * 2, 0, TIP_WIDTH, TIP_HEIGHT );
        none = tipSheet.crop( 0, 0, TIP_WIDTH, TIP_HEIGHT );
        backButton = buttonSheet.crop( 0, 0, BUTTON_WIDTH, BUTTON_HEIGHT );
        exitButton = buttonSheet.crop( BUTTON_WIDTH, 0, BUTTON_WIDTH, BUTTON_HEIGHT );
        replayButton = buttonSheet.crop( BUTTON_WIDTH * 2, 0, BUTTON_WIDTH, BUTTON_HEIGHT );
    }
}
