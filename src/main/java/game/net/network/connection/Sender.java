package game.net.network.connection;

public interface Sender<T> {

    void send(T message);
}
