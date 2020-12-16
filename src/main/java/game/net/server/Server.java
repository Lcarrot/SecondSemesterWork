package game.net.server;

import game.net.network.ConnectionListener;
import game.net.network.connection.Connection;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashSet;
import java.util.Set;

@Slf4j
public abstract class Server<L extends Connection<?>,T> implements ConnectionListener<L,T> {

    protected final int port;
    protected final Set<L> connections;
    protected boolean isActive;

    public Server(int port) {
        this.port = port;
        connections = new LinkedHashSet<>();
    }

    public void start() {
        isActive = true;
        log.info("server was started");
    }

    public void close() {
        isActive = false;
    }
}
