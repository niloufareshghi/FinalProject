package GUI;


import Logic.AudioPlayer;
import Logic.VolumeControl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PlayerGUI extends JPanel implements ActionListener{
    JButton playBtn;
    JButton nextBtn;
    JButton prevBtn;
    JFrame f;
    JProgressBar prog;
    JButton pauseBtn;
    JSlider playSlider;
    JSlider volumeSlider;
    private JLabel labelTimeCounter;
    private JLabel labelDuration = new JLabel("00:00:00");
    private JLabel label;
    AudioPlayer player;
    boolean pOp=true;
    Timer timerP;
    Timer timerR;
    int lastPosition;
    int counting =0 ;
    VolumeControl controller;




    public PlayerGUI() throws UnsupportedAudioFileException, IOException {

        GridBagConstraints c = new GridBagConstraints();
        setLayout(new GridBagLayout());
        setBackground(Color.ORANGE);

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
        prog = new JProgressBar(0,100);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx =1;
        c.weighty=1;
        c.gridwidth=50;
        c.gridx=1;
        c.gridy=1;
        c.insets=new Insets(0,50,0,0);

        prog.setValue(0);
        prog.setStringPainted(true);
        this.add(prog,c);
        prog.setModel(playSlider.getModel());
        this.add(playSlider,c);

        player =  new AudioPlayer();

        prog.setMinimum(0);
        prog.setValue(0);
        prog.setMaximum((int) player.getMp3file().getLengthInSeconds());

        timerP = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelTimeCounter.setText(String.format("%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(player.getPosition()),
                        TimeUnit.MILLISECONDS.toSeconds(player.getPosition()) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(player.getPosition())))
                );
                prog.setValue(counting);
                counting++;
                if(prog.getValue() == prog.getMaximum()){
                    labelTimeCounter.setText("00:00");
                    playBtn.setIcon(new ImageIcon(getClass().getResource("play.png")));
                    pOp = true;
                }
            }
        });

        timerR = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelTimeCounter.setText(String.format("%02d:%02d ",
                        TimeUnit.MILLISECONDS.toMinutes(lastPosition+player.getPosition()),
                        TimeUnit.MILLISECONDS.toSeconds(lastPosition+player.getPosition())-
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(lastPosition + player.getPosition())))
                );
                prog.setValue(counting);
                counting++;
                if(prog.getValue() == prog.getMaximum()){
                    labelTimeCounter.setText("00:00");
                    playBtn.setIcon(new ImageIcon(getClass().getResource("play.png")));
                    pOp = true;

                }
            }
        });

        labelDuration.setText(player.getLengthString());


        handlePlayer();
    }

    int i=0;
    @Override
    public void actionPerformed(ActionEvent e) {
        if(pOp && e.getSource()==playBtn) {
            playBtn.setIcon(new ImageIcon(getClass().getResource("pause.png")));
            if(player.isPlaying() == false && i==0){
                player.play();
                timerP.start();
                timerP.setInitialDelay(0);

            }else if(player.isPlaying() == false && i==1){
                player.resumeSong();
                timerR.setInitialDelay(0);
                timerR.start();
            }

            pOp=false;
        }
        else if(e.getSource()==playBtn) {
            lastPosition+=player.getPosition();
            playBtn.setIcon(new ImageIcon(getClass().getResource("play.png")));
            if(player.isPlaying() == true){
                player.pause();
                i=1;
                timerP.stop();
                timerR.stop();
            }
            /*
            if (playing) stop();
             */
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
    private void handlePlayer(){
        playSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (playSlider.getValueIsAdjusting()) {
                    System.out.println("changed");
                    lastPosition+=player.getPosition()+player.getmp3Long()*(playSlider.getValue()/playSlider.getMaximum());
                    if(player.isPlaying() == true){
                        prog.setValue(playSlider.getValue());
                        player.pause();
                        timerR.stop();
                        timerP.stop();
                        try {
                            player.changeByTime(playSlider.getValue(),playSlider.getMaximum());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        timerR.setInitialDelay(0);
                        timerR.start();
                        player.resumeSong();
                    }
                }
            }
        });
    }

}