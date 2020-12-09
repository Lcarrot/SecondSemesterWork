package game.net.network.connection;

public interface Sending<T> {

    void send(T message);
}
