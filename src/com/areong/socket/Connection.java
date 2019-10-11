package com.areong.socket;

import java.net.Socket;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Connection {
    private Socket socket;

    public Connection(Socket socket,OnConnectListenner onConnectListenner) {
        this.socket = socket;
        if (onConnectListenner != null) {
            onConnectListenner.onConnect(this);
        }
    }



    public void println(String message) {
        PrintWriter writer;
        try {
            writer = new PrintWriter(new OutputStreamWriter(
                                     socket.getOutputStream()), true);
            writer.println(message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}