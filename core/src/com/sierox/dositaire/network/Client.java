package com.sierox.dositaire.network;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.utils.Disposable;

import java.io.IOException;


public class Client implements Disposable {

    private Socket socket;
    private ClientThread thread;

    public Client(){
        thread = new ClientThread();
        thread.start();
    }

    public class ClientThread extends Thread {
        @Override
        public void run() {
            SocketHints hints = new SocketHints();
            hints.connectTimeout = 15000;
            socket = Gdx.net.newClientSocket(Net.Protocol.TCP, "172.16.128.55", 8765, hints);
            if(socket != null){
                try {
                    socket.getOutputStream().write(new String("Hey!").getBytes());
                    byte[] read = new byte[1024];
                    socket.getInputStream().read(read);
                    String readString  = new String(read).trim();
                    System.out.println("From server: " + readString);
                } catch (IOException e){
                    e.printStackTrace();
                }
                socket.dispose();
            }
        }
    }

    @Override
    public void dispose() {
        try {
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        if(socket != null) socket.dispose();
    }
}
