package game.net.network;

import game.net.network.connection.Connection;
import game.net.network.message.Message;

public interface ConnectionListener<T extends Connection<?>, L> {

    void openConnection(T connection);
    void closeConnection(T connection);
    void connectException(T connection, Exception exception);
    void receiveContent(L msg);
}
