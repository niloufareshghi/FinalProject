package Logic;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.*;


public class AudioPlayer{
    //Long currentFrame;
    //Clip clip;
    private boolean playing;
    FileInputStream fileInputStream;
    BufferedInputStream bufferedInputStream;
    File myFile;
    private int pausedOnFrame;
    Thread play;
    Thread resume;
    long pause;
    int totalLength;
    //AudioInputStream ais;
    static String filepath = "C:\\Users\\Niloufar Eshghi\\Downloads\\Telegram Desktop\\Bodo Dire.mp3";
    Player player;
    AudioInputStream audioInputStream;
    AudioFormat format;
    long frames;
    double duration; //milliSeconds
    long audioFileLength;
    float frameSize;
    float frameRate;


    public AudioPlayer() throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        myFile = new File(filepath);

    }

    public boolean isPlaying(){
        if(playing){
            return true;
        }else
            return false;
    }

    public boolean isComplete(){
        if(player.isComplete()) return true;
        else return false;
    }


    public int getPosition(){
        return player.getPosition();
    }
/*
    public int getTotalLength(){
        return totalLength;
    }
*/


    Runnable runnablePlay=new Runnable() {
        @Override
        public void run() {
                try {
                    //code for play button
                    fileInputStream = new FileInputStream(myFile);
                    bufferedInputStream = new BufferedInputStream(fileInputStream);
                    player = new Player(bufferedInputStream);
                    totalLength = fileInputStream.available();
                    player.play();//starting music
                    //if(player.isComplete()) player.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    };

    Runnable runnableResume=new Runnable() {
        @Override
        public void run() {
            try {
                //code for play button
                fileInputStream=new FileInputStream(myFile);
                bufferedInputStream=new BufferedInputStream(fileInputStream);
                player=new Player(bufferedInputStream);
                fileInputStream.skip(totalLength - pause);
                player.play(/*pausedOnFrame, Integer.MAX_VALUE*/);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };


    public void play(){
        play = new Thread(runnablePlay);
        play.start();
        playing = true;

    }


    public void resumeSong(){
        resume = new Thread (runnableResume);
        resume.start();
        playing = true;
    }

    public void pause() {
            /*player.setPlayBackListener(new PlaybackListener() {
                @Override
                public void playbackFinished(PlaybackEvent event) {
                    pausedOnFrame = event.getFrame();
                }
            });*/
        try {
            pause=fileInputStream.available();
        } catch (IOException e) {
            e.printStackTrace();
        }

        player.close();

            playing = false;

        System.out.println(pause);
        System.out.println(totalLength);
        System.out.println(duration);

    }


}
