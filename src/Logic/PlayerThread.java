package Logic;


import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;
import src.javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.*;

public class PlayerThread extends Thread {

    AdvancedPlayer player;
    FileInputStream fileInputStream;
    BufferedInputStream bufferedInputStream;
    File myFile;
    Mp3File mp3;
   //static String filepath = "C:\\Users\\Niloufar Eshghi\\Downloads\\Telegram Desktop\\11460851_11460513.mp3";
   static String filepath = "C:\\Users\\Niloufar Eshghi\\Downloads\\Telegram Desktop\\06 Soghati.mp3";
    boolean isPaused;
    int goalFrame;
    int passedFrame;
    int pausedPoint;

    public PlayerThread() throws JavaLayerException, IOException, InvalidDataException, UnsupportedTagException {
        myFile = new File(filepath);
        fileInputStream = new FileInputStream(myFile);
        player = new AdvancedPlayer(fileInputStream);
        isPaused = false;
        mp3 = new Mp3File(myFile);
        goalFrame=-1;
    }



    public void run() {
       while(true){
           try {
               if (!player.play(1)) break;
           } catch (JavaLayerException e) {
               e.printStackTrace();
           }
           try {
               player.play(1);
               passedFrame++;
           } catch (JavaLayerException e) {
               e.printStackTrace();
           }

           if(isPaused) {
               synchronized (player) {
                   if (goalFrame != -1) {
                       try {
                           player.close();
                           passedFrame = goalFrame;
                           myFile = new File(filepath);
                           fileInputStream = new FileInputStream(myFile);
                           player = new AdvancedPlayer(fileInputStream);
                           player.play(goalFrame,goalFrame+1);
                       } catch (JavaLayerException | FileNotFoundException e) {
                           e.printStackTrace();
                       }
                       goalFrame=-1;
                       isPaused=false;
                       continue;

                   }
                   else{
                       try {
                           player.wait();
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   }
               }
           }

       }
    }

    public int getPausedPoint(){
        return pausedPoint;
    }

    public Mp3File getMp3(){
        return mp3;
    }


    public boolean isPaused(){
        if(isPaused)
            return true;
        else return false;
    }

    public void pause() {
        this.isPaused = true;
        pausedPoint = passedFrame;
    }


    public void resumeSong(){
        this.isPaused = false;
        synchronized (player) {
            player.notifyAll();
        }
    }

    public void seekTo(int frame) throws javazoom.jl.decoder.JavaLayerException, FileNotFoundException {
        goalFrame=frame;
    }
}
