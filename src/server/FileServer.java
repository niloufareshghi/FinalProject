package server;

import Logic.SongInfo;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class FileServer {
    ServerSocket serverSocket;
    ArrayList<SongInfo> songs;

    public FileServer(ArrayList<SongInfo> songs) throws IOException {
        serverSocket = new ServerSocket(5058);
        this.songs = songs;
        SongListener listener = new SongListener(serverSocket , songs);
        listener.start();

    }

    public static void main(String[] args) throws InvalidDataException, IOException, UnsupportedTagException {
        SongInfo song = new SongInfo("C:\\Users\\Niloufar Eshghi\\Downloads\\Telegram Desktop\\Bodo Dire.mp3");
        ArrayList<SongInfo> songs = null;
        songs.add(song);
        FileServer server = new FileServer(songs);
    }
}

class SongListener extends Thread{

    ServerSocket serverSocket;
    ArrayList<SongInfo> songs;

    public SongListener(ServerSocket serverSocket , ArrayList<SongInfo> songs){
        this.serverSocket = serverSocket;
        this.songs = songs;
    }

    @Override
    public void run(){
        while (true){
            try{
                Socket socket = new Socket();
                socket = serverSocket.accept();
                SongClientHandler clientHandler = new SongClientHandler(songs , socket);
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
    Scanner in;
    ArrayList<SongInfo> songs;

    public SongClientHandler(ArrayList<SongInfo> songs, Socket socket) throws IOException {
        this.songs = songs;
        this.socket = socket;

        try {
            out = new BufferedOutputStream(socket.getOutputStream());
            in = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {


            try {

                FileInputStream fis = new FileInputStream(in.nextLine());

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
