package com.areong.socket.example;

import com.areong.socket.Connection;
import com.areong.socket.MessageHandler;

class EchoHandler implements MessageHandler {
    @Override
    public void onReceive(Connection connection, byte[] message) {
        System.out.println("Got a message from a client:");
        System.out.println("ʮ���ƣ�"+message[0]);
        System.out.println("Send back the same message back to the client.");
        connection.serverprintln(message);
    }
}
