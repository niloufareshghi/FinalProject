package GUI;

import Controller.Controller;
import Logic.PlayerThread;
import Logic.SongInfo;
import Logic.VolumeControl;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PlayerGUI extends JPanel implements ActionListener, ChangeListener {
    private JButton repeatBtn;
    private JButton shuffleBtn;
    private JButton favBtn;
    JButton playBtn;
    JButton nextBtn;
    JButton prevBtn;
    JSlider playSlider;
    JSlider volumeSlider;
    private JLabel labelTimeCounter;
    private JLabel labelDuration;
    PlayerThread thread;
    boolean pOp = true;
    Timer timer;
    Timer timerR;
    int counting = 0;
    VolumeControl controller;
    private boolean faved = false;
    private boolean shuffled = false;
    private SongInfo song ;
    private boolean repeat;
    private ArrayList<SongInfo> songs;
    private ArrayList<Integer> arranges;
    private int arrange;
    int i = 0;

    public PlayerGUI() throws IOException, JavaLayerException, InvalidDataException, UnsupportedTagException {


        GridBagConstraints c = new GridBagConstraints();
        setLayout(new GridBagLayout());
        setBackground(Color.ORANGE);

        thread = new PlayerThread();


        prevBtn = new JButton();
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 4;
        c.gridwidth = 1;
        c.insets = new Insets(0, 300, 0, 0);
        c.gridy = 0;
        this.add(prevBtn, c);
        showButton(prevBtn);
        prevBtn.setIcon(new ImageIcon(getClass().getResource("prev.png")));
        prevBtn.addActionListener(this);

        playBtn = new JButton();
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 5;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridwidth = 1;
        c.gridy = 0;
        this.add(playBtn, c);
        showButton(playBtn);
        playBtn.setIcon(new ImageIcon(getClass().getResource("play.png")));
        playBtn.addActionListener(this);

        nextBtn = new JButton();
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 6;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 0;
        c.gridwidth = 1;
        this.add(nextBtn, c);
        showButton(nextBtn);
        nextBtn.setIcon(new ImageIcon(getClass().getResource("next.png")));
        nextBtn.addActionListener(this);

        favBtn = new JButton();
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 7;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 0;
        c.gridwidth = 1;
        this.add(favBtn, c);
        showButton(favBtn);
        favBtn.setIcon(new ImageIcon(getClass().getResource("emptyHeart.png")));
        favBtn.addActionListener(this);


        shuffleBtn = new JButton();
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 8;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 0;
        c.gridwidth = 1;
        this.add(shuffleBtn, c);
        showButton(shuffleBtn);
        shuffleBtn.setIcon(new ImageIcon(getClass().getResource("shuffle.png")));
        shuffleBtn.addActionListener(this);

        repeatBtn = new JButton();
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 9;
        c.insets = new Insets(0, 0, 0, 300);
        c.gridy = 0;
        c.gridwidth = 1;
        this.add(repeatBtn, c);
        showButton(repeatBtn);
        repeatBtn.setIcon(new ImageIcon(getClass().getResource("repeat.png")));
        repeatBtn.addActionListener(this);


        labelDuration = new JLabel("00:00");
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 100;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 1;
        c.gridwidth = 0;
        this.add(labelDuration, c);


        labelTimeCounter = new JLabel("00:00");
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 2;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 1;
        c.gridwidth = 0;
        this.add(labelTimeCounter, c);

        volumeSlider = new JSlider();
        volumeSlider.setMinimum(1);
        volumeSlider.setMaximum(100);
        volumeSlider.setValue(0);
        controller = new VolumeControl();
        volumeSlider.setValue(10);
        controller.setSystemVolume(10);
        volumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                controller.setSystemVolume(volumeSlider.getValue());
            }
        });

        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 150;
        c.insets = new Insets(0, 50, 0, 0);
        c.gridy = 1;
        c.gridwidth = 0;
        this.add(volumeSlider, c);


        playSlider = new JSlider();
        playSlider.setValue(0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weighty = 2;
        c.gridwidth = 50;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(0, 50, 0, 0);
        this.add(playSlider, c);
        playSlider.addChangeListener(this);


        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                playSlider.setValue(counting);
                counting++;
                /*if(playSlider.getValue() == playSlider.getMaximum()){
                    thread.pause();
                    labelTimeCounter.setText("00:00");
                    playBtn.setIcon(new ImageIcon(getClass().getResource("play.png")));
                    playSlider.setValue(0);
                    pOp = true;
                }*/

                long minutes = (long) (((double) playSlider.getValue()) / 60);
                long seconds = (long) (((double) playSlider.getValue()) % 60);
                labelTimeCounter.setText(String.format("%02d:%02d", minutes, seconds));
                if(playSlider.getMaximum()==playSlider.getValue()+2){
                    if(repeat){
                        try {
                            setSong(song);
                        } catch (InvalidDataException e1) {
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (UnsupportedTagException e1) {
                            e1.printStackTrace();
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                    else{
                        if(arrange==songs.size()-1){
                            arrange=0;
                            try {
                                setNext();
                            } catch (JavaLayerException e1) {
                                e1.printStackTrace();
                            } catch (UnsupportedTagException e1) {
                                e1.printStackTrace();
                            } catch (InvalidDataException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }
            }

        });




    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playBtn ) {
            if (pOp) {
                if (i == 0) {
                    start();
                }

                if (i == 1) {
                    resume();
                }

            } else {
                pause();
            }
        }  else if (e.getSource() == prevBtn && song!=null) {
            try {
                setPrev();
            } catch (JavaLayerException e1) {
                e1.printStackTrace();
            } catch (UnsupportedTagException e1) {
                e1.printStackTrace();
            } catch (InvalidDataException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == nextBtn && song!=null) {
            try {
                setNext();
            } catch (JavaLayerException e1) {
                e1.printStackTrace();
            } catch (UnsupportedTagException e1) {
                e1.printStackTrace();
            } catch (InvalidDataException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

        } else if (e.getSource() == favBtn && song!=null) {
            if (!faved) {
                Controller.getRepository().addSongToPL(">>Favorites", song);
                favBtn.setIcon(new ImageIcon(getClass().getResource("redHeart.png")));
                faved = true;
            } else {
                favBtn.setIcon(new ImageIcon(getClass().getResource("emptyHeart.png")));

                faved = false;
                Controller.getRepository().removeSongFromPL(">>Favorites", song);

            }
        } else if (e.getSource() == shuffleBtn && song!=null) {
            if (!shuffled) {
                shuffleBtn.setIcon(new ImageIcon(getClass().getResource("shuffleActivated.png")));
                shuffled = true;
                Collections.shuffle(arranges);
            } else {
                shuffleBtn.setIcon(new ImageIcon(getClass().getResource("shuffle.png")));
                shuffled = false;
                for (int i = 0; i < arranges.size(); i++) {
                    arranges.set(i, i);
                }
            }
        } else if (e.getSource() == repeatBtn && song!=null) {
            if (repeat == false) {
                repeatBtn.setContentAreaFilled(true);
                repeat = true;
            } else {
                repeatBtn.setContentAreaFilled(false);
                repeat = false;

            }

        }
    }

    private void showButton(JButton b) {
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setFocusPainted(false);
        b.setOpaque(false);
        b.setSize(20, 20);


    }

    private void start() {
        playBtn.setIcon(new ImageIcon(getClass().getResource("pause.png")));
        thread.start();
        timer.start();
        timer.setInitialDelay(0);
        pOp=false;

    }
    public void setSongs(ArrayList<SongInfo> songs) throws InvalidDataException, IOException, UnsupportedTagException, InterruptedException {
        this.songs=songs;
        arranges=new ArrayList<>();
        for(int i=0;i<songs.size();i++){
            arranges.add(i);
        }
        arrange=0;
        setSong(songs.get(0));

    }

    public void setSong(SongInfo song) throws InvalidDataException, IOException, UnsupportedTagException, InterruptedException {
        this.song=song;
//            if(!thread.isPaused()){
//                pause();
//            }
            PlayerThread.setFilepath(song.getFilename());
//            if(!(playSlider.getValue() ==0)) {
                seek(0,1);
//                Thread.sleep(10);
//                pause();
//                resume();
//            }
        Controller.getWindowsGUI().getListGUI().setArtWork(song);
        Controller.makeRecentlyPlayed(song);
        if (Controller.getRepository().getLists().get(0).getSongs().contains(song)) {
            faved = true;
        } else faved = false;


        playSlider.setMaximum((int) thread.getMp3().getLengthInSeconds());
        labelDuration.setText(String.format("%02d:%02d", playSlider.getMaximum() / 60, playSlider.getMaximum() % 60));

    }

    private void setNext() throws JavaLayerException, UnsupportedTagException, InvalidDataException, IOException, InterruptedException {
        arrange++;
        arrange = arrange % arranges.size();
        setSong(songs.get(arranges.get(arrange)));

    }
    private void setPrev() throws JavaLayerException, UnsupportedTagException, InvalidDataException, IOException, InterruptedException {
        arrange--;
        arrange = arrange % arranges.size();
        setSong(songs.get(arranges.get(arrange)));
    }
    private void pause() {
        playBtn.setIcon(new ImageIcon(getClass().getResource("play.png")));
        thread.pause();
        i = 1;
        timer.stop();
        pOp = true;

    }

    private void resume() {
        playBtn.setIcon(new ImageIcon(getClass().getResource("pause.png")));
        thread.resumeSong();
        timer.setInitialDelay(0);
        timer.start();
        pOp=false;
    }


    private void seek(int time,int j) {
        try {
            thread.pause();
            timer.stop();
            thread.seekTo(time);
            //playSlider.setValue(playSlider.getValue());
            //counting = playSlider.getValue();
            if(j==1){
                counting=0;
                playSlider.setValue(0);
            }
            else if (playSlider.getValue() < thread.getPausedPoint()) {
                counting = playSlider.getValue();
                playSlider.setValue(playSlider.getValue());
            }

            timer.start();


        } catch (JavaLayerException | IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (playSlider.getValueIsAdjusting()
                && (pOp==false)) {
            seek((int) (((double) playSlider.getValue() / playSlider.getMaximum()) * thread.getMp3().getFrameCount()),0);
//            try {
////                setSong(new SongInfo("C:\\Users\\heyda\\Downloads\\Telegram Desktop\\Mohsen Namjoo-Shekveh.mp3"));
//            } catch (InvalidDataException e1) {
//                e1.printStackTrace();
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            } catch (UnsupportedTagException e1) {
//                e1.printStackTrace();
//            } catch (InterruptedException e1) {
//                e1.printStackTrace();
//            }
//            seek(0,1);


        }
    }
}




