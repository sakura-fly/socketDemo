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
            int length = 5;
            byte[] b = new byte[length];
            b[0] = 0x21;
            b[1] = 0x22;
            b[2] = 0x23;
            b[3] = 0x24;
            b[4] = 0x25;
            byte[] b2 = new byte[length];
            b2[0] = 0x31;
            b2[1] = 0x32;
            b2[2] = 0x33;
            b2[3] = 0x34;
            b2[4] = 0x35;
            for(int j=0;j<b2.length;j++){
                if(message[0] == b2[j]){
                    ps.write(b[j+1]);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
