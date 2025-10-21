package tetrisBlocks;

import java.awt.Color;
import tetris.TetrisBlock;

public class JShape extends TetrisBlock{
    public JShape(){
        super(new int[][]{
            {0,1},
            {0,1},
            {1,1}},
            //new Color(0,74,173) 
            new Color(82,113,255),
            new Color(0,74,173)
        );
    }
    
}
