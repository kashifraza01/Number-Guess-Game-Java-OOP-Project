package numberguessgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Random;

public class NumberGuessGame extends JFrame implements ActionListener{

    private final JTextField in; //inputField
    private final JButton gBtn,rBtn; //guessButton,resetButton
    private final JLabel msg,hsLbl; //messageLabel,highScoreLabel

    private int rn,left,used,hs = Integer.MAX_VALUE; //randomNumber,attemptsLeft,attemptsUsed,highScore

    private static final int MAX=10; //maxAttempts
    private static final String FILE="highscore.txt"; //highScoreFile

    public NumberGuessGame(){
        setTitle("🎯 Number Guessing Game");
        setSize(600,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        BgPanel p=new BgPanel(); //backgroundPanel
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        p.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));
        add(p);

        JLabel title=new JLabel("🎯 Guess number between 1 and 100",JLabel.CENTER);
        title.setFont(new Font("Segoe UI Emoji",Font.BOLD,22));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(title);
        p.add(Box.createRigidArea(new Dimension(0,20)));

        in=new JTextField();
        in.setFont(new Font("Segoe UI Emoji",Font.PLAIN,18));
        in.setMaximumSize(new Dimension(200,40));
        in.setHorizontalAlignment(JTextField.CENTER);
        in.setBackground(new Color(230,230,250));
        in.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(in);
        p.add(Box.createRigidArea(new Dimension(0,15)));

        gBtn=new JButton("Guess");
        gBtn.setFont(new Font("Segoe UI Emoji",Font.BOLD,18));
        gBtn.setBackground(new Color(0,123,255));
        gBtn.setForeground(Color.WHITE);
        gBtn.setFocusPainted(false);
        gBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        gBtn.addActionListener(this);
        in.addActionListener(this);
        p.add(gBtn);
        p.add(Box.createRigidArea(new Dimension(0,15)));

        msg=new JLabel("You have 10 attempts.",JLabel.CENTER);
        msg.setFont(new Font("Segoe UI Emoji",Font.PLAIN,16));
        msg.setForeground(Color.YELLOW);
        msg.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(msg);
        p.add(Box.createRigidArea(new Dimension(0,10)));

        hsLbl=new JLabel("🏆 High Score: --",JLabel.CENTER);
        hsLbl.setFont(new Font("Segoe UI Emoji",Font.PLAIN,16));
        hsLbl.setForeground(new Color(144,238,144));
        hsLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(hsLbl);
        p.add(Box.createRigidArea(new Dimension(0,20)));

        rBtn=new JButton("Reset Game");
        rBtn.setFont(new Font("Segoe UI Emoji",Font.BOLD,16));
        rBtn.setBackground(new Color(220,53,69));
        rBtn.setForeground(Color.WHITE);
        rBtn.setFocusPainted(false);
        rBtn.setEnabled(false);
        rBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        rBtn.addActionListener(e -> reset());
        p.add(rBtn);

        loadHS();
        genNum();
        setVisible(true);
    }

    private void genNum(){
        rn=new Random().nextInt(100)+1;
        left=MAX;
        used=0;
        msg.setText("You have " + MAX + " attempts.");
        msg.setForeground(Color.YELLOW);
    }

    private void reset(){
        genNum();
        in.setText("");
        in.setEditable(true);
        in.requestFocus();
        gBtn.setEnabled(true);
        rBtn.setEnabled(false);
    }

    private void loadHS(){
        try(BufferedReader br=new BufferedReader(new FileReader(FILE))){
            String line=br.readLine();
            if(line!=null)
                hs=Integer.parseInt(line.trim());
        }catch(Exception e){
            hs=Integer.MAX_VALUE;
        }
        updateHS();
    }

    private void saveHS(){
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(FILE))){
            bw.write(String.valueOf(hs));
        }catch(IOException e){
            System.out.println("Error saving high score");
        }
    }

    private void updateHS(){
        if (hs==Integer.MAX_VALUE)
            hsLbl.setText("🏆 High Score: --");
        else
            hsLbl.setText("🏆 High Score: " + hs + " attempts");
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String val=in.getText().trim();

        if(val.isEmpty()){
            msg.setText("Enter a number first.");
            msg.setForeground(Color.ORANGE);
            return;
        }

        try{
            int g=Integer.parseInt(val);

            if(g<1 || g>100){
                msg.setText("Number must be between 1 and 100.");
                msg.setForeground(Color.ORANGE);
                return;
            }

            left--;
            used++;

            if(g==n){
                msg.setText("🎉 Correct! Attempts: " + used);
                msg.setForeground(Color.GREEN);

                if(used<hs){
                    hs=used;
                    saveHS();
                    updateHS();
                }
                in.setEditable(false);
                gBtn.setEnabled(false);
                rBtn.setEnabled(true);
                
            }else{
                if(g<rn)
                    msg.setText("📉 Too Low! Left: " + left);
                else
                    msg.setText("📈 Too High! Left: " + left);

                msg.setForeground(Color.CYAN);
            }

            if(left==0 && g!=rn){
                msg.setText("😢 Game Over! Number was " + rn);
                msg.setForeground(Color.RED);
                in.setEditable(false);
                gBtn.setEnabled(false);
                rBtn.setEnabled(true);
            }

        }catch(NumberFormatException ex){
            msg.setText("Invalid input.");
            msg.setForeground(Color.ORANGE);
        }
    }

    private class BgPanel extends JPanel {
        private final Image img; //backgroundImage

        public BgPanel(){
            java.net.URL url=getClass().getResource("kashif.png");
            if(url!=null){
                img=new ImageIcon(url).getImage();
            }else{
                img=null;
            }
        }

        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            if(img!=null)
                g.drawImage(img,0,0,getWidth(),getHeight(),this);
            else{
                g.setColor(Color.DARK_GRAY);
                g.fillRect(0,0,getWidth(),getHeight());
            }
        }
    }
    public static void main(String[] args){
        new NumberGuessGame();
    }
}
