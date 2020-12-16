package game.net.network.connection;

public interface Receiver<T> {

    void receive(T message);
}
