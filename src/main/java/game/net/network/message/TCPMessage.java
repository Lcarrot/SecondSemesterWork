package game.net.network.message;

import java.io.Serializable;

public class TCPMessage implements Message, Serializable {
    @Override
    public byte[] getBytes() {
        throw new UnsupportedOperationException();
    }
}
