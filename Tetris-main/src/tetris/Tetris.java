
package tetris;

import javax.swing.JOptionPane;

public class Tetris {
    private static GameForm gf;
    private static StartupForm sf;
    private static LeaderBoardForm lf;
    private static SettingsForm stf;
    private static HowToPlayForm htf;
    
    public static void start(){

        gf.setVisible(true);

        lf.RetryButton.setVisible(false);
        gf.startGame();
    }
    
    public static void showLeaderBoard(){
        lf.setVisible(true);
    }
    
    public static void showStartup(){
        sf.setVisible(true);
    }
    
    public static void showSettings() {
        stf.setVisible(true);
    }
    
    public static void showHowToPlay() {
        htf.setVisible(true);
    }
    
    public static void exit() {
        gf.setVisible(false);
    }
    
    //Shows Game Over Input Dialog and
    public static void gameOver(int score){
       GameForm.playSound("gameover.wav");
       String playerName = JOptionPane.showInputDialog(null, "Enter name", "Game Over", JOptionPane.ERROR_MESSAGE);
       lf.RetryButton.setVisible(true);
       lf.addPlayer(playerName, score);
    }
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() { 
            public void run() {
                 gf = new GameForm();
                 sf = new StartupForm();
                 lf = new LeaderBoardForm();
                 stf = new SettingsForm();
                 htf = new HowToPlayForm();
        
                 sf.setVisible(true);
            }
        });
      
    }
    
}
