package com.areong.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class SocketClient {
    private Socket socket;

    public SocketClient(InetAddress ip, int port) {
        try {
            socket = new Socket(ip, port);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void println(byte[] message) {
        PrintWriter writer;
        PrintStream ps;
        try {
            // writer = new PrintWriter(new OutputStreamWriter(
            //                          socket.getOutputStream()), true);
            // writer.println(MessageFlag.pureMessage + message);
            ps = new PrintStream(socket.getOutputStream(),true);
            ps.write(message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*
    This function blocks.
    */
    public byte[] readLine() {
        BufferedReader reader;
        byte[] by = new byte[1024];
        try {
            // reader = new BufferedReader(new InputStreamReader(
            //                             socket.getInputStream()));
            InputStream is = socket.getInputStream();
            // return reader.readLine();
            is.read(by);
            return by;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return by;
        }
    }

    /*
     * Ready for use.
     */
    public void close() {
        try {
            // Send a message to tell the server to close the connection.
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(
                socket.getOutputStream()), true);
            writer.println(MessageFlag.connectionClosed);

            if (socket != null && !socket.isClosed())
                socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
