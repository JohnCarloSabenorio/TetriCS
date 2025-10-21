package tetris;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

public class GameForm extends JFrame {

   private GameArea ga;
   private GameThread gt;
    public GameForm() {
        initComponents();
        
        ga = new GameArea(gameAreaPlaceholder,10);
        this.add(ga);
         
        initControls();
    }
    
    //Plays sound effects
     public static void playSound(String pathName){
        try{
         AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(pathName).getAbsoluteFile());
         Clip clip = AudioSystem.getClip();
         clip.open(inputStream);
         FloatControl gainControl = 
    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
         
         clip.start();
        }
        catch(Exception e){
            System.out.println("An error occurred when playing sound");
            e.printStackTrace();
        }
    }
     
     

    private void initControls(){
        InputMap im = this.getRootPane().getInputMap();
        ActionMap am = this.getRootPane().getActionMap();
        
        im.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        im.put(KeyStroke.getKeyStroke("LEFT"), "left");
        im.put(KeyStroke.getKeyStroke("UP"), "up");
        im.put(KeyStroke.getKeyStroke("DOWN"), "down");
        im.put(KeyStroke.getKeyStroke("SPACE"), "space");

      
        
        am.put("right",new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                                    playSound("move.wav");


                    ga.moveBlockRight();
            }
            
        });
        
        am.put("left",new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                                    playSound("move.wav");
                    ga.moveBlockLeft();
            }
            
        });
        
        am.put("up",new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                                    playSound("rotate.wav");

                    ga.rotateBlock();
            }
            
        });
            
        am.put("down",new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                                    playSound("softdrop.wav");

                    ga.moveBlockDown();
            }
            
        });
        
        am.put("space",new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                                    playSound("harddrop.wav");
                    ga.dropBlock();
            }
            
        });
        
    }
    
    public void startGame(){
        ga.initBackgroundArray();
        gt = new GameThread(ga,this);
        gt.start();
    }
    
    public void updateScore(int score){
        scoreDisplay.setText("SCORE: " + score);
    }
    
     public void updateLevel(int level){
        levelDisplay.setText("LEVEL: " + level);
                                    playSound("lvl.wav");        
    }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        gameAreaPlaceholder = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        levelDisplay = new javax.swing.JLabel();
        scoreDisplay = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        LeaderboardButton = new javax.swing.JButton();
        MenuButton = new javax.swing.JButton();
        HowToPlayButton = new javax.swing.JButton();
        SettingsButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TETRICS v.201");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(30, 30, 30));
        jPanel1.setMaximumSize(new java.awt.Dimension(710, 829));

        gameAreaPlaceholder.setBackground(new java.awt.Color(23, 23, 23));
        gameAreaPlaceholder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 51), 3));
        gameAreaPlaceholder.setPreferredSize(new java.awt.Dimension(281, 561));

        javax.swing.GroupLayout gameAreaPlaceholderLayout = new javax.swing.GroupLayout(gameAreaPlaceholder);
        gameAreaPlaceholder.setLayout(gameAreaPlaceholderLayout);
        gameAreaPlaceholderLayout.setHorizontalGroup(
            gameAreaPlaceholderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
        );
        gameAreaPlaceholderLayout.setVerticalGroup(
            gameAreaPlaceholderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 415, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(23, 23, 23));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 255, 255), 6, true));

        levelDisplay.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        levelDisplay.setForeground(new java.awt.Color(255, 255, 255));
        levelDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        levelDisplay.setText("LEVEL: 1");

        scoreDisplay.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        scoreDisplay.setForeground(new java.awt.Color(255, 255, 255));
        scoreDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        scoreDisplay.setText("SCORE: 0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scoreDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                    .addComponent(levelDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(levelDisplay)
                .addGap(33, 33, 33)
                .addComponent(scoreDisplay)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/tetrics.png"))); // NOI18N

        LeaderboardButton.setBackground(new java.awt.Color(102, 0, 102));
        LeaderboardButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        LeaderboardButton.setForeground(new java.awt.Color(255, 255, 255));
        LeaderboardButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/leaderboard.png"))); // NOI18N
        LeaderboardButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 5, true));
        LeaderboardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LeaderboardButtonActionPerformed(evt);
            }
        });

        MenuButton.setBackground(new java.awt.Color(153, 0, 0));
        MenuButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        MenuButton.setForeground(new java.awt.Color(255, 255, 255));
        MenuButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/menu.png"))); // NOI18N
        MenuButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 5, true));
        MenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuButtonActionPerformed(evt);
            }
        });

        HowToPlayButton.setBackground(new java.awt.Color(255, 153, 0));
        HowToPlayButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        HowToPlayButton.setForeground(new java.awt.Color(255, 255, 255));
        HowToPlayButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/h2p.png"))); // NOI18N
        HowToPlayButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 5, true));
        HowToPlayButton.setFocusPainted(false);
        HowToPlayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HowToPlayButtonActionPerformed(evt);
            }
        });

        SettingsButton.setBackground(new java.awt.Color(0, 153, 255));
        SettingsButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        SettingsButton.setForeground(new java.awt.Color(255, 255, 255));
        SettingsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/settings.png"))); // NOI18N
        SettingsButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 5, true));
        SettingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(HowToPlayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(SettingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(MenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(LeaderboardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(28, 28, 28)
                        .addComponent(gameAreaPlaceholder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(50, 50, 50))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(gameAreaPlaceholder, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(LeaderboardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SettingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(MenuButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(HowToPlayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(192, 192, 192))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void LeaderboardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LeaderboardButtonActionPerformed
        Tetris.showLeaderBoard();
    }//GEN-LAST:event_LeaderboardButtonActionPerformed

    private void MenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuButtonActionPerformed
        this.dispose();
        Tetris.showStartup();
    }//GEN-LAST:event_MenuButtonActionPerformed

    private void HowToPlayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HowToPlayButtonActionPerformed
        Tetris.showHowToPlay();
    }//GEN-LAST:event_HowToPlayButtonActionPerformed

    private void SettingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsButtonActionPerformed
        Tetris.showSettings();
    }//GEN-LAST:event_SettingsButtonActionPerformed

  
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() { 
            public void run() {
                new GameForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton HowToPlayButton;
    private javax.swing.JButton LeaderboardButton;
    private javax.swing.JButton MenuButton;
    private javax.swing.JButton SettingsButton;
    private javax.swing.JPanel gameAreaPlaceholder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel levelDisplay;
    private javax.swing.JLabel scoreDisplay;
    // End of variables declaration//GEN-END:variables
}
