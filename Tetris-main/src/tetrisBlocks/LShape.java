package tetrisBlocks;

import java.awt.Color;
import tetris.TetrisBlock;

public class LShape extends TetrisBlock{
     public LShape(){
        super(new int[][]{
            {1,0},
            {1,0},
            {1,1}},
            //new Color(166,108,20) 
            new Color(246,159,28),
            new Color(166,108,20)
        );
    }
}
