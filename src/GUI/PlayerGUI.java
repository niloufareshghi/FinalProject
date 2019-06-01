package com.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.Border;

public class PlayerGUI extends JPanel implements ActionListener{
    JButton playBtn;
    JButton nextBtn;
    JButton prevBtn;
    JFrame f;
    JProgressBar prog;
    JButton pauseBtn;
    boolean pOp=true;


    public PlayerGUI() {

        GridBagConstraints c = new GridBagConstraints();
        setLayout(new GridBagLayout());
        setBackground(Color.ORANGE);
        prevBtn = new JButton();
        c.fill = GridBagConstraints.BOTH;
        c.anchor=GridBagConstraints.CENTER;
        c.weightx =1;
        c.weighty=1;
        c.gridx=6;
        c.gridwidth=1;
        c.insets=new Insets(0,0,0,300);
        c.gridy=0;
        this.add(prevBtn,c);
        showButton(prevBtn);
        prevBtn.setIcon(new ImageIcon(getClass().getResource("prev.png")));


        playBtn = new JButton();
        c.weightx =1;
        c.weighty=1;
        c.gridx=5;
        c.insets=new Insets(0,0,0,0);

        c.gridwidth=1;
        c.gridy=0;
        this.add(playBtn,c);
        showButton(playBtn);
        playBtn.setIcon(new ImageIcon(getClass().getResource("play.png")));
        playBtn.addActionListener(this);

        nextBtn = new JButton();
        c.weightx =1;
        c.weighty=1;
        c.gridx=4;
        c.insets=new Insets(0,300,0,0);

        c.gridy=0;
        c.gridwidth=1;
        this.add(nextBtn,c);
        showButton(nextBtn);
        nextBtn.setIcon(new ImageIcon(getClass().getResource("next.png")));


        prog = new JProgressBar();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx =1;
        c.weighty=1;
        c.gridwidth=100;
        c.gridx=0;
        c.gridy=1;
        c.insets=new Insets(0,0,0,0);

        prog.setValue(0);
        prog.setStringPainted(false);
        this.add(prog,c);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(pOp && e.getSource()==playBtn) {
            playBtn.setIcon(new ImageIcon(getClass().getResource("pause.png")));
            pOp=false;
        }
        else if(e.getSource()==playBtn) {
            playBtn.setIcon(new ImageIcon(getClass().getResource("play.png")));
            pOp=true;
        }
    }

    private void showButton(JButton b) {
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setFocusPainted(false);
        b.setOpaque(false);
        b.setSize(20,20);

    }

}
