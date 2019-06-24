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
    static String filepath = "C:\\Users\\Niloufar Eshghi\\Downloads\\Telegram Desktop\\Justina-Rahro-320.mp3";
    boolean isPaused;

    public PlayerThread() throws JavaLayerException, IOException, InvalidDataException, UnsupportedTagException {
        myFile = new File(filepath);
        fileInputStream = new FileInputStream(myFile);
        player = new AdvancedPlayer(fileInputStream);
        isPaused = false;
        mp3 = new Mp3File(myFile);
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
           } catch (JavaLayerException e) {
               e.printStackTrace();
           }

           if(isPaused){
               synchronized (player) {
                   try {
                       player.wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           }

       }
    }

    public Mp3File getMp3(){
        return mp3;
    }


    public boolean isPaused(){
        if(isPaused)
            return true;
        else return false;
    }

    public void pause(){
        this.isPaused = true;
    }

    public void resumeSong(){
        this.isPaused = false;
        synchronized (player) {
            player.notifyAll();
        }
    }

    public void seekTo(int frame) throws  javazoom.jl.decoder.JavaLayerException {
        synchronized (player){
            player.close();
            player = new AdvancedPlayer(fileInputStream);
            player.play(frame,frame+1);
        }
    }
}
