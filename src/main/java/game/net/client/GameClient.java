package game.net.client;

import game.net.network.ConnectionListener;
import game.net.network.connection.TCPConnection;
import game.net.network.message.Message;

public class GameClient implements ConnectionListener<TCPConnection, Message> {


    @Override
    public void openConnection(TCPConnection connection) {

    }

    @Override
    public void closeConnection(TCPConnection connection) {

    }

    @Override
    public void connectException(TCPConnection connection, Exception exception) {

    }

    @Override
    public void receiveContent(Message msg) {

    }
}
