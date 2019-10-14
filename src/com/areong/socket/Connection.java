package com.areong.socket;

import java.io.PrintStream;
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



    public void println(byte[] message) {
        PrintStream ps;
        try {
            ps = new PrintStream(socket.getOutputStream(),true);
            ps.write(message[0]);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void serverprintln(byte[] message) {
        PrintStream ps;
        try {
            ps = new PrintStream(socket.getOutputStream(),true);
            ps.write(message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
