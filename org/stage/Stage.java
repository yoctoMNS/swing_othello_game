package org.stage;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import org.engine.GameManager;
import org.entity.Cursor;
import org.graphics.Assets;

public class Stage {
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    public static final int TEXTURE_WIDTH = 64;
    public static final int TEXTURE_HEIGHT = 64;

    public static final int BLACK = 0;
    public static final int WHITE = 1;
    public static final int NONE = 2;

    private int[][] data;
    private GameManager game;
    private ArrayList< FlipInfo > infos;

    private class FlipInfo {
        int x;
        int y;
        int vecX;
        int vecY;
        int endX;
        int endY;

        public FlipInfo( int x, int y, int vecX, int vecY, int endX, int endY ) {
            this.x = x;
            this.y = y;
            this.vecX = vecX;
            this.vecY = vecY;
            this.endX = endX;
            this.endY = endY;
        }
    }

    public Stage( GameManager game ) {
        this.game = game;
        infos = new ArrayList<>();
        initStage();
    }

    private void initStage() {
        data = new int[ HEIGHT ][ WIDTH ];

        for ( int i = 0; i < HEIGHT; ++i ) {
            for ( int j = 0; j < WIDTH; ++j ) {
                data[ i ][ j ] = NONE;
            }
        }
        data[ 3 ][ 3 ] = BLACK;
        data[ 3 ][ 4 ] = WHITE;
        data[ 4 ][ 3 ] = WHITE;
        data[ 4 ][ 4 ] = BLACK;
    }

    public void render( Graphics g ) {
        for ( int i = 0; i < HEIGHT; ++i ) {
            for ( int j = 0; j < WIDTH; ++j ) {
                switch ( data[ i ][ j ] ) {
                case BLACK:
                    g.drawImage( Assets.black, j * TEXTURE_WIDTH, i * TEXTURE_HEIGHT, TEXTURE_WIDTH, TEXTURE_HEIGHT, null );
                    break;

                case WHITE:
                    g.drawImage( Assets.white, j * TEXTURE_WIDTH, i * TEXTURE_HEIGHT, TEXTURE_WIDTH, TEXTURE_HEIGHT, null );
                    break;

                case NONE:
                    g.drawImage( Assets.none, j * TEXTURE_WIDTH, i * TEXTURE_HEIGHT, TEXTURE_WIDTH, TEXTURE_HEIGHT, null );
                    break;
                }
            }
        }
    }

    public void update( KeyEvent e, int x, int y, int turn ) {
        if ( e.getKeyCode() == KeyEvent.VK_SPACE ) {
            if ( canPut( x, y, turn ) ) {
                setStageCell( x, y, turn );
                flip( turn );
                game.turnChange();
            }
        }
    }

    public boolean canPut( int x, int y, int turn ) {
        if ( isNoneCell( x, y ) ) {
            return checkAroundCell( x, y, turn );
        }

        return false;
    }

    public boolean checkAroundCell( int x, int y, int turn ) {
        boolean enemyFlag = false;

        for ( int i = -1; i <= 1; ++i ) {
            for ( int j = -1; j <= 1; ++j ) {
                if ( isOutOfStage( x + j, y + i ) ) {
                    continue;
                }

                if ( isEnemyPiece( x + j, y + i, turn ) ) {
                    if ( checkLineCell( x, y, j, i, turn ) ) {
                        enemyFlag = true;
                    }
                }
            }
        }

        return enemyFlag;
    }

    public boolean checkLineCell( int x, int y, int vecX, int vecY, int turn ) {
        int dx = x;
        int dy = y;

        while ( true ) {
            dx += vecX;
            dy += vecY;

            if ( isOutOfStage( dx, dy ) ) {
                break;
            }
            if ( isNoneCell( dx, dy ) ) {
                break;
            }

            if ( isMyPiece( dx, dy, turn ) ) {
                infos.add( new FlipInfo( x, y, vecX, vecY, dx, dy ) );
                return true;
            }
        }

        return false;
    }

    public void flip( int turn ) {
        for ( FlipInfo info : infos ) {
            int dx = info.x;
            int dy = info.y;

            while ( true ) {
                dx += info.vecX;
                dy += info.vecY;

                if ( isOutOfStage( dx, dy ) ) {
                    break;
                }
                if ( isNoneCell( dx, dy ) ) {
                    break;
                }
                if ( dx == info.endX && dy == info.endY ) {
                    break;
                }

                setStageCell( dx, dy, turn );
            }
        }

        infos.clear();
    }

    public int getBlackScore() {
        int cnt = 0;

        for ( int i = 0; i < HEIGHT; ++i ) {
            for ( int j = 0; j < WIDTH; ++j ) {
                if ( data[ i ][ j ] == BLACK ) {
                    ++cnt;
                }
            }
        }

        return cnt;
    }

    public int getWhiteScore() {
        int cnt = 0;

        for ( int i = 0; i < HEIGHT; ++i ) {
            for ( int j = 0; j < WIDTH; ++j ) {
                if ( data[ i ][ j ] == WHITE ) {
                    ++cnt;
                }
            }
        }

        return cnt;
    }

    public boolean isFullPiece() {
        boolean flag = true;

        for ( int i = 0; i < HEIGHT; ++i ) {
            for ( int j = 0; j < WIDTH; ++j ) {
                if ( isNoneCell( j, i ) ) {
                    flag = false;
                }
            }
        }

        return flag;
    }

    public boolean isMyPiece( int x, int y, int turn ) {
        return data[ y ][ x ] == turn;
    }

    public boolean isEnemyPiece( int x, int y, int turn ) {
        return data[ y ][ x ] == ( ( turn == BLACK ) ? WHITE : BLACK );
    }

    public boolean isNoneCell( int x, int y ) {
        return data[ y ][ x ] == NONE;
    }

    public boolean isOutOfStage( int x, int y ) {
        return ( x < 0 || x >= WIDTH ) ||
               ( y < 0 || y >= HEIGHT );
    }

    public void setStageCell( int x, int y, int turn ) {
        data[ y ][ x ] = turn;
    }

    public int getStageCell( int x, int y ) {
        return data[ y ][ x ];
    }
}
