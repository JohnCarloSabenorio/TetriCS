package tetrisBlocks;

import java.awt.Color;
import tetris.TetrisBlock;

public class ZShape extends TetrisBlock {
     public ZShape(){
        super(new int[][]{
            {1,1,0},
            {0,1,1}},
            //new Color(118,28,28)
            new Color(239,45,45),
            new Color(118,28,28)
        );
    }
}
