package server;

import Logic.PlayList;
import Logic.SongInfo;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    ServerSocket ss;
    PlayList playList;

    public Server(PlayList playList) throws IOException {
        this.playList = playList;
        ss = new ServerSocket(5555);
        Listener listener = new Listener(playList, ss);
        listener.start();
    }


}


class Listener extends Thread {

        ServerSocket ss;
        PlayList playList;

        public Listener(PlayList playList ,ServerSocket ss) {
            this.ss = ss;
            this.playList = playList;

        }

        @Override
        public void run() {
            while (true) {
                try {
                    Socket s = ss.accept();
                    ClientHandler clientHandler = new ClientHandler(playList, s);
                    clientHandler.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


}



class ClientHandler extends Thread {
    Socket socket;
    PrintWriter printWriter;
    Scanner scanner;
    PlayList playList;

    public ClientHandler(PlayList playList, Socket socket) {
        this.socket = socket;
        this.playList = playList;

        try {
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            scanner = new Scanner(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        int n = playList.getSongs().size();

        printWriter.println(playList.getSongs().size());

        for (SongInfo song : playList.getSongs()) {
            printWriter.println(song.getFilename());
        }

    }
}

