package GUI;

import Controller.Controller;
import Logic.PlayerThread;
import Logic.SongInfo;
import Logic.VolumeControl;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.Equalizer;
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

@SuppressWarnings("Duplicates")
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
    private boolean repeat = false;
    private SongInfo song;
    private ArrayList<SongInfo> songs;
    private ArrayList<Integer> arranges;
    private int arrange;
    private Equalize equalizer;

    public PlayerGUI() throws IOException, JavaLayerException, InvalidDataException, UnsupportedTagException {


        update();

    }

    public void update() throws IOException, InvalidDataException, UnsupportedTagException, JavaLayerException {
        removeAll();
        pOp = true;
        counting = 0;
        if (thread != null) thread.closethread();

//       if(song!=null){
//           if(thread!=null)
//           thread.closethread();
//           thread=new PlayerThread(song.getFilename());
//       }
        GridBagConstraints c = new GridBagConstraints();
        setLayout(new GridBagLayout());
        setBackground(new Color(0x643B39));

//        thread = new PlayerThread();

        if (song != null)
            thread = new PlayerThread(song.getFilename());
         equalizer=new Equalize();
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 0.1;
        c.weighty = 0.1;
        c.gridx = 0;
        c.gridwidth = 1;
        c.gridheight = 2;

        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 0;
        this.add(equalizer,c);
        c.gridheight = 1;

        prevBtn = new JButton();
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 4;
        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 0, 0);
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
        if (faved) {
            favBtn.setIcon(new ImageIcon(getClass().getResource("redHeart.png")));

        }
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
        if (shuffled) {
            shuffleBtn.setIcon(new ImageIcon(getClass().getResource("shuffleActivated.png")));
        }
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
        if (repeat) {
            repeatBtn.setContentAreaFilled(true);
        }

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


        if (playSlider == null)
            playSlider = new JSlider();
        if (thread != null)
            playSlider.setMaximum((int) thread.getMp3().getLengthInSeconds());
        System.out.println(playSlider.getMaximum());
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


        if (timer != null && timer.isRunning())
            timer.stop();
            equalizer.timer.stop();

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!equalizer.timer.isRunning())equalizer.timer.start();
                playSlider.setValue(counting);
                counting++;
                System.out.println(counting);
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
                if (thread.isFinished()) {
                    if (repeat) {
                        try {
                            update();
                            setThreadStarts();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (UnsupportedTagException e1) {
                            e1.printStackTrace();
                        } catch (InvalidDataException e1) {
                            e1.printStackTrace();
                        } catch (JavaLayerException e1) {
                            e1.printStackTrace();
                        }
                    } else {

                        try {
                            if (arrange == arranges.size() - 1) {
                                setSong(songs.get(arranges.get(0)));
                                arrange=0;
                            }
                            else {
                                setNext();
                            }
                        } catch (JavaLayerException e1) {
                            e1.printStackTrace();
                        } catch (InvalidDataException e1) {
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (UnsupportedTagException e1) {
                            e1.printStackTrace();
                        }

                    }


//                       update();
//                       pOp=true;
//                   } catch (IOException e1) {
//                       e1.printStackTrace();
//                   } catch (UnsupportedTagException e1) {
//                       e1.printStackTrace();
//                   } catch (JavaLayerException e1) {
//                       e1.printStackTrace();
//                   } catch (InvalidDataException e1) {
//                       e1.printStackTrace();
//                   }
                    //                   try {
//                       setThreadStarts();
//                   } catch (IOException e1) {
//                       e1.printStackTrace();
//                   } catch (UnsupportedTagException e1) {
//                       e1.printStackTrace();
//                   } catch (InvalidDataException e1) {
//                       e1.printStackTrace();
//                   } catch (JavaLayerException e1) {
//                       e1.printStackTrace();
//                   }
                }
            }

        });


        labelDuration.setText(String.format("%02d:%02d", playSlider.getMaximum() / 60, playSlider.getMaximum() % 60));

    }

    int i = 0;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playBtn && thread != null) {
            if (pOp) {
                playBtn.setIcon(new ImageIcon(getClass().getResource("pause.png")));
                if (i == 0) {

                    thread.start();
                    timer.start();
                    timer.setInitialDelay(0);
                }

                if (i == 1) {
                    thread.resumeSong();
                    timer.setInitialDelay(0);
                    timer.start();
                }

                pOp = false;
            } else {
                playBtn.setIcon(new ImageIcon(getClass().getResource("play.png")));
                thread.pause();
                i = 1;
                timer.stop();
                equalizer.timer.stop();

                pOp = true;
            }
        } else if (e.getSource() == prevBtn && thread != null) {
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
            }
        } else if (e.getSource() == nextBtn && thread != null) {
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
            }

        } else if (e.getSource() == favBtn && thread != null) {
            if (!faved) {
                Controller.getRepository().addSongToPL(">>Favorites", song);
                favBtn.setIcon(new ImageIcon(getClass().getResource("redHeart.png")));
                faved = true;
            } else {
                favBtn.setIcon(new ImageIcon(getClass().getResource("emptyHeart.png")));

                faved = false;
                Controller.getRepository().removeSongFromPL(">>Favorites", song);

            }
        } else if (e.getSource() == shuffleBtn && thread != null) {
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
        } else if (e.getSource() == repeatBtn && thread != null) {
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

    public void setSong(SongInfo songInfo) throws InvalidDataException, IOException, UnsupportedTagException, JavaLayerException {

        song = songInfo;
        Controller.getWindowsGUI().getListGUI().setArtWork(songInfo);
        Controller.makeRecentlyPlayed(song);
        if (Controller.getRepository().getLists().get(0).getSongs().contains(songInfo)) {
            faved = true;
        } else faved = false;

//        this.songs=new ArrayList<>();
//        this.songs.add(songInfo);
        i = 0;
        update();
    }

    private void setNext() throws JavaLayerException, UnsupportedTagException, InvalidDataException, IOException {
        arrange++;
        arrange = arrange % arranges.size();
        setSong(songs.get(arranges.get(arrange)));

        setThreadStarts();
    }

    private void setPrev() throws JavaLayerException, UnsupportedTagException, InvalidDataException, IOException {
        arrange--;
        arrange = arrange % arranges.size();
        setSong(songs.get(arranges.get(arrange)));
        setThreadStarts();
    }

    public void setListToPlay(ArrayList<SongInfo> songs) throws JavaLayerException, UnsupportedTagException, InvalidDataException, IOException {
        this.songs = songs;
        arranges = new ArrayList<>();
        arrange = 0;
        shuffled = false;
        for (int i = 0; i < songs.size(); i++) {
            arranges.add(i);
        }

        setSong(songs.get(arranges.get(0)));
        setThreadStarts();
    }

    public void setThreadStarts() throws IOException, UnsupportedTagException, InvalidDataException, JavaLayerException {
        playBtn.setIcon(new ImageIcon(getClass().getResource("pause.png")));
        thread.start();
        timer.start();
        timer.setInitialDelay(0);
        pOp = false;
//        updateUI();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (playSlider.getValueIsAdjusting() && thread != null && !thread.isPaused()) {
            try {
                thread.pause();
                timer.stop();
                equalizer.timer.stop();
                thread.seekTo((int) (((double) playSlider.getValue() / playSlider.getMaximum()) * thread.getMp3().getFrameCount()));
                //playSlider.setValue(playSlider.getValue());
                //counting = playSlider.getValue();
                if (playSlider.getValue() < thread.getPausedPoint()) {
                    counting = playSlider.getValue();
                    playSlider.setValue(playSlider.getValue());
                }
                timer.start();


            } catch (JavaLayerException | IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}




