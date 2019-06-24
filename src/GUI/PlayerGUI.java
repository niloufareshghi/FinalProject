package GUI;

import Logic.PlayerThread;
import Logic.VolumeControl;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PlayerGUI extends JPanel implements ActionListener{
    JButton playBtn;
    JButton nextBtn;
    JButton prevBtn;
    JSlider playSlider;
    JSlider volumeSlider;
    private JLabel labelTimeCounter;
    private JLabel labelDuration;
    PlayerThread thread;
    boolean pOp=true;
    Timer timerP;
    Timer timerR;
    int lastPosition;
    int counting =0 ;
    VolumeControl controller;




    public PlayerGUI() throws IOException, JavaLayerException, InvalidDataException, UnsupportedTagException {


        GridBagConstraints c = new GridBagConstraints();
        setLayout(new GridBagLayout());
        setBackground(Color.ORANGE);

        thread = new PlayerThread();


        prevBtn = new JButton();
        c.fill = GridBagConstraints.BOTH;
        c.anchor=GridBagConstraints.CENTER;
        c.weightx =1;
        c.weighty=1;
        c.gridx=4;
        c.gridwidth=1;
        c.insets=new Insets(0,300,0,0);
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
        c.gridx=6;
        c.insets=new Insets(0,0,0,300);
        c.gridy=0;
        c.gridwidth=1;
        this.add(nextBtn,c);
        showButton(nextBtn);
        nextBtn.setIcon(new ImageIcon(getClass().getResource("next.png")));

        labelDuration = new JLabel("00:00");
        c.weightx=1;
        c.weighty=1;
        c.gridx=100;
        c.insets = new Insets(0,0,0,0);
        c.gridy=1;
        c.gridwidth=0;
        this.add(labelDuration,c);


        labelTimeCounter = new JLabel("00:00");
        c.weightx=1;
        c.weighty=1;
        c.gridx=2;
        c.insets = new Insets(0,0,0,0);
        c.gridy=1;
        c.gridwidth=0;
        this.add(labelTimeCounter,c);

        volumeSlider = new JSlider();
        volumeSlider.setMinimum(1);
        volumeSlider.setMaximum(100);
        volumeSlider.setValue(0);
        controller = new VolumeControl();
        volumeSlider.setValue(50);
        controller.setSystemVolume(50);
        volumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                controller.setSystemVolume(volumeSlider.getValue());
            }
        });

        c.weightx=1;
        c.weighty=1;
        c.gridx=150;
        c.insets = new Insets(0,50,0,0);
        c.gridy=1;
        c.gridwidth=0;
        this.add(volumeSlider,c);



        playSlider = new JSlider();
        playSlider.setMaximum((int) thread.getMp3().getLengthInSeconds());
        playSlider.setValue(0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx =1;
        c.weighty=2;
        c.gridwidth=50;
        c.gridx=1;
        c.gridy=1;
        c.insets=new Insets(0,50,0,0);

        this.add(playSlider,c);







        timerP = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSlider.setValue(counting);
                counting++;
                if(playSlider.getValue() == playSlider.getMaximum()){
                    thread.pause();
                    labelTimeCounter.setText("00:00");
                    playBtn.setIcon(new ImageIcon(getClass().getResource("play.png")));
                    pOp = true;
                }

                long minutes = (long) (((double)playSlider.getValue() ) / 60);
                long seconds = (long) (((double)playSlider.getValue()) % 60);
                labelTimeCounter.setText(String.format("%02d:%02d", minutes, seconds));

            }

        });

        timerR = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSlider.setValue(counting);
                counting++;
                if(playSlider.getValue() == playSlider.getMaximum()){
                    thread.pause();
                    labelTimeCounter.setText("00:00");
                    playBtn.setIcon(new ImageIcon(getClass().getResource("play.png")));
                    pOp = true;

                }
                long minutes = (long) ((playSlider.getValue()) / 60);
                long seconds = (long) ((playSlider.getValue()) % 60);
                labelTimeCounter.setText(String.format("%02d:%02d", minutes, seconds));
            }
        });

        labelDuration.setText(String.format("%02d:%02d", playSlider.getMaximum() / 60, playSlider.getMaximum()%60));



   }

    int i=0;
    @Override
    public void actionPerformed(ActionEvent e) {
        if(pOp && e.getSource()==playBtn) {
            playBtn.setIcon(new ImageIcon(getClass().getResource("pause.png")));
            if(i==0) {
                thread.start();
                timerP.start();
                timerP.setInitialDelay(0);
            }

            if(thread.isPaused() && i==1){
                thread.resumeSong();
                timerR.setInitialDelay(0);
                timerR.start();
            }

            pOp=false;
        }
        else if(e.getSource()==playBtn) {
            playBtn.setIcon(new ImageIcon(getClass().getResource("play.png")));
            thread.pause();
            i=1;
                timerP.stop();
                timerR.stop();
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




