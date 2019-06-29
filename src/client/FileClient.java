package client;

import Logic.SongInfo;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class FileClient {

    private Socket socket;
    private DataInputStream inputStream;
    private PrintWriter out;
    int id;
    SongInfo song;

    public FileClient(String address , int port, SongInfo song){
        try{
            socket = new Socket(address,port);
            inputStream = new DataInputStream(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream());
            this.song = song;

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void download() throws InvalidDataException, IOException, UnsupportedTagException {


        double random = Math.random() * 49 + 1;


        out.println(song.getFilename());

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("JpotifyMusics/"+random+".mp3");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int ch;
        while((ch = inputStream.readInt()) != -1)
            fos.write(ch);

        fos.close();

    }

    public static void main(String[] args) throws InvalidDataException, IOException, UnsupportedTagException {
        //FileClient client = new FileClient(S"localhost",5058);
       // client.download();
    }
}
