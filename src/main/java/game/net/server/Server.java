package game.net.server;

import game.net.network.ConnectionListener;
import game.net.network.connection.Connection;

public interface Server<L extends Connection<?>,T> extends ConnectionListener<L,T> {

    void start();
}
