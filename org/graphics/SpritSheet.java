package org.graphics;

import java.awt.image.BufferedImage;

public class SpritSheet {
    private BufferedImage sheet;

    public SpritSheet( BufferedImage sheet ) {
        this.sheet = sheet;
    }

    public BufferedImage crop( int x, int y, int width, int height ) {
        return sheet.getSubimage( x, y, width, height );
    }
}
