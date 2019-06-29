package client;

import Logic.SongInfo;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileClient {

    private Socket socket;
    private DataInputStream inputStream;
    private PrintWriter out;
    int id;

    public FileClient(String address , int port){
        try{
            socket = new Socket(address,port);
            inputStream = new DataInputStream(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void download() throws InvalidDataException, IOException, UnsupportedTagException {

        String path = "C:\\Users\\Niloufar Eshghi\\Desktop\\newS";
        path += ".mp3";

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int ch;
        while((ch = inputStream.readInt()) != -1)
            fos.write(ch);

        fos.close();

    }

    public static void main(String[] args) throws InvalidDataException, IOException, UnsupportedTagException {
        FileClient client = new FileClient("localhost",5058);
        client.download();
    }
}
