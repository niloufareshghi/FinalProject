package client;

import Logic.PlayList;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class PlayListClient extends Thread{
    private Socket socket;
    private Scanner input;
    private PrintWriter output;

    public PlayListClient(String address , int port){

        try {
            socket = new Socket(address,port);
            input = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream(),true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){

            int n = input.nextInt();

            String a = input.nextLine();


            for(int i=0;i<n;i++) {
                System.out.println(input.nextLine());
            }
    }

    public static void main(String[] args) {
        PlayListClient client = new PlayListClient("localHost", 5555);
        client.start();
    }

}