package game.net.server;

import game.net.network.connection.UDPConnection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.LinkedHashSet;
import java.util.Set;

public class UDPServer implements Server<UDPConnection, DatagramPacket> {
    private final byte[] data;
    private final int dataLength = 512;
    private final int port;
    private final Set<UDPConnection> set;
    protected DatagramSocket socket;

    public UDPServer(int port) {
        set = new LinkedHashSet<>();
        data = new byte[dataLength];
        this.port = port;
    }

    @Override
    public void start() {
        System.out.println("server was started");
        try {
            socket = new DatagramSocket(port);
            while (true) {
                DatagramPacket packet = new DatagramPacket(data, dataLength);
                socket.receive(packet);
                set.add(new UDPConnection(packet.getAddress(), packet.getPort()));
                receiveContent(packet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void openConnection(UDPConnection connection) {

    }

    @Override
    public void closeConnection(UDPConnection connection) {

    }

    @Override
    public void connectException(UDPConnection connection, Exception exception) {

    }

    @Override
    public void receiveContent(DatagramPacket msg) {
        for (UDPConnection connection : set) {
            connection.getPacket(msg);
            try {
                socket.send(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
