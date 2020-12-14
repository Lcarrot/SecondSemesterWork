package game.net.client;

import game.net.network.connection.Sender;
import game.net.network.connection.TCPConnection;
import game.net.network.message.Message;

public class GameTCPClient extends AbstractTCPClient implements Sender<Message> {

    TCPConnection connection;

    public GameTCPClient(TCPConnection connection) {
        this.connection = connection;
    }

    @Override
    public void openConnection(TCPConnection connection) {

    }

    @Override
    public void closeConnection(TCPConnection connection) {
        connection.close();
    }

    @Override
    public void connectException(TCPConnection connection, Exception exception) {
    }

    @Override
    public void receive(Message msg) {

    }

    @Override
    public void send(Message message) {
        connection.send(message);
    }
}
