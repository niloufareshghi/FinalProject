package Logic;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.concurrent.TimeUnit;


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
    static String filepath = "C:\\Users\\Niloufar Eshghi\\Downloads\\Telegram Desktop\\Justina-Rahro-320.mp3";
    Player player;
    Mp3File mp3file;


    public AudioPlayer() throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        myFile = new File(filepath);
        try {
            mp3file = new Mp3File(myFile);
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
        } catch (InvalidDataException e) {
            e.printStackTrace();
        }

    }

    public boolean isPlaying(){
        if(playing){
            return true;
        }else
            return false;
    }

    public Mp3File getMp3file(){
        return mp3file;
    }

    public String getLengthString(){
        return (String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(mp3file.getLengthInMilliseconds()),
                TimeUnit.MILLISECONDS.toSeconds(mp3file.getLengthInMilliseconds()) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(mp3file.getLengthInMilliseconds()))));
    }

    public int getPosition(){
        return player.getPosition();
    }


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
        System.out.println(mp3file.getLengthInMilliseconds());


    }


}
