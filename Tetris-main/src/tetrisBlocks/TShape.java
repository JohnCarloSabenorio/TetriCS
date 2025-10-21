package tetrisBlocks;

import java.awt.Color;
import tetris.TetrisBlock;

public class TShape extends TetrisBlock {
     public TShape(){
        super(new int[][]{
            {1,1,1},
            {0,1,0}},
            //new Color(130,65,148)
            new Color(203,108,230),
            new Color(130,65,148)
        );
    }
}
