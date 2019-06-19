package GUI;

import Logic.AudioPlayer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.Border;

public class PlayerGUI extends JPanel implements ActionListener{
    JButton playBtn;
    JButton nextBtn;
    JButton prevBtn;
    JFrame f;
    JProgressBar prog;
    JButton pauseBtn;
    JSlider slider;
    private JLabel labelTimeCounter;
    private JLabel labelDuration = new JLabel("00:00:00");
    AudioPlayer player;
    boolean pOp=true;
    Timer timerP;
    Timer timerR;
    int lastPosition;





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


        slider = new JSlider();
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
        prog.setModel(slider.getModel());
        this.add(slider,c);

        try {
            player =  new AudioPlayer();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }



        timerP = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelTimeCounter.setText(String.format("%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(player.getPosition()),
                        TimeUnit.MILLISECONDS.toSeconds(player.getPosition()) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(player.getPosition())))
                );

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

            }
        });

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
//            if (!playing){
//                pt = new PlayerThread();
//                pt.start();
//            }
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

}
