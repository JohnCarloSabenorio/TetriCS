package tetris;
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import tetrisBlocks.*;

public class GameArea extends JPanel{
    private int gridRows;
    private int gridColumns;
    private int gridCellSize;
    private Color[][] background;
    
    private TetrisBlock block;
    private TetrisBlock[] blocks;
    
    
    public GameArea(JPanel placeholder, int columns){
    //placeholder.setVisible(false);
    this.setBounds(placeholder.getBounds());
    this.setBackground(placeholder.getBackground());
    this.setBorder(placeholder.getBorder());
    
    gridColumns = columns;
    gridCellSize = this.getBounds().width/gridColumns;
    gridRows = this.getBounds().height/gridCellSize;
    
    background = new Color[gridRows][gridColumns];
    blocks = new TetrisBlock[]{new IShape(),new JShape(),new LShape(),new OShape(),new SShape(),new TShape(),new ZShape()};
    
    
    spawnBlock();
    
    }
    
    
    public void initBackgroundArray(){
        background = new Color[gridRows][gridColumns];
    }
    
    public void spawnBlock(){
       Random r = new Random();
       block = blocks[r.nextInt(blocks.length)];
       block.spawn(gridColumns);
    }
    
    public boolean isBlockOutOfBounds(){
        if(block.getY() < 0){
            block = null;
            return true;
        }
        
        return false;
    }
    
    
    public boolean moveBlockDown(){
        if(block == null) return false;
        if(!block.getCanMove()) return false;
        if(checkBottom() == false){   

            return false;
        }

        block.moveDown();
        repaint();
        
        return true;
    }
    
    public void moveBlockRight(){
        if(block == null)return;
        if(!block.getCanMove()) return;
        if(!checkRight()) return;
        block.moveRight();
        repaint();
    }
    
    public void moveBlockLeft(){
        if(block == null)return;
        if(!block.getCanMove()) return;
        if(!checkLeft()) return;
        block.moveLeft();
        repaint();
    }
    
    public void dropBlock(){
        if(block == null)return;
        if(!block.getCanMove()) return;
        while(checkBottom()){
        block.moveDown();
        }
        
        repaint();
        block.setCanMove(false);
    }
    

    
    public void rotateBlock(){
        int[][] shape = block.getShape();
        int w= block.getWidth();
        int h = block.getHeight();
        if(block == null)return;
        if(!block.getCanMove()) return;
        block.rotate();
        if(block.getLeftEdge()<0)block.setX(0);
        if(block.getRightEdge()>= gridColumns)block.setX(gridColumns-block.getWidth());
        if(block.getBottomEdge() >= gridRows)block.setY(gridRows-block.getHeight());
        
        for(int row = 0; row < h; row++) {
           for( int col = 0; col < w; col++) {
               if(shape[row][col] != 0) {
                   int x = col + block.getX();
                   int y = row + block.getY();
                   if(y < 0)
                       break;
                   if(background[y][x] != null){
                       block.rotateBack();
                       repaint();
                       return;
                   }
                       
               }
           }
       }
        repaint();
        
    
    }
    
    
    private boolean checkBottom(){
       
        if(block.getBottomEdge()== gridRows){
            return false;
        }
        
        int[][] shape = block.getShape();
        int w= block.getWidth();
        int h = block.getHeight();
        
        for(int col = 0;col<w;col++){
            for(int row = h-1;row>= 0; row--){
                if(shape[row][col] != 0){
                    int x = col + block.getX();
                    int y= row + block.getY() + 1;
                    if(y<0) break;
                    if(background[y][x] != null) return false;
                    break;
                }
            }
        }
        return true;
    }
    
