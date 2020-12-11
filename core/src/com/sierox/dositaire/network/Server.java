package com.sierox.dositaire.network;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.utils.Disposable;

import java.io.IOException;

public class Server implements Disposable {
    private ServerSocket server;
    private ServerThread thread;

    public Server(){
        thread = new ServerThread();
        thread.start();
    }

    public class ServerThread extends Thread {
        @Override
        public void run() {
            ServerSocketHints hints =  new ServerSocketHints();
            hints.acceptTimeout = 15000;
            server = Gdx.net.newServerSocket(Net.Protocol.TCP, "localhost", 8765, hints);

            SocketHints socketHints = new SocketHints();
            Socket socket = server.accept(socketHints);

            if(socket != null){
                try {
                    byte[] read = new byte[1024];
                    socket.getInputStream().read(read);
                    String readString = new String(read).trim();
                    readString += " [Echo]";
                    socket.getOutputStream().write(readString.getBytes());
                    socket.dispose();
                } catch (IOException e){
                    e.printStackTrace();
                }
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
        if(server != null) server.dispose();
    }
}
