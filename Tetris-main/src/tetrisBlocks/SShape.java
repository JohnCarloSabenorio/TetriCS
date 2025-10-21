package tetrisBlocks;

import java.awt.Color;
import tetris.TetrisBlock;

public class SShape extends TetrisBlock{
     public SShape(){
        super(new int[][]{
            {0,1,1},
            {1,1,0}},
            //new Color(28,157,12)
            new Color(36,214,14),
            new Color(28,157,12)
        );
    }
}
