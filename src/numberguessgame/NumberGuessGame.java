package numberguessgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Random;

public class NumberGuessGame extends JFrame implements ActionListener{

    private final JTextField in; //inputField
    private final JButton gbtn,rbtn; //guessbutton,resetbutton
    private final JLabel msg,hslbl; //messagelabel,highscorelabel

    private int rn,left,used,hs = Integer.MAX_VALUE; //randomnumber,attemptsleft,attemptsused,highscore

    private static final int MAX=10; //maxattempts
    private static final String FILE="highscore.txt"; //highscorefile

    public NumberGuessGame(){
        setTitle("🎯 Number Guessing Game");
        setSize(600,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        bgpanel p=new bgpanel(); //backgroundpanel
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        p.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));
        add(p);

        JLabel t=new JLabel("🎯 Guess number between 1 and 100",JLabel.CENTER); //t = title
        t.setFont(new Font("Segoe UI Emoji",Font.BOLD,22));
        t.setForeground(Color.WHITE);
        t.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(t);
        p.add(Box.createRigidArea(new Dimension(0,20)));

        in=new JTextField();
        in.setFont(new Font("Segoe UI Emoji",Font.PLAIN,18));
        in.setMaximumSize(new Dimension(200,40));
        in.setHorizontalAlignment(JTextField.CENTER);
        in.setBackground(new Color(230,230,250));
        in.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(in);
        p.add(Box.createRigidArea(new Dimension(0,15)));

        gbtn=new JButton("Guess");
        gbtn.setFont(new Font("Segoe UI Emoji",Font.BOLD,18));
        gbtn.setBackground(new Color(0,123,255));
        gbtn.setForeground(Color.WHITE);
        gbtn.setFocusPainted(false);
        gbtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        gbtn.addActionListener(this);
        in.addActionListener(this);
        p.add(gbtn);
        p.add(Box.createRigidArea(new Dimension(0,15)));

        msg=new JLabel("You have 10 attempts.",JLabel.CENTER);
        msg.setFont(new Font("Segoe UI Emoji",Font.PLAIN,16));
        msg.setForeground(Color.YELLOW);
        msg.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(msg);
        p.add(Box.createRigidArea(new Dimension(0,10)));

        hslbl=new JLabel("🏆 High Score: --",JLabel.CENTER);
        hslbl.setFont(new Font("Segoe UI Emoji",Font.PLAIN,16));
        hslbl.setForeground(new Color(144,238,144));
        hslbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(hslbl);
        p.add(Box.createRigidArea(new Dimension(0,20)));

        rbtn=new JButton("Reset Game");
        rbtn.setFont(new Font("Segoe UI Emoji",Font.BOLD,16));
        rbtn.setBackground(new Color(220,53,69));
        rbtn.setForeground(Color.WHITE);
        rbtn.setFocusPainted(false);
        rbtn.setEnabled(false);
        rbtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        rbtn.addActionListener(e->reset());
        p.add(rbtn);

        loadhs();//loadhighscore
        gennum(); //generatenum
        setVisible(true);
    }

    private void gennum(){ //gennum = generate number
        rn=new Random().nextInt(100)+1;
        left=MAX;
        used=0;
        msg.setText("You have " + MAX + " attempts.");
        msg.setForeground(Color.YELLOW);
    }

    private void reset(){ 
        gennum(); 
        in.setText("");
        in.setEditable(true);
        in.requestFocus();
        gbtn.setEnabled(true);
        rbtn.setEnabled(false);
    }

    private void loadhs(){
        try(BufferedReader br=new BufferedReader(new FileReader(FILE))){
            String line=br.readLine();
            if(line!=null)
                hs=Integer.parseInt(line.trim());
        }catch(Exception e){
            hs=Integer.MAX_VALUE;
        }
        updatehs(); //updatehighscore
    }

    private void savehs(){ // hs = highscore
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(FILE))){
            bw.write(String.valueOf(hs));
        }catch(IOException e){
            System.out.println("Error saving high score");
        }
    }

    private void updatehs(){
        if (hs==Integer.MAX_VALUE)
            hslbl.setText("🏆 High Score: --");
        else
            hslbl.setText("🏆 High Score: " + hs + " attempts");
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String val=in.getText().trim();

        if(val.isEmpty()){
            msg.setText("enter a number first.");
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

            if(g==rn){
                msg.setText("🎉 Correct! Attempts: " + used);
                msg.setForeground(Color.GREEN);

                if(used<hs){
                    hs=used;
                    savehs();
                    updatehs();
                }
                in.setEditable(false);
                gbtn.setEnabled(false);
                rbtn.setEnabled(true);
                
            }else{
                if(g<rn)
                    msg.setText("📉 Too Low! Left: " +left);
                else
                    msg.setText("📈 Too High! Left: " +left);

                msg.setForeground(Color.CYAN);
            }

            if(left==0 && g!=rn){
                msg.setText("😢 Game Over! Number was " + rn);
                msg.setForeground(Color.RED);
                in.setEditable(false);
                gbtn.setEnabled(false);
                rbtn.setEnabled(true);
            }

        }catch(NumberFormatException ex){
            msg.setText("invalid input.");
            msg.setForeground(Color.ORANGE);
        }
    }

    private class bgpanel extends JPanel{  //background panel
        private final Image img; //background image
        public bgpanel(){
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

