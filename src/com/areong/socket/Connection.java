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
        PrintWriter writer;
        try {
            // writer = new PrintWriter(new OutputStreamWriter(
            //                          socket.getOutputStream()), true);
            // writer.println(message);
            PrintStream ps = new PrintStream(socket.getOutputStream(),true);
            byte[] b = new byte[1024];
            b[0] = 0x21;
            b[1] = 0x22;
            byte[] b2 = new byte[1024];
            b2[0] = 0x31;
            b2[1] = 0x32;
            byte[] b3 = new byte[1024];
            for(int j=0;j<b.length;j++){
                if(message[0] == b[j]){
                    ps.write(b2[j]);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
