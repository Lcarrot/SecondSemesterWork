package game.net.server;

import game.net.network.connection.TCPConnection;
import game.net.network.message.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TCPServer implements Server<TCPConnection, Message> {

    private final List<TCPConnection> connections;
    private final int port;
    private final ThreadPoolExecutor executor;

    public TCPServer(int port,int threadPoolSize) {
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadPoolSize);
        connections = new LinkedList<>();
        this.port = port;
    }

    @Override
    public void start() {
        System.out.println("server was started");
        while (true) {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                Socket socket = serverSocket.accept();
                openConnection(new TCPConnection(socket, this));
            } catch (IOException e) {
                e.printStackTrace();
            }

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
    }

    @Override
    public void connectException(TCPConnection connection, Exception exception) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void receiveContent(Message msg) {
        for (TCPConnection connection: connections) {
            connection.send(msg);
        }
    }
}