    private boolean checkLeft(){
       if(block.getLeftEdge() == 0) return false;
        int[][] shape = block.getShape();
        int w= block.getWidth();
        int h = block.getHeight();
        
        for(int row = 0;row<h;row++){
            for(int col = 0;col<w; col++){
                if(shape[row][col] != 0){
                    int x = col + block.getX() - 1;
                    int y= row + block.getY();
                    if(y<0) break;
                    if(background[y][x] != null) return false;
                    break;
                }
            }
        }
        return true;
    }  
    private boolean checkRight(){
        if(block.getRightEdge() == gridColumns) return false;
        int[][] shape = block.getShape();
        int w= block.getWidth();
        int h = block.getHeight();
        
        for(int row = 0;row<h;row++){
            for(int col = w-1; col >= 0;col--){
                if(shape[row][col] != 0){
                    int x = col + block.getX() + 1;
                    int y= row + block.getY();
                    if(y<0) break;
                    if(background[y][x] != null) return false;
                    break;
                }
            }
        }
        return true;
    }  
    
    public int clearLines(){
        int linesCleared = 0;
        boolean lineFilled;
        for(int r = gridRows -1;r>=0;r--){
            lineFilled = true;
            for(int c = 0;c<gridColumns;c++){
                if(background[r][c] == null){
                    lineFilled = false;
                    break;
                }
            }
            if(lineFilled){
                linesCleared++;

               clearLine(r);
               shiftDown(r);
               clearLine(0);
               r++;
                repaint();
            }
        }
        return linesCleared;
    }
    
    private void clearLine(int r){
         for(int i = 0;i<gridColumns;i++){
                    background[r][i] = null;
                }
         GameForm.playSound("lvl.wav");        

    }
    
    private void shiftDown(int r){
        for(int row = r;row>0;row--){
            for(int col = 0;col<gridColumns;col++){
                background[row][col] = background[row-1][col];
            }
        }
    }
    
    public void moveBlockToBackground(){
        int[][] shape = block.getShape();
        int h = block.getHeight();
        int w = block.getWidth();
        
        int xPos = block.getX();
        int yPos = block.getY();
        
        Color fill = block.getFill();
        
        for(int r = 0;r<h;r++){
            for(int c = 0;c<w;c++){
                if(shape[r][c] == 1){
                    background[r+yPos][c+xPos] = fill; 
                }
            }
        }
        
        block.setCanMove(false);
    }
    

    
    private void drawBlock(Graphics g){
        int h = block.getHeight();
        int w = block.getWidth();
        Color c = block.getColor();
        Color f = block.getFill();
        int[][] shape = block.getShape();
        
        for(int row = 0;row<h;row++){
            for(int col = 0;col<w;col++){
                if(shape[row][col] == 1){
                    int x = (block.getX() + col)*gridCellSize;
                    int y = (block.getY() + row)*gridCellSize;
                   drawGridSquare(g, c, f, x, y);
                }
            }
        }
        
        
        
        for(int row = 0; row < h; row++) {
           for( int col = 0; col < w; col++) {
               if(shape[row][col] != 0) {
                   int x = col + block.getX();
                   int y = row + block.getY();
                   if(y < 0)
                       break;
                   if(background[y][x] != null){
                       repaint();
                       return;
                   }
                       
               }
           }
       }
    }
    
    private void drawBackground(Graphics g){
        Color color;
        
        for(int r = 0;r<gridRows;r++){
            for(int c = 0;c<gridColumns;c++){
                color = background[r][c];
                
                if(color != null){
                    int x = c*gridCellSize;
                    int y = r*gridCellSize;
                    
                  drawGridSquare(g, color, color, x,y);
                }
            }
        }
    }
    
    //
    private void drawGridSquare(Graphics g, Color color, Color fill, int x, int y){
          g.setColor(fill);
          g.fillRect(x,y,gridCellSize,gridCellSize);
          g.setColor(color);
          g.fillRect(x + 5,y + 5,gridCellSize - 10,gridCellSize - 9);
          g.setColor(Color.BLACK);
          g.drawRect(x,y,gridCellSize,gridCellSize);
    }
    @Override
    
    
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
 
        
        //Code to show the grid of the game area
        for(int y = 0;y<gridRows;y++){
            for (int x = 0;x<gridColumns;x++){
                g.drawRect(x*gridCellSize,y*gridCellSize,gridCellSize,gridCellSize);
            }
        }
        
        
        drawBackground(g);
        drawBlock(g);
    } 
}
