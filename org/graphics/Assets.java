package org.graphics;

import java.awt.image.BufferedImage;

import org.graphics.SpritSheet;

public class Assets {
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    public static BufferedImage black;
    public static BufferedImage white;
    public static BufferedImage none;

    public static void init() {
        SpritSheet sheet = new SpritSheet( ImageLoader.loadImage( "/res/images/texture.png" ) );

        black = sheet.crop( HEIGHT, 0, WIDTH, HEIGHT );
        white = sheet.crop( HEIGHT * 2, 0, WIDTH, HEIGHT );
        none = sheet.crop( 0, 0, WIDTH, HEIGHT );
    }
}
