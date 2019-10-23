package com.areong.socket;

import java.io.*;
import java.net.Socket;

class ConnectionThread extends Thread {
    private Socket socket;
    private SocketServer socketServer;
    private Connection connection;
    private boolean isRunning;

    public ConnectionThread(Socket socket, SocketServer socketServer) {
        this.socket = socket;
        this.socketServer = socketServer;
        connection = new Connection(socket, socketServer.getOnConnectListenner());
        isRunning = true;
    }

    @Override
    public void run() {
        while (isRunning) {
            // Check whether the socket is closed.
            if (socket.isClosed()) {
                isRunning = false;
                break;
            }
            try {
                InputStream is = socket.getInputStream();
                ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
                byte[] buffer=new byte[1024];
                int ch;
                if ((ch = is.read(buffer)) == -1) {
                    stopRunning();
                    continue;
                }
                bytestream.write(buffer,0,ch);
                byte[] data = bytestream.toByteArray();
                bytestream.close();
                System.out.println("接收客户端的数据：" + String.format("0x%02x", data[0]));
                socketServer.getMessageHandler().onReceive(connection, data);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                break;
            }
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void stopRunning() {
        isRunning = false;
        try {
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
