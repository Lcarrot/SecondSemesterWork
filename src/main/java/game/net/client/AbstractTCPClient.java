package game.net.client;

import game.net.network.ConnectionListener;
import game.net.network.connection.TCPConnection;
import game.net.network.message.Message;

public abstract class AbstractTCPClient implements ConnectionListener<TCPConnection, Message> {

}
