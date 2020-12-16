package game.net.server;

import game.net.network.connection.TCPConnection;
import game.net.network.message.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public abstract class TCPServer extends Server<TCPConnection, Message> {

    private final ThreadPoolExecutor executor;

    public TCPServer(int port, int threadPoolSize) {
        super(port);
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadPoolSize);
    }

    @Override
    public void start() {
        super.start();
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (isActive) {
                Socket socket = serverSocket.accept();
                openConnection(new TCPConnection(socket, this));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void openConnection(TCPConnection connection) {
        connections.add(connection);
        executor.execute(connection);
    }

    @Override
    public void closeConnection(TCPConnection connection) {
        connections.remove(connection);
        connection.close();
    }

    @Override
    public void receive(Message msg) {
        for (TCPConnection connection : connections) {
            connection.send(msg);
        }
    }
}
