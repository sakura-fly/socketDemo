package com.areong.socket;

public interface MessageHandler {
    public void onReceive(Connection connection, byte[] message);
}
