package game.net.network.connection;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class UDPConnection extends AbstractConnection<DatagramPacket ,UDPConnection> {

    private final InetAddress address;
    private final int port;

    public UDPConnection(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    public DatagramPacket getPacket(DatagramPacket message) {
        return new DatagramPacket(message.getData(), message.getLength(), address, port);
    }
}
