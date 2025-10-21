package tetrisBlocks;

import java.awt.Color;
import tetris.TetrisBlock;

public class OShape extends TetrisBlock {
     public OShape(){
        super(new int[][]{
            {1,1},
            {1,1},},
            //new Color(180,152,41)
            new Color(246,208,56),
            new Color(180,152,41)
        );
    }
}
