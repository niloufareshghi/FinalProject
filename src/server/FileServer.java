package server;

import Logic.SongInfo;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class FileServer {
    ServerSocket serverSocket;
    SongInfo song;

    public FileServer(SongInfo song) throws IOException {
        serverSocket = new ServerSocket(5058);
        this.song = song;
        SongListener listener = new SongListener(serverSocket , song);
        listener.start();

    }

    public static void main(String[] args) throws InvalidDataException, IOException, UnsupportedTagException {
        SongInfo song = new SongInfo("C:\\Users\\Niloufar Eshghi\\Downloads\\Telegram Desktop\\Bodo Dire.mp3");
        FileServer server = new FileServer(song);
    }
}

class SongListener extends Thread{

    ServerSocket serverSocket;
    SongInfo song;

    public SongListener(ServerSocket serverSocket , SongInfo song){
        this.serverSocket = serverSocket;
        this.song = song;
    }

    @Override
    public void run(){
        while (true){
            try{
                Socket socket = new Socket();
                socket = serverSocket.accept();
                SongClientHandler clientHandler = new SongClientHandler(song , socket);
                clientHandler.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

class SongClientHandler extends Thread {
    Socket socket;
    BufferedOutputStream out;
    InputStream in;
    SongInfo song;

    public SongClientHandler(SongInfo song, Socket socket) throws IOException {
        this.song = song;
        this.socket = socket;

        try {
            out = new BufferedOutputStream(socket.getOutputStream());
            in = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {


        try {

            FileInputStream fis = new FileInputStream(song.getFilename());

            int ch;
            while ((ch = fis.read()) != -1) {
                out.write(ch);
            }

            out.write(-1);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
