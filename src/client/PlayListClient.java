package client;

import Logic.PlayList;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class PlayListClient extends Thread{
    private Socket socket;
    private Scanner input;
    private PrintWriter output;
    private ArrayList<String> songs;

    public PlayListClient(String address , int port){

        try {
            socket = new Socket(address,port);
            input = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream(),true);
            songs = new ArrayList<String>();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){

            int n = input.nextInt();

            String a = input.nextLine();
        System.out.println(n);


            for(int i=0;i<n;i++) {
                String s = input.nextLine();
                System.out.println(s);
                songs.add(s);
            }
    }

    public ArrayList<String> getSongs(){
        return songs;
    }


}